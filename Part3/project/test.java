class test{
    public static void main(String[] args){
        WordFrequencies freq = new WordFrequencies();
        //freq.findUnique();
        //System.out.println("The max index points to "+freq.findIndexOfMax());
        freq.tester();
        System.out.println("\n\n\n"+freq.numOfWords());
        int index = freq.findIndexOfMax();
        System.out.println(freq.word(index));
        System.out.println(freq.frequency(index));
        System.out.println("The size of words is "+freq.numOfWords());
    }
}