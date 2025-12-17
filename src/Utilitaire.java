
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilitaire {

    private static final Logger logger = App.logger;

    /**
     * Demander le choix de l'utilisateur parmi les éléments du menu
     */
    public static int choixMenu(Object classObject) {
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

            // tester la reponse si elle n'est pas vide
            if (!saisie.isBlank()) {
                // essayer de convertir la saisie en entier
                try {
                    reponse = Integer.parseInt(saisie);

                    logger.log(Level.INFO, "Conversion de la saisie \"{0}\" en entier {1}", new Object[]{saisie, reponse});

                    if (classObject instanceof App) {
                        if (reponse != App.CREER_CSV && reponse != App.LIRE_CSV && reponse != App.QUITTER) {
                            logger.log(Level.WARNING, "Saisie incorrecte pour \"{0}\"", new Object[]{reponse});
                            System.err.printf("Veuillez entrer %d ou %d ou %d.%n", new Object[]{App.CREER_CSV, App.LIRE_CSV, App.QUITTER});
                        }
                    } else if (classObject instanceof LireCSV) {
                        if (reponse != LireCSV.LECTURE_INTEGRALE && reponse != LireCSV.COLONNE_PAR_COLONNE) {
                            logger.log(Level.WARNING, "Saisie incorrecte pour \"{0}\"", new Object[]{reponse});
                            System.err.printf("Veuillez entrer %d ou %d.%n", new Object[]{LireCSV.LECTURE_INTEGRALE, LireCSV.COLONNE_PAR_COLONNE});
                        }
                    } else if (classObject instanceof CreerCSV) {
                        // TODO : gérer le cas d'une saisie de choix dans la classe CreerCSV
                    }
                } catch (NumberFormatException e) {
                    logger.log(Level.SEVERE, "La saisie \"{0}\" n''est pas un entier", new Object[]{saisie});

                    if (classObject instanceof App) {
                        System.err.printf("Veuillez entrer %d ou %d ou %d.%n", new Object[]{App.CREER_CSV, App.LIRE_CSV, App.QUITTER});
                    } else if (classObject instanceof LireCSV) {
                        System.err.printf("Veuillez entrer %d ou %d.%n", new Object[]{LireCSV.LECTURE_INTEGRALE, LireCSV.COLONNE_PAR_COLONNE});
                    } else if (classObject instanceof CreerCSV) {
                        // TODO : afficher le message d'erreur correspondant au cas du menu de la classe CreerCSV
                    }

                    // garder la valeur impossible
                    reponse = -1;
                }
            } else {
                logger.log(Level.WARNING, "Saisie vide");

                if (classObject instanceof App) {
                    System.err.printf("Veuillez entrer %d ou %d ou %d.%n", new Object[]{App.CREER_CSV, App.LIRE_CSV, App.QUITTER});
                } else if (classObject instanceof LireCSV) {
                    System.err.printf("Veuillez entrer %d ou %d.%n", new Object[]{LireCSV.LECTURE_INTEGRALE, LireCSV.COLONNE_PAR_COLONNE});
                } else if (classObject instanceof CreerCSV) {
                    // TODO : afficher le message d'erreur correspondant au cas du menu de la classe CreerCSV
                }
            }
        } while (saisie.isBlank() || reponse != App.CREER_CSV && reponse != App.LIRE_CSV && reponse != App.QUITTER
                && reponse != LireCSV.LECTURE_INTEGRALE && reponse != LireCSV.COLONNE_PAR_COLONNE);

        logger.log(Level.INFO, "Option sélectionnée : \"{0}\" validée", new Object[]{reponse});

        // sauter une ligne
        System.out.println();

        return reponse;
    }

    public static void executerChoix(int choix, Object classObject) {
        // vérifier la provenance de l'appel de la méthode
        if (classObject instanceof App) {
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
                    quitter();
                }
            }
        } else if (classObject instanceof LireCSV) {
            switch (choix) {
                case LireCSV.LECTURE_INTEGRALE -> {
                    LireCSV.lectureIntegral(LireCSV.chemin);
                }
                case LireCSV.COLONNE_PAR_COLONNE -> {
                    LireCSV.lectureColonne(LireCSV.chemin);
                }
            }
        } else if (classObject instanceof CreerCSV) {
            // TODO : gérer le cas d'une exécution de choix dans la classe CreerCSV
        }
    }

    /**
     * Quitter et fermer l'application.
     */
    private static void quitter() {
        logger.info("Fermeture de l'application");

        System.out.println("Bye");

        logger.info("Application fermé.");
    }
}
