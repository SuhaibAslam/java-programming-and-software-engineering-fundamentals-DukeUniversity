package EarthquakeFilterProgram;

public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;

    public DistanceFilter(Location thisLocation, double maximumDistToThisLocation) {
        location = thisLocation;
        maxDistance = maximumDistToThisLocation;
    }

    public boolean satisfies(QuakeEntry qe) {
        double distToThisLocation = qe.getLocation().distanceTo(location);
        return (distToThisLocation < maxDistance);
    }

    public String getName() {
        return (this.getClass().getName().split("\\.")[1]);
    }
}
