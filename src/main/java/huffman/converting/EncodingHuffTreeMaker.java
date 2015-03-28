package huffman.converting;

import static huffman.io.FileConverter.CHARSET_SIZE;
import huffman.datastructures.PriorityQueue;
import huffman.datastructures.Node;

public class EncodingHuffTreeMaker implements HuffmanTreeMaker {

    public HuffmanTree makeTree(byte[] data) {
        int[] charFreqs = countCharFreqs(new String(data));
        PriorityQueue priorityQueue = createLeaves(charFreqs);
        HuffmanTree huffmanTree = buildTree(priorityQueue);
        return huffmanTree;
    }

    private static int[] countCharFreqs(String string) {
        int[] charFreqs = new int[CHARSET_SIZE];

        for (int i = 0; i < string.length(); i++) {
            charFreqs[string.charAt(i)]++;
        }
        return charFreqs;
    }

    private static PriorityQueue createLeaves(int[] charFreqs) {
        int leafCount = countUnique(charFreqs);
        PriorityQueue leaves = new PriorityQueue(leafCount);
        for (int charInt = 0; charInt < charFreqs.length; charInt++) {
            int freq = charFreqs[charInt];
            if (freq > 0) {
                Node node = new Node((char)charInt, freq);
                leaves.add(node);
            }
        }
        // EOF char
        leaves.add(new Node((char)0, 0));
        return leaves;
    }

    private static int countUnique(int[] charFreqs) {
        int maxSize = 0;
        for (int i = 0; i < charFreqs.length; i++) {
            if (charFreqs[i] > 0) {
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
