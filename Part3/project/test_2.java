class test_2{
    public static void main(String[] args){
        CharactersInPlay play = new CharactersInPlay();
        play.tester();
        System.out.print("\n\n");
        int index = play.indexOfMaxFreq();
        System.out.println("Freq is "+play.frequency(index));
        System.out.println(play.word(index));
        play.charactersWithNumParts(50, 103);

    }
}