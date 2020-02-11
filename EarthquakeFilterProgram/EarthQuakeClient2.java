package EarthquakeFilterProgram;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        }
        return answer;
    } 

    public void quakesWithFilter(ArrayList<Filter> filters) {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeFilterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> filteredData  = new ArrayList<>(list);
        for (Filter eachFilter : filters) {
            filteredData = filter(filteredData, eachFilter);
        }
        for (QuakeEntry qe : filteredData) {
            System.out.println(qe);
        }
        System.out.println("number of quakes found was "+filteredData.size()+" quakes");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeFilterProgram/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeFilterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: "+list.size());
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0,4.0));
        maf.addFilter(new DepthFilter(-180000.0,-30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        ArrayList<QuakeEntry> allFiltersMatched = filter(list, maf);
        for (QuakeEntry qe : allFiltersMatched) {
            System.out.println(qe);
        }
        String filtersUsed = maf.getName();
        System.out.println("Filters used are: "+filtersUsed);
        System.out.println("Number of quakes found using filters was "+allFiltersMatched.size()+" quakes");
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeFilterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: "+list.size());
        MatchAllFilter maf = new MatchAllFilter();
        Location billund  = new Location(55.7308, 9.1153);
        maf.addFilter(new MagnitudeFilter(0.0,5.0));
        maf.addFilter(new DistanceFilter(billund, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));
        ArrayList<QuakeEntry> allFiltersMatched = filter(list, maf);
        for (QuakeEntry qe : allFiltersMatched) {
            System.out.println(qe);
        }
        System.out.println("Number of quakes found using filters was "+allFiltersMatched.size()+" quakes");
    }

    public static void main(String[] args) {
        EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
//        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "src/EarthquakeFilterProgram/data/nov20quakedatasmall.atom";
//        ArrayList<QuakeEntry> list  = parser.read(source);
//        System.out.println("# quakes read: "+list.size());
//        Filter minMag = new MinMagFilter(5.0);
//        ArrayList<QuakeEntry> answer = eqc2.filter(list,minMag);
//        for (QuakeEntry qe : answer) {
//            System.out.println(qe);
//        }
//        ArrayList<Filter> listOfFilters = new ArrayList<>();
//        listOfFilters.add(new MagnitudeFilter(3.5,4.5));
//        listOfFilters.add(new DepthFilter(-55000.0,-20000.0));
//        Location denver  = new Location(39.7392, -104.9903);
//        listOfFilters.add(new DistanceFilter(denver, 1000000));
//        listOfFilters.add(new PhraseFilter("end", "a"));
//        eqc2.quakesWithFilter(listOfFilters);
        //eqc2.testMatchAllFilter();
        eqc2.testMatchAllFilter2();
    }

}
