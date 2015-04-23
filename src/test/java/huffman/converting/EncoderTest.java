package huffman.converting;

import static org.junit.Assert.*;

import org.junit.*;

import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeMaker;

/**
 * Tests the functionality of the Encoder class.
 */
public class EncoderTest {

    HuffmanTreeMakerStub huffmanTreeMakerStub = new HuffmanTreeMakerStub();
    Encoder encoder = new Encoder(huffmanTreeMakerStub);

    /**
     * A stub to remove the dependency of the HuffmanTreeBuilder class. Returns a simple
     * huffman tree with three leaves.
     */
    public class HuffmanTreeMakerStub implements HuffmanTreeMaker {
        @Override
        public HuffmanTree makeTree(byte[] data) {
            Node root = new Node(new Node('t', 0),
                                 new Node(new Node((char) 0, 0), new Node('e', 0)));
            return new HuffmanTree(root);
        }
    }

    /**
     * Checks if the decoder, that uses a stub to replace HuffmanTreeBuilder, encodes a
     * string correctly.
     */
    @Test
    public void encodesCorrectly() throws Exception {
        byte[] data = encoder.convert("tete".getBytes());
        byte[] correctData = { (byte) 93, (byte) 16, (byte) 11, (byte) 43, (byte) 112 };
        assertArrayEquals(correctData, data);

    }

    /**
     * A very simple test to check that getNewPath does indeed return 'parameter + .huf'.
    */
    @Test
    public void setsNewPathCorrectly() {
        assertEquals(encoder.getNewPath("test"), "test.huf");
    }
}
