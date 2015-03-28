package huffman.io;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class BitOutputStream implements OutputStream {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private byte workingByte = 0;
    private int bitCounter = 0;

    public void write(boolean isOn) {
        workingByte <<= 1;
        if (isOn) workingByte++;
        bitCounter++;

        if (bitCounter == 8) {
            writeAndReset();
        }
    }

    private void writeAndReset() {
        out.write(workingByte);
        workingByte = 0;
        bitCounter = 0;
    }

    public void write(char character) {
        for (int mask = 128; mask != 0; mask >>>= 1) {
            write((character & mask) != 0);
        }
    }

    public byte[] read() {
        workingByte <<= 8-bitCounter;
        out.write(workingByte);
        byte[] data = out.toByteArray();
        return data;
    }
}
