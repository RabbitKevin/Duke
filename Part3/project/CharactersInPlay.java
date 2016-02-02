import java.util.*;

class CharactersInPlay{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    private HashMap<String, Integer> map;
    public CharactersInPlay(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
        map = new HashMap<String, Integer>();
    }
    public void findAllCharacters(){
        myFreqs.clear();
        myWords.clear();
        map.clear();
        FileResource source = new FileResource("text/errors.txt");
        for(String word : source.lines()){
            word = word.toLowerCase();
            int index = word.indexOf('.');
            if(index == -1) continue;
            word = word.substring(0,index);
            word = word.trim();
            update(word);
        }
    }
    private void update(String person){
        if(map.containsKey(person)){
            int index = map.get(person);
            int count = myFreqs.get(index);
            myFreqs.set(index, count+1);
        }
        else{
            map.put(person, myWords.size());
            myWords.add(person);
            myFreqs.add(1);
        }
    }
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < myFreqs.size(); ++i){
            System.out.println("Times: "+myFreqs.get(i)+" Word: "+myWords.get(i));
        }
    }
    public void charactersWithNumParts(int num1, int num2){
        for(int i = 0; i < myFreqs.size(); ++i){
            if(myFreqs.get(i) >= num1 &&  myFreqs.get(i) <= num2){
                System.out.println("Times: "+myFreqs.get(i)+" Word: "+myWords.get(i));
            }
        }
    }
    public int indexOfMaxFreq(){
        int index = -1;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < myFreqs.size(); ++i){
            if(myFreqs.get(i) > max){
                max = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }
    public int frequency(int index){
        return myFreqs.get(index);
    }
    public String word(int index){
        return myWords.get(index);
    }
}