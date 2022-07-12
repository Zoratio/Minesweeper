public class BombTile extends Tile {

    @Override
    public void Reveal() {
        revealed = true;
        ResultOfReveal();
    }

    @Override
    void ResultOfReveal() {
        //show solution, lost game
    }
}
