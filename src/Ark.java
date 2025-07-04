import java.util.Iterator;
public class Ark {
    private static final String SEPARATOR = ", ";
    private SpeciesQueue<Animal> queue = new SpeciesQueue<>();

    public Ark() {
    }

    // add an animal to the queue
    public void add(Animal animal) {
        queue.add(animal);
    }

    //entering animals to the ark
    public void enterToArk() {
        Animal animal = queue.remove();
        System.out.println("A " + animal.getSpecies() + " entered the ark");
    }

    //enter all the animals to the ark
    public void enterAllToArk() {
        int size = queue.size();
        while (size > 0) {
            enterToArk();
            size -= 1;
        }
    }

     // print species of all animals in queue
    public void showQueue() {
        Iterator<Animal> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            System.out.print(animal.getSpecies());
            if (iterator.hasNext()) {
                System.out.print(SEPARATOR);
            }
        }

        System.out.println();
    }
}