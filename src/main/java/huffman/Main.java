package huffman;

import huffman.io.FileConverter;
import java.nio.file.NoSuchFileException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main (String[] args) {
        // For testing, will handle input form args later
        String path = "src/main/resources/Example.txt";
        File inFile = new File(path);
        File outFile = new File(path + ".huf");
        try {
            FileConverter.compress(inFile);
            // FileConverter.decompress(outFile);
        } catch (NoSuchFileException e) {
            System.out.println("File '" + e.getFile() + "' not found.");
        } catch (IOException e) {
            System.out.println("Something went wrong: \n" + e);
        }
    }
}
