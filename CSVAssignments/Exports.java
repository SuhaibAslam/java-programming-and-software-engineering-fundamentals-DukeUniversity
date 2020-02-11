package CSVAssignments;
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports {

    /**
     * tester creates a CSVParser and calls each of the methods below to test them.
     * Each time you want to use the parser with another method,
     * you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
     */
    public void tester() {
        FileResource fr = new FileResource("D:\\Suhaib's Folder\\ATLAS\\Semester 5\\Software Systems\\Online java\\IntelliJ\\src\\CSVAssignments\\exports\\exports\\exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        String countryForInfo = "Germany";
        System.out.println("Country Info for "+countryForInfo);
        System.out.println(countryInfo(parser, countryForInfo));
        parser = fr.getCSVParser();
        String exportItem1 = "cotton";
        String exportItem2 = "flowers";
        System.out.println("Countries that export "+exportItem1+" and "+exportItem2);
        listExportersTwoProducts(parser, exportItem1, exportItem2);
        parser = fr.getCSVParser();
        String exportItem = "cocoa";
        System.out.println("Number of exporters of "+exportItem);
        System.out.println(numberOfExporters(parser, exportItem));
        parser = fr.getCSVParser();
        String amount = "$999,999,999,999";
        System.out.println("Countries with export value bigger than "+amount.length()+" digits");
        bigExporters(parser, amount);
    }

    /**
     * @param parser a CSVParser
     * @param country a String
     * @return This method returns a string of information about the country or
     * returns “NOT FOUND” if there is no information about the country.
     * The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports,
     * followed by “: “, followed by the countries export value.
     */

    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryFromRecord = record.get("Country");
            if (countryFromRecord.equals(country)) {
                    String exports = record.get("Exports");
                    String value = record.get("Value (dollars)");
                    return country+": "+exports+": "+value;
            }
        }
        return "NOT FOUND";
    }

    /**
     * This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
     * @param parser a CSVParser
     * @param exportItem1 a String
     * @param exportItem2 a String
     */

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    /**
     * @param parser a CSVParser
     * @param exportItem a String
     * @return This method returns the number of countries that export exportItem.
     */

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int exporterCounter = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                exporterCounter += 1;
            }
        }
        return exporterCounter;
    }

    /**
     * This method prints the names of countries and their Value amount for all countries
     * whose Value (dollars) string is longer than the amount string.
     * @param parser
     * @param amount a String in the format of a dollar sign, followed by an integer number with a comma separator
     *               every three digits from the right. An example of such a string might be “$400,000,000”.
     */

    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String exportValue = record.get("Value (dollars)");
            if (exportValue.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country+" "+exportValue);
            }
        }
    }

    public static void main(String[] args) {
        Exports exps = new Exports();
        exps.tester();
    }
}
