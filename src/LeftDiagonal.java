/**
 * This class is subclass of the abstract Search class
 * Searches on left diagonal
 * If one of jewel is being has to search on left diagonal,
 * this class's methods calls
 *
 * */

import java.util.ArrayList;

public class LeftDiagonal extends Search{

    /**Searches the matching jewels on left diagonal, then returns the row and column
     * If cannot being found triple matching, method returns the coordinates which are passed as
     * parameter.*/
    @Override
    public int[] findCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        String character = gridMapList.get(row).get(column).getSymbol();
        int[] newCoordinates = new int[2];
        int tmpRow = row, tmpColumn = column;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingUp = true, isMatchingDown = true;

        //Searches the upper part of the given coordinates
        while ( isMatchingUp != false && tmpRow != 0 && tmpColumn != 0 ) {
            if ( gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol().equalsIgnoreCase(character) ) {
                tmpRow--;
                tmpColumn--;
                counter++;
                if ( counter == 2 ) {
                    isMatchingDown = false;
                    break;
                }
            }
            else if ( !gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol().equalsIgnoreCase(character) ) {
                isMatchingUp = false;
            }
        }

        if ( counter != 2 ) {   //If there is no triple matches, assigns the row, column and counter variables to search again on lower part
            tmpColumn = column;
            tmpRow = row;
            counter = 0;
        }

        //Searches on the lower part of given coordinates
        while ( isMatchingDown != false && tmpRow != gridMapList.size()-1 && tmpColumn != gridMapList.get(row).size()-1  ) {
            if ( gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol().equalsIgnoreCase(character) ) {
                tmpRow++;
                tmpColumn++;
                counter++;
                if ( counter == 2 ) {
                    break;
                }
            }
            else if ( !gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol().equalsIgnoreCase(character) ) {
                isMatchingDown = false;
                tmpRow = row;
                tmpColumn = column;
            }
        }
        newCoordinates[0] = tmpRow;
        newCoordinates[1] = tmpColumn;

