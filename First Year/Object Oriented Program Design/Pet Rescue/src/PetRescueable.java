import java.util.LinkedList;

/** The interface for the pet rescue displays to use */
public interface PetRescueable {

    /**
     * Searches the birds in the pet rescue
     * @return the size of the biggest bird in oz or 0 if there are no birds
     */
    int biggestBird();

    /**
     * Transforms the dog records in the pet rescue
     * @return a list of ages in the same order as the dog records, 
     *         but each age is converted into human years by multiplying by 7
     */
    LinkedList<Integer> inHumanYears();

    /**
     * Awards the pet of the month with a title and/or a credential for their accomplishments
     * @param title Can be a title like "Dr." or "Capt."
     *              or the empty string "" if no title to be added
     * @param credential a degree or honor including but not limited to "MD", "PhD", or "Esq."
     *                   or the empty string "" if no credential to be added
     * effects: modifies the name on record of the form "TITLE name, CREDENTIAL"
     * multiple space separated titles can be added with the newest first
     * multiple space separated credentials can be added after the comma with the newest last.
     */
    void bestowHonor(String title, String credential);

    /**
     * Adds positive or subtracts negative food from the pantry and then prints out a new label for the contents inside
     * assume the initial amount of pellets and hay should be 0
     * @param pellets the amount of pellets to add/subtract
     * @param hay the amount of hay to add/subtract
     * @return a string in the format "Chinchilla: # pellets, # hay"
     * If the storage of pellets or hay goes negative, reset them to 0, and replace # with "unknown"
     */
    String feedChinchillas(int pellets, int hay);


    /**
     * Finds the closest cat in the rescue to some coordinate
     *
     * @param x the coordinate to find the closest to
     * @param y
     * @return the name of the cat if found or "Conspiciously Catless" if there are no cats.
     */
    String closestTo(int x, int y);

}