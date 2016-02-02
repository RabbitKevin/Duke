
/**
 * Write a description of class MarkovRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    public void runModel(IMarkovModel markov, String text, int size, int seed){
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            markov.setRandom(seed);
            System.out.println("In round "+k);
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    /*
        It runs very slow, keep observing the terminal
    */
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(3);
        runModel(markovWord, st, 10, 621);
        //System.out.println("1");
        //EfficientMarkovWord effective = new EfficientMarkovWord(2);
        //effective.rintHashMapInfo();
        //System.out.println("2");
        //runModel(effective, st, 100, 42);
    }

    public void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //String st = "this is a test yes this is really a test";
        EfficientMarkovWord effective = new EfficientMarkovWord(5);
        effective.setTraining(st);
        effective.setRandom(531);
        effective.printHashMapInfo();
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

}
