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
        int finalIndex = findFinalIndex(startIndex, node);
        nodes[finalIndex] = node;
    }

    public Node poll() {
        Node leastFreq = nodes[1];
        nodes[1] = nodes[size];
        size--;
        return leastFreq;
    }

    public int getSize() {
        return size;
    }
    private int findFinalIndex(int currentIndex, Node node) {
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
