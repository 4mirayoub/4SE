import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Priority queue for objects of type T based on dominance ordering
public class SpeciesQueue<T extends Comparable> implements Iterable<T> , Cloneable{
    private T[] queue;
    private int size;

    // Constructor: initialize with capacity 10
    public SpeciesQueue() {
        queue = (T[]) new Animal[10];
        size = 0;
    }

    // Double the array size when full
    private void resize() {
        T[] newQueue = (T[]) new Animal[queue.length * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    // Add element according to dominance ordering
    public void add(T item) {
        T temp;
        int i;
        if (item == null) throw new InvalidInputException();
        if (size == queue.length) {
            resize();
        }
        int result = 1;
        // Find insertion point based on dominance comparison
        for (i = 0; result > 0; i++) {
            if(i >= size)break;
            result = queue[i].compareTo(item);
            if(result <= 0) break;
        }
        // Further adjust insertion point to group same class types together
        for (; i < size; i++) {
            if (queue[i].getClass().equals(item.getClass()))break;
            result = queue[i].compareTo(item);
            if (result < 0) break;
        }
        // Shift elements right to make space for new item
        while (i < size) {
            temp = queue[i];
            queue[i] = item;
            item = temp;
            i++;
        }
        queue[size] = item;
        size++;
    }

    // Remove and return the head element
    public T remove() {
        T temp;
        if (size <= 0) throw new EmptyQueueException();
        temp = queue[0];
        for (int i = 0; i < size - 1; i++) queue[i] = queue[i + 1];
        size--;
        return temp;
    }

    // Return the head element without removing it
    public T peek() {
        if (size <= 0) throw new EmptyQueueException();
        return queue[0];
    }

    // Return the size
    public int size() {
        return this.size;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Returns a deep copy of this queue and its elements
    @Override
    public SpeciesQueue<T> clone() {
        SpeciesQueue<T> clonedQueue = new SpeciesQueue<>();
        // Loop through each element in the current queue
        for (int i = 0; i < size; i++) {
            try {
                T item = queue[i];
                if (item == null) continue; // Skip nulls
                // Clone each item using its clone() method
                T clonedItem = (T) item.getClass().getMethod("clone").invoke(item);
                clonedQueue.add(clonedItem);
            } catch (Exception e) {
                return null;
            }
        }
        return clonedQueue;
    }

    // Iterator for the queue
    @Override
    public Iterator<T> iterator() {
        return new SpeciesQueueIterator();
    }

    // Iterator for SpeciesQueue
    class SpeciesQueueIterator implements Iterator<T> {
        private int index = 0;

        // Check if more elements exist
        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        // Return next element or throw if none
        @Override
        public T next() {
            if(hasNext()) return queue[this.index++];
            throw new EmptyQueueException();
        }
    }
}