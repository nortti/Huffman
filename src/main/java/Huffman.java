import java.io.*;

public class Huffman {

    public static void main (String[] args) throws IOException {
        String path = "src/main/resources/file.txt";
        File file = new File(path);
        Compresser compresser = new Compresser();
        compresser.generate(file);
    }
}
