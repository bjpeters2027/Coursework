/**
 * A class that presents an exception for when voting for candidates
 */
public class CandidateNotNominatedException extends Exception{

    private String person;

    /**
     * A constructor for an exception when a candidate has been voted for but is not yet nominated
     * @param name the (not yet nominated) candidate being voted for
     */
    public CandidateNotNominatedException(String name){
        super(name + " has not been nominated.");
        person = name;
    }

    /**
     * getter method for the (not yet nominated) candidate being voted for
     * @return the name of the (not yet nominated) candidate
     */
    public String getCandidate(){
        String name = person;
        return name;
    }
}
