package huffman.compression;

import huffman.compression.tools.FileInfo;
import huffman.datastructures.*;
import huffman.io.BitOutputStream;
import java.io.*;
import java.lang.StringBuilder;
import java.math.BigInteger;
import java.util.PriorityQueue;

/**
 * A class which compresses a given input file to a given output file using Huffman coding.
 */
public class Compresser {

    /**
     * Method that controls the compression process.
     * @param file The input file
     * @throws IOException IO Exception
     */
    public static File compress(File file) throws IOException {
        BitOutputStream bitOutputStream = new BitOutputStream();
        FileInfo fileInfo = new FileInfo(file);
        HuffmanTree huffmanTree = new HuffmanTree(fileInfo.getCharFreqs());

        writeEncodedTree(huffmanTree.getRoot(), bitOutputStream);
        writeSeparator(bitOutputStream);
        writeEncodedMessage(fileInfo.getString(), huffmanTree.getCodes(), bitOutputStream);
        writeEOFCode(huffmanTree.getCodes()[0], bitOutputStream);

        File outputFile = new File(file.getPath() + ".huf");
        writeOutputFile(outputFile, bitOutputStream.read());
        return outputFile;
    }

    public static void writeEncodedTree(Node node, BitOutputStream bitOutputStream) {
        if (node.getLeftChild() == null) { // Is leaf
            bitOutputStream.write(true);
            bitOutputStream.write(node.getCharacter());
        } else {
            bitOutputStream.write(false);
            writeEncodedTree(node.getLeftChild(), bitOutputStream);
            writeEncodedTree(node.getRightChild(), bitOutputStream);
        }
    }

    public static void writeSeparator(BitOutputStream bitOutputStream) {
        for (int i = 0; i < 2; i++) bitOutputStream.write(true);
    }

    public static void writeEOFCode(String code, BitOutputStream bitOutputStream) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                bitOutputStream.write(true);
            } else {
                bitOutputStream.write(false);
            }
        }
    }
    /**
     * Encodes a String using the code table of a Huffman tree.
     * @param string String to encode
     * @param codeTable Code table of a Huffman tree
     * @return Encoded string
     */
    public static void writeEncodedMessage(String string, String[] codes,
                                           BitOutputStream bitOutputStream) {
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            String code = codes[(int) character];
            for (int bit = 0; bit < code.length(); bit++) {
                if (code.charAt(bit) == '1') {
                    bitOutputStream.write(true);
                } else {
                    bitOutputStream.write(false);
                }
            }
         }
    }

    /**
     * Creates and writes the compressed file.
     * @param file The output file
     * @param bytes The bytes to write
     * @throws IOException IO exception
     */
    public static void writeOutputFile(File file, byte[] data) throws IOException {
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
    }
}
