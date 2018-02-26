// package Assignments.HW5;

import java.util.Iterator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

/**
 * TODO: A set of points in the unit square
 */

public class PointSET {
	private SET<Point2D> pointSET;
	
	public PointSET() {
		// construct an empty set of points
		this.pointSET = new SET<Point2D>();
	}  

	public boolean isEmpty() {
		return this.pointSET.isEmpty();
	} 

	public int size() {
		// number of points in the set
		return this.pointSET.size();
	}  

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (null == p) throw new IllegalArgumentException("Call the insert() with a null pointer!");
		if (!contains(p)) this.pointSET.add(p);
	}  
	           
	public boolean contains(Point2D p) {
		// does the set contain point p?
		if (null == p) throw new IllegalArgumentException("Call the contains() with a null pointer!");
		return this.pointSET.contains(p);
	}
	
	public void draw() {
		// TODO: draw all points to standard draw
	}
	                        
	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		if (null == rect) throw new IllegalArgumentException("Call the range() with a null pointer!");
		SET<Point2D> pointsInRange = new SET<Point2D>();
		if (!this.pointSET.isEmpty()) {
			for (Point2D point : this.pointSET)
				if (rect.contains(point)) pointsInRange.add(point);
		}
		return pointsInRange;
	} 

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		if (null == p) throw new IllegalArgumentException("Call the nearest() with a null pointer!");
		if (!this.pointSET.isEmpty()) {
			Iterator<Point2D> points = pointSET.iterator();
			Point2D result = points.next();
			while (points.hasNext()) {
				Point2D tmp = points.next();
				if (tmp.distanceTo(p) < result.distanceTo(p))
					result = tmp;
			}
			return result;		
		} else
			return null;
	}              

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
