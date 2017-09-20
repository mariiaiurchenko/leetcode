package com.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
Notes:
The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 */
public class TheSkylineProblem {
    private static class Point implements Comparable<Point> {
        int coordinate;
        int height;

        Point(int coordinate, int height) {
            this.coordinate = coordinate;
            this.height = height;
        }

        @Override
        public int compareTo(Point o) {
            int compare = this.coordinate - o.coordinate;
            if (compare == 0) {
                compare = this.height - o.height;
            }
            return compare;
        }
    }

    private int[] twoIntElArray(int x, int y) {
        int[] ar = new int[2];
        ar[0] = x;
        ar[1] = y;
        return ar;
    }

    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null) {
            return null;
        }

        if (buildings.length == 0) {
            return new LinkedList<>();
        }

        PriorityQueue<Point> points = new PriorityQueue<>();
        for (int[] building : buildings) {
            points.add(new Point(building[0], building[2]));
            points.add(new Point(building[1], 0 - building[2]));
        }

        List<int[]> result = new LinkedList<>();
        PriorityQueue<Integer> heights = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int coordinate = points.peek().coordinate;
        int height = -1;
        int localMax = 0;
        int lastCoordinate = coordinate;
        for (Point point = points.poll(); point != null; point = points.poll()) {
            if (point.height > 0) {
                heights.offer(point.height);
            } else {
                heights.remove(0 - point.height);
            }
            if (coordinate != point.coordinate) {
                if (height != localMax) {
                    result.add(twoIntElArray(coordinate, localMax));
                    height = localMax;
                }
                coordinate = point.coordinate;
                localMax = heights.size() > 0 ? heights.peek() : 0;
            } else {
                localMax = Math.max(localMax, heights.size() > 0 ? heights.peek() : 0);
            }
            lastCoordinate = point.coordinate;
        }
        result.add(twoIntElArray(lastCoordinate, 0));
        return result;
    }
}
