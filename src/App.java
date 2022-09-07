import java.util.Random;

public class App {
    public static void main(String[] args) {

        boolean playerWon = false;
        boolean computerWon = false;
        boolean isGridFilled;
        int[][] gameGrid = new int[3][3];
        Game game = new Game(gameGrid);

        game.initializeGrid(gameGrid);
        game.displayGrid(gameGrid);
        do {
            game.playerTurn(gameGrid);
            playerWon = game.isWinner(gameGrid, 0);
            if (!playerWon) {
                game.computerTurn(gameGrid);
                computerWon = game.isWinner(gameGrid, -1);
            }
            game.displayGrid(gameGrid);
            isGridFilled = game.isFull(gameGrid);
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
