/**
 * This abstract class have some of abstract and helper methods
 * to make comparision and shifting operations in subclasses.
 * */


import java.util.ArrayList;

public abstract class Search {
    protected ArrayList<String> mathOperatorList = new ArrayList<>();
    protected ArrayList<String> verticalJewelList = new ArrayList<>();
    protected ArrayList<String> horizontalJewelList = new ArrayList<>();
    protected ArrayList<String> leftDiagonalJewelList = new ArrayList<>();
    protected ArrayList<String> rightDiagonalJewelList = new ArrayList<>();
    protected ArrayList<String> jewelList = new ArrayList<>();

    public void initializeMathOperators() {
        String[] mathArr = {"+","-","|", "/", "\\"};
        for ( String math : mathArr) {
            mathOperatorList.add(math);
        }
    }

    public void initializeVerticalJewelList() {
        String[] verticalArr = {"W","T","|","+"};
        for( String str : verticalArr ) {
            verticalJewelList.add(str);
        }
    }

    public void initializeHorizontalJewelList() {
        String[] horizontalArr = {"W","S","-","+"};
        for ( String str : horizontalArr ) {
            horizontalJewelList.add(str);
        }
    }

    public void initializeLeftDiagonalJewelList() {
        String[] leftDiaArr = {"\\","D","W"};
        for( String str : leftDiaArr ) {
            leftDiagonalJewelList.add(str);
        }
    }

    public void initializeRightDiagonalJewelList() {
        String[] rightDiaArr = {"/","D","W"};
        for ( String str : rightDiaArr ) {
            rightDiagonalJewelList.add(str);
        }
    }

    public void initializeJewelList() {
        String[] jewelArr = {"W","S","D","T","|","-","+","/","\\"};
        for ( String str : jewelArr ) {
            jewelList.add(str);
        }
    }
    public abstract int[] findCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList);
    public abstract  int[] findWildCardCoordinates(int row, int column, ArrayList<ArrayList<Jewel>> gridMapList);
    public abstract ArrayList<ArrayList<Jewel>> move(int row, int column, ArrayList<ArrayList<Jewel>> gridMap);
}
