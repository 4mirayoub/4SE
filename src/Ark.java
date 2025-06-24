import java.util.Iterator;

public class Ark {
    private SpeciesQueue<Animal> queue = new SpeciesQueue<>();

    public Ark() {
    }

    public void add(Animal animal) {
        queue.add(animal);
    }

    public void enterToArk() {
        Animal animal = queue.remove();
        System.out.println("A " + animal.getSpecies() + " entered the ark");
    }

    public void enterAllToArk() {
        int size = queue.size();
        while (size > 0) {
            enterToArk();
            size -= 1;
        }
    }

    public void showQueue() {
        Iterator<Animal> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            System.out.print(animal.getSpecies());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }

        System.out.println();
    }
}