package huffman.compression.tools;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;

/**
 * Tests for InputInfo.java
 */
public class InputInfoTest {

    String inputPath = "src/test/resources/Example.txt";
    File inputFile = new File(inputPath);
    InputInfo inputInfo;
    PrintWriter writer;

    @Before
    public void setUp() throws IOException {
        writer = new PrintWriter(inputPath);
        writer.println("abbccc");
        writer.close();
        inputInfo = new InputInfo(inputFile);
    }

    @Test
    public void charFreqsAreCorrect() {
        assertTrue(inputInfo.getCharFreqs()[25] == 0);
        assertTrue(inputInfo.getCharFreqs()[100] == 0);
        assertTrue(inputInfo.getCharFreqs()[97] == 1);
        assertTrue(inputInfo.getCharFreqs()[98] == 2);
        assertTrue(inputInfo.getCharFreqs()[99] == 3);
    }

    @Test
    public void contentsAreCorrect() {
        assertTrue(inputInfo.getContents().equals("abbccc\n"));
    }
}
