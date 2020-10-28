/* *****************************************************************************
 *  Name:              Zack
 *  Coursera User ID:  123456
 *  Last modified:     10/19/2020
 *  generic type practice
 **************************************************************************** */
package week2;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>{
    private Node first = null;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() { return current != null;}
        public void remove() {/* not supported */}
        public Item next() {
            Item item= current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Iterator Test:");
        Iterator<Integer> itr = stack.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        for(int inTemp : stack) StdOut.println(inTemp);
    }
}
