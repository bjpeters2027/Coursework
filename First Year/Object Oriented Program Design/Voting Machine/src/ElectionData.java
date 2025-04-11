
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.*;

/**
 * A class that holds most methods and all data for an election
 */
public class ElectionData {

    private HashMap<String, Votes> votes = new HashMap<>();
    private I3VoteStrategy strat;


    /**
     * A constructor for an ElectionData object that takes in a strategy
     * @param strategy the strategy that is stored and will determine the winner
     */
    public ElectionData(I3VoteStrategy strategy){
        this.strat = strategy;
    }

    /**
     * Setter method for the strategy
     * @param strategy the new strategy to be used
     */
    public void setStrategy(I3VoteStrategy strategy){
        this.strat = strategy;
    }

    /**
     * Getter method for the keys of the HashMap which contains all the candidates
     * @return a Set of Strings representing all the candidates
     */
    public Set<String> getCandidates(){
        Set<String> listy = new HashSet<>();

        listy.addAll(votes.keySet());

        return listy;
    }

    /**
     * Nominates a new candidate by adding a new key into the HashMap
     * @param person the candidate to be nominated
     * @throws AlreadyNominatedException if this candidate is already nominated
     */
    public void nominateCandidate(String person) throws AlreadyNominatedException{
        if (!votes.isEmpty()) {
            if (votes.containsKey(person)) {
                throw new AlreadyNominatedException(person);
            }
        }
        votes.put(person, new Votes(0,0,0));
    }

    /**
     * Submits a vote by ranking 3 candidates
     * @param first the first ranked candidate
     * @param second the second ranked candidate
     * @param third the third ranked candidate
     * @throws CandidateNotNominatedException if a candidate that has been ranked has yet to be nominated
     * @throws MoreThanOnceException if one candidate appears more than once on the ranking
     */
    public void submitVote(String first, String second, String third) throws CandidateNotNominatedException, MoreThanOnceException{
        if((first.equals(second)) || (first.equals(third))){
            throw new MoreThanOnceException(first);
        }
        if ((second.equals(third))) {
            throw new MoreThanOnceException(second);
        }
        if (!(votes).containsKey(first)){
            throw new CandidateNotNominatedException(first);
        }
        if (!(votes).containsKey(second)){
            throw new CandidateNotNominatedException(second);
        }
        if (!(votes).containsKey(third)){
            throw new CandidateNotNominatedException(third);
        }
        votes.get(first).voteFirst();
        votes.get(second).voteSecond();
        votes.get(third).voteThird();
    }

    /**
     * Calculates the winner using the current strategy
     * @return an Optional containing the name of the winner or an empty Optional if there is no clear winner
     */
    public Optional<String> calculateWinner(){
        HashMap<String, Votes> hashy = new HashMap<String, Votes>();
        hashy.putAll(votes);

       return this.strat.calculateWinner(hashy);
    }
}
