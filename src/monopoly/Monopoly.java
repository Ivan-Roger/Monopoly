package monopoly;

import monopoly.ui.InterfaceDemo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import monopoly.ui.Interface;
import monopoly.ui.InterfaceGraph;
import monopoly.ui.InterfaceTexte;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private int nbMaisonsConstruites = 0;
    private int nbHotelsConstruits = 0;
    private HashMap<Integer, Carreau> carreaux;
    private HashMap<CouleurPropriete, Groupe> groupes;
    private ArrayList<Joueur> joueurs;
    private int idPlayer;
    private HashMap<String, LinkedList<Carte>> cartes;
    public Interface inter;
    public CarreauArgent carreauDepart;
    public CarreauAction carreauPrison;
    public InterfaceDemo demo;
    public boolean modeDemo = false;
    private final int nbMaisonsMax = 4; // Modifiables à la construction pour pouvoir créer une partie personalisée
    private final int nbHotelsMax = 1;  // Modifiables à la construction pour pouvoir créer une partie personalisée

    public Monopoly(String carreauxPath, String cartesPath) {
        carreaux = new HashMap<Integer, Carreau>();
        groupes = new HashMap<CouleurPropriete, Groupe>();
        for (CouleurPropriete c : CouleurPropriete.values()) {
            groupes.put(c, new Groupe(c));
        }

        buildGamePlateau(this.getClass().getResourceAsStream(carreauxPath));
        carreauDepart = (CarreauArgent) carreaux.get(1);
        carreauPrison = (CarreauArgent) carreaux.get(11);
        inter = new InterfaceTexte(this);
      //  inter = new InterfaceGraph(this);
        joueurs = new ArrayList<Joueur>();
        cartes = new HashMap<String, LinkedList<Carte>>();
        cartes.put("Chance", new LinkedList<Carte>());
        cartes.put("Caisse de Communauté", new LinkedList<Carte>());
        buildCartes(this.getClass().getResourceAsStream(cartesPath));
        melangerCartes();
        try {
            gameLoop();
//        } catch (UnsupportedOperationException e) {
//            System.err.println("InDev ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Cette fonction est la boucle de jeu, elle gère donc les tours des joueurs
     * ainsi que la victoire.
     *
     */
    private void gameLoop() {

        inter.choixModeDemo();

        joueurs = inter.saisieJoueurs();

        inter.initInfosJoueurs(joueurs);

        int turn = 0;
        while (joueurs.size() > 1) {
            turn++;
            inter.afficherInfosTour(turn);
            for (Joueur j : joueurs) {
                j.resetNbDouble();
            }
            for (idPlayer = 0; idPlayer < joueurs.size(); idPlayer++) {
                Joueur j = joueurs.get(idPlayer);
                inter.afficherInfosJoueur(j);
                jouerUnCoup(j);
                if (j.getCash() <= 0) {
                    joueurs.remove(j);
                    inter.info("Vous avez 0€. Vous avez perdu !");
                }
            }
        }
        inter.info("Le joueur " + joueurs.get(0).getNom() + " a gagné !");
        System.exit(0);
    }

    public void addJoueur(Joueur j) {
        joueurs.add(j);
    }

    public int getNbMaisonsMax() {
        return nbMaisonsMax;
    }

    public int getNbHotelsMax() {
        return nbHotelsMax;
    }

    public HashMap<Integer, Carreau> getCarreaux() {
        return carreaux;
    }

    public HashMap<String, LinkedList<Carte>> getCartes() {
        return cartes;
    }

    public Carreau getCarreau(int id) {
        return carreaux.get(id);
    }

    public Carte getCarteSuivante(String type) {
        if (modeDemo) {
            return demo.getCarte(type);
        } else {
            return cartes.get(type).poll();
        }
    }

    /**
     * Fonction qui permet de replacer une carte d'un type donné (chance ou
     * caisse de communauté) à la fin de la pile de carte.
     *
     * @param type
     * @param c
     */
    public void addCarteFin(String type, Carte c) {
        cartes.get(type).add(c);
    }

    /**
     * Cette fonction génère le plateau de jeu à partir du fichier de donnée
     *
     * @param dataFile
     */
    private void buildGamePlateau(InputStream dataFile) {
        try {
            ArrayList<String[]> data = readDataFile(dataFile, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {

                String caseType = data.get(i)[0];
                int id = new Integer(data.get(i)[1]);

                if (caseType.compareTo("P") == 0) {
                    int[] loyers = new int[data.get(i).length - 5];
                    for (int k = 5; k < data.get(i).length; k++) {
                        loyers[k - 5] = new Integer(data.get(i)[k]);
                    }
                    groupes.get(CouleurPropriete.valueOf(data.get(i)[3])).setPrixAchatMaison(Integer.valueOf(data.get(i)[11]));
                    groupes.get(CouleurPropriete.valueOf(data.get(i)[3])).setPrixAchatHotel(Integer.valueOf(data.get(i)[12]));
                    ProprieteAConstruire carreau = new ProprieteAConstruire(id, data.get(i)[2], this, new Integer(data.get(i)[4]), groupes.get(CouleurPropriete.valueOf(data.get(i)[3])), loyers);
                    carreaux.put(id, carreau);
                    groupes.get(CouleurPropriete.valueOf(data.get(i)[3])).addPropriete(carreau);
                } else if (caseType.compareTo("G") == 0) {
                    Gare carreau = new Gare(id, data.get(i)[2], this, new Integer(data.get(i)[3]));
                    carreaux.put(id, carreau);
                } else if (caseType.compareTo("C") == 0) {
                    Compagnie carreau = new Compagnie(id, data.get(i)[2], this, new Integer(data.get(i)[3]));
                    carreaux.put(id, carreau);
                } else if (caseType.compareTo("CT") == 0) {
                    CarreauTirage carreau = new CarreauTirage(id, data.get(i)[2], this, data.get(i)[2]);
                    carreaux.put(id, carreau);
                } else if (caseType.compareTo("CA") == 0) {
                    CarreauArgent carreau = new CarreauArgent(id, data.get(i)[2], this, new Integer(data.get(i)[3]));
                    carreaux.put(id, carreau);
                } else if (caseType.compareTo("CM") == 0) {
                    CarreauMouvement carreau = new CarreauMouvement(id, data.get(i)[2], this);
                    carreaux.put(id, carreau);
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type (" + data.get(i)[0] + ") line " + i);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
    }
 
    /**
     * Cette fonction lit le fichier de donnée et permet de gérer les exceptions
     * relatif au fichier
     *
     * @param file
     * @param token
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private ArrayList<String[]> readDataFile(InputStream file, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    /**
     * Retourne deux entiers aléatoires de 1 à 6 En mode DEMO, les dés sont
     * choisis directement par l'utilisateur via l'interface
     *
     * @return
     */
    public int[] jetDeDes() {
        if (modeDemo) {
            return demo.getDes();
        } else {
            int[] des = new int[2];
            Random rand = new Random();
            des[0] = rand.nextInt(6) + 1;
            des[1] = rand.nextInt(6) + 1;
            return des;
        }
    }

    /**
     * Permet de faire la somme des résultats des dés Utilisée surtout pour la
     * gestion des déplacements et "payer" pour les compagnies
     *
     * @param des
     * @return
     */
    public int calculTotalDes(int[] des) {
        return des[0] + des[1];
    }

    public void setModeDemo(boolean modeDemo) {
        this.modeDemo = modeDemo;
        if (modeDemo) {
            demo = new InterfaceDemo(this);
        }
    }
 
    /**
     * Renvoi TRUE si le score des dés est un double
     *
     * @param des
     * @return
     */
    public boolean isDouble(int[] des) {
        return des[0] == des[1];
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    /**
     * Permet de gérer les tours des joueurs ainsi que la case départ; Elle
     * prends en compte le statut "en prison" et gère tout ce qui y est relatif;
     * En mode DEMO, elle permet de choisir la position d'un joueur à la place
     * de tirer des dés aléatoirement Cette fonction gère l'abandon d'un joueur
     *
     * @param j
     */
    private void jouerUnCoup(Joueur j) {
        if (j.estEnPrison()) {
            inter.afficherPrison(j);
            j.addTempsPrison();
            int[] lancer = jetDeDes();
            inter.afficherLancerDes(lancer);
            if (isDouble(lancer)) {
                j.sortirPrison();
                inter.afficherLiberation("Vous avez fait un double, vous sortez de prison");
            } else if (j.getNbLiberation() > 0) {
                inter.afficherLiberation("Vous utilisez votre carte de liberation, vous sortez de prison");
                Carte c = j.removeCarteLiberation();
                cartes.get(c.getType()).push(c);
                j.sortirPrison();
            } else {
                if (j.getTempsPrison() >= 3) {
                    if (j.getCash() >= 50) {
                        j.payer(50);
                        inter.afficherLiberation("Vous avez payé votre caution de 50€, vous sortez de prison");
                        j.sortirPrison();
                    } else {
                        inter.info("Vous ne pouvez pas payer votre caution (50€)");
                    }
                }
            }
        }
        if (!j.estEnPrison()) {
            int oldId = j.getCarreau().getId();
            if (!modeDemo) {
                lancerDesAvancer(j);
            } else {
                demo.deplacement(j);
            }
            int newId = j.getCarreau().getId();
            if (newId < oldId) {
                inter.passageDepart();
                j.recevoirArgent(carreauDepart.getMontant());
            }
            j.getCarreau().action(j);
        }

        if (!j.abandonne()) {
            inter.finTour(j);
        }

    }

    /**
     * Cette fonction appelle les fonctions calculTotalDes(), jetDeDes() et
     * isDouble(); Si un joueur fais trois double d'affilé, celui-ci va en
     * prison
     *
     * @param j
     */
    public void lancerDesAvancer(Joueur j) {
        int[] lancer;
        int distance = calculTotalDes(lancer = jetDeDes());
        inter.afficherLancerDes(lancer);
        if (isDouble(lancer)) {
            if (j.getNbDouble() < 2) {
                idPlayer--;
                j.addNbDouble();
            } else {
                j.allerEnPrison();
                inter.info("Vous avez fait 3 doubles ... Allez en Prison !");
            }
        }
        if (!j.estEnPrison()) {
            Carreau pos = j.getCarreau();
            if (pos.getId() + distance > carreaux.size()) {
                j.setPosition(carreaux.get(pos.getId() + distance - carreaux.size()));
            } else {
                j.setPosition(carreaux.get(pos.getId() + distance));
            }
        }
    }

    public int getTaillePlateau() {
        return carreaux.size();
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public int getNbMaisonsRestantes() {
        return nbMaisons - nbMaisonsConstruites;
    }

    public int getNbHotelsRestants() {
        return nbHotels - nbHotelsConstruits;
    }

    public void retirerJoueur(Joueur j) {
        joueurs.remove(j);
    }

    /**
     * Cette fonction permet de générer les deux jeux de cartes Chance et Caisse
     * de Communauté
     *
     * @param dataFile
     */
    private void buildCartes(InputStream dataFile) {
        try {
            ArrayList<String[]> data = readDataFile(dataFile, ",");

            String id;
            for (String[] s : data) {
                if (s[0].equals("Ch")) {
                    id = "Chance";
                } else {
                    id = "Caisse de Communauté";
                }
                switch (s[1]) {
                    case "CAP":
                        cartes.get(id).push(new CarteAllerPrison(id, s[3], Integer.parseInt(s[2]), this));
                        break;
                    case "CLP":
                        cartes.get(id).push(new CarteLiberePrison(id, s[3], Integer.parseInt(s[2]), this));
                        break;
                    case "CAN":
                        cartes.get(id).push(new CarteAnniversaire(id, s[3], Integer.parseInt(s[2]), this));
                        break;
                    case "CAR":
                        cartes.get(id).push(new CarteArgent(id, s[3], Integer.parseInt(s[2]), this, Integer.parseInt(s[4])));
                        break;
                    case "CMO":
                        cartes.get(id).push(new CarteMouvement(Integer.parseInt(s[5]), s[4], id, s[3], Integer.parseInt(s[2]), this));
                        break;
                    case "CRE":
                        cartes.get(id).push(new CarteReparation(id, s[3], Integer.parseInt(s[2]), this, Integer.parseInt(s[4]), Integer.parseInt(s[5])));
                        break;
                    default:
                        throw new Exception("buildCartes() - Invalid data type");
                }

            }
            for (String type : cartes.keySet()) {
                Collections.sort(cartes.get(type), new Comparator<Carte>() {
                    @Override
                    public int compare(Carte c1, Carte c2) {
                        return Integer.compare(c1.getId(), c2.getId());
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette fonction permet de mélanger les cartes selon leur type (chance ou
     * caisse de communauté);
     */
    private void melangerCartes() {
        for (String type : cartes.keySet()) {
            Collections.sort(cartes.get(type), new Comparator<Carte>() {
                @Override
                public int compare(Carte c1, Carte c2) {
                    Random rand = new Random();
                    return (rand.nextInt(10) + 1) - 5;
                }
            });
        }
    }

    public ArrayList<ProprieteAConstruire> getProprietes() {
        ArrayList<ProprieteAConstruire> res = new ArrayList<ProprieteAConstruire>();
        for (Carreau c : carreaux.values()) {
            if (c instanceof ProprieteAConstruire) {
                res.add((ProprieteAConstruire) c);
            }
        }
        return res;
    }

}
