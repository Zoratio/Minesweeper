public class NumberTile implements Tile {
    private int Value;  //how many bombs are touching the tile
    private boolean Revealed;   //default is false
    public NumberTile(){
        Value = 0;
    }

    public void SetValue(int Value){
        this.Value = Value;
    }
    public int GetValue(){
        return Value;
    }

    @Override
    public void Reveal() {

    }
}
