package babyNamesTotals;
import edu.duke.FileResource;

/**
 * Tester class for the ===BabyBirths=== class.
 */
public class Tester {
    private FileResource fr;
    private BabyBirths bb;

    public Tester() {
        bb = new BabyBirths();
    }

    public void testTotalBirths () {
        fr = new FileResource("babyNamesTotals/data/us_babynames/us_babynames_by_year/yob1905.csv");
        bb.totalBirths(fr);
    }

    public void testGetRank() {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = bb.getRank(year, name, gender);
        System.out.println("The rank of the name "+name+" in the file is "+rank);
    }

    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String nameAtRank = bb.getName(year, rank, gender);
        System.out.println("The person with rank "+rank+" is "+nameAtRank);
    }

    public void testWhatIsNameInYear() {
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        bb.whatIsNameInYear(name, year, newYear, gender);
    }

    public void testYearOfHighestRank() {
        String name = "Mich";
        String gender = "M";
        int highestRankYear = bb.yearOfHighestRank(name, gender);
        System.out.println("The year with the highest rank for the name and gender in selected files is "+highestRankYear);
    }

    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        double averageRank = bb.getAverageRank(name, gender);
        System.out.println("The average rank of the name and gender over the selected files is "+averageRank);
    }

    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int totalBirthsRankedHigher = bb.getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total number of births of those names with the same gender and same year who are " +
                "ranked higher than "+name+" is "+totalBirthsRankedHigher);
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testTotalBirths();
        t.testGetRank();
        t.testGetName();
        t.testWhatIsNameInYear();
        t.testYearOfHighestRank();
        //t.testGetAverageRank();
        //t.testGetTotalBirthsRankedHigher();
    }
}
