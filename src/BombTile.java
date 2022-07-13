public class BombTile extends Tile {

    @Override
    public void Reveal(Map game, int[] pos) {
        revealed = true;
        ResultOfReveal(game, pos);
    }

    @Override
    void ResultOfReveal(Map game, int[] pos) {
        //show solution, lost game
    }
}
