
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    
    private final LineSegment[] segArray;
    
    public FastCollinearPoints(Point[] inputPoints) {
        
        // check if the input is legal
        if (inputPoints == null) throw new IllegalArgumentException("No points.");
        for (int i = 0; i < inputPoints.length; i++) {
            if (inputPoints[i] == null) throw new IllegalArgumentException();
        }
        
        // deepcopy input, and check again if input is leagal
        Point[] points = inputPoints.clone();
        Arrays.sort(points);// sort is important, for duplicate check, and for avoiding 0- != 0+ cases
        int length = points.length;
        Point prev = points[0];
        for (int i = 1; i < length; i++)
            if (points[i].equals(prev)) throw new IllegalArgumentException();
            else prev = points[i];
        
        
        // Get all the line segments with reference to points[i]
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        for (int i = 0; i < length-1; i++) {
            
            Point[] pointsSlopeSort = points.clone();
            Arrays.sort(pointsSlopeSort, points[i].slopeOrder());
            
            // calculate slopes with reference to points[i]
            double[] slopes = new double[length];
            for (int j = 0; j < length; j++)
                slopes[j] = pointsSlopeSort[j].slopeTo(points[i]);
            
            // add line segments which includes >= 4 points
            // NEGTIVE_INFINITY only appears once, so end-start < 3
            int start = 0, end = 0;
            while (end <= length) {
                if (end == length || slopes[end] != slopes[start]) {
                    if ( end-start >= 3 ) {
                        Point[] linePoints = new Point[end-start+1];
                        System.arraycopy(pointsSlopeSort, start, linePoints, 0, end-start);
                        linePoints[end-start] = points[i]; // use the last place to store reference points[i]
                        LineSegment newSeg = getLineSegment(linePoints, points[i]);
                        if (newSeg != null) segments.add(newSeg);
                    }
                    start = end;
                }
                end++;
            }
        }
        segArray = segments.toArray(new LineSegment[segments.size()]);
    }
    
    private LineSegment getLineSegment(Point[] linePoints, Point curPoint) {
        Arrays.sort(linePoints);
        int length = linePoints.length;
        if (linePoints[0].equals(curPoint))
            return new LineSegment(linePoints[0], linePoints[length-1]);
        return null;
    }
    
    public int numberOfSegments() {
        return segArray.length;
    }
    
    public LineSegment[] segments() {
        return segArray.clone();
    }
}