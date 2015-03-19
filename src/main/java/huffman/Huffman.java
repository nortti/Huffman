package huffman;

import huffman.compression.Compresser;
import java.io.*;

/**
 * The main class which specifies an input and an output file for compression or decompression,
 * depending on user input (to be implemented).
 */
public class Huffman {

    public static void main (String[] args) throws IOException {
        String inputPath = "src/main/resources/Example.txt";
        String outputPath = inputPath + ".compressed";
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        Compresser compresser = new Compresser();
        compresser.compress(inputFile, outputFile);
    }
}
