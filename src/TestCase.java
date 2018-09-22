import java.util.ArrayList;

public class TestCase {
    public static void main(String[] args){
        ArrayList<Team> teams = new ArrayList<Team>();
        SingleGenerator generator;

        for (int i = 1; i <= 20; i++) {
            teams.add(new Team(Integer.toString(i), i));
        }

        generator = new SingleGenerator(teams, true);

        Bracket bracket = generator.getBracket();

        System.out.println(bracket.getNumberOfRounds());
        System.out.println(bracket.getNumberOfTeams());
        System.out.println(bracket.getNumberOfMatchesInRound(1));
        new Display(generator.getBracket());
    }

}
