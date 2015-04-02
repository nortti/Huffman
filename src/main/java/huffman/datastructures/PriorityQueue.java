package huffman.datastructures;

/**
 * A min-heap priority queue.
 */
public class PriorityQueue {

    private final Node[] nodes;
    private int size = 0;

    public PriorityQueue(int maxSize) {
        // Add 1 to account for root being at 1;
        this.nodes = new Node[maxSize + 1];
    }

    public void add(Node node) {
        this.size++;
        int startIndex = this.size;
        int correctIndex = this.findCorrectIndex(startIndex, node);
        this.nodes[correctIndex] = node;
    }

    /**
     * Retrieve and remove the node which has the lowest frequency.
     */
    public Node poll() {
        Node leastFreq = this.nodes[1];
        this.nodes[1] = this.nodes[size];
        this.size--;
        this.heapify(1);
        return leastFreq;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Finds the correct index for a node at currentIndex by bubbling up.
     */
    private int findCorrectIndex(int currentIndex, Node node) {
        int parentIndex = this.parentIndex(currentIndex);
        Node parentNode = this.nodes[parentIndex];
        // Bubble up until correct index is found
        while (currentIndex > 1 && parentNode.getFreq() > node.getFreq()) {
            this.nodes[currentIndex] = this.nodes[parentIndex];
            currentIndex = this.parentIndex(currentIndex);
            parentIndex = this.parentIndex(currentIndex);
            parentNode = this.nodes[parentIndex];
        }
        return currentIndex;
    }

    /**
     * Fix heap-property.
     */
    private void heapify(int index) {
        int leftIndex = this.leftIndex(index);
        int rightIndex = this.rightIndex(index);
        if (rightIndex <= this.size) {
            int leastFreqChildIndex;
            if (this.nodes[leftIndex].getFreq() < this.nodes[rightIndex].getFreq()) {
                leastFreqChildIndex = leftIndex;
            } else {
                leastFreqChildIndex = rightIndex;
            }
            this.swap(index, leastFreqChildIndex);
            this.heapify(leastFreqChildIndex);
        } else if (leftIndex == this.size &&
                   this.nodes[index].getFreq() > this.nodes[leftIndex].getFreq()) {
            this.swap(index, leftIndex);
        }
    }

    private void swap(int index1, int index2) {
        Node tmp = this.nodes[index1];
        this.nodes[index1] = this.nodes[index2];
        this.nodes[index2] = tmp;
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
