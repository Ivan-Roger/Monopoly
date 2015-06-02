package monopoly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private ArrayList<Carreau> carreaux;
    private HashMap<CouleurPropriete, Groupe> groupes;
    private ArrayList<Joueur> joueurs;
    private int idPlayer;
    public Interface inter;
    public CarreauArgent carreauDepart;
    public CarreauAction carreauPrison;

    public Monopoly(String _dataFilename) {
        carreaux = new ArrayList<Carreau>();
        carreaux.add(null);
        groupes = new HashMap<CouleurPropriete, Groupe>();
        for (CouleurPropriete c : CouleurPropriete.values()) {
            groupes.put(c, new Groupe(c));
        }

        buildGamePlateau(_dataFilename);
        carreauDepart = (CarreauArgent) carreaux.get(1);
        carreauPrison = (CarreauArgent) carreaux.get(11);
        inter = new Interface(this);
        joueurs = new ArrayList<Joueur>();

        joueurs.add(new Joueur("Jean-Marc", this));
        joueurs.add(new Joueur("Jean-Louis", this));
//        joueurs.add(new Joueur("Jean-Scott", this));
//        joueurs.add(new Joueur("Jean-Charles", this));
//        joueurs.add(new Joueur("Jean-Paul", this));
//        joueurs.add(new Joueur("Jean-Pierre", this));
//        joueurs.add(new Joueur("Jean-Luc", this));

        int turn = 0;
        while (joueurs.size() > 0) {
            turn++;
            inter.afficherInfosTour(turn);
            for (Joueur j : joueurs) {
                j.resetNbDouble();
            }
            for (idPlayer = 0; idPlayer < joueurs.size(); idPlayer++) {
                inter.afficherInfosJoueur(joueurs.get(idPlayer));
                jouerUnCoup(joueurs.get(idPlayer));
            }
        }
    }

    public void addJoueur(Joueur j) {
        joueurs.add(j);
    }

    public Carreau getCarreau(int id) {
        return carreaux.get(id);
    }

    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {

                String caseType = data.get(i)[0];
                int id = new Integer(data.get(i)[1]);

                if (caseType.compareTo("P") == 0) {
                    int[] loyers = new int[data.get(i).length - 5];
                    for (int k = 5; k < data.get(i).length; k++) {
                        loyers[k - 5] = new Integer(data.get(i)[k]);
                    }
                    ProprieteAConstruire carreau = new ProprieteAConstruire(id, data.get(i)[2], this, new Integer(data.get(i)[4]), groupes.get(CouleurPropriete.valueOf(data.get(i)[3])), loyers);
                    carreaux.add(id, carreau);
                    groupes.get(CouleurPropriete.valueOf(data.get(i)[3])).addPropriete(carreau);
                } else if (caseType.compareTo("G") == 0) {
                    Gare carreau = new Gare(id, data.get(i)[2], this, new Integer(data.get(i)[3]));
                    carreaux.add(id, carreau);
                } else if (caseType.compareTo("C") == 0) {
                    Compagnie carreau = new Compagnie(id, data.get(i)[2], this, new Integer(data.get(i)[3]));
                    carreaux.add(id, carreau);
                } else if (caseType.compareTo("CT") == 0) {
                    CarreauTirage carreau = new CarreauTirage(id, data.get(i)[2], this);
                    carreaux.add(id, carreau);
                } else if (caseType.compareTo("CA") == 0) {
                    CarreauArgent carreau = new CarreauArgent(id, data.get(i)[2], this, new Integer(data.get(i)[3]));
                    carreaux.add(id, carreau);
                } else if (caseType.compareTo("CM") == 0) {
                    CarreauMouvement carreau = new CarreauMouvement(id, data.get(i)[2], this);
                    carreaux.add(id, carreau);
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

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    public int[] jetDeDes() {
        int[] des = new int[2];
        Random rand = new Random();
        des[0] = rand.nextInt(6) + 1;
        des[1] = rand.nextInt(6) + 1;
        return des;
    }

    public int calculTotalDes(int[] des) {
        return des[0] + des[1];
    }

    public boolean isDouble(int[] des) {
        return des[0] == des[1];
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    private void jouerUnCoup(Joueur j) {
        inter.afficher("");
        if (j.estEnPrison()) {
            inter.afficher("Vous etes en prison");
            int[] lancer = jetDeDes();
            inter.afficherLancerDes(lancer);
            if (isDouble(lancer)) {
                j.sortirPrison();
                inter.afficher("Vous sortez de prison");
            }
        } else {
            int oldId = j.getCarreau().getId();
            lancerDesAvancer(j);
            if (!j.estEnPrison()) {
                int newId = j.getCarreau().getId();
                if (newId < oldId) {
                    j.recevoirArgent(carreauDepart.getMontant());
                }
                j.getCarreau().action(j);
            }
        }
        if (!j.abandonne()) {
            inter.afficher("Fin de votre tour ...");
            inter.afficher("Vous finissez avec " + j.getCash() + "€");
            inter.afficher("Appuyez sur entrée pour continuer.");
            inter.lireString();
        }

    }

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
                inter.afficher("Vous avez fait 3 doubles ... Allez en Prison !");
            }
        }
        if (!j.estEnPrison()) {
            Carreau pos = j.getCarreau();
            if (pos.getId() + distance >= carreaux.size()) {
                j.setPosition(carreaux.get(pos.getId() + distance - (carreaux.size() - 1)));
            } else {
                j.setPosition(carreaux.get(pos.getId() + distance));
            }
        }
    }

    public void achatMaison(int nb, int prix) {
        throw new UnsupportedOperationException();
    }

    public void achatHotel(int prix) {
        throw new UnsupportedOperationException();
    }

    public boolean verifieAchat(int nb, Joueur j) {
        throw new UnsupportedOperationException();
    }

    public int getNbMaisons() {
        return this.nbMaisons;
    }

    public int getNbHotels() {
        return this.nbHotels;
    }

    public void retirerJoueur(Joueur j) {
        joueurs.remove(j);
    }

}
