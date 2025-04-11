import java.util.LinkedList;
/** The top-level class for the PetRescue */
public class SuperPetRescue {
    /** A field containing the zones in the pet rescue */
    LinkedList<Zoneable> zones;
    /**
     * The constructor for the pet rescue
     * @param zones the zones in the pet rescue
     */
    public SuperPetRescue(LinkedList<Zoneable> zones){
        this.zones = zones;
    }
    /**
     * Computes the total number of pets in all the zones
     * @return the sum of the # of pets in each zone
     */
    public int totalPets(){
        int sum = 0;
        for(Zoneable zone : this.zones){
            sum += zone.petsInZone();
        }
        return sum;
    }
    /**
     * The heaviest pet across all of the zones
     * @return the name of the pet with the biggest weight in all of the zones
     * null if there are no pets in any of the zones
     */
    public String getHeaviestPetsName() {
        Petable chonkiest = null;
        for(Zoneable zone : this.zones){
            Petable heaviestInZone = zone.heaviestPet();
            if(chonkiest == null ||
                    chonkiest.getWeightInOz() <
                            heaviestInZone.getWeightInOz()){
                chonkiest = heaviestInZone;
            }
        }
        return chonkiest == null ? null : chonkiest.getName();
    }
}
