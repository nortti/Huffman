package huffman.converting;

import huffman.datastructures.Node;
import huffman.io.BitInputStream;

public class DecodingHuffTreeMaker implements HuffmanTreeMaker {

    @Override
     public HuffmanTree makeTree(byte[] data) {
         BitInputStream bitInputStream = new BitInputStream(data);
         Node root = decodeTreeFromData(bitInputStream);
         HuffmanTree huffmanTree = new HuffmanTree(root);
         return huffmanTree;
    }

    private static Node decodeTreeFromData(BitInputStream bitInputStream) {
        boolean isSet = bitInputStream.read();
        if (isSet) {
            char character = (char)bitInputStream.readByte();
            return new Node(character, 0);
        } else {
            Node leftChild = decodeTreeFromData(bitInputStream);
            Node rightChild = decodeTreeFromData(bitInputStream);
            Node parent = new Node(leftChild, rightChild);
            return parent;
        }
    }

}
