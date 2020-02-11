package GenesInDNAFinder;

public class FindingMultipleStringOccurrences {

    /**
     * howMany has two String parameters named stringa and stringb.
     * This method returns an integer indicating how many times stringa appears in stringb,
     * where each occurrence of stringa must not overlap with another occurrence of it.
     * @param stringa
     * @param stringb
     * @return
     */

    public int howMany (String stringa, String stringb) {
        int counts = 0;
        int currIndex = stringb.indexOf(stringa, 0);
        while (currIndex != -1) {
            if ((currIndex >= stringb.length())) {
                break;
            }
            counts += 1;
            currIndex = stringb.indexOf(stringa, currIndex+stringa.length());
        }
        return counts;
    }

    public void testHowMany () {
        int counts = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("GAA with ATGAACGAATTGAATC gives: "+counts);
        counts = howMany("bb", "abbdebbbdewvbb");
        System.out.println("bb with abbdebbbdewvbb gives: "+counts);
        counts = howMany("aa", "ddddaa");
        System.out.println("aa with ddddaa gives: "+counts);
    }

    public static void main(String[] args) {
        FindingMultipleStringOccurrences findingMultipleStringOccurrences = new FindingMultipleStringOccurrences();
        findingMultipleStringOccurrences.testHowMany();
    }
}
