package huffman.compression.tools;

import huffman.io.BitOutputStream;
import huffman.compression.tools.DataInfo;
import huffman.datastructures.HuffmanTree;
import huffman.datastructures.Node;
import java.io.File;
import java.io.IOException;

public class Encoder {

    public static byte[] encode(byte[] inputData) throws IOException {
        DataInfo dataInfo = new DataInfo(inputData);
        int[] charFreqs = dataInfo.getCharFreqs();
        String inputString = dataInfo.getString();

        HuffmanTree huffmanTree = new HuffmanTree(charFreqs);
        Node root = huffmanTree.getRoot();
        String[] codes = huffmanTree.getCodes();

        byte[] outputData = createOutput(root, inputString, codes);
        return outputData;
    }

    private static byte[] createOutput(Node root, String string, String[] codes) {
        BitOutputStream bitOutputStream = new BitOutputStream();

        writeEncodedTree(root, bitOutputStream);
        writeEncodedMessage(string, codes, bitOutputStream);
        writeEOFCode(codes[0], bitOutputStream);

        return bitOutputStream.read();
    }

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

    private static void writeEncodedMessage(String string, String[] codes, BitOutputStream bitOutputStream) {
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            String code = codes[(int)character];
            for (int bit = 0; bit < code.length(); bit++) {
                if (code.charAt(bit) == '1') {
                    bitOutputStream.write(true);
                } else {
                    bitOutputStream.write(false);
                }
            }
         }
    }

    private static void writeEOFCode(String code, BitOutputStream bitOutputStream) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                bitOutputStream.write(true);
            } else {
                bitOutputStream.write(false);
            }
        }
    }
}
