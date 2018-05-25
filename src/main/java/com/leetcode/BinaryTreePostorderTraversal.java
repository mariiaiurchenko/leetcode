package com.leetcode;

/*
Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */

import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> pathToRoot = new LinkedList<>();
        pathToRoot.push(root);
        if (pathToRoot.peek() == null) {
            return result;
        }

        TreeNode lastProceeded = null;
        while (!pathToRoot.isEmpty()) {
            TreeNode checkStackHead = pathToRoot.peek();
            if ((lastProceeded != null && lastProceeded == checkStackHead.right)
                || (lastProceeded == checkStackHead.left && checkStackHead.right == null)
                || (checkStackHead.left == null && checkStackHead.right == null)) {
                TreeNode proccedNode = pathToRoot.pop();
                result.add(proccedNode.val);
                lastProceeded = proccedNode;
            } else if (lastProceeded == null
                || (lastProceeded != checkStackHead.left && lastProceeded != checkStackHead.right)) {
                if (checkStackHead.left != null) {
                    pathToRoot.push(checkStackHead.left);
                } else if (checkStackHead.right != null) {
                    pathToRoot.push(checkStackHead.right);
                }

            } else if (lastProceeded == checkStackHead.left) {
                if (checkStackHead.right != null) {
                    pathToRoot.push(checkStackHead.right);
                }
            }


        }

        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
