package huffman.decompression;

import huffman.datastructures.HuffmanTree;
import huffman.datastructures.Node;
import huffman.io.BitReader;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;

public class Decompresser {

    public static void decompress(File file) throws IOException {
        BitReader bitReader = new BitReader(file);
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
            for (int i = 0; i < 128; i++) {
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
    }
}
