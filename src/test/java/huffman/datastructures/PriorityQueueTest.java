package huffman.datastructures;

import static org.junit.Assert.*;

import org.junit.*;

public class PriorityQueueTest {
    PriorityQueue priorityQueue = new PriorityQueue(100);

    @Test
    public void addWorks() {
        for (int i = 0; i < 50; i++) {
            priorityQueue.add(new Node((char) i, 0));
        }
        assertTrue(priorityQueue.getSize() == 50);
    }

    @Test
    public void pollWorks() {
        for (int i = 0; i < 50; i++) {
            priorityQueue.add(new Node((char) i, 0));
        }

        for (int i = 0; i < 25; i++) {
            priorityQueue.poll();
        }
        assertTrue(priorityQueue.getSize() == 25);
    }

    @Test
    public void heapifyWorks() {
        priorityQueue.add(new Node((char) 0, 0));
        priorityQueue.add(new Node((char) 1, 2));
        priorityQueue.add(new Node((char) 2, 1));
        priorityQueue.add(new Node((char) 3, 3));
        assertTrue(priorityQueue.poll().getFreq() == 0);
        assertTrue(priorityQueue.poll().getFreq() == 1);
        assertTrue(priorityQueue.poll().getFreq() == 2);
        assertTrue(priorityQueue.poll().getFreq() == 3);
    }
}
