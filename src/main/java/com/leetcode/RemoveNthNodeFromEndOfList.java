package com.leetcode;

/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = null;
        ListNode cur = head;
        int count = 1;
        while (cur.next != null) {
            cur = cur.next;
            if (count < n) {
                count++;
            } else {
                prev = prev == null ? head : prev.next;
            }
        }
        if (prev == null && count == n) {
            head = head.next;
        } else if (prev != null) {
            prev.next = prev.next.next;
        }
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
