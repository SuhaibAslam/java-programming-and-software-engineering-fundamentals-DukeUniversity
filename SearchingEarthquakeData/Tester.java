package SearchingEarthquakeData;

public class Tester {
    private EarthQuakeClient eqc;
    private ClosestQuakes cq;
    private LargestQuakes lq;

    public Tester() {
        eqc = new EarthQuakeClient();
        cq = new ClosestQuakes();
        lq = new LargestQuakes();
    }

    public void testBigQuakes() {
        eqc.bigQuakes();
    }

    public void testCloseToMe() {
        eqc.closeToMe();
    }

    public void testQuakesOfDepth() {
        eqc.quakesOfDepth();
    }

    public void testQuakesByPhrase() {
        eqc.quakesByPhrase();
    }

    public void testFindClosestQuakes() {
        cq.findClosestQuakes();
    }

    public void testFindLargestQuakes() {
        lq.findLargestQuakes();
    }

    public static void main(String[] args) {
        Tester t = new Tester();
//        t.testBigQuakes();
//        t.testCloseToMe();
//        t.testQuakesOfDepth();
//        t.testQuakesByPhrase();
        //t.testFindClosestQuakes();
        t.testFindLargestQuakes();
    }
}
