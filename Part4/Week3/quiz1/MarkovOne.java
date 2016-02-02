import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    HashMap<Character, ArrayList<Character>> map;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        map = new HashMap<Character, ArrayList<Character>>();
        trainDic();
    }

    private void trainDic(){
        for(int i = 0; i < myText.length(); ++i){
            char tmp = myText.charAt(i);
            if(map.containsKey(tmp)) continue;
            map.put(tmp, getFollows(String.valueOf(tmp)));
        }
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

    public String getRandomText(){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        char key = myText.charAt(myRandom.nextInt(myText.length()-1));
        sb.append(key);
        System.out.println("The first key is: +"+key);
        for(int i=0; i < myText.length()-1; i++){
            ArrayList<Character> list = map.get(key);
            int index = myRandom.nextInt(list.size());
            key = list.get(index);
            sb.append(key);
        }
        return sb.toString();
    }
}