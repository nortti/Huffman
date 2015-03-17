package huffman.datastructures;

/**
 * Class that represents the nodes of a Huffman tree.
 */
public class Node implements Comparable<Node> {

    private char character;
    private final int freq;
    private Node leftChild;
    private Node rightChild;

    /**
     * Creates a leaf node.
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
     * @param rightChild The rigth child
     */
     public Node(Node leftChild, Node rightChild) {
        this.freq = leftChild.freq + rightChild.freq;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
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

     public int compareTo(Node o) {
        return this.freq - o.freq;
    }
}
