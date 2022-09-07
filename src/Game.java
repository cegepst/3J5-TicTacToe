import java.util.Random;

public class Game {

    private final int PLAYER = 0;
    private final int AI = -1;
    private int[][] gameGrid;
    private boolean playerWon = false;
    private boolean computerWon = false;
    private boolean isGridFilled;

    public Game() {
        initializeGrid();
        displayGrid();
    }

    public void startGame() {
        do {
            playerTurn();
            playerWon = isTitleholder(PLAYER);
            if (!playerWon) {
                computerTurn();
                computerWon = isTitleholder(AI);
            }
            displayGrid();
            isGridFilled = isFull();
        }  while (!playerWon && !isGridFilled && !computerWon);
        gameEnded();
    }

    public void displayGrid() {
        Terminal.lineBreak();

        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                if (gameGrid[j][i] == PLAYER && i == 2) {
                    Terminal.message(" X\n");
                } else if (gameGrid[j][i] == PLAYER) {
                    Terminal.message(" X |");
                } else if (gameGrid[j][i] == AI && i == 2) {
                    Terminal.message(" O\n");
                } else if (gameGrid[j][i] == AI) {
                    Terminal.message(" O |");
                } else if (i == 2){
                    Terminal.message(" "+ gameGrid[j][i] +"\n");
                } else {
                    Terminal.message(" "+ gameGrid[j][i] +" |");
                }
            }
        }
    }

    public boolean isTitleholder(int playerValue) {
        TitleholderManeuverer maneuverer = new TitleholderManeuverer(gameGrid);
        return maneuverer.isTitleholder(playerValue);
    }

    public void initializeGrid() {
        this.gameGrid = new int[3][3];
        int inputNumber = 1;
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                gameGrid[j][i] = inputNumber;
                ++inputNumber;
            }
        }
    }

    public void playerTurn() {
        int coord = askCoordinate();
        gameGrid[(coord - 1) / 3][(coord - 1) % 3] = PLAYER;
    }

    private int askCoordinate() {
        int cellCoordinate;
        do {
            cellCoordinate = Terminal.readInt("Sélectionnez une case (1-9): ");
        } while (!isCoordinateValid(cellCoordinate));
        return cellCoordinate;
    }

    private boolean isCoordinateValid(int cellCoordinate) {
        if (cellCoordinate < 1 || cellCoordinate > 9) {
            Terminal.message("Valeur doit etre entre 1 et 9 \n");
            return false;
        }
        return true;
    }

    public void computerTurn() {

        Random randomGenerator = new Random();
        boolean valid;
        int xPosition;
        int yPosition;
        do {
            xPosition = randomGenerator.nextInt(3);
            yPosition = randomGenerator.nextInt(3);

            if (gameGrid[xPosition][yPosition] == PLAYER || gameGrid[xPosition][yPosition] == AI){
                valid = false;
            } else {
                gameGrid[xPosition][yPosition] = AI;
                valid = true;
            }
        } while (!valid);
    }

    public boolean isFull()  {
        int fullCellCount = 0;
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                if (gameGrid[j][i] != PLAYER && gameGrid[j][i] != AI) {
                    ++fullCellCount;
                }
            }
        }
        return fullCellCount <= 0;
    }

    private void gameEnded() {
        if (playerWon) {
            Terminal.message("\nVous avez gagné.\n");
        } else if (computerWon) {
            Terminal.message("\nL'IA a remporté la partie.\n");
        } else if (isGridFilled) {
            Terminal.message("\nÉgalité.\n");
        }
    }
}
