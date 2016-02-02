/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 *
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;
import java.io.*;

public class BabyBirths {
	public static void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}
	public static void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		HashSet<String> boyName = new HashSet<String>();
		HashSet<String> girlName = new HashSet<String>();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			String name = rec.get(0);
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				if(!boyName.contains(name)) boyName.add(name);
			}
			else {
				totalGirls += numBorn;
				if(!girlName.contains(name)) girlName.add(name);
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("Unique boy name : "+boyName.size());
		System.out.println("Unique girl name : "+girlName.size());
		System.out.println("Total name : "+ (boyName.size()+girlName.size()));
	}
	public static int getRank(int year, String name, String gender){
		String fileName = "data/yob"+year+".csv";
		//HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		FileResource fr = new FileResource(fileName);
		int count = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)){
				count++;
				if(name.equals(rec.get(0))) return count;
			}
		}
		return -1;
	}
	public static int getRank(CSVParser parser, String name, String gender){
		int count = 0;
		for(CSVRecord rec : parser){
			if(rec.get(1).equals(gender)){
				count++;
				if(rec.get(0).equals(name)) return count;
			}
		}
		return -1;
	}
	public static String getName (int year, int rank, String gender){
		String fileName = "data/yob"+year+".csv";
		//HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		FileResource fr = new FileResource(fileName);
		int count = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)){
				count++;
				if(count == rank) return rec.get(0);
			}
		}
		return "NO NAME";
	}
	public static void whatIsNameInYear(String name, int year, int newYear, String gender){
		String oldFile = "data/yob"+year+".csv";
		String newFile = "data/yob"+newYear+".csv";
		int rank = getRank(year, name, gender);
		String newName = getName(newYear, rank, gender);
		System.out.println(name +" born in "+year +" would be "+newName+" if she was born in "+newYear);
	}
	public static int yearOfHighestRank(String name, String gender){
		DirectoryResource resource = new DirectoryResource();
		int rank = -1;
		File file = null;
        for (File f : resource.selectedFiles()) {
            CSVParser parser = new FileResource(f).getCSVParser(false);
            int tmp = getRank(parser, name, gender);
            //System.out.println(tmp);
            if(tmp != -1){
            	if(rank == -1){
            		rank = tmp;
            		file = f;
            	}
            	else{
            		if(rank > tmp){
            			rank = tmp;
            			file = f;
            		}
            	}
            }
        }
        if(rank == -1) return -1;
        String fileName = file.getName();
        int index = fileName.indexOf("yob");
        return Integer.parseInt(fileName.substring(index+3, index+7));

	}
	public static double getAverageRank(String name, String gender){
		DirectoryResource resource = new DirectoryResource();
		int count = 0;
		int sum = 0;
		File file = null;
        for (File f : resource.selectedFiles()) {
            CSVParser parser = new FileResource(f).getCSVParser(false);
            int tmp = getRank(parser, name, gender);
            if(tmp != -1){
            	count++;
            	sum+=tmp;
            }
        }
        if(count == 0) return 1.0;
        return ((double)sum)/count;
	}
	public static int getTotalBirthsRankedHigher(String name, int year, String gender){
		String fileName = "data/yob"+year+".csv";
		FileResource fr = new FileResource(fileName);
		int count = 0;
		for(CSVRecord rec : fr.getCSVParser()){
			if(rec.get(1).equals(gender)){
				if(!rec.get(0).equals(name)) count += Integer.parseInt(rec.get(2));
				else break;
			}
		}
		return count;
	}
	public static void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	public static void testGetRank() {
		System.out.println(getRank(1971, "Frank", "M"));
	}
	public static void testGetName() {
		System.out.println(getName(1982, 450, "M"));
	}
	public static void main(String[] args){
		//FileResource fr = new FileResource("data/yob1905.csv");
		//totalBirths(fr);
		//testGetRank();
		//testGetName();
		//whatIsNameInYear("Owen", 1974, 2014, "M");
		//System.out.println(yearOfHighestRank("Mich", "M"));
		//System.out.println(getAverageRank("Robert", "M"));
		System.out.println(getTotalBirthsRankedHigher("Emily", 1990, "F"));
	}
}
