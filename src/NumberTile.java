import java.util.Stack;

public class NumberTile extends Tile {
    public void QuickReveal() {
        revealed = true;
    }
    @Override
    public void Reveal(Map game, int[] pos) {
        revealed = true;
        ResultOfReveal(game, pos);
    }

    @Override
    void ResultOfReveal(Map game, int[] pos) {
        // check to see if there are any places that is definitely clear, so they get revealed automatically
        if (value.equals("0")){
            DFS(game.GetGrid(), pos);
        }
    }

    public void DFS(Tile[][] grid, int[] pos) {
        int h = grid.length;
        if (h == 0)
            return;
        int l = grid[0].length;

        boolean[][] visited = new boolean[h][l];

        Stack<String> stack = new Stack<>();

        //stack.push(0 + "," + 0);
        stack.push(pos[0] + "," + pos[1]);

        while (!stack.empty()) {

            String x = stack.pop();
            int row = Integer.parseInt(x.split(",")[0]);
            int col = Integer.parseInt(x.split(",")[1]);

            System.out.println("looped");

            if(row<0 || col<0 || row>=h || col>=l || visited[row][col] || !grid[row][col].value.equals("0")) continue;

            visited[row][col]=true;
            ((NumberTile)grid[row][col]).QuickReveal();

            //if(row<=0 || col<=0 || row>=h || col>=l) continue;

            stack.push(row + "," + (col-1)); //go left
            stack.push(row + "," + (col+1)); //go right
            stack.push((row-1) + "," + col); //go up
            stack.push((row+1) + "," + col); //go down
            stack.push((row-1) + "," + (col-1)); //go bottom left
            stack.push((row-1) + "," + (col+1)); //go top right
            stack.push((row+1) + "," + (col+1)); //go bottom right
            stack.push((row+1) + "," + (col-1)); //go top left
        }
    }
}
