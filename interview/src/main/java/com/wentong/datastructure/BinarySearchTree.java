package com.wentong.datastructure;

import java.util.StringJoiner;

public class BinarySearchTree {

    static class Node {
        Node left;
        Node right;
        int value;

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("left=" + left)
                    .add("right=" + right)
                    .add("value=" + value)
                    .toString();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(new Node(new Node(null, null, 3), null, 4), new Node(null, null, 6), 5);
        insert(root, 10);
        printNode(root);
        Node delete = delete(root, 10);
//        int search = search(root, 199);
//        System.out.println(search);
    }

    private static Node search(Node root, int searchNum) {
        if (root == null) {
            return null;
        } else {
            if (root.value < searchNum) {
                return search(root.right, searchNum);
            } else if (root.value > searchNum) {
                return search(root.left, searchNum);
            } else {
                return root;
            }
        }
    }

    private static void insert(Node root, int insertNum) {
        Node parent = null;
        while (true) {
            if (root == null) {
                break;
            } else {
                if (root.value <= insertNum) {
                    parent = root;
                    root = root.left;
                } else {
                    parent = root;
                    root = root.right;
                }
            }
        }

        if (parent != null) {
            if (parent.value < insertNum) {
                parent.right = new Node(null, null, insertNum);
            } else {
                parent.left = new Node(null, null, insertNum);
            }
        }
    }

    private static Node delete(Node root, int deleteNum) {
        if (root == null) {
            return new Node(null, null, -1);
        } else {
            if (root.value < deleteNum) {
                return delete(root.right, deleteNum);
            } else if (root.value > deleteNum) {
                return delete(root.left, deleteNum);
            } else {
                if (isLeafNode(root)) {
                    root = null;
                    return root;
                } else {
                    if (root.right != null) {
                        root = root.right;
                        return root;
                    } else {
                        root = root.left;
                        return root;
                    }
                }
            }
        }
    }

    private static boolean isLeafNode(Node node) {
        return node.left == null && node.right == null;
    }

    static int midLeftNum = 10;
    static int midRightNum = 10;

    private static void printNode(Node node) {
        if (node != null) {
            printBlank(midLeftNum);
            printNum(node.value);
            System.out.println();
            if (node.left != null) {
                midLeftNum--;
                node = node.left;
                printBlank(midLeftNum);
                printSomething("/");
                System.out.println();
                midLeftNum--;
                printBlank(midLeftNum);
                printNum(node.value);
            }
            if (node.right != null) {
                node = node.right;
                midRightNum++;
                printBlank(midRightNum);
                printSomething("\\");
                System.out.println();
                midRightNum++;
                printBlank(midRightNum);
                printNum(node.value);
            }
            if (isLeafNode(node)) {
                return;
            }
            printNode(node);
        }
    }

    private static void printBlank(int blankNums) {
        for (int i = 0; i < blankNums; i++) {
            System.out.print(" ");
        }
    }

    private static void printNum(int num) {
        System.out.print(num);
    }

    private static void printSomething(String someThing) {
        System.out.print(someThing);
    }

}
