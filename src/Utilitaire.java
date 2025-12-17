
import java.util.logging.Logger;

public class Utilitaire {

    private static final Logger logger = App.logger;

    public static void executerChoix(int choix, Object classObject) {
        // vérifier la provenance de l'appel de la méthode
        if (classObject.getClass() == App.class) {
            switch (choix) {
                case App.CREER_CSV -> {
                    logger.info("Appel de la méthode pour créer un fichier CSV");

                    // appel de la fonction pour la création de fichier CSV
                    //CreerCSV.start();
                }

                case App.LIRE_CSV -> {
                    logger.info("Appel de la méthode pour lire un fichier CSV");

                    // appel de la fonction pour la lecture de fichier CSV
                    LireCSV.start();
                }
                case App.QUITTER -> {
                    // appel de la fonction pour quitter
                    App.quitter();
                }
                default ->
                    throw new AssertionError();
            }
        } else if (classObject.getClass() == LireCSV.class) {

        } else if (classObject.getClass() == CreerCSV.class) {

        }
    }
}
