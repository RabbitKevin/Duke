import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

class test{
    public static void main(String[] args){
        //String movie = "data/ratedmoviesfull.csv";
        //String rate = "data/ratings.csv";
        //FirstRatings rating = new FirstRatings();
        //ArrayList<Movie> movies = rating.loadMovies(movie);
        //rating.testLoadMovies(movies);
        //ArrayList<Rater> raters = rating.loadRaters(rate);
        //rating.testLoadRaters(raters);
        MovieRunnerAverage test = new MovieRunnerAverage();
        test.printAverageRatings(12);
        //test.getAverageRatingOneMovie();
    }
}