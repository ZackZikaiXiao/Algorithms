/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package week2;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    private class Node {
        Item item;
        Node next, prev;
    }
    /* construct an empty deque
    // first = last = null: none;
    // first = last != null: only one element
    // else : more than one element
     */
    public Deque() {
        first = new Node();
        last = new Node();
        first.item = null;
        first.next = null;
        first.prev = null;
        last = first;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first.item == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {    // first -> oldfirst -> ... -> last
        if ( item == null ) throw new IllegalArgumentException();
        if (isEmpty()) {
            first.item = item;
        }
        else {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            first.prev = null;
            oldfirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {    // first-> ... -> oldlast -> last
        if ( item == null ) throw new IllegalArgumentException();
        if (isEmpty()) {
            last.item = item;
        }
        else {
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.prev = oldlast;
            last.next = null;
            oldlast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() { // first -> ... -> last
        if(isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        if (first == last && first.item != null) {  // only one element in the queue
            first.item = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }
    // remove and return the item from the back
    public Item removeLast() {  // first -> ... -> last
        if(isEmpty()) throw new java.util.NoSuchElementException();
        Item item = last.item;
        if (first == last && first.item != null) {  // only one element in the queue
            last.item = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() {  throw new UnsupportedOperationException(); } /*not supported*/
        public Item next() {
            if(isEmpty()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println("Add Test:");
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        StdOut.println("Iterator Test:");
        for(int inTemp : deque) StdOut.println(inTemp);
        StdOut.println("Remove Test:");
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
    }
}