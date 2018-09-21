public class Main {
    public static void main(String[] args) throws Exception{
        String[][][][] array1 = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E"}, {"F"}}, {{"G"}, {"H"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}}}};
        String[][][][] array2 = {{{{"A"}, {"B"}}, {{"C"}, {"D"}}, {{"E"}, {"F"}}, {{"G"}, {"H"}}, {{"I"}, {"J"}}, {{"K"}, {"L"}}, {{"M"}, {"N"}}, {{"O"}, {"P"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}, {{"I", "J"}, {"K", "L"}}, {{"M", "N"}, {"O", "P"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}},{{"I", "J", "K","L"}, {"M", "N","O","P"}}},
                {{{"A", "B", "C","D", "E", "F","G","H"}, {"I", "J","K","L", "M", "N", "O", "P"}}}};
        String[][][][] array3 = {{{{"C"}, {"D"}}, {{"A"}, {"B"}}, {{"E"}, {"F"}}, {{"G"}, {"H"}}, {{"I"}, {"J"}}, {{"K"}, {"L"}}, {{"M"}, {"N"}}, {{"O"}, {"P"}}},
                {{{"A", "B"}, {"C", "D"}}, {{"E", "F"}, {"G", "H"}}, {{"I", "J"}, {"K", "L"}}, {{"M", "N"}, {"O", "P"}}},
                {{{"A", "B", "C","D"}, {"E", "F","G","H"}},{{"I", "J", "K","L"}, {"M", "N","O","P"}}},
                {{{"A", "B", "C","D", "E", "F","G","H"}, {"I", "J","K","L", "M", "N", "O", "P"}}}};

        OurBracket tournament= new OurBracket(8, array1);
        Display display = new Display(tournament);
        Thread.sleep(2000);//small delay
        display.update(new OurBracket(16, array3));
    }
}
