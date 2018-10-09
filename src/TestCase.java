//util imports
import java.util.ArrayList;

public class TestCase {
    public static void main(String[] args){
        ArrayList<Team> teams = new ArrayList<Team>();
        SingleGenerator generator1;
       // DoubleGenerator generator2;

        for (int i = 1; i <= 19; i++) {
            teams.add(new Team(Integer.toString(i), i));
        }

        generator1 = new SingleGenerator(teams, true);
       // generator2 = new DoubleGenerator(teams);

        Bracket bracket = generator1.getBracket();

        //new ManagementSystem();
        Display display = new Display(generator1.getBracket());
        //Display display2 = new Display(generator2.getBracket());
        bracket.setMatchWinner("17", 1, 1);
        bracket.setMatchWinner("1", 2, 1);
        bracket.setMatchWinner("19", 1, 3);
        //bracket.setMatchWinner("2", 1, 3);
        display.update(bracket);
    }

}
