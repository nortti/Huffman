package huffman.datastructures;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Tests the functionality of the PriorityQueue class.
 */
public class PriorityQueueTest {
    PriorityQueue priorityQueue = new PriorityQueue(100);

    /**
     * Checks that the add method works by calling it 50 times and checking that the size is indeed
     * 50.
     */
    @Test
    public void addWorks() {
        for (int i = 0; i < 50; i++) {
            priorityQueue.add(new Node((char) i, 0));
        }
        assertTrue(priorityQueue.getSize() == 50);
    }

    /**
     * Checks that the poll method works by adding 50 elements, then polling 25 and checking that
     * the size is 25.
     */
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

    /**
     * Checks that heapify works by adding nodes in such a way, that all cases of the heapify-
     * method are tested as it is checked that they are polled in the correct order.
     */
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
