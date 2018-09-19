public class Main {
    public static void main(String[] args){
        String[][][][] array = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E", "F"}}, {{"G", "H"}},
        }, {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "G"}}
            },
        {
            {{"A", "B", "C", "D"}}, {{"E", "F", "G", "H"}}
        }};

        Bracket tournament= new Bracket(8, array);
        new Display(tournament);
    }
}
