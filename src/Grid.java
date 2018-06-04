/**
 * This class stores the grid map on gridMapList as two-dimensional array list
 * */


import java.util.ArrayList;

public class Grid {
    private ArrayList<ArrayList<Jewel>> gridMapList = new ArrayList<>();


    /**Adds new sub-arrays into gridMapList after reading a line and created objects
     * This method calls from Operation class*/
    public void addNewLine(ArrayList<Jewel> gridList) {
        gridMapList.add(gridList);
    }

    /**Prints out the map on the screen*/
    public void printGrid() {
        System.out.println();
        for ( int i = 0 ; i < gridMapList.size() ; i++ ) {
            for ( int j = 0 ; j < gridMapList.get(i).size() ; j++ ) {
                System.out.print(gridMapList.get(i).get(j).getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**Finds the jewel according to given coordinates.
     * Calls the shift method from found object's class.
     * If the player enters a coordinate which are out of the map, error message prints out*/
    public void findPlace(int x, int y, LeaderBoard player, Grid grid) {
        try {
            gridMapList = gridMapList.get(x).get(y).shift(x, y, gridMapList, player, grid);
        } catch (IndexOutOfBoundsException e) {
            grid.printGrid();
            System.out.println("You have entered a coordinate which is out of map!");
        }
    }
}
