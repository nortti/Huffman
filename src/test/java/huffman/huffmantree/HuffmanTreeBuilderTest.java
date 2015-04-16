package huffman.huffmantree;

import static org.junit.Assert.*;

import org.junit.*;

public class HuffmanTreeBuilderTest {

    HuffmanTreeBuilder huffmanTreeBuilder = new HuffmanTreeBuilder();

    @Test
    public void throwsErrorWhenNonAsciiChars() {
        boolean noError = true;
        byte[] data = { (byte) 255, (byte) 255 };
        try {
            huffmanTreeBuilder.makeTree(data);
        } catch (Exception e) {
            noError = false;
        }
        assertFalse(noError);
    }
    @Test
    public void makesACorrectTree() throws Exception {
        byte[] data = { (byte) 116, (byte) 101, (byte) 116, (byte) 101 }; // "tete"
        HuffmanTree huffmanTree = huffmanTreeBuilder.makeTree(data);
        // The tree will contain two leaves of freq = 2 and one leaf of freq = 0 (EOF char).
        // Any correct tree will have a combined code length for 't' and 'e' of 3 and a code
        // length of 2 for the EOF code.
        assertTrue(huffmanTree.getCode('t').length() + huffmanTree.getCode('e').length() == 3);
        assertTrue(huffmanTree.getCode((char) 0).length() == 2);
    }
}
