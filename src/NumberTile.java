public class NumberTile extends Tile {

    @Override
    public void Reveal() {
        revealed = true;
        ResultOfReveal();
    }

    @Override
    void ResultOfReveal() {
        // check to see if there are any places that is definitely clear, so they get revealed automatically
    }
}
