
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package EarthquakeSortProgram;
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

	public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIdx = from;
        for (int i=maxIdx+1; i<quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size(); i++) {
            int maxIndex = getLargestDepth(in, i);
            QuakeEntry currentEntry = in.get(i);
            QuakeEntry maxEntrySoFar = in.get(maxIndex);
            in.set(i, maxEntrySoFar);
            in.set(maxIndex, currentEntry);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
//        System.out.println("Printing quakes after pass "+numSorted);
//        for (QuakeEntry qe : quakeData) {
//            System.out.println(qe);
//        }
        for (int i=0; i<(quakeData.size()-numSorted-1); i++) {
            QuakeEntry currentEntry =  quakeData.get(i);
            QuakeEntry nextAdjacentEntry = quakeData.get(i+1);
            if (!(currentEntry.getMagnitude() < nextAdjacentEntry.getMagnitude())) {
                quakeData.set(i, nextAdjacentEntry);
                quakeData.set(i+1, currentEntry);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size()-1; i++) {
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData) {
        for (int i=0; i<(quakeData.size()-1); i++) {
            QuakeEntry currentEntry =  quakeData.get(i);
            QuakeEntry nextAdjacentEntry = quakeData.get(i+1);
            if (!(currentEntry.getMagnitude() <= nextAdjacentEntry.getMagnitude())) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int numberOfPasses = 0;
        for (int i=0; i< in.size()-1; i++) {
            if (!checkInSortedOrder(in)) {
                onePassBubbleSort(in, i);
                numberOfPasses += 1;
            } else {
                break;
            }
        }
        System.out.println("Number of passes that were needed for sort: "+numberOfPasses);
    }

//    public void debugger() {
//        int[] iterationLimits = {1226, 1233, 1240, 1255, 1260, 1267};
//        for (int eachLimit : iterationLimits) {
//            EarthQuakeParser parser = new EarthQuakeParser();
//            String source = "src/EarthquakeSortProgram/data/earthQuakeDataWeekDec6sample2.atom";
//            ArrayList<QuakeEntry> list  = parser.read(source);
//            sortByMagnitudeWithBubbleSortWithCheck(list, eachLimit);
//            boolean isSorted = checkInSortedOrder(list);
//            System.out.println("At iteration limit "+eachLimit+" the list is sorted? "+isSorted);
//        }
//    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numberOfPasses = 0;
        for (int i=0; i<in.size(); i++) {
            if (!checkInSortedOrder(in)) {
                int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
                numberOfPasses += 1;
            } else {
                break;
            }
        }
        System.out.println("Number of passes that were needed for sort: "+numberOfPasses);
    }

    public void testSort(ArrayList<QuakeEntry> list) {
        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("Quakes in sorted order: ");
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
}
