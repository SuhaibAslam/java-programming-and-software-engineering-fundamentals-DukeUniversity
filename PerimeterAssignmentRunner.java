import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
        public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest1.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        System.out.println("number of points = " + numPoints);
        System.out.println("perimeter = " + length);
        System.out.println("average length = " + avgLength);
        System.out.println("largest side = " + largestSide);
        testPerimeterMultipleFiles();
        System.out.println(getFileWithLargestPerimeter());
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for (Point pointedAt : s.getPoints()) {
            numPoints += 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perim = getPerimeter(s);
        int numberOfSides = getNumPoints(s);
        double avgLength = perim/numberOfSides;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double currDist = 0.0;
        double newDist = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            currDist = prevPt.distance(currPt);
            if (currDist > newDist) {
                newDist = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        return newDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        return 0.0;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double currPerimeter = 0.0;
        double newPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currPerimeter = getPerimeter(s);
            if (currPerimeter > newPerimeter) {
                newPerimeter = currPerimeter;
            }
        }
        return newPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double currPerimeter = 0.0;
        double newPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            currPerimeter = getPerimeter(s);
            if (currPerimeter > newPerimeter) {
                newPerimeter = currPerimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = this.getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter of Multiple Files is: " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        System.out.println("Hello world!");
    }
}
