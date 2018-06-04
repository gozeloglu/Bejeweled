/**
 * This class stores the players in leaderboard.txt file. Also, user can add new
 * player into this class' field.
 *
 * All of players stores in ArrayList name in "leaderBoardList"
 * Also, this class provides some of mutator and accessor methods
 *
 * */


import java.util.*;

import static java.util.Collections.binarySearch;

public class LeaderBoard implements Comparable<LeaderBoard> {
    private String name;
    private int score = 0;
    private int tempScore;
    private List<LeaderBoard> leaderBoardList = new ArrayList<>();

    public LeaderBoard() {

    }

    public String getName() {
        return name;
    }

    public void addScore(int newScore) {
        this.tempScore += newScore;
    }

    public void setScore() {
        this.score = tempScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public LeaderBoard(String name, int score) {
        this.name = name;
        this.score = score;
    }


    public List<LeaderBoard> getLeaderBoardList() {
        return leaderBoardList;
    }

    public void addNewPlayer(LeaderBoard person) {
        leaderBoardList.add(person);
    }

    public void sortPlayers() {
        Collections.sort(leaderBoardList);
    }

    public int findRank(String name) {
        int index = 0;
        for ( int i = 0 ; i < leaderBoardList.size() ; i++ ) {
            if ( leaderBoardList.get(i).getName().equals(name) ) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findLowerPlayer(int index) {
        int playerScore = leaderBoardList.get(index).getScore();
        int result = leaderBoardList.size()-1;
        for ( int i = 0 ; i < leaderBoardList.size() ; i++ ) {
            if ( leaderBoardList.get(i).getScore() < playerScore ) {
                result = i;
                break;
            }
        }
        return result;
    }

    public int findHigherPlayer(int index) {
        int playerScore = leaderBoardList.get(index).getScore();
        int result = 0;
        for ( int i = leaderBoardList.size()-1 ; i >= 0 ; i-- ) {
            if ( leaderBoardList.get(i).getScore() > playerScore ) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**Prints out the ranking messages*/
    public void printRankMessage(LeaderBoard newPlayer) {
        List<Integer> scores = new ArrayList<>();

        for ( LeaderBoard leaderBoard : leaderBoardList ) {
            scores.add(leaderBoard.getScore());
        }
        int newPlayerScore = newPlayer.getScore();
        Collections.sort(leaderBoardList);
        int rank = binarySearch(scores, newPlayerScore);
        int totalPlayerNum = leaderBoardList.size();
        Collections.reverse(leaderBoardList);
        rank = totalPlayerNum - rank - 1;
        if ( findHigherPlayer(rank) == 0 ) {
            System.out.println("Your rank is " + 1 + "/" + totalPlayerNum +
                                ", your score is " + (newPlayerScore - leaderBoardList.get(findLowerPlayer(rank)).getScore())
                                + " points higher than " +
                                leaderBoardList.get(findLowerPlayer(rank)).getName());
        }
        else if ( findLowerPlayer(rank) == leaderBoardList.size()-1 ) {
            System.out.println("Your rank is " + (rank) + "/" + (totalPlayerNum) + ", your score is " +
                                (leaderBoardList.get(findHigherPlayer(rank)).getScore() - newPlayerScore) + " points lower than " +
                                leaderBoardList.get(findHigherPlayer(rank-1)).getName());
        } else {
            System.out.println("Your rank is " + (rank) + "/" + totalPlayerNum + ", your score is " +
                    (newPlayerScore - leaderBoardList.get(findLowerPlayer(rank)).getScore()) + " points higher than " +
                    leaderBoardList.get(findLowerPlayer(rank)).getName() + " and " + (leaderBoardList.get(findHigherPlayer(rank)).getScore() - newPlayerScore)
                    + " points lower than " + leaderBoardList.get(findHigherPlayer(rank)).getName());
        }
    }


    @Override
    public int compareTo(LeaderBoard o) {
        if ( this.getScore() > o.getScore() ) {
            return 1;
        }
        else if ( this.getScore() < o.getScore() ) {
            return -1;
        } else {
            return 0;
        }

    }
}