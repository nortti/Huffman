package huffman.huffmantree;

import java.io.UnsupportedEncodingException;

/**
 * Makes huffman trees.
 */
public interface HuffmanTreeMaker {
    public HuffmanTree makeTree(byte[] data) throws UnsupportedEncodingException;
}
