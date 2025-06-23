public  abstract class Animal implements Comparable<Animal>  {

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

    public int compareTo(Animal other) {
        if(other == null)return this.dominance;
        return this.dominance - other.getDominance();
    }
}