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
     * @throws IOException IO Exception
     */
    public void generateCompressed(File inputFile, File outputFile) throws IOException {
        InputInfo inputInfo = new InputInfo(inputFile);
        HuffmanTree huffmanTree = new HuffmanTree(inputInfo.getCharFreqs());
        String encodedInput = encodeInput(inputInfo.getContents(), huffmanTree.getCodeTable());
        byte[] binaryOutput = generateBinaryOutput(huffmanTree.getEncoded(), encodedInput);
        writeToOutputFile(outputFile, binaryOutput);
    }

    /**
     * Encodes the input using the code table of the huffman tree.
     * @param input Contents of the input file
     * @param codeTable code table of the huffman tree
     * @return encoded input
     */
    public String encodeInput(String input, String[] codeTable) {
        StringBuilder encodeInput = new StringBuilder();
        int length = input.length();
        for(int i = 0; i < length; i++) {
            int charAsInt = (int) input.charAt(i);
            String code = codeTable[charAsInt];
            encodeInput.append(code);
         }
        return encodeInput.toString();
    }

    /**
     * Generates the binary code to be written into the output file. 
     * @param encodedTree The encoded tree
     * @param encodedInput The encoded input
     * @return The full binary code
     */
    public byte[] generateBinaryOutput(String encodedTree, String encodedInput) throws IOException {
        String output = encodedTree + "111" + encodedInput;
        // Converts String representing bytes to bytes using BigInteger
        return new BigInteger(output.toString(), 2).toByteArray();
    }

    /**
     * Creates and writes the compressed file.
     * @param file The output file
     * @param bytes The bytes to write
     */
    public void writeToOutputFile(File file, byte[] bytes) throws IOException {
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
    }
}
