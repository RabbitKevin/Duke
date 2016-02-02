import java.util.*;
class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order){
        myRandom = new Random();
        myOrder = order;
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
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
    public ArrayList<String> getFollows(WordGram kGram){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < myText.length - kGram.length(); ++i){
            int index = indexOf(myText, kGram, i);
            if(index == -1) continue;
            result.add(myText[index+kGram.length()]);
        }
        return result;
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