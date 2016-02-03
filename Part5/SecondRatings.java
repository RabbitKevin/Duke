
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
    }
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings tmp = new FirstRatings();
        myMovies = tmp.loadMovies(moviefile);
        myRaters = tmp.loadRaters(ratingsfile);
    }
    public int  getMovieSize() {
        return myMovies.size();
    }
    public int getRaterSize() {
        return myRaters.size();
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(Movie movie : myMovies){
            String id = movie.getID();
            double average = getAverageByID(id, minimalRaters);
            if(average != 0.0){
                ratings.add(new Rating(id, average));
            }
        }
        return ratings;
    }
    public String getTitle(String id){
        for(Movie movie : myMovies){
            if(movie.getID().equals(id)) return movie.getTitle();
        }
        return "No such movie";
    }
    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title)) return movie.getID();
        }
        return "No such ID";
    }
    private double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double total = 0.0;
        for(Rater rater : myRaters){
            if(rater.hasRating(id)){
                total+=rater.getRating(id);
                count++;
            }
        }
        if(count < minimalRaters) return 0.0;
        return total/count;
    }
}
