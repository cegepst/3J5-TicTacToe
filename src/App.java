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
            Terminal.message("\nVous avez gagné.\n");
        } else if (computerWon) {
            Terminal.message("\nL'IA a remporté la partie.\n");
        } else if (isGridFilled) {
            Terminal.message("\nÉgalité.\n");
        }

    }


}
