
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         // complete constructor
        records = new ArrayList<LogEntry>();
     }
     public void readFile(String filename) {
         // complete method
        FileResource resource = new FileResource(filename);
        WebLogParser parser = new WebLogParser();
        for(String line : resource.lines()){
            line = line.trim();
            records.add(parser.parseEntry(line));
        }
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs(){
        HashSet<String> set = new HashSet<String>();
        for(LogEntry log : records){
            String tmp = log.getIpAddress();
            if(!set.contains(tmp)) set.add(tmp);
        }
        return set.size();
     }
     public void printAllHigherThanNum(int num){
        for(LogEntry log : records){
            if(log.getStatusCode() > num) System.out.println(log);
        }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        String[] input = someday.split(" ");
        String inputMonth = input[0];
        String inputDay = input[1];
        ArrayList<String> result = new ArrayList<String>();
        HashSet<String> ip = new HashSet<String>();
        for(LogEntry log : records){
            String date = log.getAccessTime().toString();
            String[] elements = date.split(" ");
            String month = elements[1];
            String day = elements[2];
            //System.out.println(month +" "+day);
            if(inputMonth.equals(month) && inputDay.equals(day) && !ip.contains(log.getIpAddress())){
                result.add(log.getIpAddress());
                ip.add(log.getIpAddress());
            }
        }
        return result;
     }
     public int countUniqueIPsInRange(int low, int high){
        HashSet<String> ip = new HashSet<String>();
        for(LogEntry log : records){
            int status = log.getStatusCode();
            if(!ip.contains(log.getIpAddress()) && status >= low && status <= high){
                ip.add(log.getIpAddress());
            }
        }
        return ip.size();
     }
     //1
     public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(LogEntry log : records){
            String ip = log.getIpAddress();
            if(map.containsKey(ip)) map.put(ip, map.get(ip)+1);
            else map.put(ip,1);
        }
        return map;
     }
     //2
     public int mostNumberVisitsByIP(HashMap<String, Integer> map){
        int max = 0;
        for(String key : map.keySet()){
            max = Math.max(max, map.get(key));
        }
        return max;
     }
     //3
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
        int max = mostNumberVisitsByIP(map);
        ArrayList<String> result = new ArrayList<String>();
        for(String key : map.keySet()){
            if(map.get(key) == max) result.add(key);
        }
        return result;
     }
     //4
     public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for(LogEntry log : records){
            String key = dateFormat(log.getAccessTime().toString());
            if(!map.containsKey(key)){
                ArrayList<String> line = new ArrayList<String>();
                line.add(log.getIpAddress());
                map.put(key, line);
            }
            else map.get(key).add(log.getIpAddress());
        }
        return map;
     }
     //5
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        String result = "";
        int max = 0;
        for(String date : map.keySet()){
            int count = countIP(map.get(date));
            if(count > max){
                max = count;
                result = date;
            }
        }
        return result;
     }
     //6
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String date){
        ArrayList<String> ips = map.get(date);
        int max = 0;
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        ArrayList<String> result = new ArrayList<String>();
        for(String ip : ips){
            if(!count.containsKey(ip)){
                count.put(ip, 1);
                max = Math.max(max,1);
            }
            else{
                int tmp = count.get(ip);
                count.put(ip,tmp+1);
                max = Math.max(max, tmp+1);
            }
        }
        for(String key : count.keySet()){
            if(count.get(key) == max) result.add(key);
        }
        return result;
     }
     /*
        private help method used for public function
     */
     private String dateFormat(String input){
        String[] elements = input.split(" ");
        String month = elements[1];
        String day = elements[2];
        String key = month+" "+day;
        return key;
     }
     private int countIP(ArrayList<String> ips){
        HashSet<String> set = new HashSet<String>();
        for(String key : ips){
            if(!set.contains(key)) set.add(key);
        }
        return set.size();
     }
}
