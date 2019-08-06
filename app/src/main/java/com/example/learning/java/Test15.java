package com.example.learning.java;

import java.util.Scanner;

import static com.example.learning.java.Utils.println;

public class Test15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        println("n = " + n);

        int[][] a = new int[n - 1][2];
        Node[] nodes = new Node[n];
        for (int i = 0; i < n - 1; i++) {
            a[i][0] = scanner.nextInt();
            a[i][1] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        Node node;
        Node head = null;
        int p;
        for (int i = 0; i < n - 1; i++) {
            p = a[i][0];
            node = nodes[p];
            if (p == 0) {
                head = node;
            }
            if (node.left == null) {
                node.left = nodes[a[i][1]];
            } else {
                node.right = nodes[a[i][1]];
            }
        }

        println(depthOfTree(head));
    }

    private static int depthOfTree(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;

        final int leftChildDepth = depthOfTree(root.left);
        final int rightChildDepth = depthOfTree(root.right);

        depth += Math.max(leftChildDepth, rightChildDepth);

        return depth;
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
