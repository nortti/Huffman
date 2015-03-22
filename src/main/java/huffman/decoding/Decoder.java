package huffman.decoding;

import static huffman.io.FileChanger.CHARSET_SIZE;
import huffman.io.BitReader;
import huffman.datastructures.HuffmanTree;

public class Decoder {
    public static byte[] decode(byte[] data) {
        BitReader bitReader = new BitReader(data);
        HuffmanTree huffmanTree = new HuffmanTree(bitReader);
        String decoded = "";
        String code = "";
        boolean EOF = false;
        while (!EOF) {
            boolean isSet = bitReader.read();
            if (!isSet) {
                code += '0';
            } else {
                code += '1';
            }
            for (int i = 0; i < CHARSET_SIZE; i++) {
                String workingCode = huffmanTree.getCodes()[i];
                if (workingCode != null && workingCode.equals(code)) {
                    if (i == 0) {
                        EOF = true;
                        break;
                    } else {
                      decoded += (char) i;
                      code = "";
                   }
                  break;
                }
            }
        }
        System.out.println(decoded);
        return new byte[1];
    }
}
