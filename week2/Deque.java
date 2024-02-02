import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private static final double MIN_LOAD = 0.25;
    private Item[] arr;
    private int size;
    private int startIndex;
    public Deque() {
        arr = (Item[]) new Object[1];
        startIndex = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= arr.length) {
            resize(2 * arr.length);
        }
        --startIndex;
        if (startIndex < 0) {
            startIndex += arr.length;
        }
        arr[startIndex] = item;
        ++size;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= arr.length) {
            resize(2 * arr.length);
        }
        arr[(startIndex + size++) % arr.length] = item;
    }

    private void resize(int newCapacity) {
        Item[] newArr = (Item[]) new Object[newCapacity];
        for (int i = 0, oldI = startIndex; i < size; oldI = (oldI + 1) % arr.length, ++i) {
            newArr[i] = arr[oldI];      
        }
        arr = newArr;
        startIndex = 0;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size <= arr.length * MIN_LOAD && size > 0) {
            resize(arr.length / 2);
        }
        Item tmp = arr[startIndex];
        arr[startIndex] = null;
        --size;
        startIndex = (startIndex + 1) % arr.length;

        return tmp;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size <= arr.length * MIN_LOAD && size > 0) {
            resize(arr.length / 2); 
        }
        int lastIndex = (startIndex + size - 1) % arr.length;    
        Item tmp = arr[lastIndex];
        arr[lastIndex] = null;
        --size;
        return tmp;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(startIndex);
    }

    private class DequeIterator implements Iterator<Item> {
        private int i;
        private boolean cycleDone;

        public DequeIterator(int startIndex) {
            i = startIndex;
            cycleDone = false;
        }
        @Override
        public boolean hasNext() {
            int endIndex = (startIndex + size) % arr.length;
            return (size != 0) && !(i == endIndex && cycleDone);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item curr = arr[i];
            cycleDone = true;
            i = (i + 1) % arr.length;
            return curr;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
 
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        StdOut.println(d.isEmpty());
        d.addFirst(1);
        d.addLast(2);
        StdOut.println(d.removeFirst());
        StdOut.println(d.removeLast());
        StdOut.println(d.size());
        d.addFirst(3);
        d.addFirst(4);
        d.addLast(5);
        Iterator<Integer> it = d.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
    }
    
}
