
/**
 * Write a description of RaterDatabase here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;

	private static void initialize() {
	    // this method is only called from addRatings
		if (ourRaters == null) {
			ourRaters = new HashMap<String,Rater>();
		}
	}

    public static void initialize(String filename) {
 		if (ourRaters == null) {
 			ourRaters= new HashMap<String,Rater>();
 			addRatings("data/" + filename);
 		}
 	}
    /*
        @para file path of the rater collections
        Used to create a map storing the rater collection
    */
    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
        }
    }
    /*
        @para rater_ID
        @para movie_ID
        @para rate of this movie
    */
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater =  null;
                if (ourRaters.containsKey(raterID)) {
                    rater = ourRaters.get(raterID);
                }
                else {
                    rater = new EfficientRater(raterID);
                    ourRaters.put(raterID,rater);
                 }
                 rater.addRating(movieID,rating);
    }
    /*
        get specified rater object
    */
    public static Rater getRater(String id) {
    	initialize();

    	return ourRaters.get(id);
    }
    /*
        @return the value collections
    */
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());

    	return list;
    }
    /*
        @return size of raters
    */
    public static int size() {
	    return ourRaters.size();
    }



}
