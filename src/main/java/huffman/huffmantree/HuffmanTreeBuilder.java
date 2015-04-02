package huffman.huffmantree;

import static huffman.io.FileConverter.CHARSET_SIZE;
import huffman.datastructures.Node;
import huffman.datastructures.PriorityQueue;

/**
 * Builds huffman trees from uncompressed data.
 */
public class HuffmanTreeBuilder implements HuffmanTreeMaker {

    /**
     * A simple huffman-tree making algorithm.
     * @param data Uncompressed data
     */
    public HuffmanTree makeTree(byte[] data) {
        int[] charFreqs = countCharFreqs(data);
        PriorityQueue priorityQueue = createLeaves(charFreqs);
        HuffmanTree huffmanTree = buildTree(priorityQueue);
        return huffmanTree;
    }

    /**
     * Creates a character-frequency lookup table.
     */
    private static int[] countCharFreqs(byte[] data) {
        int[] charFreqs = new int[CHARSET_SIZE];
        for (byte charByte : data) {
            charFreqs[(char) charByte]++;
        }
        return charFreqs;
    }

    /**
     * Creates a priorityqueue of nodes, where nodes represent characters and lower frequency has
     * higher priority.
     */
    private static PriorityQueue createLeaves(int[] charFreqs) {
        int leafCount = countUnique(charFreqs);
        // leafCount is the max-size of the PQ
        PriorityQueue leaves = new PriorityQueue(leafCount); 
        // Add a node for each character that occurs
        for (int charInt = 0; charInt < charFreqs.length; charInt++) { 
            int freq = charFreqs[charInt];
            if (freq > 0) {                
                Node node = new Node((char) charInt, freq);
                leaves.add(node);
            }
        }

        leaves.add(new Node((char) 0, 0)); // EOF character
        return leaves;
    }

    private static int countUnique(int[] charFreqs) {
        int maxSize = 0;
        for (int freq : charFreqs) {
            if (freq > 0) {
                maxSize++;
            }
        }
        return (maxSize + 1);
    }

    private static HuffmanTree buildTree(PriorityQueue nodes) {
        while (nodes.getSize() > 1) {
            Node leftChild = nodes.poll();
            Node rightChild = nodes.poll();
            Node parent = new Node(leftChild, rightChild);
            nodes.add(parent);
        }
        Node root = nodes.poll();
        HuffmanTree huffmanTree = new HuffmanTree(root);
        return huffmanTree;
    }
}
