import java.util.ArrayList;

public class TestCase {
    public static void main(String[] args){
        ArrayList<Team> teams = new ArrayList<Team>();
        DoubleGenerator generator;

        for (int i = 1; i <= 567; i++) {
            teams.add(new Team(Integer.toString(i), i));
        }

        generator = new DoubleGenerator(teams);

        Bracket bracket = generator.getBracket();

        new Display(generator.getBracket());

    }

}
