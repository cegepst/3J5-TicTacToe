import java.util.Random;

public class Game {
    public Game(int[][] gameGrid) {
        initializeGrid(gameGrid);
        displayGrid(gameGrid);
    }

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
        int cellCoordinate;
        do {
            cellCoordinate = Terminal.readInt("Sélectionnez une case (1-9): ");
        } while (!isCoordinateValid(cellCoordinate));
        return cellCoordinate;
    }

    private static boolean isCoordinateValid(int cellCoordinate) {
        if (cellCoordinate < 1 || cellCoordinate > 9) {
            System.out.println("Valeur doit etre entre 1 et 9");
            return false;
        }
        return true;
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

    public static boolean isWinner(int[][] gameGrid, int value) {
        int rowPoints = 0;
        int columnPoints = 0;
        int downwardDiagonal = 0;
        int upwardDiagonal = 0;

        for (int x = 0; x <= 2; x++) {
            if (gameGrid[x][x] == value) {
                downwardDiagonal++;
            }
            if (gameGrid[2-x][x] == value) {
                upwardDiagonal++;
            }
            for (int y = 0; y <= 2; y++) {
                if (gameGrid[x][y] == value) {
                    rowPoints++;
                }
                if (gameGrid[y][x] == value) {
                    columnPoints++;
                }
                if (rowPoints == 3 || columnPoints == 3 || downwardDiagonal == 3 || upwardDiagonal == 3) {
                    return true;
                }
            }
            columnPoints = 0;
            rowPoints = 0;
        }
        return false;
    }

}
