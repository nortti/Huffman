package huffman.converting;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

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
    public byte[] convert(byte[] data) throws UnsupportedEncodingException {
        // Make huffman tree from data
        HuffmanTree huffmanTree = this.huffmanTreeMaker.makeTree(data);

        // inputstream needs to skip the tree portion of data
        int howMuchToSkip = huffmanTree.getNodeCount() + huffmanTree.getLeafCount() * 8;
        BitInputStream bitInputStream = new BitInputStream(data, howMuchToSkip);

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

        // Write the decoded message to the output stream
        writeDecodedMessage(huffmanTree.getRoot(), bitInputStream, byteOutputStream);
        // And return it
        return byteOutputStream.toByteArray();
    }

    private void writeDecodedMessage(Node root, BitInputStream inputStream,
                                     ByteArrayOutputStream outputStream) throws UnsupportedEncodingException {
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
     * Uses recursion to find a character starting from the root and moving left/right according to
     * the bits read in the bit input stream until a leaf node is reached.
     */
    private char findCharacter(Node node, BitInputStream bitInputStream) throws UnsupportedEncodingException {
        if (node.getLeftChild() == null) { // Is leaf
            return node.getCharacter();
        } else if (bitInputStream.readBit() == true) {
            return findCharacter(node.getRightChild(), bitInputStream); // Move left
        } else {
            return findCharacter(node.getLeftChild(), bitInputStream); // Move right
        }

    }

    /**
     * Returns the new path of any file converted using this class.
     * @param path Old path
     * @return New path
     */
    @Override
    public String getNewPath(String path) {
        if (path.endsWith(".huf") && path.length() > 4) {
            return path.substring(0, path.length() - 4);
        } else {
            return path;
        }
     }
}
