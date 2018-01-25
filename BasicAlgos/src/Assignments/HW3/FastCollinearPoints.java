package Assignments.HW3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
<<<<<<< HEAD
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
=======
>>>>>>> 077fb1ab8588af92db16dd0932e763f37ad964ca

public class FastCollinearPoints {
    private final LineSegment[] lineSegments;
    private final List<LineSegment> lsList = new LinkedList<LineSegment>();
    private static final int POINTS_BATCH_NUM = 3;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicate(sortedPoints);

        int N = points.length;
        List<LineSegment> lsList = new LinkedList<LineSegment>();
        for (int i = 0; i < N; i++) {
            Point p = points[i];
            Point[] pointsBySlope = sortedPoints.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());

            int counter = 1;
            while (counter < N) {
                LinkedList<Point> candiList = new LinkedList<Point>();
                final double RF_SLOPE = p.slopeTo(pointsBySlope[counter]);
                do {
                    candiList.add(pointsBySlope[counter++]);
                }
                while (p.slopeTo(pointsBySlope[counter]) == RF_SLOPE && counter < N);  
                if (candiList.size() > POINTS_BATCH_NUM 
                    && p.compareTo(candiList.peek()) < 0) {
                    Point head = p; Point tail = candiList.getLast();
                    lsList.add(new LineSegment(head, tail));
                }
            }
        }
        lineSegments = lsList.toArray(new LineSegment[0]);

    }    
    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.length;
    }
    public LineSegment[] segments() {
        // the line segments
        return lineSegments.clone();
    }
    
    /**
     * check if the points array contains null elements 
     */
    private void checkNull(Point[] points) {
        if (null == points) throw new IllegalArgumentException();
        for (Point p : points) {
            if (null == p) throw new IllegalArgumentException();
        }
    }

    /**
     * check if the points array contains duplicated elements
     */
    private void checkDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0)
            throw new IllegalArgumentException();
        }
    }
<<<<<<< HEAD

    /**
     * Sample client
     */
    public static void main(String[] args) {
        
            // read the n points from a file
            In in = new In(args[0]);
            int n = in.readInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                int x = in.readInt();
                int y = in.readInt();
                points[i] = new Point(x, y);
            }
        
            // draw the points
            StdDraw.enableDoubleBuffering();
            StdDraw.setXscale(0, 32768);
            StdDraw.setYscale(0, 32768);
            for (Point p : points) {
                p.draw();
            }
            StdDraw.show();
        
            // print and draw the line segments
            FastCollinearPoints collinear = new FastCollinearPoints(points);
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
            StdDraw.show();
        }
=======
>>>>>>> 077fb1ab8588af92db16dd0932e763f37ad964ca
 }