import java.util.*;
import edu.duke.*;
class test{
    public static void main(String[] args){
        //new MarkovRunner().runMarkovZero();
        //testGetFollowsWithFile();
        //new MarkovRunner().runMarkovOne();
        //new MarkovRunner().runMarkovFour();
        new MarkovRunner().runMarkovModel();
    }
    public static void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<Character> result = markov.getFollows("he");
        System.out.println("Size is "+result.size());
    }
}