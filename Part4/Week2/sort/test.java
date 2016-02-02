import java.util.*;
import edu.duke.*;
class test{
    public static void main(String[] args){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //ArrayList<QuakeEntry> list  = parser.read(source);
        new QuakeSortInPlace().testSort();
    }
}