/**
 * This class reads the files and calls the methods according to
 * file and operation
 * */



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Operation {
    LeaderBoard player = new LeaderBoard();
    Grid grid = new Grid();
    File file = new File("leaderboard.txt");

    /**Reads grid map and stores the all of jewels in gridMapList as an object*/
    public void readGridFile() {
        try {
            File file = new File("gameGrid.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            try {
                while ( (line = br.readLine()) != null ) {
                    ArrayList<Jewel> tmpGridArr = new ArrayList<>();
                    for ( int i = 0 ; i < line.split(" ").length ; i++ ) {
                        if ( line.split(" ")[i].equalsIgnoreCase("T") ) {
                            tmpGridArr.add(new Triangle());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("S") ) {
                            tmpGridArr.add(new Square());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("D") ) {
                            tmpGridArr.add(new Diamond());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("W") ) {
                            tmpGridArr.add(new WildCard());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("+") ) {
                            tmpGridArr.add(new Plus());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("-") ) {
                            tmpGridArr.add(new Minus());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("/") ) {
                            tmpGridArr.add(new Slash());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("\\") ) {
                            tmpGridArr.add(new BackSlash());
                        }
                        else if ( line.split(" ")[i].equalsIgnoreCase("|") ) {
                            tmpGridArr.add(new StraightLine());
                        }
                    }
                    grid.addNewLine(tmpGridArr);
                }
                br.close();
            } catch (IOException e) {
                System.out.println("IOException handled while reading Grid Map file!");
                exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Grid Map file could not found!");
            exit(-1);
        }

    }

    /**Reads the leaderboard.txt file and stores the player informations into arrayList*/
    public void readLeaderBoardFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            try {
                while ( (line = br.readLine()) != null ) {
                    String[] tmpLine = line.split(" ");
                    player.addNewPlayer(new LeaderBoard(tmpLine[0], Integer.parseInt(tmpLine[1])));
                }
                br.close();
            } catch (IOException e) {
                System.out.println("IOException handled while reading LeaderBoard file!");
                exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("LeaderBoard file could not found!");
            exit(-1);
        }
    }

    /**Reads the test case files until "E" character entered.
     * Until the "E" char program reads the lines and calls the findPlace() method*/
    public void readTestCaseFile() {
        Scanner input = new Scanner(System.in);
        String line;
        System.out.println("Game grid:");
        grid.printGrid();
        printMenuMessage();
        while( input.hasNext() ) {
            line = input.nextLine();
            if ( line.split(" ").length > 1 ) {
                String[] coordinates = line.split(" ");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                System.out.println(coordinates[0] + " " + coordinates[1]);
                grid.findPlace(x, y, player, grid);

                player.setScore();
                printMenuMessage();
            }
            else if ( line.split(" ")[0].length() > 1 ) {
                player.setName(line.split(" ")[0]);
                player.addNewPlayer(player);
                player.sortPlayers();

                System.out.println(player.getName());
                player.printRankMessage(player);
                System.out.println("Good bye!");
                updateLeaderBoardFile();
                exit(-1);
            }
            else if ( line.split(" ")[0].equalsIgnoreCase("E") ) {
                System.out.println("E\n");
                System.out.println("Total score: " + player.getScore() + "\n");
                System.out.print("Enter name: ");
            } else {
                continue;
            }
        }
        input.close();
    }

    public void printMenuMessage() {
        System.out.print("Select coordinate or enter E to end the game: ");
    }

    public void updateLeaderBoardFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for ( LeaderBoard newPlayer : player.getLeaderBoardList() ) {
                bw.write(newPlayer.getName() + " " + newPlayer.getScore() + "\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Leader Board File could not updated!");
            exit(-1);
        }
    }
}
