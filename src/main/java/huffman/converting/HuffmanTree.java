package huffman.converting;

import static huffman.io.FileConverter.CHARSET_SIZE;
import huffman.datastructures.Node;

public class HuffmanTree {

    private Node root;
    private String[] codes = new String[CHARSET_SIZE];

    public HuffmanTree(Node root) {
        this.root = root;
        setCodes(root, "");
    }

    private void setCodes(Node node, String code) {
        if (node.getLeftChild() == null) { // Is leaf
            this.codes[node.getCharacter()] = code;
        } else {
            setCodes(node.getLeftChild(), code + '0');
            setCodes(node.getRightChild(), code + '1');
        }
    }

    public Node getRoot() {
        return this.root;
    }

    public String[] getCodes() {
        return codes;
    }
}

