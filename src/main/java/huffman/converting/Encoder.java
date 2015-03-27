package huffman.converting;

import static huffman.io.FileConverter.CHARSET_SIZE;
import huffman.io.BitOutputStream;
import huffman.datastructures.HuffmanTree;
import huffman.datastructures.Node;

public class Encoder implements DataConverter {

    @Override
    public byte[] convert(byte[] inputData) {
        String inputString = new String(inputData);
        int[] charFreqs = countCharFreqs(inputString);

        HuffmanTree huffmanTree = new HuffmanTree(charFreqs);
        Node root = huffmanTree.getRoot();
        String[] codes = huffmanTree.getCodes();

        byte[] outputData = createOutput(root, inputString, codes);
        return outputData;
    }

    @Override
    public String newPath(String path) {
        return path + ".huf";
    }
    private static int[] countCharFreqs(String string) {
        int[] charFreqs = new int[CHARSET_SIZE];

        for (int i = 0; i < string.length(); i++) {
            charFreqs[string.charAt(i)]++;
        }

        return charFreqs;
    }

    private static byte[] createOutput(Node root, String string, String[] codes) {
        BitOutputStream bitOutputStream = new BitOutputStream();

        writeEncodedTree(root, bitOutputStream);
        writeEncodedMessage(string, codes, bitOutputStream);
        writeEncodedChar(codes[0], bitOutputStream); // EOF Code

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
            writeEncodedChar(code, bitOutputStream); }
    }

    private static void writeEncodedChar(String code, BitOutputStream bitOutputStream) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                bitOutputStream.write(false);
            } else {
                bitOutputStream.write(true);
            }
        }
    }
}
