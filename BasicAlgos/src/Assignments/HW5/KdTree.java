package Assignments.HW5;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
	private enum Separator { VERTICAL, HORIZONTAL };
	private Node root;
	private int size;
	
	private class Node {
		private Separator sep;
		private Point2D pt;
		private RectHV rect;
		private Node left;
		private Node right;
		
		public Separator nextSeparator() {
			return this.sep == Separator.VERTICAL ? 
					Separator.HORIZONTAL : Separator.VERTICAL;
		}
		
		public Node(Separator sep, Point2D pt, RectHV rect) {
			this.sep = sep;
			this.pt = pt;
			this.rect = rect;
		}

		public boolean isLeftChild(Point2D pt) {
			if (null == pt) return false;
			if (this.sep == Separator.VERTICAL) {
				if (this.pt.x() > pt.x()) return true;
				else return false;
			} else {
				if (this.pt.y() > pt.y()) return true;
				else return false;
			}
		}

		public boolean isRightChild(Point2D pt) {
			if (null == pt) return false;
			if (this.sep == Separator.VERTICAL) {
				if (!(this.pt.x() > pt.x())) return true;
				else return false;
			} else {
				if (!(this.pt.x() > pt.x())) return true;
				else return false;
			}
		}

		public RectHV getLeftChildRect() {
			return this.sep == Separator.VERTICAL ?
				new RectHV(this.rect.xmin(), this.rect.ymin(), this.pt.x(), this.rect.ymax()) :
				new RectHV(this.rect.xmin(), this.rect.ymin(), this.rect.xmax(), this.pt.y());
		}

		public RectHV getRightChildRect() {
			return this.sep == Separator.VERTICAL ?
				new RectHV(this.pt.x(), this.rect.ymin(), this.rect.xmax(), this.rect.ymax()) :
				new RectHV(this.rect.xmin(), this.pt.y(), this.rect.xmax(), this.rect.ymax());			
		}
	}
	
	public KdTree() {
		this.root = null;
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return 0 == this.size;
	} 

	public int size() {
		// number of points in the set
		return this.size;
	}  

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (null == p) throw new IllegalArgumentException("Call the insert() with a null pointer!");
		if (null == root) {
			root = new Node(Separator.VERTICAL, p, new RectHV(0.0, 0.0, 1.0, 1.0));
			size++;
			return;
		}
		Node currNode = root; Node prevNode = null;
		root = insert(currNode, prevNode, p);
		size++;
	}
	
	private Node insert(Node currNode, Node prevNode, Point2D p) {
		if (null == currNode) {
			currNode = prevNode.isLeftChild(p) ?
						new Node(prevNode.nextSeparator(), p, prevNode.getLeftChildRect()) :
						new Node(prevNode.nextSeparator(), p, prevNode.getRightChildRect());
		}
		Separator currSep = currNode.sep;
		if (currSep == Separator.VERTICAL) {
			if (currNode.pt.x() > p.x()) currNode.left = insert(currNode.left, currNode, p);
			else currNode.right = insert(currNode.right, currNode, p);
		} else {
			if (currNode.pt.y() > p.y()) currNode.left = insert(currNode.left, currNode, p);
			else currNode.right = insert(currNode.right, currNode, p);
		}
		return currNode;	
	}
	           
	public boolean contains(Point2D p) {
		// does the set contain point p?
		if (null == p) throw new IllegalArgumentException("Call the contains() with a null pointer!");
		Node currNode = root;
		while (currNode != null) {
			if (currNode.pt.equals(p)) return true;
			currNode = currNode.isLeftChild(p) ? currNode.left : currNode.right;
		}
		return false;
	}
	
	public void draw() {
		// TODO: draw all points to standard draw
	}
	                        
	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		if (null == rect) throw new IllegalArgumentException("Call the range() with a null pointer!");
		List<Point2D> result = new LinkedList<Point2D>();
		Node currNode = root;
		addToResultSet(currNode, rect, result);
		return result;
	} 

	private void addToResultSet(Node currNode, RectHV rect, List<Point2D> result) {
		if (null == currNode) return;
		// if the current node is contained in the rect
		if (rect.contains(currNode.pt)) {
			result.add(currNode.pt);
			addToResultSet(currNode.left, rect, result);
			addToResultSet(currNode.right, rect, result);
			return;
		}
		// or if the rect at left to the current node
		if (currNode.isLeftChild(new Point2D(rect.xmax(), rect.ymax())))
			addToResultSet(currNode.left, rect, result);
		// or if the rect at right to the current node
		if (!currNode.isLeftChild(new Point2D(rect.xmin(), rect.ymin())))
			addToResultSet(currNode.right, rect, result);
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		if (null == p) throw new IllegalArgumentException("Call the nearest() with a null pointer!");
		Node currNode = root;
		return isEmpty() ? null : nearest(currNode, currNode.pt, p);
	}

	private Point2D nearest(Node currNode, Point2D closestPt, Point2D targetPt) {
		if (null == currNode) return closestPt;
		double minDist = closestPt.distanceTo(targetPt); // current closest distance
		if (currNode.rect.distanceTo(targetPt) < minDist) {
			if (currNode.pt.distanceTo(targetPt) < minDist) {
				closestPt = currNode.pt;
			}
			// left child
			if (currNode.isLeftChild(targetPt)) {
				closestPt = nearest(currNode.right, closestPt, targetPt);
				closestPt = nearest(currNode.left, closestPt, targetPt);
			} 
			// right child
			else {
				closestPt = nearest(currNode.left, closestPt, targetPt);
				closestPt = nearest(currNode.right, closestPt, targetPt);
			}
		}
		return closestPt;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
