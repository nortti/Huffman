package huffman.compression;

import huffman.compression.tools.InputInfo;
import huffman.datastructures.*;
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
     * @param inputFile The input file
     * @param outputFile The output file
     * @throws IOException IO Exception
     */
    public void compress(File inputFile, File outputFile) throws IOException {
        InputInfo inputInfo = new InputInfo(inputFile);
        HuffmanTree huffmanTree = new HuffmanTree(inputInfo.getCharFreqs());
        String encodedInput = encodeString(inputInfo.getContents(), huffmanTree.getCodeTable());
        byte[] binaryOutput = generateBinaryOutput(huffmanTree.getEncoded(), encodedInput);
        writeToOutputFile(outputFile, binaryOutput);
    }

    /**
     * Encodes a String using the code table of a Huffman tree.
     * @param string String to encode
     * @param codeTable Code table of a Huffman tree
     * @return Encoded string
     */
    public static String encodeString(String string, String[] codeTable) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = string.length();
        for(int i = 0; i < length; i++) {
            char character = string.charAt(i);
            String code = codeTable[(int) character];
            stringBuilder.append(code);
         }
        return stringBuilder.toString();
    }

    /**
     * Generates the binary code to be written into the output file.
     * @param encodedTree The encoded tree
     * @param encodedInput The encoded input
     * @throws IOException IO exception
     * @return The full binary code
     */
    public static byte[] generateBinaryOutput(String encodedTree, String encodedInput) throws IOException {
        String output = encodedTree + "111" + encodedInput + "111";
        // Converts String representing bytes to bytes using BigInteger
        return new BigInteger(output.toString(), 2).toByteArray();
    }

    /**
     * Creates and writes the compressed file.
     * @param file The output file
     * @param bytes The bytes to write
     * @throws IOException IO exception
     */
    public static void writeToOutputFile(File file, byte[] bytes) throws IOException {
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
    }
}
