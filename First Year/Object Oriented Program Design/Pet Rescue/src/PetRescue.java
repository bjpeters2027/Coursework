import java.util.LinkedList;

public class PetRescue implements PetRescueable {

    public LinkedList<Integer> bw;

    public LinkedList<Integer> dy;

    public String petOfTheMonth;

    public LinkedList<Coord> cc;

    public int p = 0;

    public int h = 0;

    public PetRescue(LinkedList<Integer> birdWeights,
                     LinkedList<Integer> dogYears,
                     String petOfTheMonth,
                     LinkedList<Coord> catCoords) {
        bw = birdWeights;
        dy = dogYears;
        this.petOfTheMonth = petOfTheMonth;
        cc = catCoords;
    }

    /**
     * Searches the birds in the pet rescue
     *
     * @return the size of the biggest bird in oz or 0 if there are no birds
     */
    @Override
    public int biggestBird() {
        int biggest = 0;
        for(int birb : bw){
            if(birb > biggest){
                biggest = birb;
            }
        }
        return biggest;
    }

    /**
     * Transforms the dog records in the pet rescue
     *
     * @return a list of ages in the same order as the dog records,
     * but each age is converted into human years by multiplying by 7
     */
    @Override
    public LinkedList<Integer> inHumanYears() {
        LinkedList<Integer> ans = new LinkedList<Integer>();
        for( int i = 0; i < dy.size(); i++){
            ans.add((dy.get(i) * 7));
        }
        return ans;
    }

    /**
     * Awards the pet of the month with a title and/or a credential for their accomplishments
     *
     * @param title      Can be a title like "Dr." or "Capt."
     *                   or the empty string "" if no title to be added
     * @param credential a degree or honor including but not limited to "MD", "PhD", or "Esq."
     *                   or the empty string "" if no credential to be added
     *                   effects: modifies the name on record of the form "TITLE name, CREDENTIAL"
     *                   multiple space separated titles can be added with the newest first
     *                   multiple space separated credentials can be added after the comma with the newest last.
     */
    @Override
    public void bestowHonor(String title, String credential)
    {
        if(credential.contains(",")){
        int problem = credential.indexOf(",");
        credential = credential.substring(0,problem) + credential.substring(problem +1);
        }



        if(!(petOfTheMonth.isEmpty()) && !(title.isEmpty()) && !(credential.isEmpty())) {
            if(petOfTheMonth.contains(",")){
                petOfTheMonth = (title + " " + petOfTheMonth + " " + credential);
            }
            else{
            petOfTheMonth = (title + " " + petOfTheMonth + ", " + credential);}
        }
        else if((title.isEmpty()) && !(credential.isEmpty())){
            if(petOfTheMonth.contains(",")){
                petOfTheMonth = (title + " " + petOfTheMonth + " " + credential);
            }
            else{
            petOfTheMonth = (petOfTheMonth + ", " + credential);}
        }
        else if(!(title.isEmpty()) && (credential.isEmpty())){
            petOfTheMonth = (title + " " + petOfTheMonth);
        }
    }

    /**
     * Adds positive or subtracts negative food from the pantry and then prints out a new label for the contents inside
     * assume the initial amount of pellets and hay should be 0
     *
     * @param pellets the amount of pellets to add/subtract
     * @param hay the amount of hay to add/subtract
     * @return a string in the format "Chinchilla: # pellets, # hay"
     * If the storage of pellets or hay goes negative, reset them to 0, and replace # with "unknown"
     */



    @Override
    public String feedChinchillas(int pellets, int hay)
    {
        p += pellets;
        h += hay;
        if((p >= 0) && (h >= 0)) {
            return "Chinchilla: " + p + " pellets, " + h + " hay";
        }
        if(!(p >= 0) && (h >= 0)) {
            p=0;
            return "Chinchilla: unknown pellets, " + h + " hay";
        }
        if((p >= 0) && !(h >= 0)) {
            h=0;
            return "Chinchilla: " + p + " pellets, unknown hay";
        }

        p=0;
        h=0;
        return "Chinchilla: unknown pellets, unknown hay";
    }

    /**
     * Finds the closest cat in the rescue to some coordinate
     *
     * @param x the coordinate to find the closest to
     * @param y
     * @return the name of the cat if found or "Conspiciously Catless" if there are no cats.
     */
    @Override
    public String closestTo(int x, int y) {
        Coord closest = new Coord("", 1000000000, 1000000000);
        Coord base = new Coord("", x, y);
        if(cc.isEmpty()){
            return "Conspiciously Catless";
        }
        for (Coord coord : cc) {
            if (closerPoint(base, closest, coord)) {
                closest = coord;
            }
        }
        return closest.name;
    }

    /**
    Checks which point is closest
    @param base the base point
     @param a the first checked point
     @param b the second checked point
     @return if b is closer to the base then a
     */
    public Boolean closerPoint(Coord base, Coord a, Coord b){
        int distanceAX = (base.x - a.x);
        int distanceAY = (base.y - a.y);
        int distanceBX = (base.x - b.x);
        int distanceBY = (base.y - b.y);
        double vectorA = Math.sqrt(((distanceAX*distanceAX)+(distanceAY*distanceAY)));
        double vectorB = Math.sqrt(((distanceBX*distanceBX)+(distanceBY*distanceBY)));
        if(vectorB < vectorA){
            return true;
        }
        return false;
    }
}

