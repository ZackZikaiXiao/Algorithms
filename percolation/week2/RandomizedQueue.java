/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

package week2;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.Objects;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int N;            // pointer to element, range in [0, capacity)
    // construct an empty randomized queue
    public RandomizedQueue(){
        array = (Item[]) new Object[2];
        N = 0;
    }
    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return array.length;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == array.length) resize(2 * array.length);
        array[N++] = item; // array[N] = item; item++;
    }

    // remove and return a random item
    public Item dequeue() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int randomN = StdRandom.uniform(N); // randomN in [0, N); N--;
        Item swapTemp = array[randomN];
        array[randomN] = array[N-1];
        array[N-1] = swapTemp;
        Item item = array[--N];
        if (N > 0 && N == array.length/4) resize(array.length/2);
        return item;
    }
    // return a random item (but do not remove it)
    public Item sample() {
        if(isEmpty()) throw new java.util.NoSuchElementException();
        return array[StdRandom.uniform(N)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {return new RandomizedQueueIterator();}

    private class RandomizedQueueIterator implements Iterator<Item>{
        public boolean hasNext() {
            return !isEmpty();
        }

        public Item next() {
            if(isEmpty()) throw new UnsupportedOperationException();
            return dequeue();
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    private void resize(int capacity) {
        assert capacity >= N;
        // array = java.util.Arrays.copyOf(array, capacity);
        Item[] copy = (Item[]) new Object[capacity];
        // System.arraycopy(array, 0, copy, 0, array.length);
        for (int i = 0; i < N; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();
        for(int i = 0; i < 20; i++) {
            r.enqueue(i);
        }
        for(Object intemp : r) StdOut.println(intemp);
    }

}