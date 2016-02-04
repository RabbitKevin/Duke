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
        MovieRunnerWithFilters test = new MovieRunnerWithFilters();
        //test.printAverageRatingsByYear(2000, 20);
        //test.printAverageRatingsByGenre("Comedy", 20);
        //test.printAverageRatingsByMinutes(105,135,5);
        test.printAverageRatingsByDirectors("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack", 4);
        //test.printAverageRatingsByYearAfterAndGenre(1990, "Drama", 8);
        //test.printAverageRatingsByDirectorsAndMinutes(90, 180, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 3);
        //test.getAverageRatingOneMovie();
        //test.printAverageRatings(35);
        /*
        ThirdRatings thirdRating = new ThirdRatings(rate);
        ArrayList<Rating> result = thirdRating.getAverageRatings(3);
        for(Rating rating : result){
            System.out.println(rating);
        }
        */
    }
}