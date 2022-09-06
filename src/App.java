import java.util.Random;

public class App {
    public static void afficherTableau(int[][] gameGrid) {

        Terminal.lineBreak();

        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {

                if (gameGrid[j][i] == 0 && i == 2) {
                    System.out.print(" X\n");
                } else if (gameGrid[j][i] == 0) {
                    System.out.print(" X |");
                } else if (gameGrid[j][i] == -1 && i == 2) {
                    System.out.print(" O\n");
                } else if (gameGrid[j][i] == -1) {
                    System.out.print(" O |");
                } else if (i == 2){
                    System.out.printf(" %d\n", gameGrid[j][i]);
                } else {
                    System.out.printf(" %d |", gameGrid[j][i]);
                }
            }
        }
    }

    public static void initialiserMatrice (int[][] gameGrid) {

        int k = 1;


        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {

                gameGrid[j][i] = k;
                ++k;
            }
        }
    }

    public static void tourJoueur (int[][] gameGrid) {
        boolean valide;
        int caseX;

        do {
            caseX = Terminal.readInt("Sélectionnez une case (1-9): ");

            if (caseX < 1 || caseX > 9) {
                valide = false;
                System.out.println("Valeur doit etre entre 1 et 9");
            } else {
                valide = true;
            }
        } while (!valide);

        switch (caseX) {
            case 1 : gameGrid[0][0] = 0;break;
            case 2 : gameGrid[0][1] = 0;break;
            case 3 : gameGrid[0][2] = 0;break;
            case 4 : gameGrid[1][0] = 0;break;
            case 5 : gameGrid[1][1] = 0;break;
            case 6 : gameGrid[1][2] = 0;break;
            case 7 : gameGrid[2][0] = 0;break;
            case 8 : gameGrid[2][1] = 0;break;
            case 9 : gameGrid[2][2] = 0;break;
        }
    }

    public static void tourIA (int[][] gameGrid) {

        Random randomGenerator = new Random();
        boolean valide;
        int caseO1;
        int caseO2;




        do {
            caseO1 = randomGenerator.nextInt(3);
            caseO2 = randomGenerator.nextInt(3);

            if (gameGrid[caseO1][caseO2] == 0 || gameGrid[caseO1][caseO2] == -1){
                valide = false;
            } else {
                gameGrid[caseO1][caseO2] = -1;
                valide = true;
            }
        } while (!valide);

    }

    public static boolean verifierSiPlein (int[][] gameGrid)  {
        int compteur = 0;
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                if (gameGrid[j][i] != 0 && gameGrid[j][i] != -1) {
                    ++compteur;
                }
            }
        }
        return compteur <= 0;
    }

    public static boolean verifierSiGagner (int[][] gameGrid) {

        boolean gagnant = false;

        if (gameGrid[0][0] == 0 && gameGrid[0][1] == 0 && gameGrid[0][2] == 0) {
            gagnant = true;
        } else if (gameGrid[1][0] == 0 && gameGrid[1][1] == 0 && gameGrid[1][2] == 0) {
            gagnant = true;
        } else if (gameGrid[2][0] == 0 && gameGrid[2][1] == 0 && gameGrid[2][2] == 0) {
            gagnant = true;
        } else if (gameGrid[0][0] == 0 && gameGrid[1][0] == 0 && gameGrid[2][0] == 0) {
            gagnant = true;
        } else if (gameGrid[0][1] == 0 && gameGrid[1][1] == 0 && gameGrid[2][1] == 0) {
            gagnant = true;
        } else if (gameGrid[0][2] == 0 && gameGrid[1][2] == 0 && gameGrid[2][2] == 0) {
            gagnant = true;
        } else if (gameGrid[0][0] == 0 && gameGrid[1][1] == 0 && gameGrid[2][2] == 0) {
            gagnant = true;
        } else if (gameGrid[0][2] == 0 && gameGrid[1][1] == 0 && gameGrid[2][0] == 0) {
            gagnant = true;
        }

        return gagnant;
    }

    public static boolean verifierSiIAGagner(int[][] gameGrid) {

        boolean IAGagnant = false;

        if (gameGrid[0][0] == -1 && gameGrid[0][1] == -1 && gameGrid[0][2] == -1) {
            IAGagnant = true;
        } else if (gameGrid[1][0] == -1 && gameGrid[1][1] == -1 && gameGrid[1][2] == -1) {
            IAGagnant = true;
        } else if (gameGrid[2][0] == -1 && gameGrid[2][1] == -1 && gameGrid[2][2] == -1) {
            IAGagnant = true;
        } else if (gameGrid[0][0] == -1 && gameGrid[1][0] == -1 && gameGrid[2][0] == -1) {
            IAGagnant = true;
        } else if (gameGrid[0][1] == -1 && gameGrid[1][1] == -1 && gameGrid[2][1] == -1) {
            IAGagnant = true;
        } else if (gameGrid[0][2] == -1 && gameGrid[1][2] == -1 && gameGrid[2][2] == -1) {
            IAGagnant = true;
        } else if (gameGrid[0][0] == -1 && gameGrid[1][1] == -1 && gameGrid[2][2] == -1) {
            IAGagnant = true;
        } else if (gameGrid[0][2] == -1 && gameGrid[1][1] == -1 && gameGrid[2][0] == -1) {
            IAGagnant = true;
        }

        return IAGagnant;
    }

    public static void main(String[] args) {

        boolean gagnant = false;
        boolean IAGagnant = false;
        boolean plein;
        int[][] gameGrid = new int[3][3];

        initialiserMatrice(gameGrid);
        afficherTableau(gameGrid);
        do {
            tourJoueur(gameGrid);
            gagnant = verifierSiGagner(gameGrid);
            if (!gagnant) {
                tourIA(gameGrid);
                IAGagnant = verifierSiIAGagner(gameGrid);
            }
            afficherTableau(gameGrid);
            plein = verifierSiPlein(gameGrid);
        }  while (!gagnant && !plein && !IAGagnant);

        if (gagnant) {
            System.out.println("\nVous avez gagné.");
        } else if (IAGagnant) {
            System.out.println("\nL'IA a remporté la partie.");
        } else if (plein) {
            System.out.println("\nÉgalité.");
        }

    }


}
