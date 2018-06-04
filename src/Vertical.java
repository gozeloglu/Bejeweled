/**
 * This class extends abstract Search class.
 * The jewel which searches the matches on vertical direction calls the methods from this class.
 * Deleting-shifting operations are being made by "move" method.
 * In order to find valid triplet, findCoordinate methods call.
 * */


import java.util.ArrayList;

public class Vertical extends Search {

    /**Searches on vertical direction, then returns row and column
     * If there is no triple matches, it returns coming coordinates in parameter.
     * This method searches only one character. (For Triangle jewel)*/
    @Override
    public int[] findCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        String character = gridMapList.get(row).get(column).getSymbol();
        int[] newCoordinates = new int[2];
        int tmpRow = row;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingUp = true, isMatchingDown = true;

        //Searches the upper part of given coordinate
        while ( isMatchingUp != false && tmpRow != 0 ) {
            if ( gridMapList.get(tmpRow-1).get(column).getSymbol().equalsIgnoreCase(character) ) {
                tmpRow--;
                counter++;
                if ( counter == 2 ) {   //If triple matches is found, breaks the while loop
                    isMatchingDown = false;
                    break;
                }
            }
            else if ( !(gridMapList.get(tmpRow-1).get(column).getSymbol().equalsIgnoreCase(character)) ) {
                isMatchingUp = false;
            }
        }

        if ( counter != 2 ) {   //Assign the counter and tmpRow variables to count number of matching
            tmpRow = row;
            counter = 0;
        }

        //Searches on lower part of given coordinates on the map
        while ( isMatchingDown != false && tmpRow != gridMapList.size()-1 ) {
            if ( gridMapList.get(tmpRow+1).get(column).getSymbol().equalsIgnoreCase(character) ) {
                tmpRow++;
                counter++;
                if ( counter == 2 ) {
                    break;
                }
            }
            else if ( !gridMapList.get(tmpRow+1).get(column).getSymbol().equalsIgnoreCase(character) ) {
                isMatchingDown = false;
                tmpRow = row;
            }
        }

        newCoordinates[0] = tmpRow;
        newCoordinates[1] = column;

