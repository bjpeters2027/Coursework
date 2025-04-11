
/**
 * The things a pet needs to provide, generically
 */
public interface Petable {
    /**
     * Produces the name of this pet in the rescue
     * @return the pet's name as a string
     */
    String getName();
    /**
     * the weight of the pet in Oz
     * @return the weight rounded to the nearest oz
     */
    int getWeightInOz();
    /**
     * get the age of the pet
     * @return the age of the pet in years
     */
    int getAge();
    /**
     * how much of a particular food a pet will eat
     * @param foodLabel the type of food being asked
     * @return a number (0 if the pet does not eat that food)
     */
    int eats(String foodLabel);
}

