package huffman.decompression;

import huffman.datastructures.HuffmanTree;
import huffman.io.BitReader;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;

public class Decompresser {

    public static void decompress(File file) throws IOException {
        BitReader bitReader = new BitReader(file);
        while (true) {
            boolean isSet = bitReader.read();
            if (isSet) {
                char character = (char) bitReader.readByte();
                System.out.println(character + ":" + (int) character);
                isSet = false;
            }
        }
    }
}
