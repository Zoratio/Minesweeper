import java.util.Random;
/*
ToDo
- On game start, player selects a tile, this tile will not be a bomb or have any bombs touching
- Fill in the spaces that are definitely clear (0s)
- Input validation
*/

public class Map {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String ANSI_BLACK = "\u001B[30m";

    private Tile[][] grid;
    private int size;
    private enum gameState {INCOMPLETE, COMPLETE, FAILED}
    private gameState state = gameState.INCOMPLETE;

    public Map() {
        System.out.println("Welcome to Minesweeper");
        size = ScannerInputs.CheckRange();   //Do this method
        grid = new Tile[size][size]; //makes grid then sets all values to 0. i then j.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new NumberTile();
            }
        }
        //pickStartingTile();
        placeBombs();
        buildGrid();
        displayGrid();
        gameLoop();
    }

    private void pickStartingTile() {   //CURRENLTY WORKING ON THIS. WILL NEED TO CHANGE HOW THE GRID LINE WORKS BECAUSE AT THIS POINT THE GRID HASN'T BEEN MADE. I NEED TO MAKE A BLANK FAKE GRID,
                                        // PLAYER PICKS TILE THEN REAL BOARD CREATED AND BOMB CREATION AVOIDS THOSE 9 TOUCHING TILES.
        int[] pos = new int[2];
        pos = ScannerInputs.CheckInput(pos);
        grid[pos[0]][pos[1]].Reveal(this, pos);
    }

    public void placeBombs() {
        int bombs = 0;
        int totalBombs = ScannerInputs.CheckDifficulty(size);
        while(bombs != totalBombs)
        {
            Random random = new Random();
            int i = random.nextInt(size);
            int j = random.nextInt(size);
            BombTile bt = new BombTile();
            bt.SetValue("B");
            grid[i][j] = bt;

            bombs++;
        }
    }

    public void buildGrid() {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                int BombsConnected = 0;
                if(!(grid[i][j] instanceof BombTile))   //ensures the tile being checked for connected bombs is not a bomb itself
                {

                    if(i!=0)
                    {
                        if(grid[i-1][j] instanceof BombTile) BombsConnected++;  //above
                        if(j!=0)
                        {
                            if(grid[i-1][j-1] instanceof BombTile) BombsConnected++;    //above left
                        }

                    }
                    if(i!=size-1)
                    {
                        if(grid[i+1][j] instanceof BombTile) BombsConnected++;  //below
                        if(j!=size-1)
                        {
                            if(grid[i+1][j+1] instanceof BombTile) BombsConnected++;    //below right
                        }
                    }
                    if(j!=0)
                    {
                        if(grid[i][j-1] instanceof BombTile) BombsConnected++;  //left
                        if(i!=size-1)
                        {
                            if(grid[i+1][j-1] instanceof BombTile) BombsConnected++;    //below left
                        }
                    }
                    if(j!=size-1)
                    {
                        if(grid[i][j+1] instanceof BombTile) BombsConnected++;  //right
                        if(i!=0)
                        {
                            if(grid[i-1][j+1] instanceof BombTile) BombsConnected++;    //above right
                        }
                    }

                    (grid[i][j]).SetValue(String.valueOf(BombsConnected));  //sets how many bombs are connected to this tile
                }
            }
        }
    }

    public void displayGrid() {

        boolean solved = true;
        System.out.print("\t ");
        for(int i=0; i<size; i++)
        {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for(int i=0; i<size; i++)
        {
            System.out.print(i + "\t| ");
            for(int j=0; j<size; j++)
            {
                if(grid[i][j] instanceof BombTile){
                    if ((grid[i][j]).GetRevealed()){
                        System.out.print((grid[i][j]).GetValue());
                    }
                    else{
                        System.out.print("?");
                    }
                }
                else{
                    if ((grid[i][j]).GetRevealed()){
                        switch ((grid[i][j]).GetValue()){
                            case "0":
                                System.out.print(" ");
                                break;
                            case "1":
                                System.out.print(ANSI_BLUE + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "2":
                                System.out.print(ANSI_GREEN + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "3":
                                System.out.print(ANSI_RED + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "4":
                                System.out.print(ANSI_YELLOW + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "5":
                                System.out.print(ANSI_CYAN + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            case "6":
                                System.out.print(ANSI_MAGENTA + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                            default:
                                System.out.print(ANSI_BLACK + (grid[i][j]).GetValue() + ANSI_RESET);
                                break;
                        }

                    }
                    else{
                        System.out.print("?");
                        solved = false;
                    }
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
        if (solved){
            state = gameState.COMPLETE;
        }
    }

    private void gameLoop(){
        while (state == gameState.INCOMPLETE){
            inputPosition();
            displayGrid();
        }
        if (state == gameState.COMPLETE){
            completeGame();
        }
        else{
            failedGame();
        }
    }

    private void inputPosition() {
        int[] pos = new int[2];
        pos = ScannerInputs.CheckInput(pos);
        grid[pos[0]][pos[1]].Reveal(this, pos);
    }

    public Tile[][] GetGrid(){
        return grid;
    }

    private void completeGame() {
    }
    private void failedGame() {
    }
}
