package huffman;

import huffman.compression.Compresser;
import huffman.decompression.Decompresser;
import java.io.File;
import java.io.IOException;

/**
 * The main class which specifies an input and an output file for compression or decompression,
 * depending on user input (to be implemented).
 */
public class Main {

    public static void main (String[] args) throws IOException {
        String path = "src/main/resources/Example.txt";
        File file = new File(path);
        File compressedFile = Compresser.compress(file);
        Decompresser.decompress(compressedFile);

    }
}
