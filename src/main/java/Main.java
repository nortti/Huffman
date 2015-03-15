import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static HashMap<Character, String> map = new HashMap<Character, String>();
    private static String treeString = "";
    private static int[] charFreqs = new int[128];
    public static void main (String[] args) {
        char[] string = {'h', 'e', 'l', 'l', 'o', 'h', 'e', 'l', 'l', 'o'};
        PriorityQueue<Node> queue = new PriorityQueue<Node>(); // TODO: Replace with own code
        for (int i = 0 ; i < string.length ; i++) {
            charFreqs[string[i]]++;
        }
        for (int i  = 0 ; i < charFreqs.length ; i++ ) {
            if (charFreqs[i]>0) {
                queue.add(new Node((char) i, charFreqs[i]));
            }
        }
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll())); // Construct tree
        }
        Node root = queue.poll();
        map(root, "");
        System.out.print(map);
        System.out.println(treeString);
    }

    public static void map(Node node, String code) {
        if (node.leftChild==null && node.rightChild == null) {
            map.put(node.c, code);
            treeString += '1' + "" + node.c;
        } else {
            treeString += '0';
            map(node.leftChild, code + '1');
            map(node.rightChild, code + '0');
        }

    }

    public static class Node implements Comparable<Node> {
        char c;
        int freq;
        Node leftChild;
        Node rightChild;

        public Node(char c, int freq) {
            this.c=c;
            this.freq=freq;
        }

        public Node(Node leftChild, Node rightChild) {
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.freq = leftChild.freq+rightChild.freq;
        }

        public int compareTo(Node o) {
            return this.freq-o.freq;
        }
    }
}


