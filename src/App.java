import java.util.Random;
import java.util.Scanner;

public class App {
    public static void afficherTableau(int[][] intArray) {

        Terminal.lineBreak();

        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {

                if (intArray[j][i] == 0 && i == 2) {
                    System.out.print(" X\n");
                } else if (intArray[j][i] == 0 && i != 2) {
                    System.out.print(" X |");
                } else if (intArray[j][i] == -1 && i == 2) {
                    System.out.print(" O\n");
                } else if (intArray[j][i] == -1 && i != 2) {
                    System.out.print(" O |");
                } else if (i == 2){
                    System.out.printf(" %d\n", intArray[j][i]);
                } else {
                    System.out.printf(" %d |", intArray[j][i]);
                }
            }
        }
    }

    public static void initialiserMatrice (int[][] intArray) {

        int k = 1;

        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {

                intArray[j][i] = k;
                ++k;
            }
        }
    }

    public static void tourJoueur (int[][] intArray) {

        Scanner lecteur = new Scanner(System.in);
        boolean valide;
        int caseX;

        do {
            System.out.printf("Sélectionnez une case (1-9): ");
            caseX = lecteur.nextInt();

            if (caseX < 1 || caseX > 9) {
                valide = false;
                System.out.println("Valeur doit etre entre 1 et 9");
            } else {
                valide = true;
            }
        } while (valide == false);

        switch (caseX) {
            case 1 : intArray[0][0] = 0;break;
            case 2 : intArray[0][1] = 0;break;
            case 3 : intArray[0][2] = 0;break;
            case 4 : intArray[1][0] = 0;break;
            case 5 : intArray[1][1] = 0;break;
            case 6 : intArray[1][2] = 0;break;
            case 7 : intArray[2][0] = 0;break;
            case 8 : intArray[2][1] = 0;break;
            case 9 : intArray[2][2] = 0;break;
        }
    }

    public static void tourIA (int[][] intArray) {

        Random randomGenerator = new Random();
        boolean valide;
        int caseO1;
        int caseO2;



        do {
            caseO1 = randomGenerator.nextInt(3);
            caseO2 = randomGenerator.nextInt(3);

            if (intArray[caseO1][caseO2] == 0 || intArray[caseO1][caseO2] == -1){
                valide = false;
            } else {
                intArray[caseO1][caseO2] = -1;
                valide = true;
            }
        } while (valide == false);

    }

    public static boolean verifierSiPlein (int[][] intArray)  {

        boolean plein = false;
        int compteur = 0;

        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                if (intArray[j][i] != 0 && intArray[j][i] != -1) {
                    ++compteur;
                }
            }
        }
        if (compteur <= 0) {
            plein = true;
        }
        return plein;
    }

    public static boolean verifierSiGagner (int[][] intArray) {

        boolean gagnant = false;

        if (intArray[0][0] == 0 && intArray[0][1] == 0 && intArray[0][2] == 0) {
            gagnant = true;
        } else if (intArray[1][0] == 0 && intArray[1][1] == 0 && intArray[1][2] == 0) {
            gagnant = true;
        } else if (intArray[2][0] == 0 && intArray[2][1] == 0 && intArray[2][2] == 0) {
            gagnant = true;
        } else if (intArray[0][0] == 0 && intArray[1][0] == 0 && intArray[2][0] == 0) {
            gagnant = true;
        } else if (intArray[0][1] == 0 && intArray[1][1] == 0 && intArray[2][1] == 0) {
            gagnant = true;
        } else if (intArray[0][2] == 0 && intArray[1][2] == 0 && intArray[2][2] == 0) {
            gagnant = true;
        } else if (intArray[0][0] == 0 && intArray[1][1] == 0 && intArray[2][2] == 0) {
            gagnant = true;
        } else if (intArray[0][2] == 0 && intArray[1][1] == 0 && intArray[2][0] == 0) {
            gagnant = true;
        }

        return gagnant;
    }

    public static boolean verifierSiIAGagner(int[][] intArray) {

        boolean IAGagnant = false;

        if (intArray[0][0] == -1 && intArray[0][1] == -1 && intArray[0][2] == -1) {
            IAGagnant = true;
        } else if (intArray[1][0] == -1 && intArray[1][1] == -1 && intArray[1][2] == -1) {
            IAGagnant = true;
        } else if (intArray[2][0] == -1 && intArray[2][1] == -1 && intArray[2][2] == -1) {
            IAGagnant = true;
        } else if (intArray[0][0] == -1 && intArray[1][0] == -1 && intArray[2][0] == -1) {
            IAGagnant = true;
        } else if (intArray[0][1] == -1 && intArray[1][1] == -1 && intArray[2][1] == -1) {
            IAGagnant = true;
        } else if (intArray[0][2] == -1 && intArray[1][2] == -1 && intArray[2][2] == -1) {
            IAGagnant = true;
        } else if (intArray[0][0] == -1 && intArray[1][1] == -1 && intArray[2][2] == -1) {
            IAGagnant = true;
        } else if (intArray[0][2] == -1 && intArray[1][1] == -1 && intArray[2][0] == -1) {
            IAGagnant = true;
        }

        return IAGagnant;
    }

    public static void main(String[] args) {

        boolean gagnant = false;
        boolean IAGagnant = false;
        boolean plein;
        int[][] intArray = new int[3][3];

        initialiserMatrice(intArray);
        afficherTableau(intArray);
        do {
            tourJoueur(intArray);
            gagnant = verifierSiGagner(intArray);
            if (gagnant == false) {
                tourIA(intArray);
                IAGagnant = verifierSiIAGagner(intArray);
            }
            afficherTableau(intArray);
            plein = verifierSiPlein(intArray);
        }  while (gagnant == false && plein == false && IAGagnant == false);

        if ( gagnant == true) {
            System.out.println("\nVous avez gagné.");
        } else if (IAGagnant == true) {
            System.out.println("\nL'IA a remporté la partie.");
        } else if (plein == true) {
            System.out.println("\nÉgalité.");
        }

    }


}
