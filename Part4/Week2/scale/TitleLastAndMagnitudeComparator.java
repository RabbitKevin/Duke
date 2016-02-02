import java.util.*;
class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry qe1, QuakeEntry qe2){
        String one = lastWord(qe1.getInfo().trim());
        String two = lastWord(qe2.getInfo().trim());
        int stringCompare = one.compareTo(two);
        if(stringCompare > 0) return 1;
        if(stringCompare < 0) return -1;
        double magnitudeOne = qe1.getMagnitude();
        double magnitudeTwo = qe2.getMagnitude();
        if(magnitudeOne < magnitudeTwo) return -1;
        if(magnitudeOne > magnitudeTwo) return 1;
        return 0;
    }
    private String lastWord(String word){
        int index = word.lastIndexOf(" ");
        return word.substring(index+1);
    }
}