import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;

public class TestCase {
    public static void main(String[] args){
        ArrayList<Team> teams = new ArrayList<Team>();
        SingleGenerator generator;

        for (int i = 1; i <= 3; i++) {
            teams.add(new Team(Integer.toString(i), i));
        }

        generator = new SingleGenerator(teams, true);

        Bracket bracket = generator.getBracket();

        //new ManagementSystem();
        Display display = new Display(generator.getBracket());
        bracket.setMatchWinner("1", 1, 1);
        bracket.setMatchWinner("2", 1, 3);
        display.update(bracket);
    }

}
