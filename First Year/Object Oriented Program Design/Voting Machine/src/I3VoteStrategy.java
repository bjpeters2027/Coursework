import java.util.HashMap;
import java.util.Optional;

/**
 * An interface for a modular "vote for 3 people" strategy
 */
public interface I3VoteStrategy {

    /**
     * The method that determines the winner based on the current strategy
     * @param votes the HashMap of candidates and votes that the winner will be chosen from
     * @return an Optional containing the name of the winner , or an empty Optional if
     * the winner cannot clearly be determined form this strategy
     */
    Optional<String> calculateWinner(HashMap<String, Votes> votes);
}