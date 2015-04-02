package huffman.datastructures;

/**
 * Class that represents the nodes of a Huffman tree.
 */
public class Node {

    private char character;
    private final long freq;
    private Node leftChild;
    private Node rightChild;

    /**
     * Leaf node
     */
    public Node(char character, int freq) {
        this.character = character;
        this.freq = freq;
    }

    /**
     * Internal node
     */
    public Node(Node leftChild, Node rightChild) {
        this.freq = leftChild.getFreq() + rightChild.getFreq();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public char getCharacter() {
        return this.character;
    }

    public long getFreq() {
        return this.freq;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }
}
