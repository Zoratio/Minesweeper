import java.util.Random;
/*
ToDo
- Size and difficulty input
- Fill in the spaces that are definitely clear (0s)
*/

public class Map {
    private Tile[][] grid;
    private int size = 10;

    public Map(){
        ScannerInputs scanner = new ScannerInputs();
        grid = new Tile[size][size]; //makes grid then sets all values to 0. i then j.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new NumberTile();
            }
        }
        int bombs = 0;
        while(bombs != 10)
        {
            Random random = new Random();
            int i = random.nextInt(10);
            int j = random.nextInt(10);
            //System.out.println("i: " + i + " j: " + j);
            grid[i][j] = new BombTile();
            bombs++;
        }
        buildGrid();
        displayGrid();
    }

    public void buildGrid()
    {
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
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
                    if(i!=9)
                    {
                        if(grid[i+1][j] instanceof BombTile) BombsConnected++;  //below
                        if(j!=9)
                        {
                            if(grid[i+1][j+1] instanceof BombTile) BombsConnected++;    //below right
                        }
                    }
                    if(j!=0)
                    {
                        if(grid[i][j-1] instanceof BombTile) BombsConnected++;  //left
                        if(i!=9)
                        {
                            if(grid[i+1][j-1] instanceof BombTile) BombsConnected++;    //below left
                        }
                    }
                    if(j!=9)
                    {
                        if(grid[i][j+1] instanceof BombTile) BombsConnected++;  //right
                        if(i!=0)
                        {
                            if(grid[i-1][j+1] instanceof BombTile) BombsConnected++;    //above right
                        }
                    }

                    ((NumberTile)grid[i][j]).SetValue(BombsConnected);  //sets how many bombs are connected to this tile
                }
            }
        }
    }

    public void displayGrid()
    {
        System.out.print("\t ");
        for(int i=0; i<10; i++)
        {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for(int i=0; i<10; i++)
        {
            System.out.print(i + "\t| ");
            for(int j=0; j<10; j++)
            {
                if(grid[i][j] instanceof BombTile){
                    System.out.print("B");
                }
                else{
                    System.out.print(((NumberTile)grid[i][j]).GetValue());
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }
}