        return newCoordinates;
    }


    /**Searches the triplet for math operators
     * Searches triplet for math operators*/
    public int[] findMathCoordinates(int row,int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        String character = gridMapList.get(row).get(column).getSymbol();

        //This method adds math operators into arrayList to compare given coordinates on left diagonal
        initializeMathOperators();

        int[] newCoordinates = new int[2];
        int tmpRow = row, tmpColumn = column;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingUp = true, isMatchingDown = true;


        while ( isMatchingUp != false && tmpRow != 0 && tmpColumn != 0 ) {
            if ( counter == 0 && gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol().equalsIgnoreCase(character) ) { //Looks at second jewel after looking given coordinate
                tmpRow--;
                tmpColumn--;
                counter++;
            }
            else if ( counter == 1 && mathOperatorList.contains(gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol()) ) { //Looks at third jewel
                tmpRow--;
                tmpColumn--;
                counter++;
                isMatchingDown = false;
                break;
            } else {
                isMatchingUp = false;
            }
        }

        if ( counter != 2 ) {   //Initialize the variables to search lower part if triplet is not found.
            tmpRow = row;
            tmpColumn = column;
            counter = 0;
        }

        //Searches on the lower part of the given coordinate
        while ( isMatchingDown != false && tmpRow != gridMapList.size()-1 && tmpColumn != gridMapList.get(row).size()-1) {
            if ( counter == 0 && gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol().equalsIgnoreCase(character) ) {
                tmpRow++;
                tmpColumn++;
                counter++;
            }
            else if ( counter == 1 && mathOperatorList.contains(gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol()) ) {
                tmpRow++;
                tmpColumn++;
                break;
            } else {
                isMatchingDown = false;
            }
        }

        newCoordinates[0] = tmpRow;
        newCoordinates[1] = tmpColumn;

        return newCoordinates;
    }

    /**Searches the triplet for wildcard
     * This method searches the multi-jewels for wildcard*/
    @Override
    public int[] findWildCardCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        String tmpChar = "";

        //Array lists are being initialized for possible situations.
        //These arrayLists are being used for comparison.
        initializeMathOperators();
        initializeLeftDiagonalJewelList();
        initializeJewelList();

        int[] newCoordinates = new int[2];
        int tmpRow = row, tmpColumn = column;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingUp = true, isMatchingDown = true;

        while ( isMatchingUp != false && tmpRow != 0 && tmpColumn != 0 ) {
            //Searches the possible jewels which are matching on left diagonal direction.
            if ( counter == 0 && leftDiagonalJewelList.contains(gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol()) ) {
                tmpChar = gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol();
                tmpRow--;
                tmpColumn--;
                counter++;
            }
            //Searches the third jewel when second jewel is wildcard.
            else if( counter == 1 && tmpChar.equals("W") && jewelList.contains(gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol()) ) {
                tmpRow--;
                tmpColumn--;
                counter++;
                isMatchingDown = false;
                break;
            }
            //Searches the third jewel when second jewel is diamond
            else if ( counter == 1 && tmpChar.equals("D") && gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol().equals("D") ) {
                tmpRow--;
                tmpColumn--;
                counter++;
                isMatchingDown = false;
                break;
            }
            //Searches the third jewel when second jewel is back slash
            else if ( counter == 1 && tmpChar.equals("\\") & mathOperatorList.contains(gridMapList.get(tmpRow-1).get(tmpColumn-1).getSymbol()) ) {
                tmpRow--;
                tmpColumn--;
                counter++;
                isMatchingDown = false;
                break;
            } else {    //Triplet is not found
                isMatchingUp = false;
            }
        }

        if ( counter != 2 ) {   //Initialize the variables to searches lower part of the given coordinate
            tmpRow = row;
            tmpColumn = column;
            counter = 0;
            tmpChar = "";
        }

        while ( isMatchingDown != false && tmpRow != gridMapList.size()-1 && tmpColumn != gridMapList.get(row).size()-1 ) {
            //Searches the possible jewel which are matching on left diagonal direction
            if ( counter == 0 && leftDiagonalJewelList.contains(gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol()) ) {
                tmpChar = gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol();
                tmpRow++;
                tmpColumn++;
                counter++;
            }
            //Searches the valid third jewel when second jewel is wildcard
            else if ( counter == 1 && tmpChar.equals("W") && jewelList.contains(gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol()) ) {
                tmpRow++;
                tmpColumn++;
                break;
            }
            //Searches the valid third jewel when second jewel is diamond
            else if ( counter == 1 && tmpChar.equals("D") && gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol().equals("D") ) {
                tmpRow++;
                tmpColumn++;
                counter++;
                break;
            }
            //Searches the valid third jewel when second jewel is back slash
            else if ( counter == 1 && tmpChar.equals("\\") && mathOperatorList.contains(gridMapList.get(tmpRow+1).get(tmpColumn+1).getSymbol()) ) {
                tmpRow++;
                tmpColumn++;
                counter++;
                break;
            } else {
                isMatchingDown = false;
            }
        }

        newCoordinates[0] = tmpRow;
        newCoordinates[1] = tmpColumn;

        return newCoordinates;
    }

    /**Moves the jewel objects by given coordinates
     * After shifting all of columns and rows, it puts the Empty objects on empty places
     * Returns the updated grid map*/
    @Override
    public ArrayList<ArrayList<Jewel>> move(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        for ( int i = row ; i > 0 ; i-- ) {
            gridMapList.get(i).set(column, gridMapList.get(i-1).get(column));
        }

        for ( int i = (row + 1) ; i > 0 ; i-- ) {
            gridMapList.get(i).set(column+1, gridMapList.get(i-1).get(column+1));
        }

        for ( int i = (row + 2) ; i > 0 ; i-- ) {
            gridMapList.get(i).set(column+2, gridMapList.get(i-1).get(column+2));
        }

        gridMapList.get(0).set(column, new Empty());
        gridMapList.get(0).set(column+1, new Empty());
        gridMapList.get(0).set(column+2, new Empty());

        return gridMapList;
    }
}
