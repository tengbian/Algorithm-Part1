
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    
    private final LineSegment[] segArray;

    public BruteCollinearPoints(Point[] inputPoints) {
        
        if (inputPoints == null) throw new IllegalArgumentException("No points.");
        for (int i = 0; i < inputPoints.length; i++) {
            if (inputPoints[i] == null) throw new IllegalArgumentException();
        }
        Point[] points = inputPoints.clone();
        int length = points.length;
        
        Arrays.sort(points);
        
        Point prev = points[0];
        for (int i = 1; i < length; i++)
            if (points[i].equals(prev)) throw new IllegalArgumentException();
            else prev = points[i];
        
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

        // add line segments which includes 4 segments
        for (int i = 0; i < length; i++)
            for (int l = i+3; l < length; l++) {
                boolean checkSeg = false;
                for (int j = i+1; j < l; j++) {
                    if (checkSeg) break;
                    for (int k = j+1; k < l; k++) {
                        double slopej = points[i].slopeTo(points[j]);
                        double slopek = points[i].slopeTo(points[k]);
                        double slopel = points[i].slopeTo(points[l]);
                        if (slopej == slopek && slopej == slopel) { // here warning for float equality is fine
                            LineSegment newSeg = new LineSegment(points[i], points[l]);
                            segments.add(newSeg);
                            checkSeg = true;
                            break;
                        }                        
                    }
                }
            }
        segArray = segments.toArray(new LineSegment[segments.size()]);

    }
    
    public int numberOfSegments() {
        return segArray.length;
    }
    public LineSegment[] segments() {
        return segArray.clone();
    }
}