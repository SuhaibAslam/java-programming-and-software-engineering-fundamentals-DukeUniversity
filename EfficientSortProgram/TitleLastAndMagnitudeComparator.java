package EfficientSortProgram;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] q1TitleArray = q1.getInfo().split("\\W+");
        String[] q2TitleArray = q2.getInfo().split("\\W+");
        String q1TitleLastWord = q1TitleArray[q1TitleArray.length-1];
        String q2TitleLastWord = q2TitleArray[q2TitleArray.length-1];
        int titleComparison = q1TitleLastWord.compareTo(q2TitleLastWord);
        if (titleComparison != 0) {
            return titleComparison;
        } else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}
