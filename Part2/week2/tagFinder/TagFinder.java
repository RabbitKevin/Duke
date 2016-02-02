/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
	public static String findProtein(String dna) {
		dna = dna.toUpperCase();
		int start = dna.indexOf("ATG");
		if (start == -1) {
			return "";
		}
		int stop = dna.indexOf("TAG", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		stop = dna.indexOf("TGA", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		stop = dna.indexOf("TAA", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		return "";
	}
	public static String stopCodon(String gene){
		if(gene == null || gene.length() < 3) return "";
		return gene.substring(gene.length()-3);
	}
	public static void testing() {
		String a = "AAATGCCCTAACTAGATTGAAACC";
		String ap = "atgttttaa";
		//String a = "atgcctag";
		//String ap = "";
		//String a = "ATGCCCTAG";
		//String ap = "ATGCCCTAG";
		String result = findProtein(a);
		ap = ap.toUpperCase();
		if (ap.equals(result)) {
			System.out.println("success for " + ap + " length " + ap.length());
			System.out.println("End Condon is "+stopCodon(result));
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}
	public static void realTesting() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
			String result = findProtein(s);
			System.out.println("found " + result);
		}
	}
	public static void main(String[] args){
		testing();
	}
}
