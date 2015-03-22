package huffman.datastructures;

import static huffman.io.FileChanger.CHARSET_SIZE;
import huffman.io.BitInputStream;
import java.util.PriorityQueue;

/**
 * Class that builds a Huffman tree and supplies the encodings generated from traversing it
 */
public class HuffmanTree {

    private String[] codes = new String[CHARSET_SIZE];
    private Node root;

    /**
     * Calls for the construction of a Huffman tree and the codes related to it.
     * @param charFreqs The character frequencies
     */
    public HuffmanTree (int[] charFreqs) {
        PriorityQueue<Node> minHeap = createLeaves(charFreqs);
        this.root = buildTree(minHeap);
        setCodes(root, "");
    }

    public HuffmanTree(BitInputStream bitInputStream) {
        this.root = decodeTree(bitInputStream);
        setCodes(root, "");
    }

    /**
     * Goes through all 256 characters in the extended ASCII table, while creating and enqueueing a node for
     * every character with a frequency greater than 0.
     * @param charFreqs table containing frequencies for all 256 ASCII characters
     * @return Min-heap of all leaves
     */
    private PriorityQueue<Node> createLeaves(int[] charFreqs) {
        PriorityQueue<Node> leaves = new PriorityQueue<Node>();
        for (int charInt = 0; charInt < charFreqs.length; charInt++) {
            int freq = charFreqs[charInt];
            if (freq > 0) {
                System.out.println(charInt);
                Node node = new Node((char)charInt, freq);
                leaves.add(node);
            }
        }
        // EOF char
        leaves.add(new Node((char)0, 0));
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
    * constructing the encoded bit representation of the tree.
    * @param node current node
    * @param code binary code
    * @return a table with a binary code for each character
    */
    private void setCodes(Node node, String code) {
        if (node.getLeftChild() == null) { // Is leaf
            this.codes[node.getCharacter()] = code;
        } else {
            setCodes(node.getLeftChild(), code + '0');
            setCodes(node.getRightChild(), code + '1');
        }
    }

    public Node decodeTree(BitInputStream bitInputStream) {
        boolean isSet = bitInputStream.read();
        if (isSet) {
            char character = (char)bitInputStream.readByte();
            return new Node(character, 0);
        } else {
            Node leftChild = decodeTree(bitInputStream);
            Node rightChild = decodeTree(bitInputStream);
            Node parent = new Node(leftChild, rightChild);
            return parent;
        }
    }

    public String[] getCodes() {
        return this.codes;
    }

    public Node getRoot() {
        return this.root;
    }
}
