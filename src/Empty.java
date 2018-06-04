/**
 * Empty class creates empty places on the grid map.
 * */

import java.util.ArrayList;

public class Empty extends Jewel {

    public Empty() {
        this.symbol = " ";
    }

    public String getSymbol() {
        return symbol;
    }
    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        grid.printGrid();
        System.out.println("You have selected empty places!");
        return gridMapList;
    }
}
