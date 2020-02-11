package EfficientSortProgram;

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int titleComparison = (q1.getInfo()).compareTo(q2.getInfo());
        if (titleComparison != 0) {
            return titleComparison;
        } else {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
    }
}
