import java.util.LinkedList;

public class BirdZone implements Zoneable{
    public LinkedList<Bird> birds;

    public int seedPantry = 0;

    public BirdZone(LinkedList<Bird> birds){
        this.birds = birds;
    }


    /**
     * Produces the number of pets in the zone
     *
     * @return the number of total pets in the zone
     */
    @Override
    public int petsInZone() {
        return birds.size();
    } //done

    /**
     * finds and produces the fattest animal available
     *
     * @return the heaviest pet (the one with the greatest weight according to its
     * getWeightInOz() method)
     * @return null if there are no pets
     */
    @Override
    public Petable heaviestPet() {
        Coord2D tCoord = new Coord2D(0,0);
        Bird temp = new Bird("",0,0, tCoord, false);

        if(!(birds.isEmpty())) {
            for (Bird b : birds) {
                if (b.weightInOz > temp.weightInOz) {
                    temp = b;
                }
            }
            return temp;
        }
        return null;
    } //done

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
        Coord2D tCoord = new Coord2D(0,0);
        Bird temp = new Bird("",0,0, tCoord, false);

        for(Bird b : birds){
            if(petName.equals(b.getName())){
                temp = b;
            }
        }
        int ans =(temp.getAge() * 9);
        if (ans == 0){
            return -1;
        }
        return ans;
    } //done

    /**
     * @param foodName The name of a food item expected to be stocked in this
     *                 zone's pantry
     * @param foodAmt  A non-negative number (>= 0) representing how much of that
     *                 food is going into the pantry
     * @return `this` zone for the purpose of method chaining
     */
    @Override
    public Zoneable restockPetFood(String foodName, int foodAmt) {
        if(foodName.equals("seeds")){
            seedPantry +=foodAmt;
        }

        return this;
    } //done

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
    int eaten = 0;
        for (Bird b : birds) {
            eaten += b.eats("seeds");
        }
        seedPantry -= eaten;
        if(seedPantry < 0){
            seedPantry = 0;
        }
        return this;
    } // done

    /**
     * Fetch me that pet!
     *
     * @param petName the pet to look up in the zone, you may assume the pet is in
     *                the zone
     * @return the pet with that name or null if pet is not in the zone
     */
    @Override
    public Petable getPet(String petName) {
        for( Bird b : birds){
            if(b.getName().equals(petName)){
                return b;
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
        return ("Bird: " + seedPantry + " seeds");
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
        //Bird closest = new Bird("h", 0, 0, new Coord2D(1000000,1000000), true);
        int index = 0;
        if(birds.isEmpty()){
            return "No Pets Found";
        }
        for (int i = 0; i < birds.size(); i++) {
            if (closerPoint3D(base, birds.get(index).location, birds.get(i).location, birds.get(i).wingsClipped)) {
                index = i;
            }
        }

        return birds.get(index).getName();

    }

    /**
     Checks which point is closest
     @param base the base point
     @param a the first checked point
     @param b the second checked point
     @return if b is closer to the base then a
     */
    public Boolean closerPoint3D(Coord2D base, Coord2D a, Coord2D b, boolean clipped){

        /*int distanceAX = (base.x - a.x);
        int distanceAY = (base.y - a.y);
        int distanceBX = (base.x - b.x);
        int distanceBY = (base.y - b.y);*/
        if(clipped){
            double vectorA = Math.sqrt((((base.x - a.x) * (base.x - a.x))+((base.y - a.y) * (base.y - a.y))));
            double vectorB = Math.sqrt((((base.x - b.x) * (base.x - b.x))+((base.y - b.y) * (base.y - b.y))));
            if(vectorB < vectorA){
                return true;
            }
            else{
                return false;
            }
        }

       /* double a3D = Math.sqrt((a.x * a.x) + (a.y * a.y) + 4);
        double b3D = Math.sqrt((b.x * b.x) + (b.y * b.y) + 4);
        double base3D = Math.sqrt((base.x *base.x) + (base.y * base.y));
        double diffA = base3D - a3D;
        double diffB = base3D - b3D;*/
        double diffA = Math.sqrt(((base.x-a.x)*(base.x-a.x)) + ((base.y-a.y)*(base.y-a.y)) + 4);
        double diffB = Math.sqrt(((base.x-b.x)*(base.x-b.x)) + ((base.y-b.y)*(base.y-b.y)) + 4);
        return diffB < diffA;

    }
}
