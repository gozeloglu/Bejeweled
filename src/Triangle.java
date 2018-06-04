/**
 * Triangle object searches on vertical direction to match with other Triangle's
 * Calls the methods from Vertical class for searching and shifting operations
 * */


import java.util.ArrayList;

public class Triangle extends Jewel {
    Vertical vertical = new Vertical();

    public Triangle() {
        this.symbol = "T";
        this.score = 15;
    }

    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        int[] coordinates = vertical.findCoordinates(row, column, gridMapList);
        if ( Math.abs(coordinates[0] - row) != 2 ) {
            grid.printGrid();
            System.out.println("Your selected coordinate did not have any triple to match! Try again!");
        } else {
            if ( row - coordinates[0] == 2) {
                int newScore = gridMapList.get(row).get(column).getScore() +
                                gridMapList.get(row-1).get(column).getScore() +
                                gridMapList.get(row-2).get(column).getScore();
                player.addScore(newScore);
                gridMapList = vertical.move(row, column, gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
            else if ( coordinates[0] - row == 2) {
                int newScore = gridMapList.get(coordinates[0]).get(column).getScore() +
                                gridMapList.get(coordinates[0]-1).get(column).getScore() +
                                gridMapList.get(coordinates[0]-2).get(column).getScore();
                player.addScore(newScore);
                gridMapList = vertical.move(coordinates[0], column, gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
        }
        return gridMapList;
    }


}
