import java.util.*;
class MovieRunnerSimilarRatings{
    public void printAverageRatings(int times) {
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        FourthRatings tmp = new FourthRatings();
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        ArrayList<Rating> ratings = tmp.getAverageRatings(times);
        ratings.sort(null);

        for(Rating rating : ratings){
            System.out.println(MovieDatabase.getTitle(rating.getItem())+": "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
    public void printAverageRatingsByYearAfterAndGenre(int year, String genre, int times) {
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        FourthRatings tmp = new FourthRatings();
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        //------Create allFilter------//
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(year));
        allFilter.addFilter(new GenreFilter(genre));
        ArrayList<Rating> ratings = tmp.getAverageRatingsByFilter(times, allFilter);
        ratings.sort(null);

        for(Rating rating : ratings){
            String item = rating.getItem();
            System.out.println(MovieDatabase.getTitle(item)+" : "+MovieDatabase.getYear(item)+" : "+MovieDatabase.getGenres(item)+" : "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
    public void printSimilarRatings(){
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings tmp = new FourthRatings();
        //-------------------------------------//
        ArrayList<Rating> suggestedRating = tmp.getSimilarRatings("71",20,5);
        for(Rating rating : suggestedRating){
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printSimilarRatingsByGenre(){
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings tmp = new FourthRatings();
        //-------------------------------------//
        ArrayList<Rating> suggestedRating = tmp.getSimilarRatingsByFilter("964",20,5, new GenreFilter("Mystery"));
        for(Rating rating : suggestedRating){
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printSimilarRatingsByDirector(){
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings tmp = new FourthRatings();
        //-------------------------------------//
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        ArrayList<Rating> suggestedRating = tmp.getSimilarRatingsByFilter("120",10,2, new DirectorsFilter(directors));
        for(Rating rating : suggestedRating){
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings tmp = new FourthRatings();
        //--------//
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(80, 160));
        allFilter.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> suggestedRating = tmp.getSimilarRatingsByFilter("168",10,3, allFilter);
        for(Rating rating : suggestedRating){
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
        String ratingFile = "ratings.csv";
        RaterDatabase.initialize(ratingFile);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings tmp = new FourthRatings();
        //--------//
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(70, 200));
        allFilter.addFilter(new YearAfterFilter(1975));
        ArrayList<Rating> suggestedRating = tmp.getSimilarRatingsByFilter("314",10,5, allFilter);
        for(Rating rating : suggestedRating){
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
        }
    }
}