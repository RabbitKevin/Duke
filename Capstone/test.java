import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

class test{
    public static void main(String[] args){
        MovieRunnerSimilarRatings test = new MovieRunnerSimilarRatings();
        //test.printAverageRatingsByDirectors("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack", 4);
        //test.printAverageRatingsByYearAfterAndGenre(1990, "Drama", 8);
        //test.printAverageRatingsByDirectorsAndMinutes(90, 180, "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 3);
        //test.getAverageRatingOneMovie();
        //test.printAverageRatings(35);
        //test.printSimilarRatings();
        //test.printSimilarRatingsByGenre();
        //test.printSimilarRatingsByDirector();
        //test.printSimilarRatingsByGenreAndMinutes();
        test.printSimilarRatingsByYearAfterAndMinutes();
        /*
        ThirdRatings thirdRating = new ThirdRatings(rate);
        ArrayList<Rating> result = thirdRating.getAverageRatings(3);
        for(Rating rating : result){
            System.out.println(rating);
        }
        */
    }
}