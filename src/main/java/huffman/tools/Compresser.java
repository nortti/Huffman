package huffman.tools;

import huffman.datastructures.Node;
import java.io.File;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.PriorityQueue;

/**
 * A class which compresses a given file using Huffman coding.
 */
public class Compresser {

    /**
     * Method in which a compressed file is generated when Huffmans
     * construction algorithm is applied to the input.
     * @param inputFile source text file
     * @throws IOException file reading failed
     */
    public void generate(File inputFile) throws IOException {
        FileInfo fileInfo = new FileInfo(inputFile);
        PriorityQueue<Node> leaves = createLeaves(fileInfo.getCharFreqs());
        Node root = buildTree(leaves);
        String[] codeTable = createCodes(root, new StringBuilder());
    }

    /**
     * Goes through all 128 characters in the ASCII table, and creates and
     * enqueues a node for every character with a frequency greater than 0.
     * @param charFreqs table containing frequencies for all 128 ASCII characters
     * @return Min-heap of all leaves
     */
    public PriorityQueue<Node> createLeaves(int[] charFreqs) {
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
     * Builds a Huffman tree from the given leaves.
     * @param nodes the leaves
     * @return root of the tree
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
    * Traverses the Huffman tree recursively, assigning a binary code
    * to each leaf.
    * @param node current node
    * @param code binary code
    * @return a table with a binary code for each character
    */
    public String[] createCodes(Node node, StringBuilder code) {
        String[] codeTable = new String[128];
        if (node.getLeftChild() == null) { // Is leaf
            System.out.println(node.getCharacter() + ":" + code.toString());
            codeTable[node.getCharacter()] = code.toString();
        } else {
            createCodes(node.getLeftChild(), code.append('0'));
            createCodes(node.getRightChild(), code.append('1'));
        }
        return codeTable;
    }
}
