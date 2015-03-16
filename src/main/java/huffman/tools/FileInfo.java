package huffman.tools;

import java.lang.StringBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * The class where, during compression, the original file is read
 * and the required information for compression is stored.
 */
public class FileInfo {
    private int[] charFreqs = new int[128];
    private StringBuilder contents = new StringBuilder();

    public FileInfo(File file) throws IOException {
        generateInfo(new FileInputStream(file));
    }

    /**
     * The file is read byte by byte (char by char) as we build a string
     * of the whole file and set up a table of character frequencies.
     * */
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

    public String contents() {
        return this.contents.toString();
    }
}
