import java.util.*;
class FourthRatings{
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
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for(Rater rater : raters){
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
        ArrayList<String> movies = MovieDatabase.filterBy(filter);
        for(String movieID : movies){
            double average = getAverageByID(movieID, minimalRaters);
            if(average == 0.0) continue;
            ratings.add(new Rating(movieID, average));
        }
        return ratings;
    }
    public int getRaterSize() {
        return RaterDatabase.size();
    }
    /*
        @para id for target client
        returns sorted list of those cllient similar with this id.
    */
    public ArrayList<Rating> getSimilarities(String ID){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rater> allClient = RaterDatabase.getRaters();
        Rater referenceClient = RaterDatabase.getRater(ID);
        for(Rater rater : allClient){
            if(rater.getID().equals(ID)) continue;
            double similarity = dotProduct(referenceClient, rater);
            if(similarity > 0.0){
                result.add(new Rating(rater.getID(), similarity));
            }
        }
        result.sort(null);
        result.sort(Collections.reverseOrder());
        return result;
    }
    /*
        @para id for target client
        @para # of similar client used
        @para # of mininal rating for suggested movie
        returns sorted list of those cllient similar with this id.
    */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similarClient = getSimilarities(id);
        ArrayList<Rating> topRatedClient = new ArrayList<Rating>();
        for(int i = 0; i < numSimilarRaters && i < similarClient.size(); ++i){
            topRatedClient.add(similarClient.get(i));
        }
        int total = topRatedClient.size();
        //-------------------------------//
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID: movies){
            int count = 0;
            double sum = 0.0;
            for(Rating rating : topRatedClient){
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if(rater.hasRating(movieID)){
                    sum+=rating.getValue()*rater.getRating(movieID);
                    count++;
                }
            }
            if(count >= minimalRaters){
                double weightRating = sum/count;
                result.add(new Rating(movieID, weightRating));
            }
        }
        result.sort(null);
        result.sort(Collections.reverseOrder());
        return result;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter){
        ArrayList<Rating> similarClient = getSimilarities(id);
        ArrayList<Rating> topRatedClient = new ArrayList<Rating>();
        for(int i = 0; i < numSimilarRaters && i < similarClient.size(); ++i){
            topRatedClient.add(similarClient.get(i));
        }
        //-------------------------------//
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filter);
        for(String movieID: movies){
            int count = 0;
            double sum = 0.0;
            for(Rating rating : topRatedClient){
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if(rater.hasRating(movieID)){
                    sum+=rating.getValue()*rater.getRating(movieID);
                    count++;
                }
            }
            if(count >= minimalRaters){
                double weightRating = sum/count;
                result.add(new Rating(movieID, weightRating));
            }
        }
        result.sort(null);
        result.sort(Collections.reverseOrder());
        return result;
    }
    /*
        @para id for target client
        returns sorted list of those cllient similar with this id.
    */
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> rateList = me.getItemsRated();
        double count = 0.0;
        for(String item : rateList){
            if(r.hasRating(item)){
                count+=(me.getRating(item)-5)*(r.getRating(item)-5);
            }
        }
        return count;
    }
}