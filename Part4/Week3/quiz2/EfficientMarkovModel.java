import java.util.*;

class EfficientMarkovModel extends AbstractMarkovModel{
    HashMap<String,ArrayList<Character>> map;
    int n;
    public EfficientMarkovModel(int n) {
        myRandom = new Random();
        this.n = n;
    }

    public void setTraining(String s){
        myText = s.trim();
        map = new HashMap<String, ArrayList<Character>>();
        trainDic();
        printHashMapInfo();
    }

    public String toString(){
        return "EffectiveMorkovModel of order "+n;
    }

    private void trainDic(){
        for(int i = 0; i <= myText.length()-n; ++i){
            String tmp = myText.substring(i, i+n);
            if(!map.containsKey(tmp)) map.put(tmp, new ArrayList<Character>());
            ArrayList<Character> list = map.get(tmp);
            int index = i+n;
            if(index == myText.length()) index = 0;
            list.add(myText.charAt(index));
        }
    }

    public ArrayList<Character> getFollows(String key){
        if(map.containsKey(key)) return map.get(key);
        else return new ArrayList<Character>();
    }

    public void printHashMapInfo(){
        System.out.println("Key size is "+map.size());
        int count = 0;
        for(String key : map.keySet()){
            count = Math.max(count, map.get(key).size());
        }
        System.out.println("Largest size is "+count);
    }

    public String getRandomText(int numChars){
        if (myText == null) return "";
        StringBuilder sb = new StringBuilder();
        int pos = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(pos,pos+n);
        sb.append(key);
        //System.out.println("The first key is: +"+key);
        //System.out.println("Size is "+numChars);
        for(int i=0; i < numChars-n; i++){
            ArrayList<Character> list = getFollows(key);
            if(list.size() == 0){
                //System.out.println("End when i is "+i);
                break;
            }
            int index = myRandom.nextInt(list.size());
            key = key.substring(1)+String.valueOf(list.get(index));
            //System.out.println("Append : "+list.get(index));
            sb.append(list.get(index));
        }
        return sb.toString();
    }
}