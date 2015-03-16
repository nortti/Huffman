import java.util.PriorityQueue;
import java.lang.StringBuilder;
import java.io.*;

public class Compresser {

    public void generate(File inputFile) throws IOException {
        FileInfo fileInfo = new FileInfo(inputFile);
        Node root = buildTree(fileInfo.getCharFreqs());
        String[] codeTable = createCodes(root, new StringBuilder());
    }

    private Node buildTree(int[] charFreqs) {
        PriorityQueue<Node> queue = createLeaves(charFreqs); // TODO: Replace PriorityQueue
        while (queue.size() > 1) { // Construct tree
            Node leftChild = queue.poll();
            Node rightChild = queue.poll();
            Node parent = new Node(leftChild, rightChild);
            queue.add(parent);
        }
        return queue.poll();
    }

    public PriorityQueue<Node> createLeaves(int[] charFreqs) {
        PriorityQueue<Node> leaves = new PriorityQueue();
        for (int i = 0; i < 128; i++) {
            int freq = charFreqs[i];
            if (freq > 0) {
                Node node = new Node((char) i, freq);
                leaves.add(node);
            }
        }
        return leaves;
    }

    public String[] createCodes(Node node, StringBuilder code) { // Traverses recursively
        String[] codeTable = new String[128];
        if (node.getLeftChild() == null) { // Is leaf
            codeTable[node.getCharacter()] = code.toString();
        } else {
            createCodes(node.getLeftChild(), code.append('0'));
            createCodes(node.getRightChild(), code.append('1'));
        }
        return codeTable;
    }
}
