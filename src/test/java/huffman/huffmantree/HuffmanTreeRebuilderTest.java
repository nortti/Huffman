package huffman.huffmantree;

import static org.junit.Assert.*;

import org.junit.*;

import huffman.datastructures.Node;

/**
 * Checks the functionality of the HuffmanTreeRebuilder class.
 */
public class HuffmanTreeRebuilderTest {

    HuffmanTreeRebuilder huffmanTreeRebuilder = new HuffmanTreeRebuilder();
    Node correctTree = new Node(new Node('t', 0), new Node(new Node((char) 0, 0),
                                new Node('e', 0)));

    /**
     * Checks that when the data isn't in the correct format for this application, an error is
     * thrown.
     */
    @Test
    public void throwsExceptionWhenBadData() {
        // 111111111
        byte[] data = { (byte) 255, (byte) 255 };
        boolean noError = true;
        try {
        huffmanTreeRebuilder.makeTree(data);
        } catch (Exception e) {
            noError = false;
        }
        assertFalse(noError);
    }

    /**
     * Checks that the tree created is indeed correct, by comparing the output with the expected
     * output.
     */
    @Test
    public void makesACorrectTree() throws Exception {
        // 0 1 01110100 0 1 00000000 1 01100101000
        byte[] data = { (byte) 93, (byte) 16, (byte) 11, (byte) 40 };
        HuffmanTree huffmanTree = huffmanTreeRebuilder.makeTree(data);
        Node tree = huffmanTree.getRoot();
        Node correctTree = new Node(new Node('t', 0), new Node(new Node((char) 0, 0),
                                    new Node('e', 0)));
        assertTrue(sameTree(correctTree, tree));
    }

    /**
     * A recursive algorithm to check if two binary trees are the same.
     */
    public boolean sameTree(Node tree1, Node tree2) {
        if (tree1.getLeftChild() == null) { // Is leaf
            if (tree2.getLeftChild() == null) {
                if (tree1.getCharacter() == tree2.getCharacter()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return sameTree(tree1.getLeftChild(), tree2.getLeftChild()) &&
                   sameTree(tree1.getRightChild(), tree2.getRightChild());
        }
    }
}
