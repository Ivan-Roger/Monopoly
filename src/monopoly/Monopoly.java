package monopoly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private ArrayList<Carreau> carreaux;
    private HashMap<CouleurPropriete, Groupe> groupes;
    private ArrayList<Joueur> joueurs;
    private int idJoueur = -1;
    public Interface inter;
    private CarreauArgent carreauDepart;

    public Monopoly(String dataFilename) {
        carreaux = new ArrayList<Carreau>();
        carreaux.add(null);
        groupes = new HashMap<CouleurPropriete, Groupe>();
        for (CouleurPropriete c : CouleurPropriete.values()) {
            groupes.put(c, new Groupe(c));
        }
        buildGamePlateau(dataFilename);
        carreauDepart = (CarreauArgent)carreaux.get(1);
        inter = new Interface(this);
        joueurs = new ArrayList<Joueur>();

        for (int i = 0; i < 10; i++) {
            joueurSuivant();
            afficherInfosTour();
            int oldId = joueurs.get(idJoueur).getCarreau().getId();
            lancerDesAvancer();
            int newId = joueurs.get(idJoueur).getCarreau().getId();
            if (newId<oldId) {
                joueurs.get(idJoueur).recevoirArgent(carreauDepart.getMontant());
            }
            joueurs.get(idJoueur).getCarreau().action(joueurs.get(idJoueur));
        }
    }
    
    public void addJoueur(Joueur j) {
        joueurs.add(j);
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
        throw new UnsupportedOperationException();
    }

    public Joueur joueurSuivant() {
        if (idJoueur + 1 > joueurs.size()) {
            idJoueur++;
        } else {
            idJoueur = 0;
        }
        return joueurs.get(idJoueur);
    }

    public void afficherInfosTour() {
        throw new UnsupportedOperationException();
    }

    public int calculTotalDes(int[] des) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Joueur> getJoueurs() {
        throw new UnsupportedOperationException();
    }

    public void jouerUnCoup(Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void lancerDesAvancer() {
        throw new UnsupportedOperationException();
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
}
