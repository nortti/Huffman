package huffman.datastructures;

import huffman.io.BitWriter;
import java.util.PriorityQueue;

/**
 * Class that builds a Huffman tree and supplies the encodings generated from traversing it
 */
public class HuffmanTree {

    private byte[] encoded;
    private String[] codeTable = new String[128];

    /**
     * Calls for the construction of a Huffman tree and the codes related to it.
     * @param charFreqs The character frequencies
     */
    public HuffmanTree (int[] charFreqs) {
        PriorityQueue<Node> minHeap = createLeaves(charFreqs);
        Node root = buildTree(minHeap);
        BitWriter bitWriter = new BitWriter();
        createCodes(root, "", bitWriter);
        this.encoded = bitWriter.read();
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
    * Traverses the Huffman tree recursively, assigning a binary code to each leaf, as well as
    * constructing the encoded String representation of the tree.
    * @param node current node
    * @param code binary code
    * @return a table with a binary code for each character
    */
    private void createCodes(Node node, String code, BitWriter bitWriter) {
        if (node.getLeftChild() == null) { // Is leaf
            this.codeTable[node.getCharacter()] = code.toString();
            bitWriter.write(true);
            bitWriter.write(node.getCharacter());
        } else {
            bitWriter.write(false);
            createCodes(node.getLeftChild(), code += '0', bitWriter);
            createCodes(node.getRightChild(), code += '1', bitWriter);
        }
    }

    public String[] getCodeTable() {
        return this.codeTable;
    }

    public byte[] getEncoded() {
        return this.encoded;
    }
}
