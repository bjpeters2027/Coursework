public class testTest implements Petable, Zoneable{
    /**
     * Produces the name of this pet in the rescue
     *
     * @return the pet's name as a string
     */
    @Override
    public String getName() {
        return null;
    }



    /**
     * the weight of the pet in Oz
     *
     * @return the weight rounded to the nearest oz
     */
    @Override
    public int getWeightInOz() {
        return 0;
    }

    /**
     * get the age of the pet
     *
     * @return the age of the pet in years
     */
    @Override
    public int getAge() {
        return 0;
    }

    /**
     * how much of a particular food a pet will eat
     *
     * @param foodLabel the type of food being asked
     * @return a number (0 if the pet does not eat that food)
     */
    @Override
    public int eats(String foodLabel) {
        return 0;
    }

    /**
     * Produces the number of pets in the zone
     *
     * @return the number of total pets in the zone
     */
    @Override
    public int petsInZone() {
        return 0;
    }

    /**
     * finds and produces the fattest animal available
     *
     * @return the heaviest pet (the one with the greatest weight according to its
     * getWeightInOz() method)
     * @return null if there are no pets
     */
    @Override
    public Petable heaviestPet() {
        return null;
    }

    /**
     * Looks up a pet in the zone and computers its age relative to human years
     *
     * @param petName the name of the pet.
     * @return the age of the pet converted into human years using the per-pet
     * calculation
     * or -1 if that pet name is not in this zone
     */
    @Override
    public int inHumanYears(String petName) {
        return 0;
    }

    /**
     * @param foodName The name of a food item expected to be stocked in this
     *                 zone's pantry
     * @param foodAmt  A non-negative number (>= 0) representing how much of that
     *                 food is going into the pantry
     * @return `this` zone for the purpose of method chaining
     */
    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        return null;
    }

    /**
     * For each animal in the zone, brings them in to eat
     * Use their eats() method on each type of food in the pantry for them
     *
     * @return `this` zone for the purpose of method chaining
     * If a food item in the zone goes negative, set it to 0. (No need for
     * "unknown" logic).
     */
    @Override
    public Zoneable feedZone() {
        return null;
    }

    /**
     * Fetch me that pet!
     *
     * @param petName the pet to look up in the zone, you may assume the pet is in
     *                the zone
     * @return the pet with that name or null if pet is not in the zone
     */
    @Override
    public Petable getPet(String petName) {
        return null;
    }

    /**
     * Returns the stock of the pantry for the zone
     *
     * @return a string printing out the pantry in the format of "Animal: # food1,
     * # food2, ...etc"
     */
    @Override
    public String getPantryLabel() {
        return null;
    }

    /**
     * Gets the name of the pet that is closest to the given 3D coord
     *
     * @param x the 3D coord that only has the location information
     * @param y
     * @return the name of the pet closest to that coord
     * In the result of a tie, you may return any one
     * (we won't test ties)
     * if there are no pets, return null
     * assume the "z" coordinate we are using is 0 by default
     * (some pets may be up on perches)
     * return "No Pets Found" if there are no pets in the zone.
     */
    @Override
    public String closestPet(int x, int y) {
        return null;
    }
}
