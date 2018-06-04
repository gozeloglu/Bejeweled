/**
 * This class extends abstract Search class
 * The jewel which searches the matches on horizontal direction calls the methods from this class.
 * Deleting-shifting operations are being made by "move" method.
 * In order to find valid triplet, findCoordinate methods call.
 * */


import java.util.ArrayList;

public class Horizontal extends Search {

    /**Searches on horizontal direction, then returns row and column.
     * If there is no triple matches, it returns coming coordinates.
     * This method searches only one character. (For Square jewel)*/
    @Override
    public int[] findCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        String character = gridMapList.get(row).get(column).getSymbol();
        int[] newCoordinates = new int[2];
        int tmpColumn = column;
        int counter = 0; //Counts the number of matches
        boolean isMatchingLeft = true, isMatchingRight = true;

        //Searches the left part of given coordinate
        while ( isMatchingLeft != false && tmpColumn != 0 ) {
            if ( gridMapList.get(row).get(tmpColumn-1).getSymbol().equalsIgnoreCase(character) ) {
                tmpColumn--;
                counter++;
                if ( counter == 2 ) {   //If triple matches is found, breaks the while loop
                    isMatchingRight = false;
                    break;
                }
            }
            else if ( !gridMapList.get(row).get(tmpColumn-1).getSymbol().equalsIgnoreCase(character) ) {
                isMatchingLeft = false;
            }
        }

        if ( counter != 2 ) {   //Assign the counter and tmpColumn variables to count number of matching
            counter = 0;
            tmpColumn = column;
        }

        //Searches on right part of given coordinates on the map
        while ( isMatchingRight != false && tmpColumn != gridMapList.get(row).size()-1 ) {
            if ( gridMapList.get(row).get(tmpColumn+1).getSymbol().equalsIgnoreCase(character) ) {
                tmpColumn++;
                counter++;
                if ( counter == 2 ) {
                    break;
                }
            }
            else if ( !gridMapList.get(row).get(tmpColumn+1).getSymbol().equalsIgnoreCase(character) ) {
                isMatchingRight = false;
                tmpColumn = column;
            }
        }

        newCoordinates[0] = row;
        newCoordinates[1] = tmpColumn;

