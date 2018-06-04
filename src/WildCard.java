/**
* This jewel searches (2 and 8), (4 and 6),
* (1 and 9) and (3 and 7).
* When coordinate matches with wildcard, this class's
* shift method calls and implements.
*
* */


import java.util.ArrayList;

public class WildCard extends Jewel {
    Vertical vertical = new Vertical();
    Horizontal horizontal = new Horizontal();
    LeftDiagonal leftDiagonal = new LeftDiagonal();
    RightDiagonal rightDiagonal = new RightDiagonal();

    public WildCard() {
        this.symbol = "W";
        this.score = 10;
    }


    @Override
    public ArrayList<ArrayList<Jewel>> shift(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList, LeaderBoard player, Grid grid) {
        int[] coordinates = vertical.findWildCardCoordinates(row, column, gridMapList);

        if ( Math.abs(coordinates[0] - row) != 2 ) {
            coordinates = horizontal.findWildCardCoordinates(row, column, gridMapList);

            if ( Math.abs(coordinates[1] - column) != 2 ) {
                coordinates = leftDiagonal.findWildCardCoordinates(row, column, gridMapList);

                if ( Math.abs(coordinates[0] - row) != 2 ) {
                    coordinates = rightDiagonal.findWildCardCoordinates(row, column, gridMapList);

                    if ( Math.abs(coordinates[0] - row) != 2 ) {
                        grid.printGrid();
                        System.out.println("Your selected coordinate did not have any triple to match! Try again!");
                    } else {
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
        } else {
            if ( row - coordinates[0] == 2 ) {
                int newScore = gridMapList.get(row).get(column).getScore() +
                                gridMapList.get(row-1).get(column).getScore() +
                                gridMapList.get(row-2).get(column).getScore();
                player.addScore(newScore);
                gridMapList = vertical.move(row, column, gridMapList);
                grid.printGrid();
                System.out.println("Score: " + newScore + "\n");
            }
            else if ( coordinates[0] - row == 2 ) {
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
