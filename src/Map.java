public class Map {
    private Tile[][] grid;

    public Map(){
        ScannerInputs scanner = new ScannerInputs();
        int size = 5; //to change
        if (size > 1) {
            grid = new Tile[size][size]; //makes grid then sets all values to 0. i then j.
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = new BlankTile();
                }
            }
        }
        else {
            System.out.println("Please input a size that is larger than 1 so that the game is playable.");
        }
    }
}
