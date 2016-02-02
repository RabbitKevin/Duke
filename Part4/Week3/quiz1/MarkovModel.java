import java.util.*;
class MarkovModel{
    private String myText;
    private Random myRandom;
    //HashMap<String, ArrayList<Character>> map;

    public MarkovModel() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        //map = new HashMap<String, ArrayList<Character>>();
        //trainDic();
    }
    /*
    private void trainDic(){
        for(int i = 0; i < myText.length()-4; ++i){
            String tmp = myText.substring(i,i+4);
            if(map.containsKey(tmp)) continue;
            map.put(tmp, getFollows(tmp));
        }
    }
    */
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

    public String getRandomText(int n){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        int pos = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(pos,pos+n);
        sb.append(key);
        System.out.println("The first key is: +"+key);
        for(int i=0; i < myText.length()-n; i++){
            ArrayList<Character> list = getFollows(key);
            if(list.size() == 0) break;
            int index = myRandom.nextInt(list.size());
            key = key.substring(1)+String.valueOf(list.get(index));
            sb.append(list.get(index));
        }
        return sb.toString();
    }
}