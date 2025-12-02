
import java.util.Scanner;

class LireCSV {

    private static Scanner sc = App.sc;
    private static Logger logger = App.logger;

    public static void lireCSV(String titre) {
        App.titreChoix(titre);

        String reponse;

        System.out.print("Chemin du fichier CSV : ");
        reponse = App.sc.nextLine();

        if (!reponse.isBlank()) {
        }
    }
}
