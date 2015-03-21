package huffman.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class BitReader {

    private byte[] data;
    private int bytePointer = 0;
    private int mask = 128;

    public BitReader(File file) throws IOException {
        Path path = file.toPath();
        this.data = Files.readAllBytes(path);
    }

    public boolean read() {
        boolean isSet = (data[bytePointer] & mask) != 0;
        mask >>>= 1;
        if (mask == 0) {
            bytePointer++;
            mask = 128;
        }
        return isSet;
    }

    public byte readByte() {
        byte b = 0;
        for (int mask = 128; mask > 0; mask >>>= 1) {
            if (this.read()) {
                b += mask;
            }
            // Separator
            if (b < 0) {
                return 50;
            }
        }
        return b;
    }
}
