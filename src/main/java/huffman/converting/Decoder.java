package huffman.converting;

import java.io.ByteArrayOutputStream;

import huffman.datastructures.Node;
import huffman.huffmantree.HuffmanTree;
import huffman.huffmantree.HuffmanTreeMaker;
import huffman.io.BitInputStream;

/**
 * Used for converting huffman-encoded data back to its original form.
 */
public class Decoder implements DataConverter {

    private HuffmanTreeMaker huffmanTreeMaker;

    public Decoder(HuffmanTreeMaker huffmanTreeMaker) {
        this.huffmanTreeMaker = huffmanTreeMaker;
    }

    @Override
    public byte[] convert(byte[] data) {
        // Make huffman tree from data
        HuffmanTree huffmanTree = this.huffmanTreeMaker.makeTree(data);

        // inputstream needs to skip the tree portion of data
        int howMuchToSkip = huffmanTree.getNodeCount() + huffmanTree.getLeafCount() * 8;
        BitInputStream bitInputStream = new BitInputStream(data, howMuchToSkip);

        ByteArrayOutputStream bitOutputStream = new ByteArrayOutputStream();

        // Write the decoded message to the output stream
        writeDecodedMessage(huffmanTree.getRoot(), bitInputStream, bitOutputStream);
        // And return it
        return bitOutputStream.toByteArray();
    }

    private void writeDecodedMessage(Node root, BitInputStream inputStream,
                                     ByteArrayOutputStream outputStream) {
        while (true) {
            // Get next character to be written
            char nextCharacter = findCharacter(root, inputStream);
            if ((int) nextCharacter == 0) {
                // If it's the EOF code, finish
                break;
            } else {
                // Otherwise, add it to the output stream and continue
                outputStream.write(nextCharacter);
            }
        }
    }

    /** 
     * Uses recursion to find a character (leaf node) starting from the root and moving left/right 
     * according to the bits read in the bit input stream
     */
    private char findCharacter(Node node, BitInputStream bitInputStream) {
        if (node.getLeftChild() == null) {
            return node.getCharacter();
        } else if (bitInputStream.readBit() == true) {
            return findCharacter(node.getRightChild(), bitInputStream);
        } else {
            return findCharacter(node.getLeftChild(), bitInputStream);
        }

    }

    @Override
    public String getNewPath(String path) {
        if (path.endsWith(".huf") && path.length() > 4) {
            return path.substring(0, path.length() - 4);
        } else {
            return path;
        }
     }
}
