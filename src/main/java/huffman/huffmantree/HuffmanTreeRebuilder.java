package huffman.huffmantree;

import java.io.UnsupportedEncodingException;

import huffman.datastructures.Node;
import huffman.io.BitInputStream;

/**
 * Builds huffman trees from compressed data.
 */
public class HuffmanTreeRebuilder implements HuffmanTreeMaker {

    /**
     * Builds a huffman tree from compressed data.
     * @throws UnsupportedEncodingException if the tree-building algorithm fails because the data
     * does not start with a correctly encoded tree.
     */
    @Override
     public HuffmanTree makeTree(byte[] data) throws UnsupportedEncodingException {
         BitInputStream bitInputStream = new BitInputStream(data);
         Node root = decodeTreeFromData(bitInputStream);
         HuffmanTree huffmanTree = new HuffmanTree(root);
         return huffmanTree;
    }

    /**
     * Builds a huffman tree from compressed data, using the algorithm described in
     * <a href="http://google.com">http://stackoverflow.com/a/759766</a>.
     * @throws UnsupportedEncodingException if the data is not encoded using the encoding
     * alrorithm in the link above
     * @return The root of the tree
     */
    private static Node decodeTreeFromData(BitInputStream bitInputStream)
                                           throws UnsupportedEncodingException {
        boolean isSet = bitInputStream.readBit();
        if (isSet) {
            char character = (char) bitInputStream.readByte();
            if ((int) character > 256) {
                throw new UnsupportedEncodingException();
            }
            return new Node(character, 0);
        } else {
            Node leftChild = decodeTreeFromData(bitInputStream);
            Node rightChild = decodeTreeFromData(bitInputStream);
            Node parent = new Node(leftChild, rightChild);
            return parent;
        }
    }

}
