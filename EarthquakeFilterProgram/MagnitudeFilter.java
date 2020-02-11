package EarthquakeFilterProgram;

public class MagnitudeFilter implements Filter {
    private double minMag;
    private double maxMag;
    public MagnitudeFilter(double min, double max) {
        minMag = min;
        maxMag = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        double magnitude = qe.getMagnitude();
        return (magnitude >= minMag && magnitude <= maxMag);
    }

    public String getName() {
        return (this.getClass().getName().split("\\.")[1]);
    }
}
