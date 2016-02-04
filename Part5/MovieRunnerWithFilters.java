import java.util.*;
class MovieRunnerWithFilters{
    public void printAverageRatings(int times) {
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
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
    public void printAverageRatingsByYear(int year, int times) {
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        ArrayList<Rating> ratings = tmp.getAverageRatingsByFilter(times, new YearAfterFilter(year));
        ratings.sort(null);

        for(Rating rating : ratings){
            String item = rating.getItem();
            System.out.println(MovieDatabase.getYear(item)+": "+MovieDatabase.getTitle(item)+" : "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
    public void printAverageRatingsByGenre(String genre, int times) {
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        ArrayList<Rating> ratings = tmp.getAverageRatingsByFilter(times, new GenreFilter(genre));
        ratings.sort(null);

        for(Rating rating : ratings){
            String item = rating.getItem();
            System.out.println(MovieDatabase.getYear(item)+": "+MovieDatabase.getTitle(item)+" : "+MovieDatabase.getGenres(item)+" : "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
    public void printAverageRatingsByMinutes(int min, int max, int times) {
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        ArrayList<Rating> ratings = tmp.getAverageRatingsByFilter(times, new MinutesFilter(min, max));
        ratings.sort(null);

        for(Rating rating : ratings){
            String item = rating.getItem();
            System.out.println(MovieDatabase.getTitle(item)+" : "+MovieDatabase.getMinutes(item)+" : "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
    public void printAverageRatingsByDirectors(String director, int times) {
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        ArrayList<Rating> ratings = tmp.getAverageRatingsByFilter(times, new DirectorsFilter(director));
        ratings.sort(null);

        for(Rating rating : ratings){
            String item = rating.getItem();
            System.out.println(MovieDatabase.getTitle(item)+" : "+MovieDatabase.getDirector(item)+" : "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
    public void printAverageRatingsByYearAfterAndGenre(int year, String genre, int times) {
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
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
    public void printAverageRatingsByDirectorsAndMinutes (int min, int max, String director, int times){
        String ratingFile = "data/ratings.csv";
        ThirdRatings tmp = new ThirdRatings(ratingFile);
        System.out.println("Raters: "+tmp.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase: "+MovieDatabase.size());
        //------Create allFilter------//
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(min, max));
        allFilter.addFilter(new DirectorsFilter(director));
        ArrayList<Rating> ratings = tmp.getAverageRatingsByFilter(times, allFilter);
        ratings.sort(null);

        for(Rating rating : ratings){
            String item = rating.getItem();
            System.out.println(MovieDatabase.getTitle(item)+" : "+MovieDatabase.getMinutes(item)+" : "+MovieDatabase.getDirector(item)+" : "+rating.getValue());
        }

        System.out.println("There has "+ratings.size()+" movies");
    }
}