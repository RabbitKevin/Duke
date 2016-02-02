import java.util.*;
class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;

    public EfficientMarkovWord(int order){
        myRandom = new Random();
        myOrder = order;
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }
    private int indexOf(String[] words, WordGram target, int start){
        for(int i = start; i < words.length-target.length(); ++i){
            boolean match = true;
            for(int j = 0; j < target.length(); ++j){
                if(!words[i+j].equals(target.wordAt(j))){
                    match = false;
                    break;
                }
            }
            if(match) return i;
        }
        return -1;
    }

    private void buildMap(){
        map = new HashMap<WordGram, ArrayList<String>>();
        //System.out.println("hehe");
        for(int i = 0; i <= myText.length-myOrder; ++i){
            //System.out.println(i + " of "+myText.length);
            WordGram tmp = new WordGram(myText, i, myOrder);
            if(map.containsKey(tmp)) continue;
            //System.out.println("hehe");
            ArrayList<String> result = new ArrayList<String>();
            int j = i;
            while(j != -1 && j < myText.length-myOrder){
                j = indexOf(myText, tmp, j);
                if(j != -1){
                    result.add(myText[j+myOrder]);
                    j++;
                }
            }
            map.put(tmp, result);
            //System.out.println("hehe");
        }
    }
    public ArrayList<String> getFollows(WordGram kGram){
        String key = kGram.toString();
        if(map.containsKey(key)) return map.get(key);
        else return new ArrayList<String>();
    }

    public void printHashMapInfo(){
        System.out.println("The size of map is "+map.size());
        int max = Integer.MIN_VALUE;
        for(WordGram gram : map.keySet()){
            //System.out.print("Key: "+gram+" :");
            ArrayList<String> append = map.get(gram);
            max = Math.max(max, append.size());
            /*
            for(String str : append){
                System.out.print(" "+str);
            }
            System.out.println();
            */
        }
        System.out.println("Largest value is "+max);
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        String[] source = new String[myOrder];
        for(int i = 0; i < myOrder; ++i){
            source[i] = myText[index+i];
            sb.append(source[i]);
            sb.append(" ");
        }
        System.out.println("Test3");
        WordGram gram = new WordGram(source, 0, myOrder);
        //System.out.println("running with ");
        for(int k=0; k < numWords-myOrder; k++){
            System.out.println("Test3232");
            ArrayList<String> follows = getFollows(gram);
            System.out.println("Test4");
            if (follows.size() == 0) {
                //System.out.println("running with ");
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            gram = gram.shiftAdd(next);
        }
        System.out.println("Test5");
        return sb.toString().trim();
    }
}