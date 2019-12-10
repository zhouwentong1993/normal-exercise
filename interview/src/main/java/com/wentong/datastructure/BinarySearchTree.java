//package com.wentong.datastructure;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringJoiner;
//
//public class BinarySearchTree {
//
//    static class Node {
//        Node left;
//        Node right;
//        int value;
//
//        public Node(Node left, Node right, int value) {
//            this.left = left;
//            this.right = right;
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
//                    .add("left=" + left)
//                    .add("right=" + right)
//                    .add("value=" + value)
//                    .toString();
//        }
//    }
//
//    public static void main(String[] args) {
//        Node root = new Node(new Node(new Node(null, null, 3), null, 4), new Node(null, null, 6), 5);
//        insert(root, 10);
//        printTree(root);
//        Node delete = delete(root, 10);
////        int search = search(root, 199);
////        System.out.println(search);
//    }
//
//    private static Node search(Node root, int searchNum) {
//        if (root == null) {
//            return null;
//        } else {
//            if (root.value < searchNum) {
//                return search(root.right, searchNum);
//            } else if (root.value > searchNum) {
//                return search(root.left, searchNum);
//            } else {
//                return root;
//            }
//        }
//    }
//
//    private static void insert(Node root, int insertNum) {
//        Node parent = null;
//        while (true) {
//            if (root == null) {
//                break;
//            } else {
//                if (root.value <= insertNum) {
//                    parent = root;
//                    root = root.right;
//                } else {
//                    parent = root;
//                    root = root.left;
//                }
//            }
//        }
//
//        if (parent != null) {
//            if (parent.value < insertNum) {
//                parent.right = new Node(null, null, insertNum);
//            } else {
//                parent.left = new Node(null, null, insertNum);
//            }
//        }
//    }
//
//    private static Node delete(Node root, int deleteNum) {
//        if (root == null) {
//            return new Node(null, null, -1);
//        } else {
//            if (root.value < deleteNum) {
//                return delete(root.right, deleteNum);
//            } else if (root.value > deleteNum) {
//                return delete(root.left, deleteNum);
//            } else {
//                if (isLeafNode(root)) {
//                    root = null;
//                    return root;
//                } else {
//                    if (root.right != null) {
//                        root = root.right;
//                        return root;
//                    } else {
//                        root = root.left;
//                        return root;
//                    }
//                }
//            }
//        }
//    }
//
//    private static boolean isLeafNode(Node node) {
//        return node.left == null && node.right == null;
//    }
//
//    static int midLeftNum = 10;
//    static int midRightNum = 10;
//
//    private static void printNode(Node node) {
//        if (node != null) {
//            printBlank(midLeftNum);
//            printNum(node.value);
//            System.out.println();
//            if (node.left != null) {
//                midLeftNum--;
//                node = node.left;
//                printBlank(midLeftNum);
//                printSomething("/");
//                System.out.println();
//                midLeftNum--;
//                printBlank(midLeftNum);
//                printNum(node.value);
//            }
//            if (node.right != null) {
//                node = node.right;
//                midRightNum++;
//                printBlank(midRightNum);
//                printSomething("\\");
//                System.out.println();
//                midRightNum++;
//                printBlank(midRightNum);
//                printNum(node.value);
//            }
//            if (isLeafNode(node)) {
//                return;
//            }
//            printNode(node);
//        }
//    }
//
//    private static void printBlank(int blankNums) {
//        for (int i = 0; i < blankNums; i++) {
//            System.out.print(" ");
//        }
//    }
//
//    private static void printNum(int num) {
//        System.out.print(num);
//    }
//
//    private static void printSomething(String someThing) {
//        System.out.print(someThing);
//    }
//
//
//    // 层次遍历
//    private void levelIterator(Node root) {
//        if (root == null) {
//            return;
//        }
//        LinkedList<Node> queue = new LinkedList<>();
//        Node current;
//        queue.offer(root);//将根节点入队
//        while (!queue.isEmpty()) {
//            current = queue.poll();//出队队头元素并访问
//            System.out.print(current.value + "-->");
//            if (current.left != null)//如果当前节点的左节点不为空入队
//            {
//                queue.offer(current.left);
//            }
//            if (current.right != null)//如果当前节点的右节点不为空，把右节点入队
//            {
//                queue.offer(current.right);
//            }
//        }
//    }
//
//    public static void printTree(Node root) {
//        if (root == null)
//            return;
//        Queue<Node> queue = new LinkedList<>();
//
//        int current;//当前层 还未打印的结点个数
//        int next;//下一层结点个数
//
//        queue.offer(root);
//        current = 1;
//        next = 0;
//        while (!queue.isEmpty()) {
//            Node currentNode = queue.poll();
//            System.out.printf("%-4d", currentNode.value);
//            current--;
//
//            if (currentNode.left != null) {
//                queue.offer(currentNode.left);
//                next++;
//            }
//            if (currentNode.right != null) {
//                queue.offer(currentNode.right);
//                next++;
//            }
//            if (current == 0) {
//                System.out.println();
//                current = next;
//                next = 0;
//            }
//        }
//    }
//
//
//}
