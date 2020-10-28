/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package week2;

/*  file
*   quene insert in the head and pop from the end, pointer is from head to end.
* */
public class QueueOfString {
    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else    oldlast.next = last;
    }
    String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }
    boolean isEmpty() {
        return first == null;
    }
    public static void main(String[] args) {
        QueueOfString stringQuene = new QueueOfString();
        stringQuene.enqueue("a");
        stringQuene.enqueue("b");
        stringQuene.enqueue("c");
        stringQuene.enqueue("d");
        System.out.println(stringQuene.dequeue());
        System.out.println(stringQuene.dequeue());
        System.out.println(stringQuene.dequeue());
        System.out.println(stringQuene.dequeue());
    }
}

