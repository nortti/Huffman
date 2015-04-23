package huffman.converting;

import static org.junit.Assert.*;

import org.junit.*;

import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeMaker;

/**
 * Tests the functionality of the Decoder class.
 */
public class DecoderTest {

    HuffmanTreeMakerStub huffmanTreeMakerStub = new HuffmanTreeMakerStub();
    Decoder decoder = new Decoder(huffmanTreeMakerStub);

    /**
     * A stub to remove the dependency of the HuffmanTreeRebuilder class. Returns a simple
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
     * Checks if the decoder, that uses a stub to replace HuffmanTreeRebuilder decodes a
     * correctly encoded message correctly.
     */
    @Test
    public void decodesCorrectly() throws Exception {
        byte[] inputData = { (byte) 93, (byte) 16, (byte) 11, (byte) 43, (byte) 112 };
        byte[] decoded = decoder.convert(inputData);
        byte[] correctData = "tete".getBytes();
        assertArrayEquals(correctData, decoded);

    }

    /**
     * Checks that getNetPath removes the '.huf' extension from the parameter if possible.
     */
    @Test
    public void correctNewPathWithHufExtension() {
        assertTrue(decoder.getNewPath("test.txt.huf").equals("test.txt"));
    }

    /**
     * Checks that getNetPath returns the parameter untouched if no '.huf' extension is present.
     */
    @Test
    public void corretNewPathWithoutHufExtension() {
        assertTrue(decoder.getNewPath("test.txt").equals("test.txt"));
    }

    /**
     * Checks that getNetPath doesn't remove the '.huf' extension in the special case that it 
     * would leave the file with an empty name.
     */
    @Test
    public void corretNewPathWithOnlyHufExtension() {
        assertTrue(decoder.getNewPath(".huf").equals(".huf"));
    }
}
