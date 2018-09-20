public class Main {
    public static void main(String[] args) throws Exception{
        String[][][][] array1 = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E"}, {"F"}}, {{"G"}, {"H"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}}}};
        String[][][][] array2 = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E"}, {"F"}}, {{"G"}, {"H"}}, {{"I"}, {"J"}}, {{"K"}, {"L"}}, {{"M"}, {"N"}}, {{"O"}, {"P"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}, {{"I", "J"}, {"K", "L"}}, {{"M", "N"}, {"O", "P"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}},{{"I", "J", "K","L"}, {"M", "N","O","P"}}},
                {{{"A", "B", "C","D", "E", "F","G","H"}, {"I", "J","K","L", "M", "N", "O", "P"}}}};

        Bracket tournament= new Bracket(16, array2);
        Display display = new Display(tournament);
        //Thread.sleep(2000);//small delay
        //display.update(new Bracket(8, array1));
    }
}
