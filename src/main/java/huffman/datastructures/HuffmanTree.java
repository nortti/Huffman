package huffman.datastructures;

import java.util.PriorityQueue;
import java.lang.StringBuilder;

/**
 * Class that builds a Huffman tree and supplies the encodings generated from traversing it
 */
public class HuffmanTree {

    private StringBuilder encoded = new StringBuilder();
    private String[] codeTable = new String[128];

    /**
     * Calls for the construction of a Huffman tree and the codes related to it.
     * @param charFreqs The character frequencies
     */
    public HuffmanTree (int[] charFreqs) {
        PriorityQueue<Node> minHeap = createLeaves(charFreqs);
        Node root = buildTree(minHeap);
        createCodes(root, new StringBuilder());
    }

    /**
     * Goes through all 128 characters in the ASCII table, while creating and enqueueing a node for
     * every character with a frequency greater than 0.
     * @param charFreqs table containing frequencies for all 128 ASCII characters
     * @return Min-heap of all leaves
     */
    private PriorityQueue<Node> createLeaves(int[] charFreqs) {
        PriorityQueue<Node> leaves = new PriorityQueue<Node>();
        for (int i = 0; i < 128; i++) {
            int freq = charFreqs[i];
            if (freq > 0) {
                Node node = new Node((char) i, freq);
                leaves.add(node);
            }
        }
        return leaves;
    }

    /**
     * Builds a Huffman tree.
     * @param nodes The nodes
     * @return Root of the tree
     */
    private Node buildTree(PriorityQueue<Node> nodes) {
        while (nodes.size() > 1) {
            Node leftChild = nodes.poll();
            Node rightChild = nodes.poll();
            Node parent = new Node(leftChild, rightChild);
            nodes.add(parent);
        }
        return nodes.poll();
    }

   /**
    * Traverses the Huffman tree recursively, assigning a binary code  to each leaf, as well as
    * constructing the encoded String representation of the tree.
    * @param node current node
    * @param code binary code
    * @return a table with a binary code for each character
    */
    private void createCodes(Node node, StringBuilder code) {
        if (node.getLeftChild() == null) { // Is leaf
            this.codeTable[node.getCharacter()] = code.toString();
            this.encoded.append('1');
            String binaryAsciiChar = Integer.toBinaryString(node.getCharacter());
            for (int i = 0; i < 8 - binaryAsciiChar.length(); i++) {
                this.encoded.append('0');
            }
            this.encoded.append(binaryAsciiChar);
        } else {
            this.encoded.append('0');
            createCodes(node.getLeftChild(), new StringBuilder(code).append('0'));
            createCodes(node.getRightChild(), new StringBuilder(code).append('1'));
        }
    }

    public String[] getCodeTable() {
        return this.codeTable;
    }

    public String getEncoded() {
        return this.encoded.toString();
    }
}
