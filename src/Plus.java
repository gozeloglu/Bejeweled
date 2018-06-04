/**
 * Plus class searches on vertically and horizontally in order.
 * Calls the methods from vertical and horizontal class for searching and shifting operations.
 * Plus object only matches with plus characters.
 * */


import java.util.ArrayList;

public class Plus extends Jewel {
    Horizontal horizontal = new Horizontal();
    Vertical vertical = new Vertical();

    public Plus() {
        this.symbol = "+";
        this.score = 20;
    }


    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        int[] coordinates = horizontal.findMathCoordinates(row, column, "-", gridMapList);   //Calls the method for searching operations

        //Calls the method for shifting operations if found triplet
        if (Math.abs(coordinates[1] - column) != 2) {
            coordinates = vertical.findMathCoordinates(row, column, "|", gridMapList);
            if (Math.abs(coordinates[0] - row) != 2) {
                grid.printGrid();
                System.out.println("Your selected coordinate did not have any triple to match! Try again!\n");
            } else {
                if (row - coordinates[0] == 2) {
                    int newScore = gridMapList.get(row).get(column).getScore() +
                                    gridMapList.get(row-1).get(column).getScore() +
                                    gridMapList.get(row-2).get(column).getScore();
                    player.addScore(newScore);
                    gridMapList = vertical.move(row, column, gridMapList);
                    grid.printGrid();
                    System.out.println("Score: " + newScore + "\n");
                } else if (coordinates[0] - row == 2) {
                    int newScore = gridMapList.get(coordinates[0]).get(column).getScore() +
                                    gridMapList.get(coordinates[0]-1).get(column).getScore() +
                                    gridMapList.get(coordinates[0]-2).get(column).getScore();
                    player.addScore(newScore);
                    gridMapList = vertical.move(coordinates[0], column, gridMapList);
                    grid.printGrid();
                    System.out.println("Score: " + newScore + "\n");
                }
            }
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
