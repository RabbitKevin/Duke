import edu.duke.*;
import java.util.*;

public class GladLib {
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	//Verb & Fruit
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	//Used word
	private HashSet<String> usedWords;
	//
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	/*
		initialize the arraylist of each kind of word
	*/
	
	private void initializeFromSource(String source) {
		adjectiveList = readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
		usedWords = new HashSet<String>();	
	}

	/*
		Randomly select one word from specified word category	
	*/
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	/*
		Using label to select corresponding ArrayList
	*/
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}
	/*
		If there is word waiting for processing.
		if there is "<label>" in the word, choose word from corresponding word list and replace
		the <label>.
		else return the original word
	*/
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		boolean isRepeated = true;
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = "";
		while(isRepeated){
			sub = getSubstitute(w.substring(first+1,last));
			if(!usedWords.contains(sub)){
				isRepeated = false;
				usedWords.add(sub);
			}
			//else System.out.println("The word "+sub+" is used, try next");
		}
		return prefix+sub+suffix;
	}
	/*
		Function used to console output.
		If the content exceeds the width of single line, jump to a new line
		Else output it in the current console
	*/
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
		System.out.println();
	}
	/*
		Using the template to finish a story
	*/
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	/*
		Read the txt content from source file path
		Put it into a ArrayList and return it as a result
	*/
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		//Read fron http link
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		//Read from local file path
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){//separate by white space
				list.add(line);
			}
		}
		return list;
	}
	/*
		Using the temlplate function to create a new story
	*/
	public void makeStory(){
	    System.out.println("\n");
	    usedWords.clear();
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}
}
