public class TitleholderManeuverer {

    private int[][] gameGrid;
    private int rowPoints = 0;
    private int columnPoints = 0;
    private int downwardDiagonal = 0;
    private int upwardDiagonal = 0;
    public TitleholderManeuverer(int[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public boolean isTitleholder(int value) {
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
