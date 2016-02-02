/*

*/
import edu.duke.*;
import java.util.*;
public class condon{
    private HashMap<String, Integer> map;
    public condon(){
        map = new HashMap<String, Integer>();
    }
    public void buildCodonMap(int start, String dna){
        map.clear();
        for(int i = start; i+3 <= dna.length(); i+=3){
            String condon = dna.substring(i,i+3);
            if(map.containsKey(condon)){
                int count = map.get(condon);
                map.put(condon, count+1);
            }
            else map.put(condon, 1);
        }
    }
    public String getMostCommonCodon(){
        int max = Integer.MIN_VALUE;
        String majorityCondon = "";
        for(String key : map.keySet()){
            int count = map.get(key);
            if(count > max){
                majorityCondon = key;
                max = count;
            }
        }
        return majorityCondon;
    }
    public void printCodonCounts(int start, int end){
        for(String key : map.keySet()){
            int count = map.get(key);
            if(count >= start && count <= end){
                System.out.println("Condon: "+key+" Total: "+count);
            }
        }
    }
    public int mapSize(){
        return map.size();
    }
    public int wordTimes(String str){
        return map.get(str);
    }
    public void test(){
        FileResource file = new FileResource("data/dnaMystery2.txt");
        String dna = file.asString().trim();
        for(int i = 0; i < 1; ++i){
            buildCodonMap(i, dna);
            System.out.println("In Frame "+i);
            //printCodonCounts(6, 6);
        }
    }
}