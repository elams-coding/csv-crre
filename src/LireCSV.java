
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

class LireCSV extends GestionCSV {

    private static final String TITRE = "Lecture de fichier CSV";
    private static final String SEPARATEUR_VIRGULE = ",";
    private static final String SEPARATEUR_POINT_VIRGULE = ";";
    private static final String SEPARATEUR_TABULATION = "\t";

    static final int LECTURE_INTEGRALE = 1;
    static final int COLONNE_PAR_COLONNE = 2;

    public static String chemin;
    private static Path tempChemin;
    private static String separateur;

    /**
     * Point d'entrée de la lecture de fichier CSV.
     */
    public static void start() {
        // afficher le titre du choix fait
        titreChoix(TITRE);

        // demander le chemin tant que celui-ci n'est pas valide
        do {
            chemin = askPath();
        } while (!isPathValidCSV(chemin));

        menu();

        int choix = Utilitaire.choixMenu(new LireCSV());

        Utilitaire.executerChoix(choix, new LireCSV());
    }

    /**
     * Affiche le menu de la partie de lecture de fichier CSV.
     */
    private static void menu() {
        System.out.printf("""
                -----------------------------
                Choisir une option :
                    (%d) Lecture intégral
                    (%d) Colonne par colonne
                -----------------------------

                """, new Object[]{LECTURE_INTEGRALE, COLONNE_PAR_COLONNE});
    }

    /**
     * Lire le contenu du fichier CSV intégralement.
     *
     * @param chemin Chemin du fichier à lire.
     */
    public static void lectureIntegral(String chemin) {
        File fichierCSV = new File(chemin);

        tempChemin = Path.of(chemin);
        afficherMessageLectureFichier(tempChemin);

        // lire le fichier
        try (Scanner lecteur = new Scanner(fichierCSV)) {
            while (lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();
                System.out.println(ligne);
            }
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite.");
        }
    }

    /**
     * Lire le contenu du fichier CSV colonne par colonne.
     *
     * @param chemin Chemin du fichier à lire.
     */
    public static void lectureParColonne(String chemin, String separateur) {
        // demander le séparateur
        if (separateur.isBlank()) {
            separateur = demanderSeparateur();
        }

        File fichierCSV = new File(chemin);

        tempChemin = Path.of(chemin);
        afficherMessageLectureFichier(tempChemin);

        // liste qui contiendra les différentes colonnes
        List<List<String>> colonnes = new ArrayList<>();

        // lire le fichier par colonne
        try (Scanner lecteur = new Scanner(fichierCSV)) {
            boolean estPremiereLigne = true;

            while (lecteur.hasNextLine()) {
                String ligne = lecteur.nextLine();
                // HACK : faire en sorte d'ajouter les éléments d'une même colonne et les lires ensembles
            }
        } catch (Exception e) {
            System.err.println("Une erreur s'est produite.");
        }
    }

    /**
     * Afficher le message d'affichage du contenu du fichier.
     *
     * @param chemin Chemin du fichier CSV qui va être lu.
     */
    private static void afficherMessageLectureFichier(Path chemin) {
        System.out.printf("Contenu du fichier : %s", chemin.getFileName());

        // saut de ligne
        System.out.println();
    }

    /**
     * Demander le séparateur du fichier CSV.
     *
     * @return Le caractère servant de séparateur dans le fichier CSV.
     */
    private static String demanderSeparateur() {
        logger.info("Demande du séparateur dans le fichier CSV.");

        System.out.print("Quel est le séparateur du fichier : ");
        String reponse = App.SC.nextLine();

        while (reponse.isBlank() || reponse.equals(SEPARATEUR_VIRGULE) && reponse.equals(SEPARATEUR_POINT_VIRGULE)
                && reponse.equals(SEPARATEUR_TABULATION)) {
            logger.log(Level.WARNING, "Le séparateur n'est pas valide.");

            System.err.println("Erreur de saisie.");
            System.err.printf("Veuillez saisir '%d' ou '%d' ou '%d'.",
                    new Object[]{SEPARATEUR_VIRGULE, SEPARATEUR_POINT_VIRGULE, SEPARATEUR_TABULATION});
            System.out.print("Recommencez : ");

            reponse = App.SC.nextLine();
        }

        return reponse;
    }
