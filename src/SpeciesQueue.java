import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class SpeciesQueue<T extends Comparable> implements Iterable<T> , Cloneable{
    private T[] queue;
    private int size;

    public SpeciesQueue() {//constructor
        queue = (T[]) new Animal[10]; // start with capacity 10
        size = 0;
    }

    private void resize() {//Doubles the size of the array each time you reach the end.
        T[] newQueue = (T[]) new Animal[queue.length * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    public void add(T animal) {//Adds elements to an array according to its dominance
        T saveAnimal;
        int i;
        if (animal == null) throw new InvalidInputException();//catch error
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

    public T remove() {//removing function
        T saveAnimal;
        if (size <= 0) throw new EmptyQueueException();//catch error
        saveAnimal = queue[0];
        for (int i = 0; i < size - 1; i++) queue[i] = queue[i + 1];//removing
        size--;
        return saveAnimal;
    }

    public T peek() {//returning the head of an array without removing it
        if (size <= 0) throw new EmptyQueueException();//catch error
        return queue[0];
    }

    public int size() {
        return this.size;
    }//getsize of an array

    public boolean isEmpty() {
        return this.size == 0;
    }//checking if the array is empty
    @Override
    public SpeciesQueue<T> clone() {//cloning function
        SpeciesQueue<T> clonedQueue = new SpeciesQueue<>();
        for (int i = 0; i < size; i++) {
            try {//use of try catch
                T item = queue[i];
                if (item == null) continue;
                T clonedItem = (T) item.getClass().getMethod("clone").invoke(item);//use the invoke and getmethod as ordered
                clonedQueue.add(clonedItem);

            } catch (Exception e) {//catch error and return null
                return null;
            }
        }
        return clonedQueue;
    }

    @Override
    public Iterator<T> iterator() {
        return new SpeciesQueueIterator();
    }// iterator function

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