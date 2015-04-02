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
}
