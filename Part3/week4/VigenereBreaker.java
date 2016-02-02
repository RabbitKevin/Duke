import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public static String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    public static int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; ++i){
            String slice = sliceString(encrypted, i, klength);
            key[i] = cracker.getKey(slice);
        }
        return key;
    }
    public static HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict = new HashSet<String>();
        for(String line : fr.lines()){
            line = line.trim().toLowerCase();//lower case
            if(!dict.contains(line)) dict.add(line);
        }
        return dict;
    }
    public static int countWords(String message, HashSet<String> dict){
        String[] words = message.split("\\W");
        int count = 0;
        for(int i = 0; i < words.length; ++i){
            if(dict.contains(words[i].toLowerCase())) count++;
        }
        //System.out.println("The count # is "+count);
        return count;
    }
    public static String breakForLanguage(String encrypted, HashSet<String> dict, char mostCommon){
        String result = "";
        int max = 0;
        int index = -1;
        for(int i = 1; i <= 100; ++i){
            int[] keys = tryKeyLength(encrypted, i, mostCommon);
            String output = new VigenereCipher(keys).decrypt(encrypted);
            int count = countWords(output, dict);
            //System.out.println("Key length : "+i+" The count # is "+count);
            if(count > max){
                result = output;
                max = count;
                index = i;
            }
        }
        //System.out.println("The key length is "+index);
        //System.out.println("Valid number is  "+max);
        return result;
    }
    public static char mostCommonCharIn(HashSet<String> dict){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(String word : dict){
            for(int i = 0; i < word.length(); ++i){
                char tmp = word.charAt(i);
                if(map.containsKey(tmp)) map.put(tmp, map.get(tmp)+1);
                else map.put(tmp, 1);
            }
        }
        int max = Integer.MIN_VALUE;
        char common = 'e';
        for(Character x : map.keySet()){
            int value = map.get(x);
            if(value > max){
                max = value;
                common = x;
            }
        }
        return common;
    }
    public static void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> dicts){
        String finalLanguage = "";
        String finalResult = "";
        int number = 0;
        for(String language : dicts.keySet()){
            HashSet<String> dict = dicts.get(language);
            char mostCommon = mostCommonCharIn(dict);
            String result = breakForLanguage(encrypted, dict, mostCommon);//most match one in this language
            int count = countWords(result, dict);
            if(count > number){
                number = count;
                finalLanguage = language;
                finalResult = result;
            }
        }
        System.out.println("The language is "+finalLanguage);
        System.out.print("\n\n\n\n");
        System.out.println(finalResult);
    }
    public static void breakVigenere () {
        String decrypt = new FileResource("messages/secretmessage4 .txt").asString();
        HashMap<String, HashSet<String>> dicts = new HashMap<String, HashSet<String>>();
        dicts.put("English", readDictionary(new FileResource("dictionaries/English")));
        dicts.put("Dutch", readDictionary(new FileResource("dictionaries/Dutch")));
        dicts.put("Danish", readDictionary(new FileResource("dictionaries/Danish")));
        dicts.put("French", readDictionary(new FileResource("dictionaries/French")));
        dicts.put("German", readDictionary(new FileResource("dictionaries/German")));
        dicts.put("Italian", readDictionary(new FileResource("dictionaries/Italian")));
        dicts.put("Portuguese", readDictionary(new FileResource("dictionaries/Portuguese")));
        dicts.put("Spanish", readDictionary(new FileResource("dictionaries/Spanish")));
        //----------------//
        breakForAllLanguages(decrypt, dicts);
    }
    public static void main (String[] args){
        breakVigenere();
    }
    private static String readFile(){
        FileResource resource = new FileResource("messages/secretmessage1.txt");
        return resource.asString();
    }
}
