package huffman;

import huffman.converting.Decoder;
import huffman.converting.Encoder;
import huffman.converting.DecodingHuffTreeMaker;
import huffman.io.FileConverter;
import java.nio.file.NoSuchFileException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main (String[] args) {
        // For testing, will handle input from args later
        String path = "src/main/resources/Example.txt";
        File inFile = new File(path);
        File outFile = new File(path + ".huf");
        DecodingHuffTreeMaker huffmanTreeMaker = new DecodingHuffTreeMaker();
        FileConverter fileConverter = new FileConverter();
        Encoder encoder = new Encoder(huffmanTreeMaker);
        Decoder decoder = new Decoder(huffmanTreeMaker);
        try {
            fileConverter.convert(inFile, encoder);
            fileConverter.convert(outFile, decoder);
        } catch (NoSuchFileException e) {
            System.out.println("File '" + e.getFile() + "' not found.");
        } catch (IOException e) {
            System.out.println("Something went wrong: \n" + e);
        }
        System.out.println("Done");
    }
}
