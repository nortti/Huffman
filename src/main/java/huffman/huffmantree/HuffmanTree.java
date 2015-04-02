package huffman.huffmantree;

import static huffman.io.FileConverter.CHARSET_SIZE;
import huffman.datastructures.Node;

public class HuffmanTree {

    private Node root;
    private String[] codes = new String[CHARSET_SIZE];
    private int leafCount;
    private int nodeCount;

    public HuffmanTree(Node root) {
        this.root = root;
        setCodes(root, "");
    }

    private void setCodes(Node node, String code) {
        this.nodeCount++;
        if (node.getLeftChild() == null) { // Is leaf
            this.leafCount++;
            this.codes[node.getCharacter()] = code;
        } else {
            setCodes(node.getLeftChild(), code + '0');
            setCodes(node.getRightChild(), code + '1');
        }
    }

    public Node getRoot() {
        return this.root;
    }

    public String getCode(char character) {
        return codes[character];
    }

    public int getLeafCount() {
        return this.leafCount;
    }

    public int getNodeCount() {
        return this.nodeCount;
    }
}

