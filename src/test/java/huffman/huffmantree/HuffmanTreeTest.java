package huffman.huffmantree;

import static org.junit.Assert.*;

import org.junit.*;

import huffman.datastructures.Node;

/**
 * Tests the fuctionality of the HuffmanTree class.
 */
public class HuffmanTreeTest {

    HuffmanTree huffmanTree;

    /**
     * We set up a simple binary tree to be the huffmantree.
     */
    @Before
    public void setUp() {
       Node root = new Node(new Node('t', 0), new Node(new Node((char) 0, 0), new Node('e', 0)));
       huffmanTree = new HuffmanTree(root);
    }

    /**
     * Checks that the unambiguous character-code map is correct.
     */
    @Test
    public void codesAreSetCorrectly() {
        assertTrue(huffmanTree.getCode('t').equals("0"));
        assertTrue(huffmanTree.getCode('e').equals("11"));
        assertTrue(huffmanTree.getCode('x') == null);
    }

    @Test
    public void leafCountIsCorrect() {
        assertTrue(huffmanTree.getLeafCount() == 3);
    }

    @Test
    public void nodeCountIsCorrect() {
        assertTrue(huffmanTree.getNodeCount() == 5);
    }
}
