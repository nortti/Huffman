package huffman.compression;

import huffman.compression.tools.Encoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compresser {

    public static void compress(File inputFile) throws IOException {
        Path inputFilePath = Paths.get(inputFile.getPath());
        byte[] inputData = Files.readAllBytes(inputFilePath);
        byte[] outputData  = Encoder.encode(inputData);
        File outputFile = createOutputFile(inputFile.getPath());
        writeToOutputFile(outputFile, outputData);
    }

    private static File createOutputFile(String inputFilePath) throws IOException {
        String path = inputFilePath + ".huf";
        File outputFile = new File(path);
        outputFile.createNewFile();
        return outputFile;
    }

    private static void writeToOutputFile(File file, byte[] data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
    }
}
