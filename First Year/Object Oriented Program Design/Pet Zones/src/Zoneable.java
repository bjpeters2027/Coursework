/** The interface for a zone at the pet rescue and what it needs to be able to
 compute */
public interface Zoneable {
    /**
     * Produces the number of pets in the zone
     *
     * @return the number of total pets in the zone
     */
    int petsInZone();

    /**
     * finds and produces the fattest animal available
     *
     * @return the heaviest pet (the one with the greatest weight according to its
     * getWeightInOz() method)
     * @return null if there are no pets
     */
    Petable heaviestPet();

    /**
     * Looks up a pet in the zone and computers its age relative to human years
     *
     * @param petName the name of the pet.
     * @return the age of the pet converted into human years using the per-pet
     * calculation
     * or -1 if that pet name is not in this zone
     */
    int inHumanYears(String petName);

    /**
     * @param foodName The name of a food item expected to be stocked in this
     *                 zone's pantry
     * @param foodAmt  A non-negative number (>= 0) representing how much of that
     *                 food is going into the pantry
     * @return `this` zone for the purpose of method chaining
     */
    Zoneable restockPetFood(String foodName, int foodAmt);

    /**
     * For each animal in the zone, brings them in to eat
     * Use their eats() method on each type of food in the pantry for them
     *
     * @return `this` zone for the purpose of method chaining
     * If a food item in the zone goes negative, set it to 0. (No need for
     * "unknown" logic).
     */
    Zoneable feedZone();

    /**
     * Fetch me that pet!
     *
     * @param petName the pet to look up in the zone, you may assume the pet is in
     *                the zone
     * @return the pet with that name or null if pet is not in the zone
     */
    Petable getPet(String petName);

    /**
     * Returns the stock of the pantry for the zone
     *
     * @return a string printing out the pantry in the format of "Animal: # food1,
     * # food2, ...etc"
     */
    String getPantryLabel();

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
    String closestPet(int x, int y);
}
