/**
 * Diamond class searches on the left diagonal and right diagonal directions in order.
 * Calls the methods from LeftDiagonal or RightDiagonal classes for searching and shifting
 * operations according to which side is being catched triplet.
 * */


import java.util.ArrayList;

public class Diamond extends Jewel {
    LeftDiagonal leftDiagonal = new LeftDiagonal();
    RightDiagonal rightDiagonal = new RightDiagonal();

    public Diamond() {
        this.symbol = "D";
        this.score = 30;
    }


    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        int[] coordinates =  leftDiagonal.findCoordinates(row, column, gridMapList);    //Calls the method for searching operation

        //Calls the method for shifting operations if found triplet
        if ( Math.abs(coordinates[0] - row) != 2 ) {
            coordinates = rightDiagonal.findCoordinates(row, column, gridMapList);
            if ( Math.abs(coordinates[1] - column) != 2 ) {
                grid.printGrid();
                System.out.println("Your selected coordinate did not have any triple to match! Try again!\n");
                player.addScore(0);
            } else {    //Shifting operations are doing else block
                if ( row - coordinates[0] == 2 ){
                    int newScore = gridMapList.get(row).get(column).getScore() +
                            gridMapList.get(row-1).get(column+1).getScore() +
                            gridMapList.get(row-2).get(column+2).getScore();
                    player.addScore(newScore);
                    gridMapList = rightDiagonal.move(row, column, gridMapList);
                    grid.printGrid();
                    System.out.println("Score: " + newScore + "\n");
                }
                else if ( coordinates[0] - row == 2 ) {
                    int newScore = gridMapList.get(coordinates[0]).get(coordinates[1]).getScore() +
                                    gridMapList.get(coordinates[0]-1).get(coordinates[1]+1).getScore() +
                                    gridMapList.get(coordinates[0]-2).get(coordinates[1]+2).getScore();
                    player.addScore(newScore);
                    gridMapList = rightDiagonal.move(coordinates[0], coordinates[1], gridMapList);
                    grid.printGrid();
                    System.out.println("Score: " + newScore + "\n");
                }
            }
        } else {
            if ( row - coordinates[0] == 2 ) {
                int newScore = gridMapList.get(coordinates[0]).get(coordinates[1]).getScore() +
                                gridMapList.get(coordinates[0]+1).get(coordinates[1]+1).getScore() +
                                gridMapList.get(coordinates[0]+2).get(coordinates[1]+2).getScore();
                player.addScore(newScore);
                gridMapList = leftDiagonal.move(coordinates[0], coordinates[1], gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
            else if ( coordinates[0] - row == 2 ) {
                int newScore = gridMapList.get(row).get(column).getScore() +
                                gridMapList.get(row+1).get(column+1).getScore() +
                                gridMapList.get(row+2).get(column+2).getScore();
                player.addScore(newScore);
                gridMapList = leftDiagonal.move(row, column, gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
        }
        return gridMapList;
    }
}
