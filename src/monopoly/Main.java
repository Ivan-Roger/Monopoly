package monopoly;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String user = System.getenv("LOGNAME");
        Monopoly m = new Monopoly("/users/info/etu-s2/"+user+"/ProjetMonopoly/Monopoly/src/assets/data.txt");
    }

}
