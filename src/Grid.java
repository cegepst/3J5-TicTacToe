public class Grid {

    public static final int PLAYER = 0;
    public static final int AI = -1;

    private int[][] gameGrid;

    public Grid() {
        initialize();
    }

    public void placeIndicator(int x, int y, int value) {
        gameGrid[x][y] = value;
    }

    public boolean indicatorExists(int x, int y) {
        return gameGrid[x][y] == PLAYER || gameGrid[x][y] == AI;
    }

    public boolean isTitleholder(int playerValue) {
        TitleholderManeuverer maneuverer = new TitleholderManeuverer(gameGrid);
        return maneuverer.isTitleholder(playerValue);
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

    public void display() {
        Terminal.lineBreak();
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                displayCell(i, j);
            }
            Terminal.lineBreak();
        }
    }

    private void initialize() {
        this.gameGrid = new int[3][3];
        int gridInitializer = 1;
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < 3; ++i) {
                gameGrid[j][i] = gridInitializer;
                ++gridInitializer;
            }
        }
    }

    private void displayCell(int i, int j) {
        if (gameGrid[j][i] == PLAYER) {
            Terminal.message(" X ");
        } else if (gameGrid[j][i] == AI) {
            Terminal.message(" O ");
        } else {
            Terminal.message(" "+ gameGrid[j][i] +" ");
        }
        if (i != 2) {
            Terminal.message("|");
        }
    }
}
