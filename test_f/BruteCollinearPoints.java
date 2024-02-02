import edu.princeton.cs.algs4.Stack;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] p_copy = new Point[points.length];
        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];
            if (p == null) {
                throw new IllegalArgumentException();
            }
            p_copy[i] = p;
        }
        for (int i = 0; i < p_copy.length - 1; ++i) {
            for (int j = i + 1; j < p_copy.length; ++j) {
                if (p_copy[i].compareTo(p_copy[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        if(p_copy.length < 4){
            lineSegments = new LineSegment[0];
            return;
        }
        Stack<LineSegment> segmentStack = new Stack<>();
        Point[] ps = new Point[4];
        for (int i1 = 0; i1 < p_copy.length - 3; ++i1) {
            for (int i2 = i1 + 1; i2 < p_copy.length - 2; ++i2) {
                for (int i3 = i2 + 1; i3 < p_copy.length - 1; ++i3) {
                    for (int i4 = i3 + 1; i4 < p_copy.length; ++i4) {
                        ps[0] = p_copy[i1];
                        ps[1] = p_copy[i2];
                        ps[2] = p_copy[i3];
                        ps[3] = p_copy[i4];

                        Double s1 = ps[0].slopeTo(ps[1]);
                        Double s2 = ps[0].slopeTo(ps[2]);
                        Double s3 = ps[0].slopeTo(ps[3]);
                        if (s1.equals(s2) && s2.equals(s3)) {
                            Arrays.sort(ps);
                            Point left_p = ps[0];
                            Point right_p = ps[3];
                            segmentStack.push(new LineSegment(left_p, right_p));
                        }
                    }
                }
            }
        }
        lineSegments = new LineSegment[segmentStack.size()];
        for (int i = 0; i < lineSegments.length; ++i) {
            lineSegments[i] = segmentStack.pop();
        }
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
