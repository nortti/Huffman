package huffman;

import java.io.File;

import huffman.converting.Decoder;
import huffman.converting.Encoder;
import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.huffmantree.HuffmanTreeRebuilder;
import huffman.io.FileConverter;

/**
 * Main class, currenty for manual testing purposes.
 */
public class Main {

    public static void main (String[] args) {
        FileConverter fileConverter = new FileConverter();
        String fileName = "Example.txt";
        Encoder encoder = new Encoder(new HuffmanTreeBuilder());
        Decoder decoder = new Decoder(new HuffmanTreeRebuilder());

        fileConverter.convert(new File(fileName), encoder);
        fileConverter.convert(new File(fileName + ".huf"), decoder);
    }
}
