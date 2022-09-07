import java.util.Random;

public class Game {

    private Grid grid;
    private boolean playerWon = false;
    private boolean computerWon = false;
    private boolean isGridFilled;

    public Game() {
        grid = new Grid();
    }

    public void start() {
        do {
            grid.display();
            playerWon = playerTurn();
            if (!playerWon) {
                computerWon = computerTurn();
            }
            isGridFilled = grid.isFull();
        }  while (!playerWon && !isGridFilled && !computerWon);
        gameEnded();
    }

    public boolean isTitleholder(int playerValue) {
        return grid.isTitleholder(playerValue);
    }

    public boolean playerTurn() {
        int coord = askCoordinate();
        grid.placeIndicator((coord - 1) / 3, (coord - 1) % 3, Grid.PLAYER);
        return isTitleholder(Grid.PLAYER);
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

    public boolean computerTurn() {

        Random randomGenerator = new Random();
        boolean valid;
        int xPosition;
        int yPosition;
        do {
            xPosition = randomGenerator.nextInt(3);
            yPosition = randomGenerator.nextInt(3);

            if (grid.indicatorExists(xPosition, yPosition)){
                valid = false;
            } else {
                grid.placeIndicator(xPosition, yPosition, Grid.AI);
                valid = true;
            }
        } while (!valid);
        return isTitleholder(Grid.AI);
    }

    private void gameEnded() {
        if (playerWon) {
            Terminal.message("\nVous avez gagné.\n");
            return;
        }
        if (computerWon) {
            Terminal.message("\nL'IA a remporté la partie.\n");
            return;
        }
        if (isGridFilled) {
            Terminal.message("\nÉgalité.\n");
        }
    }
}
