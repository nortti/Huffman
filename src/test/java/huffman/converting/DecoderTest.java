package huffman.converting;

import static org.junit.Assert.*;

import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeMaker;

import org.junit.*;

public class DecoderTest {

    HuffmanTreeMakerStub huffmanTreeMakerStub = new HuffmanTreeMakerStub();
    Decoder decoder = new Decoder(huffmanTreeMakerStub);

    public class HuffmanTreeMakerStub implements HuffmanTreeMaker {
        @Override
        public HuffmanTree makeTree(byte[] data) {
            Node root = new Node(new Node('t', 0), new Node(new Node((char) 0, 0), new Node('e', 0)));
            return new HuffmanTree(root);
        }
    }

    @Test
    public void decodesCorrectly() {
        byte[] inputData = { (byte) 93, (byte) 16, (byte) 11, (byte) 43, (byte) 112 };
        byte[] decoded = decoder.convert(inputData);
        byte[] correctData = { (byte) 116, (byte) 101, (byte) 116, (byte) 101 };
        assertArrayEquals(correctData, decoded);

    }

    @Test
    public void correctNewPathWithHufExtension() {
        assertTrue(decoder.getNewPath("test.txt.huf").equals("test.txt"));
    }

    @Test
    public void corretNewPathWithoutHufExtension() {
        assertTrue(decoder.getNewPath("test.txt").equals("test.txt"));
    }

    @Test
    public void corretNewPathWithOnlyHufExtension() {
        assertTrue(decoder.getNewPath(".huf").equals(".huf"));
    }
}
