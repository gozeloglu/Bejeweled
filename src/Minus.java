/**
 * Minus class searches horizontally
 * Calls the methods from horizontal class for searching and shifting operations
 *
 * */


import java.util.ArrayList;

public class Minus extends Jewel {
    Horizontal horizontal = new Horizontal();

    public Minus() {
        this.symbol = "-";
        this.score = 20;
    }

    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        int[] coordinates = horizontal.findMathCoordinates(row, column,"+", gridMapList);   //Calls the method for searching operation

        //Calls the method for shifting operations if found triplet
        if (Math.abs(coordinates[1] - column) != 2) {
            grid.printGrid();
            System.out.println("Your selected coordinate did not have any triple to match! Try again!");
        } else {
            if (column - coordinates[1] == 2) {
                int newScore = gridMapList.get(row).get(column).getScore() +
                                gridMapList.get(row).get(column-1).getScore() +
                                gridMapList.get(row).get(column-2).getScore();
                player.addScore(newScore);
                gridMapList = horizontal.move(row, column, gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            } else if (coordinates[1] - column == 2) {
                int newScore = gridMapList.get(row).get(coordinates[1]).getScore() +
                                gridMapList.get(row).get(coordinates[1]-1).getScore() +
                                gridMapList.get(row).get(coordinates[1]-2).getScore();
                player.addScore(newScore);
                gridMapList = horizontal.move(row, coordinates[1], gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
        }
        return gridMapList;
    }


}
