package huffman.huffmantree;

import java.io.UnsupportedEncodingException;

/**
 * Makes huffman trees.
 */
public interface HuffmanTreeMaker {
    HuffmanTree makeTree(byte[] data) throws UnsupportedEncodingException;
}
