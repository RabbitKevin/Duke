
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
        int index = -1;
        double largest = Double.NEGATIVE_INFINITY;
        for(int i = from; i < quakes.size(); ++i){
            double depth = quakes.get(i).getDepth();
            if(depth > largest){
                index = i;
                largest = depth;
            }
        }
        return index;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> quakes){
        int size = 70;
        for(int i = 0; i < size; ++i){
            int index = getLargestDepth(quakes, i);
            //System.out.println("i : "+i+" index: "+index);
            QuakeEntry tmp = quakes.get(i);
            quakes.set(i, quakes.get(index));
            quakes.set(index, tmp);
        }
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i = 0; i < quakeData.size()-numSorted-1; ++i){
            QuakeEntry left = quakeData.get(i);
            QuakeEntry right = quakeData.get(i+1);
            if(right.getMagnitude() < left.getMagnitude()){
                quakeData.set(i, right);
                quakeData.set(i+1, left);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakes){
        for(int i = 0; i < quakes.size()-1; ++i){
            onePassBubbleSort(quakes, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        double before = Double.NEGATIVE_INFINITY;
        for(QuakeEntry entry : quakes){
            if(entry.getMagnitude() < before) return false;
            before = entry.getMagnitude();
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakes){
        for(int i = 0; i < quakes.size()-1; ++i){
            if(checkInSortedOrder(quakes)) return;
            System.out.println("Times : "+i);
            onePassBubbleSort(quakes, i);
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> quakes){
        for (int i=0; i< quakes.size(); i++) {
            if(checkInSortedOrder(quakes)) break;
            System.out.println("Times : "+i);
            int minIdx = getSmallestMagnitude(quakes,i);
            QuakeEntry qi = quakes.get(i);
            QuakeEntry qmin = quakes.get(minIdx);
            quakes.set(i,qmin);
            quakes.set(minIdx,qi);
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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
}
