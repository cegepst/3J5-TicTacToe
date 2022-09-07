import java.util.Random;

public class App {
    public static void displayGrid(int[][] gameGrid) {

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

    public static void initializeGrid(int[][] gameGrid) {

        int inputNumber = 1;


        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {

                gameGrid[j][i] = inputNumber;
                ++inputNumber;
            }
        }
    }

    public static void playerTurn(int[][] gameGrid) {
        switch (askCoordinate()) {
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

    private static int askCoordinate() {
        boolean valid;
        int cellCoordinate;
        do {
            valid = true;
            cellCoordinate = Terminal.readInt("Sélectionnez une case (1-9): ");
            if (cellCoordinate < 1 || cellCoordinate > 9) {
                valid = false;
                System.out.println("Valeur doit etre entre 1 et 9");
            }
        } while (!valid);
        return cellCoordinate;
    }

    public static void computerTurn(int[][] gameGrid) {

        Random randomGenerator = new Random();
        boolean valid;
        int xPosition;
        int yPosition;




        do {
            xPosition = randomGenerator.nextInt(3);
            yPosition = randomGenerator.nextInt(3);

            if (gameGrid[xPosition][yPosition] == 0 || gameGrid[xPosition][yPosition] == -1){
                valid = false;
            } else {
                gameGrid[xPosition][yPosition] = -1;
                valid = true;
            }
        } while (!valid);

    }

    public static boolean isFull(int[][] gameGrid)  {
        int fullCellCount = 0;
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                if (gameGrid[j][i] != 0 && gameGrid[j][i] != -1) {
                    ++fullCellCount;
                }
            }
        }
        return fullCellCount <= 0;
    }

    public static boolean isPlayerWinner(int[][] gameGrid) {

        boolean won = false;

        if (gameGrid[0][0] == 0 && gameGrid[0][1] == 0 && gameGrid[0][2] == 0) {
            won = true;
        } else if (gameGrid[1][0] == 0 && gameGrid[1][1] == 0 && gameGrid[1][2] == 0) {
            won = true;
        } else if (gameGrid[2][0] == 0 && gameGrid[2][1] == 0 && gameGrid[2][2] == 0) {
            won = true;
        } else if (gameGrid[0][0] == 0 && gameGrid[1][0] == 0 && gameGrid[2][0] == 0) {
            won = true;
        } else if (gameGrid[0][1] == 0 && gameGrid[1][1] == 0 && gameGrid[2][1] == 0) {
            won = true;
        } else if (gameGrid[0][2] == 0 && gameGrid[1][2] == 0 && gameGrid[2][2] == 0) {
            won = true;
        } else if (gameGrid[0][0] == 0 && gameGrid[1][1] == 0 && gameGrid[2][2] == 0) {
            won = true;
        } else if (gameGrid[0][2] == 0 && gameGrid[1][1] == 0 && gameGrid[2][0] == 0) {
            won = true;
        }

        return won;
    }

    public static boolean isComputerWinner(int[][] gameGrid) {

        boolean won = false;

        if (gameGrid[0][0] == -1 && gameGrid[0][1] == -1 && gameGrid[0][2] == -1) {
            won = true;
        } else if (gameGrid[1][0] == -1 && gameGrid[1][1] == -1 && gameGrid[1][2] == -1) {
            won = true;
        } else if (gameGrid[2][0] == -1 && gameGrid[2][1] == -1 && gameGrid[2][2] == -1) {
            won = true;
        } else if (gameGrid[0][0] == -1 && gameGrid[1][0] == -1 && gameGrid[2][0] == -1) {
            won = true;
        } else if (gameGrid[0][1] == -1 && gameGrid[1][1] == -1 && gameGrid[2][1] == -1) {
            won = true;
        } else if (gameGrid[0][2] == -1 && gameGrid[1][2] == -1 && gameGrid[2][2] == -1) {
            won = true;
        } else if (gameGrid[0][0] == -1 && gameGrid[1][1] == -1 && gameGrid[2][2] == -1) {
            won = true;
        } else if (gameGrid[0][2] == -1 && gameGrid[1][1] == -1 && gameGrid[2][0] == -1) {
            won = true;
        }

        return won;
    }

    public static void main(String[] args) {

        boolean playerWon = false;
        boolean computerWon = false;
        boolean isGridFilled;
        int[][] gameGrid = new int[3][3];

        initializeGrid(gameGrid);
        displayGrid(gameGrid);
        do {
            playerTurn(gameGrid);
            playerWon = isPlayerWinner(gameGrid);
            if (!playerWon) {
                computerTurn(gameGrid);
                computerWon = isComputerWinner(gameGrid);
            }
            displayGrid(gameGrid);
            isGridFilled = isFull(gameGrid);
        }  while (!playerWon && !isGridFilled && !computerWon);

        if (playerWon) {
            System.out.println("\nVous avez gagné.");
        } else if (computerWon) {
            System.out.println("\nL'IA a remporté la partie.");
        } else if (isGridFilled) {
            System.out.println("\nÉgalité.");
        }

    }


}
