package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if (points.length < 3) {
            return points.length;
        }
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> countMap = new HashMap<>();
            int samePoint = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    samePoint++;
                } else {
                    double slope = getSlope(points[i], points[j]);
                    if (countMap.containsKey(slope)) {
                        countMap.put(slope, countMap.get(slope) + 1);
                    } else {
                        countMap.put(slope, 2);
                    }
                }
            }
            count = Math.max(count, samePoint + 1);
            for (int localCount : countMap.values()) {
                count = Math.max(count, localCount + samePoint);
            }
        }
        return count;
    }

    private double getSlope(Point p1, Point p2) {
        if (p1.x == p2.x) {
            return Double.MAX_VALUE;
        }
        if (p1.y == p2.y) {
            return 0;
        }
        return (double) (p1.y - p2.y) / (double) (p1.x - p2.x);
    }

    private class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }
        Point(int a, int b) {
            x = a;
            y = b;
        }
    }
}
