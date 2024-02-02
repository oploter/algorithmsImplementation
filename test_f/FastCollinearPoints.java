import edu.princeton.cs.algs4.Queue;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

public class FastCollinearPoints {
    private class MySegment {
        private Point p1;
        private Point p2;
        public Double slope;

        public MySegment(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.slope = this.p1.slopeTo(this.p2);
        }

        public LineSegment lineSegment() {
            return new LineSegment(p1, p2);
        }

        public boolean same(FastCollinearPoints.MySegment o) {
            return (p1.compareTo(o.p1) == 0 && p2.compareTo(o.p2) == 0)
                    || (p1.compareTo(o.p2) == 0 && p2.compareTo(o.p1) == 0);
        }
    }

    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] ps = new Point[points.length];
        Point[] p_copy = new Point[points.length];
        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];
            if (p == null) {
                throw new IllegalArgumentException();
            }
            ps[i] = p;
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
        Queue<MySegment> prevAllSegments = new Queue<>();
        Queue<MySegment> currAllSegments = new Queue<>();
        for (Point p : p_copy) {
            Arrays.sort(ps, p.slopeOrder());
            int st = 0;
            while (st < ps.length && p.slopeTo(ps[st]) == Double.NEGATIVE_INFINITY) {
                ++st;
            }
            Queue<MySegment> pSegments = new Queue<>();
            while (st < ps.length) {
                int cnt = 1;
                Point minPoint = p;
                Point maxPoint = p;
                Double currSlope = p.slopeTo(ps[st]);
                while (st < ps.length && p.slopeTo(ps[st]) == currSlope) {
                    ++cnt;
                    if (maxPoint.compareTo(ps[st]) < 0) {
                        maxPoint = ps[st];
                    }
                    if (minPoint.compareTo(ps[st]) > 0) {
                        minPoint = ps[st];
                    }
                    ++st;
                }
                if (cnt >= 4) {
                    pSegments.enqueue(new MySegment(minPoint, maxPoint));
                }
            }
            while (!pSegments.isEmpty()) {
                while (!prevAllSegments.isEmpty() && prevAllSegments.peek().slope < pSegments.peek().slope) {
                    currAllSegments.enqueue(prevAllSegments.dequeue());
                }
                boolean foundSame = false;
                while (!prevAllSegments.isEmpty() && prevAllSegments.peek().slope.equals(pSegments.peek().slope)) {
                    if (pSegments.peek().same(prevAllSegments.peek()) == true) {
                        pSegments.dequeue();
                        foundSame = true;
                        break;
                    } else {
                        currAllSegments.enqueue(prevAllSegments.dequeue());
                    }
                }
                if (!foundSame) {
                    currAllSegments.enqueue(pSegments.dequeue());
                }
            }
            while (!prevAllSegments.isEmpty()) {
                currAllSegments.enqueue(prevAllSegments.dequeue());
            }
            prevAllSegments = currAllSegments;
            currAllSegments = new Queue<>();
        }
        lineSegments = new LineSegment[prevAllSegments.size()];
        for (int i = 0; i < lineSegments.length; ++i) {
            lineSegments[i] = prevAllSegments.dequeue().lineSegment();
        }
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
