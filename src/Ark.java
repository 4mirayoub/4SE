import java.util.Iterator;

public class Ark {
    private SpeciesQueue<Animal> queue = new SpeciesQueue<>();

    public Ark() {
    }

    public void add(Animal animal) {
        queue.add(animal);
    }//adding function

    public void enterToArk() {//entering animals to the ark
        Animal animal = queue.remove();
        System.out.println("A " + animal.getSpecies() + " entered the ark");
    }

    public void enterAllToArk() {//enter all the animals to the ark
        int size = queue.size();
        while (size > 0) {
            enterToArk();
            size -= 1;
        }
    }

    public void showQueue() {//Prints the names of the animals in the array in the order of the indexes in the array (from 0 to the end)
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