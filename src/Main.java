/**
 * Program starts to executing in this class with calling some of methods
 * from Operation class
 * */


public class Main {
    public static void main(String[] args) {
        Operation operation = new Operation();
        operation.readGridFile();
        operation.readLeaderBoardFile();
        operation.readTestCaseFile();
    }
}
