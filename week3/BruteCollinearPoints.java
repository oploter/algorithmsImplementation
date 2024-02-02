import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;
import java.lang.IllegalArgumentException;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private LineSegment[] segments_arr;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pcp = Arrays.copyOf(points, points.length);
        for (Point p : pcp) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(pcp);
        segments_arr = new LineSegment[0];
        Stack<LineSegment> segmentsSt = new Stack<>();
        for(int i = 0; i < pcp.length - 1; ++i){
            if(pcp[i].compareTo(pcp[i + 1]) == 0){
                throw new IllegalArgumentException();
            }
        }
        if (pcp.length < 4) {
            return;
        }
        for (int i1 = 0; i1 < pcp.length - 3; ++i1) {
            Point p = pcp[i1];
            for (int i2 = i1 + 1; i2 < pcp.length - 2; ++i2) {
                Point q = pcp[i2];
                for (int i3 = i2 + 1; i3 < pcp.length - 1; ++i3) {
                    Point r = pcp[i3];
                    for (int i4 = i3 + 1; i4 < pcp.length; ++i4) {
                        Point s = pcp[i4];
                        double slope1 = p.slopeTo(q);
                        double slope2 = p.slopeTo(r);
                        double slope3 = p.slopeTo(s);
                        if (slope1 == slope2 && slope2 == slope3) {
                            Point[] pts = { p, q, r, s };
                            Arrays.sort(pts);
                            segmentsSt.push(new LineSegment(pts[0], pts[3]));
                        }
                    }
                }
            }
        }
        segments_arr = new LineSegment[segmentsSt.size()];
        for (int i = 0; i < segments_arr.length; ++i) {
            segments_arr[i] = segmentsSt.pop();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments_arr.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments_arr;
    }

    public static void main(String[] args){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(34, 1);
        Point p4 = new Point(2, 3);
        System.out.println(p2.compareTo(p4));
        Point[] pts = {p1, p2, p3, p4};
        BruteCollinearPoints bcp = new BruteCollinearPoints(pts);
        System.out.println(bcp.numberOfSegments());
    }
}