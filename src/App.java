import java.util.Random;

public class App {
    public static void main(String[] args) {

        boolean playerWon = false;
        boolean computerWon = false;
        boolean isGridFilled;
        Game game = new Game();

        do {
            game.playerTurn();
            playerWon = game.isWinner(0);
            if (!playerWon) {
                game.computerTurn();
                computerWon = game.isWinner(-1);
            }
            game.displayGrid();
            isGridFilled = game.isFull();
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
