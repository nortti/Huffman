package huffman;

import java.io.File;
import java.io.UnsupportedEncodingException;

import huffman.converting.DataConverter;
import huffman.converting.Decoder;
import huffman.converting.Encoder;
import huffman.huffmantree.HuffmanTreeBuilder;
import huffman.huffmantree.HuffmanTreeRebuilder;
import huffman.io.ArgsParser;
import huffman.io.FileConverter;

/**
 * Sets up the file conversion using input from args
 */
public class Main {

    public static void main (String[] args) throws Exception, UnsupportedEncodingException {
        ArgsParser argsParser = new ArgsParser();
        if (!argsParser.parse(args)) { // validation failed
            System.out.println(argsParser.getErrorMessage());
            return;
        }

        boolean compressing = argsParser.getIsCompressing();
        File file = argsParser.getFile();

        FileConverter fileConverter = new FileConverter();
        // true sets to an Encoder, false to a Decoder
        DataConverter dataConverter = converter(compressing);
        fileConverter.convert(file, dataConverter);
    }

    private static DataConverter converter(boolean compressing) {
        if (compressing) {
            return new Encoder(new HuffmanTreeBuilder());
        } else {
            return new Decoder(new HuffmanTreeRebuilder());
        }
    }
}
