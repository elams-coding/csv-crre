
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilitaire {

    private static final Logger logger = App.logger;

    /**
     * Demander le choix de l'utilisateur parmi les éléments du menu
     */
    public static int choixMenu() {
        logger.info("Saisie de l'utilisateur");
        String saisie;

        // mettre à une valeur de menu impossible pour éviter le 0
        // par défaut dans les logs et éviter toutes confusions
        int reponse = -1;

        do {
            System.out.print("Option choisie : ");

            // lire la saisie de l'utilisateur et supprimer les
            // espaces précédents et suivants la chaîne de
            // caractères saisie
            saisie = App.SC.nextLine().trim();

            // tester la reponse
            if (!saisie.isBlank()) {
                // essayer de convertir la saisie en entier
                try {
                    reponse = Integer.parseInt(saisie);

                    logger.log(Level.INFO, "Conversion de la saisie \"{0}\" en entier {1}",
                            new Object[]{saisie, reponse});

                    if (reponse != App.CREER_CSV && reponse != App.LIRE_CSV && reponse != App.QUITTER) {
                        logger.log(Level.WARNING, "Saisie incorrecte pour \"{0}\"", new Object[]{reponse});
                        System.err.printf("Veuillez entrer %d ou %d ou %d.%n", new Object[]{App.CREER_CSV, App.LIRE_CSV, App.QUITTER});
                    }
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, "La saisie \"{0}\" n''est pas un entier", saisie);
                    System.err.printf("Veuillez entrer %d ou %d ou %d.%n", new Object[]{App.CREER_CSV, App.LIRE_CSV, App.QUITTER});

                    // garder la valeur impossible
                    reponse = -1;
                }
            } else {
                logger.log(Level.WARNING, "Saisie vide");
                System.err.printf("Veuillez entrer %d ou %d ou %d.%n", new Object[]{App.CREER_CSV, App.LIRE_CSV, App.QUITTER});
            }
        } while (saisie.isBlank() || reponse != App.CREER_CSV && reponse != App.LIRE_CSV && reponse != App.QUITTER);

        logger.log(Level.INFO, "Option sélectionnée : \"{0}\" validée", new Object[]{reponse});

        // sauter une ligne
        System.out.println();

        return reponse;
    }

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
                    // appel de la fonction pour App.QUITTER
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
