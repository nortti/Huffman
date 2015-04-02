package huffman.converting;

import static org.junit.Assert.*;

import org.junit.*;

import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeMaker;

public class EncoderTest {

    HuffmanTreeMakerStub huffmanTreeMakerStub = new HuffmanTreeMakerStub();
    Encoder encoder = new Encoder(huffmanTreeMakerStub);

    public class HuffmanTreeMakerStub implements HuffmanTreeMaker {
        @Override
        public HuffmanTree makeTree(byte[] data) {
            Node root = new Node(new Node('t', 0),
                                 new Node(new Node((char) 0, 0), new Node('e', 0)));
            return new HuffmanTree(root);
        }
    }

    @Test
    public void encodesCorrectly() {
        byte[] data = encoder.convert("tete".getBytes());
        byte[] correctData = { (byte) 93, (byte) 16, (byte) 11, (byte) 43, (byte) 112 };
        assertArrayEquals(correctData, data);

    }
}
