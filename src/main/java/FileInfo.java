import java.lang.StringBuilder;
import java.io.*;

public class FileInfo {

    private int[] charFreqs = new int[128];
    private StringBuilder contents = new StringBuilder();

    public FileInfo(File file) throws IOException {
        generateInfo(new FileInputStream(file));
    }

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
