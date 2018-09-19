package com.leetcode;

import java.util.LinkedList;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */
public class MinStack {

    LinkedList<Integer> data;
    LinkedList<Integer> minData;

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-3);
        minStack.push(0);
        minStack.push(-2);
        System.out.println(minStack.getMin());
    }
    /**
     * initialize your data structure here.
     */
    public MinStack() {
        data = new LinkedList<>();
        minData = new LinkedList<>();
    }

    public void push(int x) {
        data.push(x);
        if (minData.size() == 0 || minData.peek() >= x) {
            minData.push(x);
        }
    }

    public void pop() {
        int remove = data.pop();
        if (minData.peek() == remove) {
            minData.pop();
        }
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minData.peek();
    }
}
