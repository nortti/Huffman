package huffman.datastructures;

public class PriorityQueue {

    private Node[] nodes = new Node[256 * 256];
    private int size = 0;

    public void add(Node node) {
        size++;
        int index = size;

        while (index > 1 && nodes[parentIndex(index)].getFreq() > node.getFreq()) {
            nodes[index] = nodes[parentIndex(index)];
            index = parentIndex(index);
        }
        nodes[index] = node;
    }

    public Node poll() {
        Node leastFreq = nodes[1];
        nodes[1] = nodes[size];
        size--;
        return leastFreq;
    }

    private void heapify(int index) {
        int leftIndex = leftIndex(index);
        int rightIndex = rightIndex(index);
        if (rightIndex <= this.size) {
            int leastFreqChildIndex;
            if (nodes[leftIndex].getFreq() < nodes[rightIndex].getFreq()) {
                leastFreqChildIndex = leftIndex;
            } else {
                leastFreqChildIndex = rightIndex;
            }
            heapify(leastFreqChildIndex);
        } else if (leftIndex == this.size && nodes[index].getFreq() > nodes[leftIndex].getFreq()) {
            swap(index, leftIndex);
        }
    }

    private void swap(int index1, int index2) {
        Node tmp = nodes[index1];
        nodes[index1] = nodes[index2];
        nodes[index2] = tmp;
    }

    public int getSize() {
        return size;
    }

    private int parentIndex(int index) {
        return index / 2;
    }

    private int leftIndex(int index) {
        return index * 2;
    }

    private int rightIndex(int index) {
        return index * 2 + 1;
    }
}
