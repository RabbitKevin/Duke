import java.util.*;
class ThirdRatings{
    private ArrayList<Rater> myRaters;

    public ThirdRatings () {
        this("data/ratings.csv");
    }
    public ThirdRatings (String ratingsfile) {
        myRaters = new FirstRatings().loadRaters(ratingsfile);
    }
    public int getRaterSize() {
        return myRaters.size();
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());//it pass all movie.
        for(String movieID : movies){
            double average = getAverageByID(movieID, minimalRaters);
            if(average == 0.0) continue;
            ratings.add(new Rating(movieID, average));
        }
        return ratings;
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
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filter){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filter);//it pass all movie.
        for(String movieID : movies){
            double average = getAverageByID(movieID, minimalRaters);
            if(average == 0.0) continue;
            ratings.add(new Rating(movieID, average));
        }
        return ratings;
    }
}