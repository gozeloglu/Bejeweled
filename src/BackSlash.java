/**
 * Backslash object searches on the left diagonal direction to match with other backslash's
 * Calls the methods from LeftDiagonal class for searching and shifting operations.
 * */


import java.util.ArrayList;

public class BackSlash extends Jewel {
    LeftDiagonal leftDiagonal = new LeftDiagonal();

    public BackSlash() {
        this.symbol = "\\";
        this.score = 20;
    }

    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        int[] coordinates = leftDiagonal.findMathCoordinates(row, column, gridMapList);     //Calls the method for searchin operation

        //Calls the method for shifting operations if found triplet
        if ( Math.abs(coordinates[0] - row) != 2 ) {
            grid.printGrid();
            System.out.println("Your selected coordinate did not have any triple to match! Try again!\n");
        } else {
            if ( row - coordinates[0] == 2 ) {
                int newScore = (gridMapList.get(coordinates[0]).get(coordinates[1]).getScore() +
                                gridMapList.get(coordinates[0]+1).get(coordinates[1]+1).getScore() +
                                gridMapList.get(coordinates[0]+2).get(coordinates[1]+2).getScore());
                player.addScore(newScore);
                gridMapList = leftDiagonal.move(coordinates[0], coordinates[1], gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
            else if ( coordinates[0] - row == 2 ) {
                int newScore = (gridMapList.get(row).get(column).getScore() +
                                gridMapList.get(row+1).get(column+1).getScore() +
                                gridMapList.get(row+2).get(column+2).getScore());
                player.addScore(newScore);
                gridMapList = leftDiagonal.move(row, column, gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
        }
        return gridMapList;
    }
}
