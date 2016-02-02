import edu.duke.*;
import java.util.*;
class test{
    public static void main(String[] args){
        //new MarkovRunner().runMarkov();
        //System.out.println("a");
        new MarkovRunner().testHashMap();
    }
    public static void partOneTest(){
        String[] original  = {"a", "b", "c", "d"};
        WordGram gramOne = new WordGram(original, 0, 4);
        WordGram gramTwo = gramOne.shiftAdd("abc");
        System.out.println(gramOne.toString());
        System.out.println(gramTwo.toString());
    }
}