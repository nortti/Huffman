package huffman;

import huffman.io.FileChanger;
import java.nio.file.NoSuchFileException;
import java.io.File;
import java.io.IOException;

/**
 * -
 */
public class Main {

    public static void main (String[] args) {
        // For testing, will handle input form args later
        String path = "src/main/resources/Example.txt";
        File file = new File(path);
        try {
            FileChanger.compress(file);
            FileChanger.decompress(file);
        } catch (NoSuchFileException e) {
            System.out.println("File '" + e.getFile() + "' not found.");
        } catch (IOException e) {
            System.out.println("Something went wrong: \n" + e);
        }
    }
}
