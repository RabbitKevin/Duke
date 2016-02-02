
/**
 * Write a description of class MarkovRunner here.
 *
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(AbstractMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }

    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
        int seed = 20;
        //System.out.println("1");
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
        //System.out.println("2");
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        //System.out.println("3");
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        //System.out.println("4");
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

    public void compareMethod(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        int order = 2;
        MarkovModel model = new MarkovModel(2);
        EfficientMarkovModel ecModel = new EfficientMarkovModel(2);
        long start = System.nanoTime();
        runModel(model, st, size, seed);
        long end = System.nanoTime();
        long timeOne = end-start;
        start = System.nanoTime();
        runModel(ecModel, st, size, seed);
        end = System.nanoTime();
        long timeTwo = end-start;
        System.out.println("For normal model: "+timeOne+" For effective model: "+timeTwo);
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
