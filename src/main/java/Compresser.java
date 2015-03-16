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
        PriorityQueue<Node> queue = new PriorityQueue<Node>(); // TODO: Replace with own code
        for (int i = 0; i < 128; i++) { // Create node for every ASCII char present in input
            int freq = charFreqs[i];
            if (freq > 0) queue.add(new Node((char) i, freq));
        }
        while (queue.size() > 1) {
            queue.add(new Node(queue.poll(), queue.poll())); // Construct tree
        }
        return queue.poll();
    }

    public String[] createCodes(Node node, StringBuilder code) {
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

