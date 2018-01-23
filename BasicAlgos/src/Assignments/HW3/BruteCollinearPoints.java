package Assignments.HW3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;
    private static final int POINTS_BATCH_NUM = 4;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicate(sortedPoints);

        int N = points.length;
        List<LineSegment> lsList = new LinkedList<LineSegment>();
        for (int i = 0; i < N - POINTS_BATCH_NUM + 1; i++) {
            Point pA = points[i];
            for (int j = i + 1; j < N - POINTS_BATCH_NUM + 2; j++) {
                Point pB = points[j];
                double slopeAB = pA.slopeTo(pB);

                for (int k = j + 1; k < N - POINTS_BATCH_NUM + 3; k++) {
                    Point pC = points[k];
                    double slopeAC = pA.slopeTo(pC);
                    if (slopeAB == slopeAC) {
                        for (int m = k + 1; m < N; m++) {
                            Point pD = points[m];
                            double slopeAD = pA.slopeTo(pD);
                            if (slopeAB == slopeAD) lsList.add(new LineSegment(pA, pD));
                        }
                    }
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
 }