import edu.duke.*;
import org.apache.commons.csv.*;

public class Export{
    public static CSVParser tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }
    public static String countryInfo(String country, CSVParser parser){
        for (CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                return country+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.indexOf(exportItem1) != -1 && export.indexOf(exportItem2) != -1) System.out.println(record.get("Country"));
        }
    }
    public static int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            if(record.get("Exports").indexOf(exportItem) != -1) count++;
        }
        return count;
    }
    public static void bigExporters(CSVParser parser, String amount){
        int length = amount.length();
        for (CSVRecord record : parser){
            String number = record.get("Value (dollars)");
            if(number.length() > length) System.out.println(record.get("Country")+" "+number);
        }
    }
    public static void main(String[] args){
        CSVParser parser = tester();
        bigExporters(parser, "$999,999,999,999");
        //System.out.print("\n\n\n");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.print("\n\n\n");
        //System.out.println("Number is: "+numberOfExporters(parser, "cocoa"));
        //System.out.print("\n\n\n");
        //System.out.println(countryInfo("cocoa", parser));
        //System.out.print("\n\n\n");
    }
}