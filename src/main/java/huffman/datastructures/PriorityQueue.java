package huffman.datastructures;

public class PriorityQueue {

    private final Node[] nodes;
    private int size = 0;

    public PriorityQueue(int maxSize) {
        // Add 1 to account for root being at 1;
        this.nodes = new Node[maxSize+1];
    }

    public void add(Node node) {
        this.size++;
        int startIndex = this.size;
        int correctIndex = findCorrectIndex(startIndex, node);
        nodes[correctIndex] = node;
    }

    public Node poll() {
        Node leastFreq = nodes[1];
        nodes[1] = nodes[size];
        size--;
        heapify(1);
        return leastFreq;
    }

    public int getSize() {
        return size;
    }

    private int findCorrectIndex(int currentIndex, Node node) {
        int parentIndex = parentIndex(currentIndex);
        Node parentNode = nodes[parentIndex];
        // Bubble up until correct index is found
        while (currentIndex > 1 && parentNode.getFreq() > node.getFreq()) {
            nodes[currentIndex] = nodes[parentIndex];
            currentIndex = parentIndex(currentIndex);
            parentIndex = parentIndex(currentIndex);
            parentNode = nodes[parentIndex];
        }
        return currentIndex;
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
            swap(index, leastFreqChildIndex);
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
