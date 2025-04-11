/**
 * A class that present an exception for nominating a potential candidate
 */
public class AlreadyNominatedException extends Exception{

    /**
     * A constructor for the exception where a candidate is to be nominated
     * but already has been and is therefore already on the ballot
     * @param name the name of the candidate that has been, and is ti be, nominated
     */
    public AlreadyNominatedException(String name)
    {
        super(name + " is already nominated.");
    }
}
