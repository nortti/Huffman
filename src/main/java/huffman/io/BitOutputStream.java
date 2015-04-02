package huffman.io;

import java.io.ByteArrayOutputStream;

/**
 * An output stream that works on bit-level.
 */
public class BitOutputStream {

    /**
     * The underlying output stream that works on byte-level.
     */
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * The byte currently being modified. Is written to outputStream and reset after each full byte.
     */
    private byte currentByte = 0;

    /**
     * Pointer to which bit in currentByte will be written to next. Incremented on each written
     * bit, and resets at 8 bits, along with currentByte.
     */
    private int bitCounter = 0;

    /**
     * Writes a single bit to currentByte
     */
    public void write(boolean isOn) {
        // Shift left to make room for next bit
        currentByte <<= 1;
        // Increment byte by one to mark an 1-bit, or don't to mark a 0-bit
        if (isOn) {
            currentByte++;
        }
        bitCounter++;
        // If the last bit of a byte was written, reset
        if (bitCounter == 8) {
            writeAndReset();
        }
    }

    private void writeAndReset() {
        outputStream.write(currentByte);
        currentByte = 0;
        bitCounter = 0;
    }

    /**
     * Writes the 8 bits of a character.
     */
    public void write(char character) {
        // Loops 8 times, once for each bit in the character-byte, with a mask with one positive
        // bit moving from left to right. Using a bitwise and on the mask writes one bit at a time.
        for (int mask = 128; mask != 0; mask >>>= 1) {
            write((character & mask) != 0);
        }
    }

    public byte[] toByteArray() {
        // If an unfinished byte was started, shifts that byte left accordingly and adds it too
        if (currentByte != 0 && bitCounter != 0) {
            currentByte <<= 8 - bitCounter;
            outputStream.write(currentByte);
        }
        byte[] data = outputStream.toByteArray();
        return data;
    }
}
