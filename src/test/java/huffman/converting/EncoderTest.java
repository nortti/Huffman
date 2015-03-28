package huffman.converting;

import static org.junit.Assert.*;
import org.junit.*;

// In its current state this class is hard to test properly as any changes to the underlying algorithms that
// determine the character-to-code map may get this class to produce a different encoding

public class EncoderTest {

    Encoder encoder = new Encoder(new DecodingHuffTreeMaker());

    @Test
    public void correctEncodingOnExample() {
        byte[] input = { (byte)74, (byte)65, (byte)73, (byte)74 }; // = test
        byte[] output = encoder.convert(input);
        byte[] correctOutput = { (byte)82, (byte)-108, (byte)-108, (byte)2, (byte)-126, (byte)-13, (byte)0 };
        assertArrayEquals(output, correctOutput);
    }
}
