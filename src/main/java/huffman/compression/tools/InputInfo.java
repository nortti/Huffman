package huffman.compression.tools;

import java.lang.StringBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class where, during compression, the original file is read  and the required information for
 * compression is stored.
 */
public class InputInfo {

    private int[] charFreqs = new int[128];
    private StringBuilder contents = new StringBuilder();

    /**
     * Calls the method that initializes chafFreqs and contents by reading the input file.
     * @param file The input file
     * @throws IOException IO Exception
     */
    public InputInfo(File file) throws IOException {
        generateInfo(new FileInputStream(file));
    }

    /**
     * The file is read byte by byte (char by char) as we build a String of its contents and set
     * up a table of character frequencies.
     * @param inputStream A stream of the input file
     * @throws IOException IO exception
     */
    private void generateInfo(FileInputStream inputStream) throws IOException {
        int r;
        while ((r = inputStream.read()) != -1) {
            contents.append((char) r);
            charFreqs[r]++;
        }
    }

    public int[] getCharFreqs() {
        return this.charFreqs;
    }

    public String getContents() {
        return this.contents.toString();
    }
}
