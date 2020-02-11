package SearchingEarthquakeData;
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> closestQuakesToLocation = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copyOfQuakeDate = new ArrayList<>(quakeData);
        int quakeDataLength = quakeData.size();
        int iterationCondition = 0;
        if (quakeDataLength < howMany) {
            iterationCondition = quakeDataLength;
        } else {
            iterationCondition = howMany;
        }
        for (int j=0; j<iterationCondition; j++){
            int minIndex = 0;
            for (int i=1; i<copyOfQuakeDate.size(); i++) {
                QuakeEntry qe = copyOfQuakeDate.get(i);
                Location currentLocation = qe.getLocation();
                float distanceFromCity = currentLocation.distanceTo(current);
                float currentMinDistance = copyOfQuakeDate.get(minIndex).getLocation().distanceTo(current);
                if (distanceFromCity < currentMinDistance) {
                    minIndex = i;
                }
            }
            closestQuakesToLocation.add(copyOfQuakeDate.get(minIndex));
            copyOfQuakeDate.remove(minIndex);
        }

        return closestQuakesToLocation;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "src/SearchingEarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
