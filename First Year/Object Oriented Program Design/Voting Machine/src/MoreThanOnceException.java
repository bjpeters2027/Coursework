/**
 * A class that presents an exception for when voting for candidates
 */
public class MoreThanOnceException extends Exception{

    /**
     * A constructor for an exception when a candidate appears more than once on an incoming vote
     * @param person the candidate voted for more than once
     */
    public MoreThanOnceException(String person){
        super(person + " was voted for more than once");
    }
}
