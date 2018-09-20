public class Main {
    public static void main(String[] args){
        String[][][][] array1 = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E"}, {"F"}}, {{"G", "H"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}}}};
        String[][][][] array2 = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E"}, {"F"}}, {{"G"}, {"H"}}, {{"I"}, {"J"}}, {{"K"}, {"L"}}, {{"M"}, {"N"}}, {{"O"}, {"P"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}, {{"I", "J"}, {"K", "L"}}, {{"M", "N"}, {"O", "P"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}},{{"I", "J", "K","L"}, {"M", "N","O","P"}}},
                {{{"A", "B", "C","D", "E", "F","G","H"}, {"I", "J","K","L", "M", "N", "O", "P"}}}};

        Bracket tournament= new Bracket(16, array2);
        new Display(tournament);
    }
}
