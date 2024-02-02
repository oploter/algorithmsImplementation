import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
 

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final double MIN_LOAD = 0.25;
    private Item[] arr;
    private int size;
    private int startIndex;
    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        startIndex = 0;
        size = 0;
    }

    private void resize(int newCapacity) {
        Item[] newArr = (Item[]) new Object[newCapacity];
        for (int i = 0, oldI = startIndex; i < size; oldI = (oldI + 1) % arr.length, ++i) {
            newArr[i] = arr[oldI];      
        }
        arr = newArr;
        startIndex = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= arr.length) {
            resize(2 * arr.length);
        }
        arr[(startIndex + size) % arr.length] = item;
        ++size;
    }

    private void swap() {
        int indexToRemove = (startIndex + StdRandom.uniformInt(size)) % arr.length;
        Item tmp = arr[startIndex];
        arr[startIndex] = arr[indexToRemove];
        arr[indexToRemove] = tmp;    
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size <= arr.length * MIN_LOAD && size > 0) {
            resize(arr.length / 2);
        }
        swap();
        Item removed = arr[startIndex];
        arr[startIndex] = null;
        --size;
        startIndex = (startIndex + 1) % arr.length;
        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        swap();
        return arr[startIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(startIndex, size); 
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int start;
        private int ind;
        private int[] seq;
        public RandomizedQueueIterator(int startIndex, int size) {
            seq = new int[size];
            for (int i = 0; i < size; ++i) {
                seq[i] = i;
            }
            StdRandom.shuffle(seq);
            start = startIndex;
            ind = 0;
        }

        @Override
        public boolean hasNext() {
            return ind < seq.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }  
            Item removed =  arr[(start + seq[ind++]) % arr.length];
            return removed;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        StdOut.println(q.isEmpty());
        for (int i = 1; i < 9; ++i) {
            q.enqueue(i);
        }
        StdOut.println(q.size());
        while (!q.isEmpty()) {
            StdOut.print(q.sample() +  " ");
            StdOut.println(q.dequeue());
        }
        for (int i = 1; i < 5; ++i) {
            q.enqueue(i);
        }
        Iterator<Integer> it = q.iterator();
        while (it.hasNext()) {
            StdOut.println(it.hasNext() + " " + it.next());
        }
    }

}
