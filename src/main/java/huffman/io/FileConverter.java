package huffman.io;

import huffman.encoding.Encoder;
import huffman.decoding.Decoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConverter {

    public static final int CHARSET_SIZE = 256;

    public static void compress(File file) throws IOException {
        boolean compress = true;
        changeFile(file, compress);
    }

    public static void decompress(File file) throws IOException {
        boolean compress = false;
        changeFile(file, compress);
    }

    private static void changeFile(File inputFile, boolean compress) throws IOException {
        Path inputFilePath = Paths.get(inputFile.getPath());
        byte[] inputData = Files.readAllBytes(inputFilePath);
        byte[] outputData;
        if (compress) {
            outputData  = Encoder.encode(inputData);
        } else {
            outputData  = Decoder.decode(inputData);
        }
        File outputFile = createOutputFile(inputFile.getPath(), compress);
        writeToOutputFile(outputFile, outputData);
    }

    private static File createOutputFile(String inputFilePath, boolean compress) throws IOException {
        String path = inputFilePath;
        File outputFile = new File(path);
        outputFile.createNewFile();
        return outputFile;
    }

    private static void writeToOutputFile(File file, byte[] data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
    }
}
