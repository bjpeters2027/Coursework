import org.junit.Test;


import java.util.LinkedList;

public class ChinchillaZone implements Zoneable{


    public LinkedList<Chinchilla> chList;

    public int pellets;
    public int hay;


    public ChinchillaZone(LinkedList<Chinchilla> chList){
        this.chList = chList;
    }
    /**
     * Produces the number of pets in the zone
     *
     * @return the number of total pets in the zone
     */
    @Override
    public int petsInZone() {
        return chList.size();
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
        Chinchilla temp = new Chinchilla("", 0,0,new Coord2D(0,0), 0);
        if(!chList.isEmpty()){
            for(Chinchilla c : chList){
                if(c.getWeightInOz() > temp.getWeightInOz()){
                    temp = c;
                }
            }
            return temp;
        }
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
        for(Chinchilla c: chList){
            if(c.getName().equals(petName)){
                return (c.getAge() * 10);
            }
        }
        return -1;
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
        if(foodName.equals("pellets")){
            pellets += foodAmt;
        }
        else if(foodName.equals("hay")){
            hay += foodAmt;
        }
        return this;
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
        for(Chinchilla c : chList){
            pellets -= c.eats("pellets");
            hay -= c.eats("hay");
        }
        if(pellets < 0){
            pellets = 0;
        }
        if(hay < 0){
            hay = 0;
        }
        return this;
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
        for(Chinchilla c : chList){
            if(c.getName().equals(petName)){
                return c;
            }
        }
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
        return ("Chinchilla: " + pellets + " pellets, " + hay + " hay");
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
        Coord2D base = new Coord2D(x,y);
        int index = 0;
        if(chList.isEmpty()){
            return "No Pets Found";
        }
        for (int i = 0; i < chList.size(); i++) {
            if (closerPoint(base, chList.get(index).location, chList.get(i).location)) {
                index = i;
            }
        }

        return chList.get(index).getName();
    }

    public Boolean closerPoint(Coord2D base, Coord2D a, Coord2D b){
        int distanceAX = (base.x - a.x);
        int distanceAY = (base.y - a.y);
        int distanceBX = (base.x - b.x);
        int distanceBY = (base.y - b.y);
        double vectorA = Math.sqrt(((distanceAX*distanceAX)+(distanceAY*distanceAY)));
        double vectorB = Math.sqrt(((distanceBX*distanceBX)+(distanceBY*distanceBY)));
        return vectorB < vectorA;
    }

}
