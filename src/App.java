
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private static final int CREER_CSV = 1;
    private static final int LIRE_CSV = 2;

    public static void main(String[] args) throws Exception {
        logger.info("Début de l'application");

        System.out.printf("""
                ----------------------------
                          CSV CRRE
                ----------------------------
                Choisir une option :
                    (%d) Créer un fichier CSV
                    (%d) Lire  un fichier CSV
                ----------------------------

                """, CREER_CSV, LIRE_CSV);

        // conserver le choix dans une variable
        int choix = choixMenu();

        executerChoix(choix);
    }

    /**
     * Demander le choix de l'utilisateur parmi les éléments du menu
     */
    private static int choixMenu() {
        logger.info("Saisie de l'utilisateur");
        String saisie;

        // mettre à une valeur de menu impossible
        // pour éviter le 0 par défaut dans les
        // logs et éviter toutes confusions
        int reponse = -1;

        do {
            System.out.print("Option choisie : ");

            // lire la saisie de l'utilisateur
            // et supprimer les espaces précédents
            // et suivants la chaîne de caractères
            // saisie
            saisie = sc.nextLine().trim();

            // tester la reponse
            if (!saisie.isBlank()) {
                // essayer de convertir la saisie en entier
                try {
                    reponse = Integer.parseInt(saisie);

                    logger.log(Level.INFO, "Conversion de la saisie \"{0}\" en entier {1}",
                            new Object[]{saisie, reponse});

                    if (reponse != CREER_CSV && reponse != LIRE_CSV) {
                        logger.log(Level.WARNING, "Saisie incorrecte pour \"{0}\"", reponse);
                        System.err.printf("Veuillez entrer %d ou %d.%n", CREER_CSV, LIRE_CSV);
                    }
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, "La saisie \"{0}\" n''est pas un entier", saisie);
                    System.err.printf("Veuillez entrer %d ou %d.%n", CREER_CSV, LIRE_CSV);

                    // garder la valeur impossible
                    reponse = -1;
                }
            } else {
                logger.log(Level.WARNING, "Saisie vide");
                System.err.printf("Veuillez entrer %d ou %d.%n", CREER_CSV, LIRE_CSV);
            }
        } while (saisie.isBlank() || reponse != CREER_CSV && reponse != LIRE_CSV);

        logger.log(Level.INFO, "Option sélectionnée : \"{0}\" validée", reponse);

        return reponse;
    }

    private static void executerChoix(int choix) {
        if (choix == CREER_CSV) {
            logger.info("Appel de la méthode pour créer un fichier CSV");
            // appel de la méthode pour
            // créer le fichier CSV
            creerCSV();
        } else if (choix == LIRE_CSV) {
            logger.info("Appel de la méthode pour lire un fichier CSV");
            // appel de la méthode pour
            // lire le fichier CSV
            lireCSV();
        }
    }

    private static void creerCSV() {

    }

    private static void lireCSV() {

    }
}
