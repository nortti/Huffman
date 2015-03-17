package huffman.compression;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.math.BigInteger;

/**
 * Tests for Compresser.java
 */
public class CompresserTest {

    Compresser compresser = new Compresser();
    String inputPath = "src/test/resources/Example.txt";
    String outputPath = inputPath + ".compressed";
    File inputFile = new File(inputPath);
    File outputFile = new File(outputPath);
    PrintWriter writer;

    @Before
    public void setUp() throws IOException {
        outputFile.createNewFile();
        writer = new PrintWriter(inputPath);
    }


    @Test
    public void generatesCompressedFileWhenInputNotEmpty() throws IOException {
        writer.println("abc");
        writer.close();
        compresser.generateCompressed(inputFile, outputFile);
        assertTrue(outputFile.length() != 0);
    }

    @Test
    public void encodeInputWorks() {
        String input = "abcba";
        String[] codeTable = new String[128];
        codeTable['a'] = "10";
        codeTable['x'] = "111";
        codeTable['b'] = "11";
        codeTable['y'] = "000";
        codeTable['c'] = "01";
        assertTrue(Compresser.encodeInput(input, codeTable).equals( "1011011110"));
    }

    @Test
    public void generateBinaryOutputWorks() throws IOException {
        String encodedTree = "0101100001101100010";
        String encodedInput = "010101";
        byte[] binaryOutput = Compresser.generateBinaryOutput(encodedTree, encodedInput);
        assertTrue(java.util.Arrays.equals(binaryOutput, BigInteger.valueOf(92718549).toByteArray()));
    }

    @Test
    public void writeToOutputFileWorks() throws IOException {
       byte[] bytes = {1, 2, 3};
       Compresser.writeToOutputFile(outputFile, bytes);
       assertTrue(outputFile.length() != 0);
    }

    @After
    public void tearDown() {
        outputFile.delete();
    }
}
