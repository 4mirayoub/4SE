// Abstract base class for all animal types, supports compare and clone
public  abstract class Animal implements Comparable<Animal> ,Cloneable {
    protected  String species;
    protected int dominance;


    public String getSpecies() {
        return species;
    }
    public int getDominance() {
        return dominance;
    }
    public String toString() {
        return this.species;
    }
    @Override
    public Animal clone() {//Copy function
        try {
            return (Animal) super.clone();//Covariant Return Type
        } catch (CloneNotSupportedException e) {// catch error
            return null;
        }
    }
    public int compareTo(Animal other) {
        if(other == null)return this.dominance;
        return this.dominance - other.getDominance();//The difference between two objects
    }

}