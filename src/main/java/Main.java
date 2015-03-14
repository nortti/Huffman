import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o', 'h', 'e', 'l', 'l', 'o'};
        Arrays.sort(s); // eehhlllloo
        PriorityQueue<Node> q = new PriorityQueue();
        Node n = new Node(s[0]);
        for (int i = 1 ; i < s.length ; i++) {
            if (n.c!=s[i]) {
                q.add(n);
                n = new Node(s[i]);
            } else {
                n.a++;
            }
        }
        q.add(n);
        System.out.println(q);
    }

    public static class Node implements Comparable<Node> {
        char c;
        int a = 1;
        Node left;
        Node right;

        public Node(char c) {
            this.c=c;
        }

        public int compareTo(Node o) {
            return this.a-o.a;
        }

        public String toString() {
            return c + "," + a;
        }
    }
}


