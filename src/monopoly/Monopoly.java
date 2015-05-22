package monopoly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Monopoly {

    private int nbMaisons = 32;
    private int nbHotels = 12;
    private ArrayList<Carreau> carreaux;
    private ArrayList<Joueur> joueurs;
    public Interface inter;

    public Monopoly(String dataFilename) {
        buildGamePlateau(dataFilename);
    }

    private void buildGamePlateau(String dataFilename) {
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //TODO: create cases instead of displaying
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new ProprieteAConstruire());
                } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new Gare());
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new Compagnie());
                } else if (caseType.compareTo("CT") == 0) {
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new CarreauTirage());
                } else if (caseType.compareTo("CA") == 0) {
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new CarreauArgent());
                } else if (caseType.compareTo("CM") == 0) {
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new CarreauMouvement());
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type ("+data.get(i)[0]+") line "+i);
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
        throw new UnsupportedOperationException();
    }

    public int getNbMaison() {
        throw new UnsupportedOperationException();
    }

    public void afficherInfosTour(Joueur j, int jet, Carreau c) {
        throw new UnsupportedOperationException();
    }

    public int calculTotalDes(int[] des) {
        throw new UnsupportedOperationException();
    }

    public void saisir() {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Joueur> getJoueurs() {
        throw new UnsupportedOperationException();
    }

    public void jouerUnCoup(Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void lancerDésAvancer() {
        throw new UnsupportedOperationException();
    }

    public boolean demanderConstruction() {
        throw new UnsupportedOperationException();
    }

    public void achatMaison(int nb, int prix) {
        throw new UnsupportedOperationException();
    }

    public void achatHotel(int prix) {
        throw new UnsupportedOperationException();
    }

    public void setMaison(int nb) {
        throw new UnsupportedOperationException();
    }

    public boolean verifieAchat(int nbConstruction, int cashJoueur, int maisonBanque) {
        throw new UnsupportedOperationException();
    }

    public int getNbMaisons() {
        return this.nbMaisons;
    }

    public int getNbHotels() {
        return this.nbHotels;
    }
}
