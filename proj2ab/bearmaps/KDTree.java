package bearmaps;

import java.util.*;

public class KDTree implements PointSet {

    private Node root;

    private class Node {
        Point point;
        Node left;
        Node right;
        int dimensionality;
        Node(Point point, int dimensionality) {
            this.point = point;
            this.dimensionality = dimensionality;
        }
    }

    private Node add(Node node, Point point, int dimensionality) {
        if (node == null) {
            return new Node(point, dimensionality);
        }
        if (point.equals(node.point)) {
            node.point = point;
            return node;
        }
        if (node.dimensionality == 0) {
            if (point.getX() < node.point.getX()) {
                node.left = add(node.left, point, 1);
            } else {
                node.right = add(node.right, point, 1);
            }
        } else {
            if (point.getY() < node.point.getY()) {
                node.left = add(node.left, point, 0);
            } else {
                node.right = add(node.right, point, 0);
            }
        }
        return node;
    }

    private void add(Point point) {
        Node node = add(root, point, 0);
        if (root == null) {
            root = node;
        }
    }

    public KDTree(List<Point> points) {
        for (Point point : points) {
            this.add(point);
        }
    }

    private void buildPath(Stack<Node> searchPath, Node node, Point point) {
        Node cur = node;
        while (cur != null) {
            searchPath.push(cur);
            if (cur.dimensionality == 0) {
                if (point.getX() < cur.point.getX()) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            } else {
                if (point.getY() < cur.point.getY()) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
        }
    }

    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        Stack<Node> searchPath = new Stack<>();
        buildPath(searchPath, root, point);
        Node nearsetNode = null;
        double minDistance = Double.MAX_VALUE;
        while (!searchPath.isEmpty()) {
            Node backPoint = searchPath.pop();
            double distance = Point.distance(point, backPoint.point);
            if (nearsetNode == null || distance < minDistance) {
                nearsetNode = backPoint;
                minDistance = distance;
            }
            Node tmp = null;
            if (backPoint.dimensionality == 0) {
                if (Math.abs(backPoint.point.getX() - point.getX()) < minDistance) {
                    if (point.getX() < backPoint.point.getX()) {
                        tmp = backPoint.right;
                    } else {
                        tmp = backPoint.left;
                    }
                }
            } else {
                if (Math.abs(backPoint.point.getY() - point.getY()) < minDistance) {
                    if (point.getY() < backPoint.point.getY()) {
                        tmp = backPoint.right;
                    } else {
                        tmp = backPoint.left;
                    }
                }
            }
            if (tmp != null) {
                buildPath(searchPath, tmp, point);
            }
        }
        return nearsetNode.point;
    }

    public static void main(String[] args) {
//        Point p1 = new Point(2, 3);
//        Point p2 = new Point(4, 2);
//        Point p3 = new Point(4, 2);
//        Point p4 = new Point(4, 5);
//        Point p5 = new Point(3, 3);
//        Point p6 = new Point(1, 5);
//        Point p7 = new Point(4, 4);
//
//        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
