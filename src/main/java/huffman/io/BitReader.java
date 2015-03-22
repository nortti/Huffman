package huffman.io;

import java.io.ByteArrayInputStream;

public class BitReader {

    private byte[] data;
    private int bytePointer = 0;
    private int mask = 128;

    public BitReader(byte[] data) {
        this.data = data;
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

    public int readByte() {
        int b = 0;
        for (int mask = 128; mask != 0; mask >>>= 1) {
            if (this.read()) {
                b += mask;
            }
        }
        return b;
    }
}
