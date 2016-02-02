import edu.duke.*;
import java.io.*;

public class FindWebLink{
    public static void readURL(String url){
        URLResource reader = new URLResource(url);
        for(String line : reader.lines()){
            String lowerCaseLine = line.toLowerCase();
            int index = lowerCaseLine.indexOf("youtube.com");
            if(index != -1){
                int rightBound = lowerCaseLine.indexOf("\"", index);
                if(rightBound == -1) continue;
                int leftBound = lowerCaseLine.lastIndexOf("\"", index);
                if(leftBound == -1) continue;
                System.out.println(line.substring(leftBound+1, rightBound));
            }
        }
    }
    public static StorageResource findURLs(String url){
        StorageResource resource = new StorageResource();
        URLResource reader = new URLResource(url);
        String input = reader.asString().toLowerCase();
        int pos = 0;
        while(true){
            pos = input.indexOf("href=",pos);
            if(pos == -1) break;
            int leftBound = input.indexOf("\"", pos+5);
            if(leftBound == -1){
                pos++;
                continue;
            }
            int rightBound = input.indexOf("\"", leftBound+1);
            if(rightBound == -1){
                pos++;
                continue;
            }
            String link = input.substring(leftBound+1, rightBound);
            if(link.startsWith("http")) resource.add(link);
            pos = rightBound+1;
        }
        return resource;
    }
    public static void countURL(StorageResource resource){
        System.out.println("Size of URL found is : "+resource.size());
        int secureCount = 0;
        int countOne = 0;
        int countTwo = 0;
        int dotNumber = 0;
        for(String link : resource.data()){
            System.out.println(link);
            link = link.toLowerCase();
            if(link.indexOf(".com") != -1){
                countOne++;
                if(link.endsWith(".com") || link.endsWith(".com/")) countTwo++;
            }
            if(link.startsWith("https")) secureCount++;
            dotNumber+=countDot(link);
        }
        System.out.println("The # of secure link is "+secureCount);
        System.out.println("The # of link end with .com or .com/ is "+countTwo);
        System.out.println("The # of link has .com is "+countOne);
        System.out.println("The # of dot is "+dotNumber);
    }
    private static int countDot(String str){
        int count = 0;
        for(int i = 0; i < str.length(); ++i){
            if(str.charAt(i) == '.') count++;
        }
        return count;
    }
    public static void main(String[] args){
        //readURL("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        countURL(findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html"));
    }
}