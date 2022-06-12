package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class GrahamAlgorithm {

    private Point p;

    private class PolarComparator implements Comparator<Point> {

        @Override
        public int compare(Point q, Point r) {
            int det = ((q.x - p.x) * (r.y - p.y)) - ((r.x - p.x) * (q.y - p.y));

            return -Integer.compare(det, 0);
        }
    }

    private Point findStartPoint(ArrayList<Point> points) {
        Point sp = points.get(0);

        for(Point i : points) {
            if(sp.y > i.y || (sp.y == i.y && sp.x > i.x)) {
                sp = i;
            }
        }
        return sp;
    }

    private int orientation(Point p1, Point p2, Point p3) {
        int det = ((p2.x - p1.x) * (p3.y - p1.y)) - ((p3.x - p1.x) * (p2.y - p1.y));

        return -Integer.compare(det, 0);
    }

    private ArrayList<Point> removeCollinear(ArrayList<Point> points) {
        Comparator<Point> cmp = new PolarComparator();

        for(int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            for(int j = i+1; j < points.size(); j++) {
                if(cmp.compare(point, points.get(j)) == 0) {
                    if(distance(p, point) > distance(p, points.get(j)))
                        points.remove(points.remove(j));
                    else
                        points.remove(point);
                }
            }
        }

        return points;
    }

    private int distance(Point p1, Point p2) {
        return ((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y));
    }

    public Stack<Point> convexHull(ArrayList<Point> points) {
        Stack<Point> ch = new Stack<>();
        p = findStartPoint(points);

        points.remove(p);
        points.sort(new PolarComparator());
        removeCollinear(points);
        System.out.println(points);

        ch.push(p);
        ch.push(points.get(0));
        ch.push(points.get(1));

        for(int i = 2; i < points.size(); i++) {
            while(orientation(ch.get(ch.size()-2), ch.peek(), points.get(i)) > 0)
                ch.pop();
            ch.push(points.get(i));
        }
        return ch;
    }
}
