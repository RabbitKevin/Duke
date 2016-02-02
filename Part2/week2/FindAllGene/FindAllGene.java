import edu.duke.*;
import java.io.*;

public class FindAllGene{
    public static int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("TGA", index);
        if(stop1 == -1 || ((stop1-index) % 3 != 0)) stop1 = dna.length();
        int stop2 = dna.indexOf("TAA", index);
        if(stop2 == -1 || ((stop2-index) % 3 != 0)) stop2 = dna.length();
        int stop3 = dna.indexOf("TAG", index);
        if(stop3 == -1 || ((stop3-index) % 3 != 0)) stop3 = dna.length();
        int stop = Math.min(stop1, Math.min(stop2, stop3));
        return stop == dna.length()?-1:stop;
    }
    public static void printAll(String dna){
        System.out.println("DNA String is ");
        System.out.println(dna);
        String sample = dna.toUpperCase();
        int pos = 0;
        System.out.println("Gene found is:");
        while(true){
            pos = sample.indexOf("ATG", pos);
            if(pos == -1) break;
            int right = findStopIndex(sample, pos+3);
            if(right == -1) pos+=3;
            else{
                System.out.println(dna.substring(pos, right+3));
                pos = right+3;
            }
        }
    }
    public static void testFinder(String dna){
        printAll(dna);
    }
    public static void main(String[] args){
        testFinder("ATGAAATGAAAA");
        testFinder("ccatgccctaataaatgtctgtaatgtaga");
        testFinder("CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA");
    }
}