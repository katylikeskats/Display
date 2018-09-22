/**
 * [Bracket.java]
 * HARD CODED BRACKET TEST!
 * @author Katelyn Wang & Dora Su
 * September 18 2018
 */

public class Bracket {
    private int numberOfTeams;
    private String[][][][] rounds;
    public Bracket(int numberOfTeams, String[][][][] rounds){
        this.numberOfTeams = numberOfTeams;
        this.rounds = rounds;
    }

    public int getNumberOfTeams(){
        return this.numberOfTeams;
    }

    public int getNumberOfRounds(){
        return this.rounds.length;
    }

    public int getNumberOfMatchesInRound(int round){
        return this.rounds[round].length;
    }

    public String[][] getTeamsInMatch(int round, int matchNumber){
        return this.rounds[round][matchNumber];
    }
}
