import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        char[] string = {'h', 'e', 'l', 'l', 'o', 'h', 'e', 'l', 'l', 'o'};
        Arrays.sort(string); // eehhlllloo
        PriorityQueue<Node> queue = new PriorityQueue();
        Node node = new Node(string[0]);
        for (int i = 1 ; i < string.length ; i++) {
            if (node.c!=string[i]) {
                queue.add(node);
                node = new Node(string[i]);
            } else {
                node.amount++;
            }
        }
        queue.add(node);

        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll()));
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
        }

        public int compareTo(Node o) {
            return this.amount-o.amount;
        }
    }
}


