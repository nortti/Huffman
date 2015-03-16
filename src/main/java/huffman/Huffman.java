package huffman;

import huffman.tools.Compresser;
import java.io.File;
import java.io.IOException;

/**
 * The main class from where we can either compress or decompress a given file.
 */
public class Huffman {

    public static void main (String[] args) throws IOException {
        String path = "src/main/resources/Example.txt";
        File file = new File(path);
        Compresser compresser = new Compresser();
        compresser.generate(file);
    }
}
