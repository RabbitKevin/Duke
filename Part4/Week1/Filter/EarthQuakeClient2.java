import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        Filter f = new MagnitudeFilter(3.5, 4.5);
        list = filter(list, f);
        f = new DepthFilter(-20000.0, -55000.0);
        list = filter(list,f);
        
        /*
        Filter f = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
        list = filter(list, f);
        f = new PhaseFilter("end", "a");
        list = filter(list,f);
        */
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("Remain data for "+list.size()+" quakes");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        MatchAllFilter allFilter = new MatchAllFilter();
        allFilter.addFilter(new MagnitudeFilter(1.0, 4.0));
        allFilter.addFilter(new PhaseFilter("any", "o"));
        allFilter.addFilter(new DepthFilter(-30000.0,-180000.0));
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry entry : list){
            if(allFilter.satisfies(entry)) answer.add(entry);
        }
        for (QuakeEntry qe: answer) {
            System.out.println(qe);
        }
        System.out.println("read data for "+answer.size()+" quakes");
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        MatchAllFilter maf = new MatchAllFilter();
        System.out.println("read data for "+list.size()+" quakes");
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new PhaseFilter("any", "e"));
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry entry : list){
            if(maf.satisfies(entry)) answer.add(entry);
        }
        for (QuakeEntry qe: answer) {
            System.out.println(qe);
        }
        System.out.println("read data for "+answer.size()+" quakes");
        System.out.println(maf.getName());
    }
}
