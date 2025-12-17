
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

class LireCSV extends GestionCSV {

    private static final String TITRE = "Lecture de fichier CSV";

    static final int LECTURE_INTEGRALE = 1;
    static final int COLONNE_PAR_COLONNE = 2;

    public static String chemin;
    private static Path tempChemin;

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

        afficherMessage(tempChemin);

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
    public static void lectureColonne(String chemin) {
        File fichierCSV = new File(chemin);

        tempChemin = Path.of(chemin);

        afficherMessage(tempChemin);

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

    private static void afficherMessage(Path chemin) {
        System.out.printf("Contenu du fichier : %s", chemin.getFileName());

        // saut de ligne
        System.out.println();
    }
}
