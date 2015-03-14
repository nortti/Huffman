import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static HashMap<Character, String> map = new HashMap<Character, String>();
    public static void main (String[] args) {
        char[] string = {'h', 'e', 'l', 'l', 'o', 'h', 'e', 'l', 'l', 'o'};
        char[] stringCopy = string;
        Arrays.sort(stringCopy); // TODO: Replace with own code
        PriorityQueue<Node> queue = new PriorityQueue<Node>(); // TODO: Replace with own code
        Node node = new Node(stringCopy[0]);
        for (int i = 1 ; i < stringCopy.length ; i++) { // Create leaf nodes
            if (node.c!=stringCopy[i]) {
                queue.add(node);
                node = new Node(stringCopy[i]);
            } else {
                node.amount++;
            }
        }
        queue.add(node);

        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll())); // Construct tree
        }
        map(queue.poll(), "");
        System.out.println(map);
    }

    public static void map(Node node, String code) {
        if (node.leftChild==null) {
            map.put(node.c, code);
        } else {
            map(node.leftChild, code + '1');
            map(node.rightChild, code + '0');
        }

    }


    public static class Node implements Comparable<Node> {
        char c;
        int amount = 1;
        Node leftChild;
        Node rightChild;

        public Node(char c) {
            this.c=c;
        }

        public Node(Node leftChild, Node rightChild) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.amount = leftChild.amount+rightChild.amount;
        }

        public int compareTo(Node o) {
            return this.amount-o.amount;
        }
    }
}


