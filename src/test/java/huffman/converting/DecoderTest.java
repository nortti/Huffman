package huffman.converting;

import static org.junit.Assert.*;
import org.junit.*;

// Better tests need some overall restructuring of the program
public class DecoderTest {

    Decoder decoder = new Decoder(new DecodingHuffTreeMaker());

    @Test
    public void correctDecodingOnExample() {
        byte[] data = { (byte)82, (byte)-108, (byte)-108, (byte)2, (byte)-126, (byte)-13, (byte)0 };
        byte[] decodedData = decoder.convert(data);
        byte[] correctDecodedData = { (byte)74, (byte)65, (byte)73, (byte)74 }; // = test
        assertArrayEquals(decodedData, correctDecodedData);
    }
}
