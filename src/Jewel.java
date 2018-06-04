/**
 * This abstract class have common attributes for jewels like symbol, score and
 * shift method.
 * This class have 10 different jewel classes(Empty class extends Jewel class)
 * */



import java.util.ArrayList;

public abstract class Jewel {
    protected String symbol;
    protected int score;

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }


    public abstract ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid);
}
