import java.util.*;
class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        int stringCompare = qe1.getInfo().compareTo(qe2.getInfo());
        if(stringCompare > 0) return 1;
        if(stringCompare < 0) return -1;
        double depthOne = qe1.getDepth();
        double depthTwo = qe2.getDepth();
        if(depthOne < depthTwo) return -1;
        if(depthOne > depthTwo) return 1;
        return 0;
    }
}