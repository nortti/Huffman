package huffman;

import huffman.converting.Decoder;
import huffman.converting.Encoder;
import huffman.converting.DecodingHuffTreeMaker;
import huffman.converting.EncodingHuffTreeMaker;
import huffman.io.FileConverter;
import java.io.File;

public class Main {

    public static void main (String[] args) {
        // For testing, will handle input from args later
        String path = "src/main/resources/Example.txt";
        File inFile = new File(path);
        File outFile = new File(path + ".huf");
        FileConverter fileConverter = new FileConverter();
        Encoder encoder = new Encoder(new EncodingHuffTreeMaker());
        Decoder decoder = new Decoder(new DecodingHuffTreeMaker());
        fileConverter.convert(inFile, encoder);
        fileConverter.convert(outFile, decoder);
        System.out.println("Done");
    }
}
