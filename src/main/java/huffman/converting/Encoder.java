package huffman.converting;

import huffman.io.BitOutputStream;
import huffman.datastructures.Node;

public class Encoder implements DataConverter {

    private DecodingHuffTreeMaker huffmanTreeMaker;

    public Encoder(DecodingHuffTreeMaker huffmanTreeMaker) {
        this.huffmanTreeMaker = huffmanTreeMaker;
    }

    @Override
    public byte[] convert(byte[] inputData) {
        String inputString = new String(inputData);
        HuffmanTree huffmanTree = this.huffmanTreeMaker.makeTree(inputString);

        Node root = huffmanTree.getRoot();
        String[] codes = huffmanTree.getCodes();

        byte[] outputData = createOutput(root, inputString, codes);
        return outputData;
    }

    @Override
    public String newPath(String path) {
        return path + ".huf";
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
