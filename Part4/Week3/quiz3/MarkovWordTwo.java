import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        String input = "this is just a test yes this is a simple test";
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        sb.append(key1);
        sb.append(" ");
        String key2 = myText[index+1];
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            //System.out.println("Key1 is "+key1+" key2 is "+key2);
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                System.out.println("Break");
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            key1 = key2;
            key2 = next;
            sb.append(next);
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public void testindexOf(){
        String input = "this is just a test yes this is a simple test";
        String[] array = input.split(" ");
        System.out.println("The index is "+indexOf(array, "", "", 0));
    }

    private int indexOf(String[] words, String target1, String target2, int start){
        for(int i = start; i < words.length-2; ++i){
            if(words[i].equals(target1) && words[i+1].equals(target2)) return i;
        }
        return -1;
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        //String input = "this is just a test yes this is a simple test";
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while(start != -1 && start < myText.length-2){
            //System.out.println("running with ");
            start = indexOf(myText, key1, key2, start);
            if(start != -1){
                follows.add(myText[start+2]);
                start++;
            }
        }
        return follows;
    }

}