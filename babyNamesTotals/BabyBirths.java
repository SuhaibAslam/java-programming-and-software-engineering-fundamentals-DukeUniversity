package babyNamesTotals;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

    /**
     * This method prints total births, total female births, total male births, number of unique female names and the
     * number of unique male names.
     * @param fr FileResource object (see edu.duke library import)
     */

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int numberOfGirlNames = 0;
		int numberOfBoyNames = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				numberOfBoyNames += 1;
			}
			else {
				totalGirls += numBorn;
				numberOfGirlNames += 1;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("number of female names = "+numberOfGirlNames);
		System.out.println("female girls = " + totalGirls);
        System.out.println("number of male names = "+numberOfGirlNames);
		System.out.println("male boys = " + totalBoys);
	}

    /**
     * @param year an integer
     * @param name a string
     * @param gender a string (F for female and M for male)
     * @return This method returns the rank of the name in the file for the given gender, where rank 1 is the name
     * with the largest number of births. If the name is not in the file, then -1 is returned.
     */

	public int getRank(int year, String name, String gender) {
	    FileResource fr = new FileResource("babyNamesTotals/data/yob"+year+".csv");
	    int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if ((rec.get(0).equals(name)) && (rec.get(1).equals(gender))) {
                    return rank;
                }
            }
        }
        return -1;
    }

    /**
     *
     * @param year an integer
     * @param rank an integer
     * @param gender a string (F for female and M for male)
     * @return This method returns the name of the person in the file at this rank, for the given gender,
     * where rank 1 is the name with the largest number of births.
     * If the rank does not exist in the file, then “NO NAME” is returned.
     */

    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("babyNamesTotals/data/yob"+year+".csv");
        int currentRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank += 1;
                if ((currentRank == rank) && (rec.get(1).equals(gender))) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }

    /**
     * What would your name be if you were born in a different year? This method determines what name would
     * have been named if a person were born in a different year, based on the same name popularity.
     * That is, the method determines the rank of name in the year they were born,
     * and then prints the name born in newYear that is at the same rank and same gender.
     * @param name a string
     * @param year an integer
     * @param newYear an integer
     * @param gender a string (F for female and M for male)
     */

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rankOfCurrentYearName = getRank(year, name, gender);
        String nameInNewYear = getName(newYear, rankOfCurrentYearName, gender);
        System.out.println(name+" born in "+year+" would be "+nameInNewYear+ " if they were " +
                "born in "+newYear);

    }

    /**
     * @param name a string
     * @param gender a string (F for female and M for male)
     * @return This method selects a range of files to process and returns an integer,
     * the year with the highest rank for the name and gender.
     * If the name and gender are not in any of the selected files, it should return -1.
     */

    public int yearOfHighestRank(String name, String gender) {
	    DirectoryResource dr = new DirectoryResource();
	    int highestRank = 100000;
	    int highestRankYear = 0;
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int currentRank = getRank(year, name, gender);
            if ((currentRank < highestRank) && currentRank != -1) {
                highestRank = currentRank;
                highestRankYear = year;
            }
        }
        return highestRankYear;
    }

    /**
     *
     * @param name a string
     * @param gender a string (F for female and M for male)
     * @return This method selects a range of files to process and returns a double representing
     * the average rank of the name and gender over the selected files.
     * It should return -1.0 if the name is not ranked in any of the selected files.
     */

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double sumOfRanks = 0;
        double numberOfItems = 0;
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int currentRank = getRank(year, name, gender);
            sumOfRanks += currentRank;
            numberOfItems += 1;
        }
        return sumOfRanks/numberOfItems;
    }

    public int getBirthsForName(int year, String name, String gender) {
        FileResource fr = new FileResource("babyNamesTotals/data/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if ((rec.get(0).equals(name)) && (rec.get(1).equals(gender))) {
                return Integer.parseInt(rec.get(2));
            }
        }
        return -1;
    }

    /**
     *
     * @param year an integer
     * @param name a string
     * @param gender a string (F for female and M for male)
     * @return This method returns an integer, the total number of births
     * of those names with the same gender and same year who are ranked higher than name.
     */

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
	    int rankOfName = getRank(year, name, gender);
	    int totalBirthsRankedHigher = 0;
	    for (int currentRank = rankOfName-1; currentRank > 0; currentRank--) {
	        String currentName = getName(year, currentRank, gender);
	        int birthsForCurrentName = getBirthsForName(year, currentName, gender);
	        totalBirthsRankedHigher += birthsForCurrentName;
        }
        return totalBirthsRankedHigher;
    }

}
