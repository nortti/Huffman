package huffman.converting;

// import static org.junit.Assert.*;
// import static huffman.io.FileConverter.CHARSET_SIZE;
// import huffman.io.BitInputStream;

// import org.junit.*;

public class HuffmanTreeTest {

    // HuffmanTree huffmanTreeFromFreqs;
    // HuffmanTree huffmanTreeFromBitInputStream;

    // @Before
    // public void setUp() {
    //     int[] charFreqs = new int[CHARSET_SIZE];
    //     charFreqs['a'] = 100;
    //     charFreqs['b'] = 50;
    //     charFreqs['c'] = 25;
    //     charFreqs['d'] = 12;
    //     huffmanTreeFromFreqs = new HuffmanTree(charFreqs);

    //     byte[] data = { (byte)8,(byte) 5, (byte)-110, (byte)-57, (byte)98, (byte)-80, (byte)-1, (byte)-1, (byte)-86, (byte)-86, (byte)-110, (byte)72, -(byte)120, (byte)0 };
    //     BitInputStream bitInputStream = new BitInputStream(data);
    //     huffmanTreeFromBitInputStream = new HuffmanTree(bitInputStream);
    // }

    // @Test
    // public void buildsTreeCorrectlyFromFreqs() {
    //     Node root = huffmanTreeFromFreqs.getRoot();
    //     assertTrue(root.getRightChild().getCharacter() == 'a');
    //     assertTrue(root.getLeftChild().getRightChild().getCharacter() == 'b');
    //     assertTrue(root.getLeftChild().getLeftChild().getRightChild().getCharacter() == 'c');
    //     assertTrue(root.getLeftChild().getLeftChild().getLeftChild().getRightChild().getCharacter() == 'd');
    //     assertTrue(root.getLeftChild().getLeftChild().getLeftChild().getLeftChild().getCharacter() == (int)0);
    // }

    // @Test
    // public void setsCodesCorrectlyFromFreqs() {
    //     String[] codes = huffmanTreeFromFreqs.getCodes();
    //     assertTrue(codes['a'].equals("1"));
    //     assertTrue(codes['b'].equals("01"));
    //     assertTrue(codes['c'].equals("001"));
    //     assertTrue(codes['d'].equals("0001"));
    //     assertTrue(codes[0].equals("0000"));
    // }

    // @Test
    // public void buildsTreeCorrectlyFromBitInputStream() {
    //     Node root = huffmanTreeFromBitInputStream.getRoot();
    //     assertTrue(root.getRightChild().getCharacter() == 'a');
    //     assertTrue(root.getLeftChild().getRightChild().getCharacter() == 'b');
    //     assertTrue(root.getLeftChild().getLeftChild().getRightChild().getCharacter() == 'c');
    //     assertTrue(root.getLeftChild().getLeftChild().getLeftChild().getRightChild().getCharacter() == 'd');
    //     assertTrue(root.getLeftChild().getLeftChild().getLeftChild().getLeftChild().getCharacter() == (int)0);
    // }

    // @Test
    // public void setsCodesCorrectlyFromBitInputStream() {
    //     String[] codes = huffmanTreeFromBitInputStream.getCodes();
    //     assertTrue(codes['a'].equals("1"));
    //     assertTrue(codes['b'].equals("01"));
    //     assertTrue(codes['c'].equals("001"));
    //     assertTrue(codes['d'].equals("0001"));
    //     assertTrue(codes[0].equals("0000"));
    // }
}
