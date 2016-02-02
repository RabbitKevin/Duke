
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel{
    protected String myText;
    protected Random myRandom;

    public AbstractMarkovModel() {
        myRandom = new Random();
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public ArrayList<Character> getFollows(String key){
        ArrayList<Character> result = new ArrayList<Character>();
        int length = key.length();
        for(int i = myText.indexOf(key, 0); i != -1;){
            if(i+length == myText.length()) result.add(myText.charAt(0));
            else result.add(myText.charAt(i+length));
            i = myText.indexOf(key,i+1);
        }
        return result;
    }

    abstract public String getRandomText(int numChars);

}
