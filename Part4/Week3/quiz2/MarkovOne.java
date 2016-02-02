import java.util.*;

public class MarkovOne extends AbstractMarkovModel{

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String toString(){
        return "MorkovModel of order "+1;
    }

    public String getRandomText(int numChars){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        int pos = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(pos,pos+1);
        sb.append(key);
        //System.out.println("The first key is: +"+key);
        for(int i=0; i < numChars-1; i++){
            ArrayList<Character> list = getFollows(key);
            if(list.size() == 0) break;
            int index = myRandom.nextInt(list.size());
            key = key.substring(1)+String.valueOf(list.get(index));
            sb.append(list.get(index));
        }
        return sb.toString();
    }
}