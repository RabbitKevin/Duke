import java.util.*;
class LargestQuakes{
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        list = getLargest(list, 50);
        for(QuakeEntry entry : list){
            System.out.println(entry);
        }
        System.out.println("number found: "+list.size());
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int index = -1;
        double max = Double.MIN_VALUE;
        for(int i = 0; i < quakeData.size(); ++i){
            double magnitude = quakeData.get(i).getMagnitude();
            if(magnitude > max){
                max = magnitude;
                index = i;
            }
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        int times = Math.min(howMany, quakeData.size());
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(int j = 0; j < times; ++j){
            int index = indexOfLargest(quakeData);
            answer.add(quakeData.remove(index));
        }
        return answer;
    }
}