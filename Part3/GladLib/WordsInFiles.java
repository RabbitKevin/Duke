/*

*/
import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles{
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File file){
        String fileName = file.getName();
        FileResource resource = new FileResource(file);
        for(String word : resource.words()){
            if(!map.containsKey(word)){
                ArrayList<String> tmp = new ArrayList<String>();
                tmp.add(fileName);
                map.put(word, tmp);
            }
            else{
                ArrayList<String> line = map.get(word);
                if(!line.get(line.size()-1).equals(fileName)) line.add(fileName);
            }
        }
    }
    private void buildWordFileMap(){
        map.clear();
        DirectoryResource resource = new DirectoryResource();
        for(File file : resource.selectedFiles()){
            addWordsFromFile(file);
        }
    }
    public int maxNumber(){
        int max = Integer.MIN_VALUE;
        for(String key : map.keySet()){
            max = Math.max(max, map.get(key).size());
        }
        return max;
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list = new ArrayList<String>();
        for(String key : map.keySet()){
            if(map.get(key).size() == number) list.add(key);
        }
        return list;
    }
    public void printFilesIn(String word){
        if(!map.containsKey(word)) System.out.println("Not exist");
        else{
            for(String file : map.get(word)){
                System.out.println(file);
            }
        }
    }
    public void tester(){
        buildWordFileMap();
        System.out.println("The max number of appereance is "+maxNumber());
        printFilesIn("tree");
        //ArrayList<String> words1 = wordsInNumFiles(7);
        //System.out.println("7 files +"+words1.size());
        //ArrayList<String> words2 = wordsInNumFiles(4);
        //System.out.println("4 files +"+words2.size());
        //for(String word : words) System.out.println(word);
    }
}