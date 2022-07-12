abstract class Tile {
    boolean revealed = false;   //false by default
    String value;
    public void SetValue(String value){
        this.value = value;
    }
    String GetValue(){ return value; };
    boolean GetRevealed(){ return revealed; }
    abstract void Reveal();
    abstract void ResultOfReveal();
}
