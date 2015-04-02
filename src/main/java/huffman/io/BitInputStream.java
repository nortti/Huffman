package huffman.io;

public class BitInputStream {

    private byte[] data;

    /**
     * Index pointer to the current byte being read in data.
     */
    private int bytePointer = 0;

    /**
     * Bitmask with one positive bit moving from left to right, and resetting on 0
     */
    private int mask = 128;

    public BitInputStream(byte[] data) {
        this(data, 0);
    }

    /**
     * Constructor which skips a portion of the bits in data.
     */
    public BitInputStream(byte[] data, int startBit) {
        this.data = data;
        // First skips full bytes
        bytePointer += Math.floor(startBit / 8);
        // Then the remaining bits
        for (int i = 0; i < startBit % 8; i++) {
            readBit();
        }
    }

    public boolean readBit() {
        // First read next bit with the help of mask
        boolean isSet = (data[bytePointer] & mask) != 0;
        // Shift mask right for next bit
        mask >>>= 1;
        // If mask is 0, reset it and move on to the next byte
        if (mask == 0) {
            bytePointer++;
            mask = 128;
        }
        return isSet;
    }

    public int readByte() {
        byte aByte = 0;
        // Use read() 8 times and using 2^i to increment aByte accordingly
        for (int i = 7; i >= 0; i--) {
            if (this.readBit()) {
                aByte += Math.pow(2, i);
            }
        }
        return aByte;
    }
}
