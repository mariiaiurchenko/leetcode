package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 You may assume that the intervals were initially sorted according to their start times.
 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        Integer start = null;
        int i = 0;
        for (i = 0; i < intervals.size(); i++) {
            if (start == null) {
                if (intervals.get(i).start < newInterval.start) {
                    if (intervals.get(i).end < newInterval.start) {
                        result.add(intervals.get(i));
                    } else {
                        start = intervals.get(i).start;
                        if (intervals.get(i).end >= newInterval.end) {
                            result.add(new Interval(start, intervals.get(i).end));
                            i++;
                            break;
                        }
                    }
                } else if (intervals.get(i).start > newInterval.end) {
                    result.add(newInterval);
                    start = newInterval.start;
                    break;
                }
                if (intervals.get(i).end >= newInterval.end) {
                    result.add(new Interval(newInterval.start, intervals.get(i).end));
                    start = newInterval.start;
                    i++;
                    break;
                }
            } else {
                if (newInterval.end < intervals.get(i).start) {
                    result.add(new Interval(start, newInterval.end));
                    break;
                } else if (newInterval.end <= intervals.get(i).end) {
                    result.add(new Interval(start, intervals.get(i).end));
                    i++;
                    break;
                }
            }
        }

        if (i == intervals.size()) {
            if (start == null) {
                result.add(newInterval);
            } else if (intervals.get(i - 1).end < newInterval.end) {
                result.add(new Interval(start, newInterval.end));
            }
        } else {
            for (int j = i; j < intervals.size(); j++) {
                result.add(intervals.get(j));
            }
        }
        return result;
    }

    private class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
