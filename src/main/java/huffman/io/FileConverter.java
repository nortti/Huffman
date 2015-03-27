package huffman.io;

import huffman.converting.DataConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileConverter {

    public static final int CHARSET_SIZE = 256;

    public void convert(File file, DataConverter dataConverter) throws IOException {
        String path = file.getPath();
        byte[] inputData = Files.readAllBytes(Paths.get(path));
        byte[] outputData  = dataConverter.convert(inputData);
        String newPath = dataConverter.newPath(path);
        replaceFile(file, newPath, outputData);
    }

    private void replaceFile(File file, String newPath, byte[] data) throws IOException {
        file.delete();
        file = new File(newPath);
        writeToFile(file, data);
        file.createNewFile();
    }

    private void writeToFile(File file, byte[] data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(data);
        outputStream.close();
    }
}