        return newCoordinates;
    }

    /**Searches on the horizontal direction for math operators.
     * Searches triplet for math operators*/
    public int[] findMathCoordinates(int row,int column,String secondCharacter, ArrayList<ArrayList<Jewel>> gridMapList) {
        String character = gridMapList.get(row).get(column).getSymbol();

        //Minus(-) and Plus(+) jewels can make triplet. So, this arrayList stores these of them.
        ArrayList<String> matchedChars = new ArrayList<>();
        matchedChars.add(character);
        matchedChars.add(secondCharacter);

        //This method adds math operators into arrayList to compare given coordinates on horizontal
        initializeMathOperators();
        int[] newCoordinates = new int[2];
        int tmpColumn = column;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingLeft = true, isMatchingRight = true;

        while ( isMatchingLeft != false && tmpColumn != 0 ) {
            if ( counter == 0 && matchedChars.contains(gridMapList.get(row).get(tmpColumn-1).getSymbol()) ) {   //Looks at second jewel after looking given coordinate
                tmpColumn--;
                counter++;
            }
            else if ( counter == 1 && mathOperatorList.contains(gridMapList.get(row).get(tmpColumn-1).getSymbol()) ) {  //Looks at third jewel
                tmpColumn--;
                counter++;
                isMatchingRight = false;
                break;
            } else {
                isMatchingLeft = false;
            }
        }

        if ( counter != 2 ) {   //Initialize the variables to search right part if triplet is not found.
            tmpColumn = column;
            counter = 0;
        }

        //Searches on the right part of the given coordinate
        while ( isMatchingRight != false && tmpColumn != gridMapList.get(row).size()-1 ) {
            if ( counter == 0 && matchedChars.contains(gridMapList.get(row).get(tmpColumn+1).getSymbol()) ) {
                tmpColumn++;
                counter++;
            }
            else if ( counter == 1 && mathOperatorList.contains(gridMapList.get(row).get(tmpColumn+1).getSymbol()) ) {
                tmpColumn++;
                counter++;
                break;
            } else {
                isMatchingRight = false;
            }
        }

        newCoordinates[0] = row;
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
        initializeHorizontalJewelList();
        initializeMathOperators();
        initializeJewelList();

        int[] newCoordinates = new int[2];
        int tmpColumn = column;
        int counter = 0;    //Counts the number of matches
        boolean isMatchingLeft = true, isMatchingRight = true;

        while ( isMatchingLeft != false && tmpColumn != 0 ) {
            //Searches the possible jewels which are matching on horizontal direction.
            if ( counter == 0 && horizontalJewelList.contains(gridMapList.get(row).get(tmpColumn-1).getSymbol()) ) {
                tmpChar = gridMapList.get(row).get(tmpColumn-1).getSymbol();
                tmpColumn--;
                counter++;
            }
            //Searches the third jewel when second jewel is wildcard.
            else if ( counter == 1 && tmpChar.equals("W") && jewelList.contains(gridMapList.get(row).get(tmpColumn-1).getSymbol()) ) {
                tmpColumn--;
                counter++;
                break;
            }
            //Searches the third jewel when second jewel is square
            else if ( counter == 1 && tmpChar.equals("S") && gridMapList.get(row).get(tmpColumn-1).getSymbol().equals("S") ) {
                tmpColumn--;
                counter++;
                break;
            }
            //Searches the third jewel when second jewel is minus or plus
            else if ( counter == 1 && (tmpChar.equals("-") || tmpChar.equals("+")) && mathOperatorList.contains(gridMapList.get(row).get(tmpColumn-1).getSymbol()) ) {
                tmpColumn--;
                counter++;
                break;
            } else {    //Triplet is not found
                isMatchingLeft = false;
            }
        }

        if ( counter != 2 ) {   //Initialize the variables to searches right part of the given coordinate
            tmpColumn = column;
            counter = 0;
            tmpChar = "";
        }

        while ( isMatchingRight != false && tmpColumn != gridMapList.get(row).size()-1 ) {
            //Searches the possible jewel which are matching on horizontal direction
            if ( counter == 0 && horizontalJewelList.contains(gridMapList.get(row).get(tmpColumn+1).getSymbol()) ) {
                tmpChar = gridMapList.get(row).get(tmpColumn+1).getSymbol();
                tmpColumn++;
                counter++;
            }
            //Searches the valid third jewel when second jewel is wildcard
            else if ( counter == 1 && tmpChar.equals("W") && jewelList.contains(gridMapList.get(row).get(tmpColumn+1).getSymbol()) ) {
                tmpColumn++;
                counter++;
                break;
            }
            //Searches the valid third jewel when second jewel is square
            else if ( counter == 1 && tmpChar.equals("S") && gridMapList.get(row).get(tmpColumn+1).getSymbol().equals("S") ) {
                tmpColumn++;
                counter++;
                break;
            }
            //Searches the valid third jewel when second jewel is plus or minus
            else if ( counter == 1 && (tmpChar.equals("-") || tmpChar.equals("+")) && mathOperatorList.contains(gridMapList.get(row).get(tmpColumn+1).getSymbol()) ) {
                tmpColumn++;
                counter++;
                break;
            } else {
                isMatchingRight = false;
            }
        }

        newCoordinates[0] = row;
        newCoordinates[1] = tmpColumn;

        return newCoordinates;
    }

    /**Moves the jewel objects by given coordinate
     * After shifting all of columns and rows, it puts the Empty objects on the empty places.
     * Returns the updated grid map*/
    @Override
    public ArrayList<ArrayList<Jewel>> move(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList) {
        for ( int i = row ; i > 0 ; i-- ) {
            gridMapList.get(i).set(column-2, gridMapList.get(i-1).get(column-2));
            gridMapList.get(i).set(column-1, gridMapList.get(i-1).get(column-1));
            gridMapList.get(i).set(column, gridMapList.get(i-1).get(column));
        }

        gridMapList.get(0).set(column-2, new Empty());
        gridMapList.get(0).set(column-1, new Empty());
        gridMapList.get(0).set(column, new Empty());

        return gridMapList;
    }
}