package EfficientSortProgram;
/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    private EarthQuakeParser parser;
    private String source;
    private ArrayList<QuakeEntry> list;

    public DifferentSorters() {
        parser = new EarthQuakeParser();
        source = "src/EfficientSortProgram/data/earthQuakeDataWeekDec6sample1.atom";
        list  = parser.read(source);
    }

    public void sortWithCompareTo() {
        Collections.sort(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));

    }    

    public void sortByMagnitude() {
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance() {
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

    public void sortByTitleAndDepth() {
        Collections.sort(list, new TitleAndDepthComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public void sortByLastWordInTitleThenByMagnitude() {
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }

    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
        //ds.sortWithCompareTo();
        //ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }
}