        return newCoordinates;
    }

    /**Searches on the vertical direction for the math operators.
     * Searches triplet for math operators.*/
    public int[] findMathCoordinates(int row,int column,String secondCharacter, ArrayList<ArrayList<Jewel>> gridMapList) {
        String character = gridMapList.get(row).get(column).getSymbol();

        //Plus(+) or Straight Line(|) characters can make triplet. So, this array list stores these of them.
        ArrayList<String> matchedChars = new ArrayList<>();
        matchedChars.add(character);
        matchedChars.add(secondCharacter);

        initializeMathOperators();
        int[] newCoordinates = new int[2];
        int tmpRow = row;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingUp = true, isMatchingDown = true;

        while ( isMatchingUp != false && tmpRow != 0 ) {
            if ( counter == 0 && matchedChars.contains(gridMapList.get(tmpRow-1).get(column).getSymbol()) ) {   //Looks at second jewel after given coordinate
                tmpRow--;
                counter++;
            }
            else if ( counter == 1 && mathOperatorList.contains(gridMapList.get(tmpRow-1).get(column).getSymbol()) ) {  //Looks at third jewel
                tmpRow--;
                counter++;
                isMatchingDown = false;
                break;
            } else {
                isMatchingUp = false;
            }
        }

        if ( counter != 2 ) {   //Initialize the variables to searches on lower part if triplet cannot found.
            tmpRow = row;
            counter = 0;
        }

        //Searches on lower part of the given coordinate
        while ( isMatchingDown != false && tmpRow != gridMapList.size()-1 ) {
            if ( counter == 0 && matchedChars.contains(gridMapList.get(tmpRow+1).get(column).getSymbol()) ) {
                tmpRow++;
                counter++;
            }
            else if ( counter == 1 && mathOperatorList.contains(gridMapList.get(tmpRow+1).get(column).getSymbol()) ) {
                tmpRow++;
                counter++;
                break;
            } else {
                isMatchingDown = false;
            }
        }

        newCoordinates[0] = tmpRow;
        newCoordinates[1] = column;

        return newCoordinates;

    }

    /**Searches the triplet for wildcard.
     * This method searches the multi-jewels for wildcard.*/
    @Override
    public int[] findWildCardCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        String tmpChar = "";    //Holds the second jewel to compare third jewel

        //Array lists are being initialized for possible situations.
        //These arrayLists are being used for comparison.
        initializeMathOperators();
        initializeVerticalJewelList();
        initializeJewelList();

        int[] newCoordinates = new int[2];
        int tmpRow = row;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingUp = true, isMatchingDown = true;

        while ( isMatchingUp != false && tmpRow != 0 ) {
            //Searches the possible jewels which are matching on vertical direction.
            if ( counter == 0 && verticalJewelList.contains(gridMapList.get(tmpRow-1).get(column).getSymbol()) ) {
                tmpChar = gridMapList.get(tmpRow-1).get(column).getSymbol();
                tmpRow--;
                counter++;
            }
            //Searches the third jewel when second jewel is wildcard.
            else if ( counter == 1 && tmpChar.equals("W") && jewelList.contains(gridMapList.get(tmpRow-1).get(column).getSymbol()) ) {
                tmpRow--;
                counter++;
                isMatchingDown = false;
                break;
            }
            //Searches the third jewel when second jewel is triangle
            else if ( counter == 1 && tmpChar.equals("T") && gridMapList.get(tmpRow-1).get(column).getSymbol().equals("T") ) {
                tmpRow--;
                counter++;
                isMatchingDown = false;
                break;
            }
            //Searches the third jewel when second jewel is straight line or plus
            else if ( counter == 1 && (tmpChar.equals("|") || tmpChar.equals("+")) && mathOperatorList.contains(gridMapList.get(tmpRow-1).get(column).getSymbol()) ) {
                tmpRow--;
                counter++;
                isMatchingDown = false;
                break;
            } else {    //Triplet is not found
                isMatchingUp = false;
            }
        }

        if ( counter != 2 ) {   //Initialize the variables to searches lower part of the given coordinate
            tmpRow = row;
            counter = 0;
            tmpChar = "";
        }

        while ( isMatchingDown != false && tmpRow != gridMapList.size()-1 ) {
            //Searches the possible jewel which are matching on vertical direction
            if ( counter == 0 && verticalJewelList.contains(gridMapList.get(tmpRow+1).get(column).getSymbol()) ) {
                tmpChar = gridMapList.get(tmpRow+1).get(column).getSymbol();
                tmpRow++;
                counter++;
            }
            //Searches the valid third jewel when second jewel is wildcard
            else if ( counter == 1 && tmpChar.equals("W") && jewelList.contains(gridMapList.get(tmpRow+1).get(column).getSymbol()) ) {
                tmpRow++;
                counter++;
                break;
            }
            //Searches the valid third jewel when second jewel is triangle
            else if ( counter == 1 && tmpChar.equals("T") && gridMapList.get(tmpRow+1).get(column).getSymbol().equals("T") ) {
                tmpRow++;
                counter++;
                break;
            }
            //Searches the valid third jewel when second jewel is plus or straight line
            else if ( counter == 1 && (tmpChar.equals("|") || tmpChar.equals("+")) && mathOperatorList.contains(gridMapList.get(tmpRow+1).get(column).getSymbol()) ) {
                tmpRow++;
                counter++;
                break;
            } else {
                isMatchingDown = false;
            }
        }

        newCoordinates[0] = tmpRow;
        newCoordinates[1] = column;

        return newCoordinates;
    }

    /**Moves the jewel objects by given coordinate
     * After shifting all of columns and rows, it puts the Empty objects on the empty places.
     * Returns the updated grid map*/
    @Override
    public ArrayList<ArrayList<Jewel>> move(int row, int column, ArrayList<ArrayList<Jewel>> gridMap) {
        for ( int i = row ; i > 2 ; i-- ) {
            gridMap.get(i).set(column, gridMap.get(i-3).get(column));
        }
        gridMap.get(0).set(column, new Empty());
        gridMap.get(1).set(column, new Empty());
        gridMap.get(2).set(column, new Empty());

        return gridMap;
    }

}