package SearchingEarthquakeData;
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/SearchingEarthquakeData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        /*
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
        int indexOfLargestQuake = indexOfLargest(list);
        //System.out.println("Index of largest quake is "+indexOfLargestQuake+" with magnitude "+list.get(indexOfLargestQuake).getMagnitude());
        ArrayList<QuakeEntry> largestMagnitudeQuakes = getLargest(list,50);
        for (QuakeEntry qe : largestMagnitudeQuakes) {
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int maxIndex = 0;
        for (int i=1; i<data.size(); i++) {
            QuakeEntry qe = data.get(i);
            double currentMagnitude = qe.getMagnitude();
            double maxMagnitudeSoFar = data.get(maxIndex).getMagnitude();
            if (currentMagnitude > maxMagnitudeSoFar) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> largestMagnitudeQuakes = new ArrayList<>();
        ArrayList<QuakeEntry> copyOfQuakeDate = new ArrayList<>(quakeData);
        int quakeDataLength = quakeData.size();
        int iterationCondition = 0;
        if (quakeDataLength < howMany) {
            iterationCondition = quakeDataLength;
        } else {
            iterationCondition = howMany;
        }
        for (int j=0; j<iterationCondition; j++){
            int maxIndex = indexOfLargest(copyOfQuakeDate);
            largestMagnitudeQuakes.add(copyOfQuakeDate.get(maxIndex));
            copyOfQuakeDate.remove(maxIndex);
        }

        return largestMagnitudeQuakes;

    }
}
