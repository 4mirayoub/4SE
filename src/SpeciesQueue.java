import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class SpeciesQueue<T extends Comparable> implements Iterable<T> , Cloneable{
    private T[] queue;
    private int size;

    public SpeciesQueue() {
        queue = (T[]) new Animal[10]; // start with capacity 10
        size = 0;
    }

    private void resize() {
        T[] newQueue = (T[]) new Animal[queue.length * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    public void add(T animal) {
        T saveAnimal;
        int i;
        if (animal == null) throw new InvalidInputException();
        if (size == queue.length) {
            resize();
        }
        int resalt = 1;
        for (i = 0; resalt > 0; i++) {
            if(i >= size)break;
            resalt = queue[i].compareTo(animal);
            if(resalt <= 0) break;
        }
        for (; i < size; i++) {
            if (queue[i].getClass().equals(animal.getClass()))break;
            resalt = queue[i].compareTo(animal);
            if (resalt < 0) break;
        }
        while (i < size) {
            saveAnimal = queue[i];
            queue[i] = animal;
            animal = saveAnimal;
            i++;
        }
        queue[size] = animal;
        size++;
    }

    public T remove() {
        T saveAnimal;
        if (size <= 0) throw new EmptyQueueException();
        saveAnimal = queue[0];
        for (int i = 0; i < size - 1; i++) queue[i] = queue[i + 1];
        size--;
        return saveAnimal;
    }

    public T peek() {
        if (size <= 0) throw new EmptyQueueException();
        return queue[0];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public SpeciesQueue<T> clone() {
        SpeciesQueue<T> clonedQueue = new SpeciesQueue<>();

        for (int i = 0; i < size; i++) {
            T item = queue[i];

            if (item instanceof Cloneable) {
                try {
                    // נניח שלמחלקות יש clone() שמחזיר T
                    T clonedItem = (T) item.getClass().getMethod("clone").invoke(item);
                    clonedQueue.add(clonedItem);
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null; // אם איבר לא מממש Cloneable - אסור להכניס
            }
        }

        return clonedQueue;
    }
    @Override
    public Iterator<T> iterator() {
        return new SpeciesQueueIterator();
    }

    class SpeciesQueueIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return this.index < size;
        }
        @Override
        public T next() {
            if(hasNext()) return queue[this.index++];
            throw new EmptyQueueException();
        }
    }
}