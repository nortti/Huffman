package huffman.datastructures;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Tests for HuffmanTree.java
 */
public class HuffmanTreeTest {

    int[] charFreqs = new int[128];
    HuffmanTree huffmanTree;

    @Before
    public void setUp() {
        charFreqs[97] += 10;
        charFreqs[98] += 5;
        charFreqs[99] += 1;
        huffmanTree = new HuffmanTree(charFreqs);
    }

    @Test
    public void encodedTreeIsCorrect() {
        assertTrue(huffmanTree.getEncoded().equals("00101100011101100010101100001"));
    }

    @Test
    public void codeTableIsCorrect() {
        assertTrue(huffmanTree.getCodeTable()[97].length() == 1);
        assertTrue(huffmanTree.getCodeTable()[98].length() == 2);
        assertTrue(huffmanTree.getCodeTable()[99].length() == 2);
    }
}
