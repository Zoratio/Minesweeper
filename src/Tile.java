abstract class Tile {
    boolean revealed = false;   //false by default
    boolean flagged = false;
    String value;
    public void SetValue(String value){
        this.value = value;
    }
    String GetValue(){ return value; };
    boolean GetRevealed(){ return revealed; }
    boolean GetFlagged(){ return flagged; }
    abstract void Reveal(Map game, int[] pos);
    abstract void Flag(Map game, int[] pos);
    abstract void ResultOfReveal(Map game, int[] pos);
}
