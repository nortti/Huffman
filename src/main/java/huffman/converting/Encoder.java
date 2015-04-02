package huffman.converting;

import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeMaker;
import huffman.io.BitOutputStream;

/**
 * Used for encoding uncompressed normal files using huffman coding.
 */
public class Encoder implements DataConverter {

    private HuffmanTreeMaker huffmanTreeMaker;

    public Encoder(HuffmanTreeMaker huffmanTreeMaker) {
        this.huffmanTreeMaker = huffmanTreeMaker;
    }

    /**
     * Creates a compressed version of an input data using huffman coding.
     */
    @Override
    public byte[] convert(byte[] inputData) {
        // Make huffman tree from data
        HuffmanTree huffmanTree = this.huffmanTreeMaker.makeTree(inputData);

        BitOutputStream bitOutputStream = new BitOutputStream();

        // Write a binary representation of the tree, followed by the encoded message.
        writeEncodedTree(huffmanTree.getRoot(), bitOutputStream);
        writeEncodedMessage(inputData, huffmanTree, bitOutputStream);

        return bitOutputStream.toByteArray();
    }

    /**
     * Writes an encoded representation of a tree to a bit output stream using recursion.
     */
    private static void writeEncodedTree(Node node, BitOutputStream bitOutputStream) {
        if (node.getLeftChild() == null) { // Is leaf
            bitOutputStream.write(true);
            bitOutputStream.write(node.getCharacter());
        } else {
            bitOutputStream.write(false);
            writeEncodedTree(node.getLeftChild(), bitOutputStream);
            writeEncodedTree(node.getRightChild(), bitOutputStream);
        }
    }

    /**
     * Uses the huffman codes to write out the full original data to the bit output stream.
     */
    private static void writeEncodedMessage(byte[] data, HuffmanTree huffmanTree,
                                            BitOutputStream bitOutputStream) {
        for (byte characterByte : data) { // For each character in the message
            char character = (char) characterByte;
            String code = huffmanTree.getCode(character); // Get the huffman encoding
            writeEncodedChar(code, bitOutputStream); // And write it
        }
        writeEncodedChar(huffmanTree.getCode((char) 0), bitOutputStream); // add an EOF code
    }

    /**
     * Writes a single huffman-encoded character to the bit output stream.
     */
    private static void writeEncodedChar(String code, BitOutputStream bitOutputStream) {
        for (char character : code.toCharArray()) {
            if (character == '0') {
                bitOutputStream.write(false);
            } else {
                bitOutputStream.write(true);
            }
        }
    }

    /**
     * Returns the new path of any file converted using this class.
     * @param path Old path
     * @return New path
     */
    @Override
    public String getNewPath(String path) {
        return path + ".huf";
    }
}
