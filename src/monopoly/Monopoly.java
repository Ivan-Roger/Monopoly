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
                for (int j=0; j<data.get(i).length; j++) {
                    System.out.print(data.get(i)[j]+" - ");
                }
                System.out.println("");
                String caseType = data.get(i)[0];
                if (caseType.compareTo("P") == 0) {
                    System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    int[] loyers = new int[data.get(i).length-4];
                    for (int k=4; k<data.get(i).length; k++) {
                        loyers[k-4] = new Integer(data.get(i)[k]);
                    }
                    carreaux.add(new Integer(data.get(i)[1]), new ProprieteAConstruire(new Integer(data.get(i)[1]),data.get(i)[2],this,new Integer(data.get(i)[3]),loyers));
                } else if (caseType.compareTo("G") == 0) {
                    System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new Gare(new Integer(data.get(i)[1]),data.get(i)[2],this,new Integer(data.get(i)[3])));
                } else if (caseType.compareTo("C") == 0) {
                    System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new Compagnie(new Integer(data.get(i)[1]),data.get(i)[2],this,new Integer(data.get(i)[3]) ));
                } else if (caseType.compareTo("CT") == 0) {
                    System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new CarreauTirage(new Integer(data.get(i)[1]),data.get(i)[2],this));
                } else if (caseType.compareTo("CA") == 0) {
                    System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new CarreauArgent(new Integer(data.get(i)[1]),data.get(i)[2],this,new Integer(data.get(i)[3])));
                } else if (caseType.compareTo("CM") == 0) {
                    System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    carreaux.add(new Integer(data.get(i)[1]), new CarreauMouvement(new Integer(data.get(i)[1]),data.get(i)[2],this));
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
