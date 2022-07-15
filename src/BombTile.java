public class BombTile extends Tile {

    @Override
    public void Reveal(Map game, int[] pos) {
        revealed = true;
        ResultOfReveal(game, pos);
    }

    @Override
    void Flag(Map game, int[] pos) {
        flagged = true;
    }

    @Override
    void ResultOfReveal(Map game, int[] pos) {
        //show solution, lost game
        game.state = Map.gameState.FAILED;
    }
}
