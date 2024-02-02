import java.lang.IllegalArgumentException;
import java.util.Arrays;
import edu.princeton.cs.algs4.Stack;

public class FastCollinearPoints {
    // finds all line segments containing 4 or more points
    private LineSegment[] segments_arr;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pcp = new Point[points.length];
        for (int i = 0; i < points.length; ++i) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            pcp[i] = points[i];
        }
        segments_arr = new LineSegment[0];
        Stack<LineSegment> segmentsSt = new Stack<>();
        for (Point p : points) {
            Arrays.sort(pcp, p.slopeOrder());
            int l = 0;
            int r = 0;
            double d = p.slopeTo(pcp[l]);
            int sameCnt = 1;
            if (d == Double.NEGATIVE_INFINITY) {
                while (r < pcp.length && p.slopeTo(pcp[r]) == d) {
                    ++r;
                }
                sameCnt = r - l;
            }
            l = r;
            Point minP = p;
            Point maxP = p;
            while (r < pcp.length) {
                d = p.slopeTo(pcp[l]);
                while (r < pcp.length && p.slopeTo(pcp[r]) == d) {
                    if (pcp[r].compareTo(minP) < 0) {
                        minP = pcp[r];
                    }
                    if (pcp[r].compareTo(maxP) > 0) {
                        maxP = pcp[r];
                    }
                    ++r;
                }
                if (sameCnt + r - l >= 4) {
                    segmentsSt.push(new LineSegment(minP, maxP));
                }
                l = r;
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
}