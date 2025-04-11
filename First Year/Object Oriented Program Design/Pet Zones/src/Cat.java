public class Cat implements Petable{

    public String name;

    public int age;

    public int weightInOz;

    public Coord2D location;

    public boolean hasBeenPetToday;

    public Cat(String name, int age, int weightInOz, Coord2D location, boolean hasBeenPetToday){
        this.name = name;
        this.age = age;
        this.location = location;
        this.hasBeenPetToday = hasBeenPetToday;
        this.weightInOz = weightInOz;
    }

    /**
     * Produces the name of this pet in the rescue
     *
     * @return the pet's name as a string
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * the weight of the pet in Oz
     *
     * @return the weight rounded to the nearest oz
     */
    @Override
    public int getWeightInOz() {
        return weightInOz;
    }

    /**
     * get the age of the pet
     *
     * @return the age of the pet in years
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * how much of a particular food a pet will eat
     *
     * @param foodLabel the type of food being asked
     * @return a number (0 if the pet does not eat that food)
     */
    @Override
    public int eats(String foodLabel) {
        if(foodLabel.equals("cans")){
            if(hasBeenPetToday){
                return 2;
            }
            return 1;
        }
        else if (foodLabel.equals("treats")) {
            return (1 + (weightInOz / 8));
        }
        return 0;
    }
}
