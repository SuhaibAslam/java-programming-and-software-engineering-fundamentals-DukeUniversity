package CSVAssignments;
import ArrayListAssignments.CharactersInPlay;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class Weather {

    /**
     * @param parser a CSVParser
     * @return This method returns the CSVRecord with the hottest temperature in the file and thus all the
     * information about the hottest temperature, such as the hour of the hottest temperature.
     */

    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currRecord : parser) {
            largestSoFar = getLargestOfTwo(currRecord, largestSoFar);
        }
        return largestSoFar;
    }

    /**
     * @param parser a CSVParser
     * @return This method returns the CSVRecord with the coldest temperature in the file and thus all the
     * information about the coldest temperature, such as the hour of the coldest temperature.
     */

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currRecord : parser) {
            smallestSoFar = getSmallestOfTwo(currRecord, smallestSoFar, "TemperatureF");
        }
        return smallestSoFar;
    }

    //tester
    public void testHottestDay() {
        FileResource fr = new FileResource("CSVAssignments/nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest Temperature was "+largest.get("TemperatureF")+" at "+largest.get("TimeEST"));
    }

    //tester
    public void testColdestDay() {
        FileResource fr = new FileResource("CSVAssignments/nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature was "+smallest.get("TemperatureF")+" at "+smallest.get("TimeEST"));
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRecord, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRecord;
        } else {
            double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRecord;
            }
        }
        return largestSoFar;
    }

    public CSVRecord getSmallestOfTwo (CSVRecord currentRecord, CSVRecord smallestSoFar, String fieldName) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRecord;
        } else {
            double currentTemp = Double.parseDouble(currentRecord.get(fieldName));
            double smallestTemp = Double.parseDouble(smallestSoFar.get(fieldName));
            if ((currentTemp < smallestTemp) && currentTemp != -9999) {
                smallestSoFar = currentRecord;
            }
        }
        return smallestSoFar;
    }

    public CSVRecord hottestHourInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRecord = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currRecord, largestSoFar);
        }
        return largestSoFar;
    }

    /**
     * @return This method returns a string that is the name of the file from selected files that has
     * the coldest temperature.
     */

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        String coldestTemperatureFilename = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRecord = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currRecord, smallestSoFar, "TemperatureF");
        }
        coldestTemperatureFilename = smallestSoFar.get("DateUTC");
        return coldestTemperatureFilename.substring(0,10);
    }

    //tester
    public void testFileWithColdestTemperature() {
        String coldestTemperatureFilename = fileWithColdestTemperature();
        String folderDate = coldestTemperatureFilename.substring(0, 4);
        FileResource fr = new FileResource("CSVAssignments/nc_weather/"+folderDate+"/weather-"+coldestTemperatureFilename+".csv");
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("The file with coldest temperature is "+coldestTemperatureFilename);
        System.out.println("Coldest temperature on the day represented by that file was "+coldestRecord.get("TemperatureF"));
    }

    //tester
    public void testHottestInManyDays() {
        CSVRecord hottestInManyDays = hottestHourInManyFiles();
        System.out.println("Hottest temperature was "+hottestInManyDays.get("TemperatureF")+ " at "+hottestInManyDays.get("DateUTC"));
    }

    /**
     * @param parser a CSVParser
     * @return This method returns the CSVRecord that has the lowest humidity.
     * If there is a tie, then return the first such record that was found.
     */

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumiditySoFar = null;
        for (CSVRecord currRecord : parser) {
            String humidityValue = currRecord.get("Humidity");
            if (!humidityValue.equals("N/A")) {
                lowestHumiditySoFar = getSmallestOfTwo(currRecord, lowestHumiditySoFar, "Humidity");
            }
        }
        return lowestHumiditySoFar;
    }

    //tester
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("CSVAssignments/nc_weather/2014/weather-2014-07-22.csv");
        CSVRecord lowestHumidityRecord = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+lowestHumidityRecord.get("Humidity")+" at "+lowestHumidityRecord.get("DateUTC"));
    }

    /**
     * @return This method returns a CSVRecord that has the lowest humidity over all the files.
     * If there is a tie, then return the first such record that was found.
     */

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRecord = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(currRecord, lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }

    //tester
    private void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        String humidity = lowestHumidityRecord.get("Humidity");
        String time = lowestHumidityRecord.get("DateUTC");
        System.out.println("Lowest Humidity across multiple files was "+humidity+" at "+time);

    }

    /**
     * @param parser a CSVParser
     * @return This method returns a double that represents the average temperature in the file.
     */

    private double averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int numberOfItems = 0;
        for (CSVRecord record : parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            numberOfItems += 1;
        }
        return sum/numberOfItems;
    }

    //tester
    private void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("CSVAssignments/nc_weather/2013/weather-2013-08-10.csv");
        CSVParser parser = fr.getCSVParser();
        double averageTemperature = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+averageTemperature);
    }

    /**
     * @param parser a CSVParser
     * @param value an integer
     * @return This method returns a double that represents the average temperature of only those temperatures
     * when the humidity was greater than or equal to value.
     */

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double sum = 0;
        int numberOfItems = 0;
        for (CSVRecord record : parser) {
            if (Integer.parseInt(record.get("Humidity"))>=value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                numberOfItems += 1;
            }
        }
        return sum/numberOfItems;
    }

    //tester
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource("CSVAssignments/nc_weather/2013/weather-2013-09-02.csv");
        CSVParser parser = fr.getCSVParser();
        int humidity = 80;
        double averageTemperature = averageTemperatureWithHighHumidityInFile(parser, humidity);
        System.out.println("Average temperature when humidity is greater than or equal to "+humidity+" is "+averageTemperature);
    }

    public static void main(String[] args) {
        Weather w = new Weather();
        w.testHottestDay();
        w.testColdestDay();
        //w.testHottestInManyDays();
        w.testFileWithColdestTemperature();
        w.testLowestHumidityInFile();
        //w.testLowestHumidityInManyFiles();
        w.testAverageTemperatureInFile();
        w.testAverageTemperatureWithHighHumidityInFile();
    }
}
