import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather{
    public static CSVRecord coldestHourInFile(CSVParser parser){
        double min = Double.MAX_VALUE;
        CSVRecord result = null;
        for(CSVRecord record : parser){
            String temp = record.get("TemperatureF");
            if(temp.equals("-9999")) continue;
            double temperature = Double.parseDouble(temp);
            if(temperature < min){
                min = temperature;
                result = record;
            }
        }
        return result;
    }
    public static void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        if(record != null) System.out.println(record.get("DateUTC"));
        else System.out.println("NULL, there is no hour for lowest temp");
    }
    public static String fileWithColdestTemperature(){
        DirectoryResource resource = new DirectoryResource();
        double min = Double.MAX_VALUE;
        String name = "";
        for (File f : resource.selectedFiles()) {
            CSVParser parser = new FileResource(f).getCSVParser();
            CSVRecord record = coldestHourInFile(parser);
            double temperature = Double.parseDouble(record.get("TemperatureF"));
            if(temperature < min){
                min = temperature;
                name = f.getName();
            }
        }
        System.out.println("Filename: "+name);
        System.out.println("Temperature: "+min);
        return name;
    }
    public static void testFileWithColdestTemperature(){
        String path = fileWithColdestTemperature();
        CSVParser parser = new FileResource("data/2013/"+path).getCSVParser();
        for(CSVRecord record : parser){
            System.out.print(record.get("DateUTC"));
            System.out.print(record.get("TemperatureF"));
            System.out.print("\n");
        }
    }
    public static CSVRecord lowestHumidityInFile(CSVParser parser){
        int min = Integer.MAX_VALUE;
        CSVRecord result = null;
        for(CSVRecord record : parser){
            String humidity = record.get("Humidity");
            if(humidity.equals("N/A")) continue;
            int humidityValue = Integer.parseInt(humidity);
            if(humidityValue < min){
                min = humidityValue;
                result = record;
            }
        }
        return result;
    }
    public static void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        //----//
        System.out.println("Lowest humidity is "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public static CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource resource = new DirectoryResource();
        int min = Integer.MAX_VALUE;
        CSVRecord lowest = null;
        for(File f : resource.selectedFiles()){
            CSVParser parser = new FileResource(f).getCSVParser();
            CSVRecord tmp = lowestHumidityInFile(parser);
            int humidity = Integer.parseInt(tmp.get("Humidity"));
            if(humidity < min){
                lowest = tmp;
                min = humidity;
            }
        }
        return lowest;
    }
    public static void testLowestHumidityInManyFiles(){
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity is "+record.get("Humidity")+" at "+record.get("DateUTC"));
    }
    public static double averageTemperatureInFile(CSVParser parser){
        double sum = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            String temp = record.get("TemperatureF");
            if(temp.equals("-9999")) continue;
            else{
                sum+=Double.parseDouble(temp);
                count++;
            }
        }
        return sum/count;
    }
    public static void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("The average temperature is "+average);
    }
    public static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            String temp = record.get("TemperatureF");
            if(temp.equals("-9999")) continue;
            else{
                String humidity = record.get("Humidity");
                if(humidity.equals("N/A")) continue;
                if(Integer.parseInt(humidity) >= value){
                    sum += Double.parseDouble(temp);
                    count++;
                }
            }
        }
        return sum/count;
    }
    public static void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("The average temperature is "+average);
    }
    public static void main(String[] args){
        //testAverageTemperatureWithHighHumidityInFile();
        testFileWithColdestTemperature();
        //testLowestHumidityInFile();
        //testLowestHumidityInManyFiles();
        //testAverageTemperatureInFile();
        //testAverageTemperatureWithHighHumidityInFile();
    }
}