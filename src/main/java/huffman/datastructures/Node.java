package huffman.datastructures;

/**
 * Class that represents the nodes of a Huffman tree.
 */
public class Node {

    private char character;
    private final int freq;
    private Node leftChild;
    private Node rightChild;

    /**
     * Constructor for leaf nodes.
     * @param character The character
     * @param freq The frequency of the character
     */
    public Node(char character, int freq) {
        this.character = character;
        this.freq = freq;
    }

    /**
     * Creates a non-leaf node.
     * @param leftChild The left child
     * @param rightChild The right child
     */
     public Node(Node leftChild, Node rightChild) {
        this.freq = leftChild.getFreq() + rightChild.getFreq();
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public String toString() {
        if (this.character != 0) return "" + this.character + "(" + this.freq + ")";
        return leftChild + "" + rightChild + "(" + this.freq + ")";
    }

    public char getCharacter() {
        return this.character;
    }

    public int getFreq() {
        return this.freq;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }
}
