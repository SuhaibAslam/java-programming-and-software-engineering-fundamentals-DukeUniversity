package EarthquakeSortProgram;
import java.util.*;

public class Tester {
    private EarthQuakeParser parser;
    private String source;
    ArrayList<QuakeEntry> list;

    public Tester() {
        parser = new EarthQuakeParser();
        source = "src/EarthquakeSortProgram/data/earthQuakeDataDec6sample2.atom";
        list  = parser.read(source);
    }

    public void testSortWithTwoArrayLists() {
        QuakeSortWithTwoArrayLists twoArraySort = new QuakeSortWithTwoArrayLists();
        twoArraySort.testSort(list);
    }

    public void testSortInPlace() {
        QuakeSortInPlace sortInPlace = new QuakeSortInPlace();
        sortInPlace.testSort(list);
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        //t.testSortWithTwoArrayLists();
        t.testSortInPlace();
    }

}

