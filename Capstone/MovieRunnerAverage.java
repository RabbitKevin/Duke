import java.util.*;
class MovieRunnerAverage{
    public void printAverageRatings(){
        String movieFile = "data/ratedmovies_short.csv";
        String ratingFile = "data/ratings_short.csv";
        SecondRatings tmp = new SecondRatings(movieFile, ratingFile);
        System.out.println("Size of movies: "+tmp.getMovieSize());
        System.out.println("Size of raters: "+tmp.getRaterSize());
        //-------------------------------------------------------//
    }
    public void printAverageRatings(int times){
        String movieFile = "data/ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv";
        SecondRatings tmp = new SecondRatings(movieFile, ratingFile);
        ArrayList<Rating> ratings = tmp.getAverageRatings(times);
        ratings.sort(null);
        for(Rating rating : ratings){
            System.out.println(tmp.getTitle(rating.getItem())+": "+rating.getValue());
        }
        System.out.println("There has "+ratings.size()+" movies");
    }
    public void getAverageRatingOneMovie(){
        String title = "Vacation";
        String movieFile = "data/ratedmoviesfull.csv";
        String ratingFile = "data/ratings.csv";
        SecondRatings tmp = new SecondRatings(movieFile, ratingFile);
        String id = tmp.getID(title);
        if(id.equals("No such ID")){
            System.out.println("No such ID");
            return;
        }
        double rate = 0.0;
        ArrayList<Rating> list = tmp.getAverageRatings(1);
        for(Rating rating : list){
            if(rating.getItem().equals(id)){
                rate = rating.getValue();
                break;
            }
        }
        System.out.println(title+" "+rate);
    }
    public void printSimilarRatings(){
        
    }
}