package huffman.converting;

/**
 * Interface for classes that convert data.
 */
public interface DataConverter {

    byte[] convert(byte[] data);

    /**
     * Returns a modified version of a filename appropriate for the file after being converted by
     * this data converter. For example, "Hello.txt.huf" might return "Hello.txt".
     */
    String getNewPath(String path);
}
