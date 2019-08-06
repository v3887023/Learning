package com.example.learning.java;

import static com.example.learning.java.Utils.print;
import static com.example.learning.java.Utils.println;

public class BinaryTreeTest {
    public static void main(String[] args) {
        final TreeNode<Integer> tree = createTree();

        printTree(tree);
        println();

        final int depth = depthOfTree(tree);
        println(depth);
    }

    private static TreeNode<Integer> createTree() {
        TreeNode<Integer> sevenNode = new TreeNode<>(9);
        TreeNode<Integer> sixthNode = new TreeNode<>(6, sevenNode, null);
        TreeNode<Integer> fifthNode = new TreeNode<>(5, null, sixthNode);
        TreeNode<Integer> fourthNode = new TreeNode<>(4, null, fifthNode);
        TreeNode<Integer> secondNode = new TreeNode<>(2, fourthNode, null);
        TreeNode<Integer> thirdNode = new TreeNode<>(3, new TreeNode<>(7), new TreeNode<>(8));

        return new TreeNode<>(1, secondNode, thirdNode);
    }

    private static <T> int depthOfTree(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        int depth = 1;

        final int leftChildDepth = depthOfTree(root.left);
        final int rightChildDepth = depthOfTree(root.right);

        depth += Math.max(leftChildDepth, rightChildDepth);

        return depth;
    }

    private static <T> void printTree(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        print(String.valueOf(root.value));
        printTree(root.left);
        printTree(root.right);
    }

    static class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T value) {
            this.value = value;
        }

        TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
