import java.util.PriorityQueue;
import java.io.*;

public class Main {
    private static FileInputStream fileInput;
    private static int[] charFreqs = new int[128];
    private static String[] codeTable = new String[128];
    private static String tree = "";

    public static void main (String[] args) throws IOException {
        fileInput = new FileInputStream("src/main/resources/" + args[0]);
        if (args[1].equals("-d")) {
            decode();
        } else {
            encode();
        }
    }

    public static void encode() throws IOException {
        PriorityQueue<Node> queue = new PriorityQueue<Node>(); // TODO: Replace with own code
        int r;
        String input = "";
        while ((r = fileInput.read()) != -1) {
            input += (char) r;
            charFreqs[r]++;
        }
        for (int i = 0 ; i < charFreqs.length ; i++) {
            if (charFreqs[i]>0) {
                queue.add(new Node((char) i, charFreqs[i]));
            }
        }
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll())); // Construct tree
        }
        Node root = queue.poll();
        createCodes(root, "");
        String output = tree + ' ';
        for (int i = 0; i<input.length() ; i++) output+="."+codeTable[input.charAt(i)]+".";
        System.out.print(output);
    }

    public static void decode() {
    }

    public static void createCodes(Node node, String code) {
        if (node.leftChild==null && node.rightChild==null) {
            codeTable[node.c] = code;
            tree += "1" + node.c;
        } else {
            tree += '0';
            createCodes(node.leftChild, code + '0');
            createCodes(node.rightChild, code + '1');
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


