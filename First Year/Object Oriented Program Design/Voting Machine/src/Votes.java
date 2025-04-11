/**
 * The class Votes is a class that holds the amount of each type of vote for a candidate
 */
public class Votes {
    private int firstVotes;
    private int secondVotes;
    private int thirdVotes;

    /**
     * Constructor for a Votes object
     * @param first The amount of first votes
     * @param second The amount of second votes
     * @param third The amount of third votes
     */
    public Votes(int first, int second, int third){
        this.firstVotes = first;
        this.secondVotes = second;
        this.thirdVotes = third;
    }

    public Votes(Votes v){
        this.firstVotes = v.firstVotes;
        this.secondVotes = v.secondVotes;
        this.thirdVotes = v.thirdVotes;
    }


    /**
     * A getter method for the amount of first votes
     * @return the amount of first votes
     */
    public int getFirstVotes(){
        return this.firstVotes;
    }

    /**
     * A getter method for the amount of second votes
     * @return the amount of second votes
     */
    public int getSecondVotes(){
        return this.secondVotes;
    }

    /**
     * A getter method for the amount of third votes
     * @return the amount of third votes
     */
    public int getThirdVotes(){
        return this.thirdVotes;
    }

    /**
     * Adds a first vote when necessary for the candidate
     */
    public void voteFirst(){
        this.firstVotes++;
    }

    /**
     * Adds a second vote when necessary for the candidate
     */
    public void voteSecond(){
        this.secondVotes++;
    }

    /**
     * Adds a third vote when necessary for the candidate
     */
    public void voteThird(){
        this.thirdVotes++;
    }
}
