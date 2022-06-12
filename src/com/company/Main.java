package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    GrahamAlgorithm graham = new GrahamAlgorithm();

        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(0, -2));
        points.add(new Point(1, 10));
        points.add(new Point(2, 5));
        points.add(new Point(4, 3));
        points.add(new Point(0, 0));
        points.add(new Point(1, -2));
        points.add(new Point(3, 1));
        points.add(new Point(3, 3));

        System.out.println(graham.convexHull(points));
    }
}
