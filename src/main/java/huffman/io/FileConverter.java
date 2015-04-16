package huffman.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import huffman.converting.DataConverter;

/**
 * For handling the files involved in compressing/decompressing, i.e. deleting
 * the old file, and creating a new one with new data and possibly filename.
 */
public class FileConverter {

    /**
     * Global variable representing the size of the extended ascii charset.
     */
    public static final int CHARSET_SIZE = 256;

    /**
     * Converts a file with a compressed/decompressed version of that file.
     * @param Dataconverter An encoder/decoder
     */
    public void convert(File file, DataConverter dataConverter) throws IOException {
        String path = file.getPath();
        // Get data from file
        byte[] inputData = Files.readAllBytes(Paths.get(path));
        // Then get an encoded/decoded version of that data
        byte[] outputData;
        try {
         outputData  = dataConverter.convert(inputData);
        } catch (UnsupportedEncodingException e) {
            System.out.println("File conversion failure - did you try to compress/decompress" +
                               " an already compressed/decompressed file?");
            return;
        }
        // And replace input file with a new file with new data
        replaceFile(file, dataConverter.getNewPath(path), outputData);
    }

    private static void replaceFile(File file, String newPath, byte[] data) throws IOException {
        file.delete();
        file = new File(newPath);
        writeToFile(file, data);
        file.createNewFile();
    }

    private static void writeToFile(File file, byte[] data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
        outputStream.close();
    }
}
