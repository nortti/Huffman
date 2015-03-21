package huffman.compression.tools;

public class DataInfo {

    private int[] charFreqs = new int[128];
    private StringBuilder asString = new StringBuilder();

    public DataInfo(byte[] data) {
        for (byte charByte : data) {
            this.asString.append((char) charByte);
            this.charFreqs[charByte]++;
        }
    }

    public int[] getCharFreqs() {
        return this.charFreqs;
    }

    public String getString() {
        return this.asString.toString();
    }
}
