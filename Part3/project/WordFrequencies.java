import java.util.*;

class WordFrequencies{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        myFreqs.clear();
        myWords.clear();
        FileResource source = new FileResource("text/errors.txt");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String word : source.words()){
            word = word.toLowerCase();
            if(map.containsKey(word)){
                int index = map.get(word);
                int count = myFreqs.get(index);
                myFreqs.set(index, count+1);
            }
            else{
                map.put(word, myWords.size());
                myWords.add(word);
                myFreqs.add(1);
            }
        }
    }
    public void tester(){
        findUnique();
        for(int i = 0; i < myFreqs.size(); ++i){
            System.out.println("Times: "+myFreqs.get(i)+" Word: "+myWords.get(i));
        }
    }
    public int findIndexOfMax(){
        int max = Integer.MIN_VALUE;
        int index = -1;
        for(int i = 0; i < myFreqs.size(); ++i){
            if(myFreqs.get(i) > max){
                index = i;
                max = myFreqs.get(i);
            }
        }
        return index;
    }
    public int numOfWords(){
        return myWords.size();
    }
    public String word(int index){
        return myWords.get(index);
    }
    public int frequency(int index){
        return myFreqs.get(index);
    }
}