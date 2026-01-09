import java.util.Queue;
import java.util.LinkedList;

public class EvenOddTree {

    private class Node {
        public Node left, right;
        public int value;
        public Node() {

        }

        public Node(Node left, Node right, int value) {

            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    public Node buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        Node root = new Node(null, null, arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;

        while (i < arr.length) {
            Node current = queue.poll();

            // Left child
            if (i < arr.length && arr[i] != null) {
                current.left = new Node(null, null, arr[i]);
                queue.offer(current.left);
            }
            i++;

            // Right child
            if (i < arr.length && arr[i] != null) {
                current.right = new Node(null, null, arr[i]);
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        EvenOddTree eoTree = new EvenOddTree();
        Integer[] arr ={1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2};
        Node root = eoTree.buildTree(arr);
        boolean result = evenodd(root);
        System.out.println(result);
    }

    private static boolean evenodd(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int len = queue.size();
        boolean even = true;
        while (!queue.isEmpty()) {
            int prev = even ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < len; i++) {
                Node current = queue.remove();
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
                if (even) {
                    if (current.value % 2 ==0 || current.value <= prev) return false;
                } else {
                    if (current.value % 2 !=0 || current.value >= prev) return false;
                }

                prev = current.value;
            }

            len = queue.size();
            even = !even;
        }

        return true;
    }
}