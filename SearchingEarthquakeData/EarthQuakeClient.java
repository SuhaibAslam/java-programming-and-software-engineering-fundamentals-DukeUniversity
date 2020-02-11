package SearchingEarthquakeData;

import java.util.*;

import WebLogProgram.LogEntry;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> filteredData = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                filteredData.add(qe);
            }
        }
        return filteredData;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> filteredData = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            Location currentLocation = qe.getLocation();
            if (currentLocation.distanceTo(from) < distMax) {
                filteredData.add(qe);
            }
        }
        return filteredData;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> magnitudeFilteredData = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : magnitudeFilteredData) {
            System.out.println(qe);
        }
        System.out.println("Found "+ magnitudeFilteredData.size() +" quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> distanceFilteredData = filterByDistanceFrom(list,1000.0*1000, city);
        for (QuakeEntry qe : distanceFilteredData) {
            Location currentLocation = qe.getLocation();
            float distanceFromCity = currentLocation.distanceTo(city)/1000;
            System.out.println(distanceFromCity+" "+qe.getInfo());
        }
        System.out.println("Found "+ distanceFilteredData.size() +" quakes that match the distance criteria");
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> depthFilteredData = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            double currentDepth = qe.getDepth();
            if (currentDepth > minDepth && currentDepth < maxDepth) {
                depthFilteredData.add(qe);
            }
        }
        return depthFilteredData;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/SearchingEarthquakeData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> depthFilteredData = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Quakes with depth between "+minDepth+" and "+maxDepth);
        for (QuakeEntry qe : depthFilteredData) {
            System.out.println(qe);
        }
        System.out.println("Found "+ depthFilteredData.size() +" quakes that match the depth criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> phraseFilteredData = new ArrayList<>();
        int indexToCheck = 0;
        if (where.equals("start") || where.equals("end")) {
            if (where.equals("start")) {
                for (QuakeEntry qe : quakeData) {
                    String currentQuakeTitle = qe.getInfo();
                    String[] wordsInTitle = currentQuakeTitle.split("\\W+");
                    if (wordsInTitle[indexToCheck].contains(phrase)) {
                        phraseFilteredData.add(qe);
                    }
                }
            } else {
                for (QuakeEntry qe : quakeData) {
                    String currentQuakeTitle = qe.getInfo();
                    String[] wordsInTitle = currentQuakeTitle.split("\\W+");
                    indexToCheck = wordsInTitle.length-1;
                    if (wordsInTitle[indexToCheck].contains(phrase)) {
                        phraseFilteredData.add(qe);
                    }
                }
            }
        } else {
            for (QuakeEntry qe : quakeData) {
                String currentQuakeTitle = qe.getInfo();
                String[] wordsInTitle = currentQuakeTitle.split("\\W+");
                for (String eachWord : wordsInTitle) {
                    if (!phraseFilteredData.contains(qe) && eachWord.contains(phrase)) {
                        phraseFilteredData.add(qe);
                    }
                }
            }
        }
        return phraseFilteredData;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/SearchingEarthquakeData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String where = "end";
        String phrase = "Alaska";
        ArrayList<QuakeEntry> phraseFilteredData = filterByPhrase(list, where, phrase);
        for (QuakeEntry qe : phraseFilteredData) {
            System.out.println(qe);
        }
        System.out.println("Found "+ phraseFilteredData.size() +" quakes that match "+ phrase +" at "+where);
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
