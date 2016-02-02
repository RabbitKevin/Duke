import edu.duke.*;
import java.io.*;

public class FindStoreAllGene{
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
    public static StorageResource storeAll(String dna){
        StorageResource resource = new StorageResource();
        String sample = dna.toUpperCase();
        int pos = 0;
        while(true){
            pos = sample.indexOf("ATG", pos);
            if(pos == -1) break;
            int right = findStopIndex(sample, pos+3);
            if(right == -1) pos+=3;
            else{
                resource.add(dna.substring(pos, right+3));
                pos = right+3;
            }
        }
        return resource;
    }
    public static void testStorageFinder(String filename){
        FileResource resource = new FileResource(filename);
        System.out.println("The # of CTG is: "+countCTG(resource.asString()));
        StorageResource dnaList = storeAll(resource.asString());
        System.out.println("The size of dna list found is "+dnaList.size());
        for(String dna : dnaList.data()){
            System.out.println(dna);
        }
        printGenes(dnaList);
    }
    public static float cgRatio(String dna){
        dna = dna.toUpperCase();
        int count = 0;
        for(int i = 0; i < dna.length(); ++i){
            char tmp = dna.charAt(i);
            if(tmp == 'G' || tmp == 'C') count++;
        }
        return ((float)count)/dna.length();
    }
    public static void printGenes(StorageResource sr){
        int countForString = 0;
        int countForRatio = 0;
        int length = 0;
        for(String dna : sr.data()){
            if(dna.length() > 60){
                System.out.println("Length > 60: "+dna);
                countForString++;
            }
            if(cgRatio(dna) > 0.35){
                System.out.println("CG-Ratio higher than 0.35: "+dna);
                countForRatio++;
            }
            length = Math.max(length, dna.length());
        }
        System.out.println("# for String lenght > 60: "+countForString);
        System.out.println("# for String with cgRaito > 0.35: "+countForRatio);
        System.out.println("Max length is "+length);
    }
    public static int countCTG(String dna){
        dna = dna.toUpperCase();
        int pos = 0;
        int count = 0;
        while(true){
            pos = dna.indexOf("CTG", pos);
            if(pos == -1) break;
            else{
                count++;
                pos+=3;
            }
        }
        return count;
    }
    public static void main(String[] args){
        testStorageFinder("data/GRch38dnapart.fa");
    }
}