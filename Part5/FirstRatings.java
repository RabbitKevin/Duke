import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/*
    Class to process ratings and movie
    @filename filename of movie
*/
class FirstRatings{
    /*
        Class to process ratings and movie
        @filename filename of movie
    */
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> result = new ArrayList<Movie>();
        FileResource resource = new FileResource(filename);
        for(CSVRecord record : resource.getCSVParser()){
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            result.add(new Movie(id, title, year, genre, director, country, poster, minutes));
        }
        return result;
    }
    /*
        @filename: path of input file
    */
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> result = new ArrayList<Rater>();
        FileResource resource = new FileResource(filename);
        HashMap<String, Integer> map = new HashMap<String, Integer>();//map used to mapping id and index
        int currentIndex = 0;
        for(CSVRecord record : resource.getCSVParser()){
            String rater_ID = record.get("rater_id");
            String movie_ID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            if(map.containsKey(rater_ID)){
                int index = map.get(rater_ID);
                result.get(index).addRating(movie_ID, rating);
            }
            else{
                Rater rater = new EfficientRater(rater_ID);
                rater.addRating(movie_ID, rating);
                result.add(rater);
                map.put(rater_ID, currentIndex++);
            }
        }
        return result;
    }
    public void testLoadRaters(ArrayList<Rater> list){
        System.out.println("There is "+list.size()+" raters");
        //-----Find specified ID of rater-----//
        for(Rater rater : list){
            if(rater.getID().equals("193")){
                System.out.println("The size of rating of this rater is "+rater.numRatings());
            }
        }
        int max = 0;
        for(Rater rater : list){
            max = Math.max(max, rater.numRatings());
        }
        System.out.println("The max raters who rate "+max+" are :");
        ArrayList<String> maxRaters = new ArrayList<String>();
        for(Rater rater : list){
            String id = rater.getID();
            if(rater.numRatings() == max){
                maxRaters.add(id);
                System.out.println(id);
            }
        }
        //-----------------------------//
        String movie = "1798709";
        int count = 0;
        for(Rater rater : list){
            if(rater.hasRating(movie)) count++;
        }
        System.out.println("There is "+count+" raters for movie "+movie);
        //----------------------------//
        HashSet<String> set = new HashSet<String>();
        for(Rater rater : list){
            ArrayList<String> items = rater.getItemsRated();
            for(String item : items){
                if(!set.contains(item)) set.add(item);
            }
        }
        System.out.println("There is "+set.size()+" movies in the list");
    }
    /*
        @list: list of movies
    */
    public void testLoadMovies(ArrayList<Movie> list){
        System.out.println("There is "+list.size()+" movies");
        int countGenre = 0;
        int longMovie = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int max = 0;
        for(Movie movie : list){
            //System.out.println(object);
            if(movie.getGenres().indexOf("Comedy") != -1) countGenre++;
            if(movie.getMinutes() > 150) longMovie++;
            //-----Directors-----//
            String[] directors = movie.getDirector().split(",");
            for(int i = 0; i < directors.length; ++i){
                String name = directors[i].trim();
                if(map.containsKey(name)){
                    int tmp = map.get(name)+1;
                    max = Math.max(max, tmp);
                    map.put(name, tmp);
                }
                else{
                    max = Math.max(max, 1);
                    map.put(name,1);
                }
            }
        }
        System.out.println("Comedy: "+countGenre);
        System.out.println("Long Movie: "+longMovie);
        System.out.println("Max movie by one director: "+max);
        ArrayList<String> names = new ArrayList<String>();
        for(String director : map.keySet()){
            if(map.get(director) == max){
                System.out.print(director);
                System.out.print(" ");
                System.out.println();
            }
        }
    }
}