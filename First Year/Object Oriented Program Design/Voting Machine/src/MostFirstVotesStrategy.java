import java.util.HashMap;
import java.util.Optional;

/**
 * A strategy to determine the winner of an election by who has the most first votes.
 */
public class MostFirstVotesStrategy  implements I3VoteStrategy{

    /**
     * The method that determines the winner based on the current strategy
     * @param votes the HashMap of candidates and votes that the winner will be chosen from
     * @return an Optional containing the name of the winner , or an empty Optional if
     * the winner cannot clearly be determined form this strategy
     */
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        int mostFirstVotes = 0;
        String mostName = "";
        boolean tie = false;
        for (String person : votes.keySet()){
            if(votes.get(person).getFirstVotes() == mostFirstVotes){
                tie = true;
            }
            if (votes.get(person).getFirstVotes() > mostFirstVotes){
                mostName = person;
                mostFirstVotes = votes.get(person).getFirstVotes();
                tie = false;
            }

        }
        if (tie){
            return Optional.empty();
        }
        return Optional.of(mostName);
    }
}
