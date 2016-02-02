import java.util.*;
import edu.duke.*;

public class MatchAllFilter implements Filter{
    ArrayList<Filter> filterList;
    public MatchAllFilter(){
        filterList = new ArrayList<Filter>();
    }
    public void addFilter(Filter filter){
        filterList.add(filter);
    }
    public boolean satisfies(QuakeEntry quakeData){
        for(Filter filter : filterList){
            if(!filter.satisfies(quakeData)) return false;
        }
        return true;
    }
    public String getName(){
        String result = "";
        for(Filter filter : filterList){
            result += filter.getName()+" ";
        }
        return result;
    }
}