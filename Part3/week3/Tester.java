
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public void testLogAnalyzer() {
        // complete method

    }
    public static void testUniqueIP(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("data/weblog2_log.txt");
        System.out.println("Unique ip " +log.countUniqueIPs());
        ArrayList<String> list = log.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("size is "+list.size());
        System.out.println("Number is "+log.countUniqueIPsInRange(200,299));
        HashMap<String,Integer> map = log.countVisitsPerIP();
        //iteration(map);
        int maxVisit = log.mostNumberVisitsByIP(map);
        System.out.println("Maximum visit is "+maxVisit);
        ArrayList<String> tmp = log.iPsMostVisits(map);
        System.out.println("Maximum visit from these ip: ");
        iteration(tmp);
        System.out.println("IP list for specified day ");
        HashMap<String, ArrayList<String>> ipsPerDay = log.iPsForDays();
        /*
        for(String key : ipsPerDay.keySet()){
            System.out.println("In day "+key);
            iteration(ipsPerDay.get(key));
        }
        */
        System.out.println("days with most ip visit");
        String date = log.dayWithMostIPVisits(ipsPerDay);
        System.out.println("The date is "+date);
        System.out.println("Most visit list for specified day");
        iteration(log.iPsWithMostVisitsOnDay(ipsPerDay, "Sep 29"));
    }
    public static void main(String[] args){
        testUniqueIP();
    }
    public static void iteration(ArrayList<String> list){
        for(String str : list) System.out.println(str);
    }
    public static void iteration(HashMap<String,Integer> map){
        for(String key : map.keySet()) System.out.println("Key is "+key+" Value is "+map.get(key));
    }
}
