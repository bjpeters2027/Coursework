import java.util.HashMap;
import java.util.Optional;

/**
 * A class to determine the winner based off who has the most votes in a single category
 */
public class MostAgreeableStrategy implements I3VoteStrategy{

    /**
     * The method that determines the winner based on the current strategy
     * @param votes the HashMap of candidates and votes that the winner will be chosen from
     * @return an Optional containing the name of the winner , or an empty Optional if
     * the winner cannot clearly be determined form this strategy
     */
    @Override
    public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
        int mostAggreances = 0;
        String mostName = "";
        boolean tie = false;
        for (String person : votes.keySet()){
            if(votes.get(person).getFirstVotes() == mostAggreances){
                tie = true;
            }
            if (votes.get(person).getFirstVotes() > mostAggreances){
                mostName = person;
                mostAggreances = votes.get(person).getFirstVotes();
                tie = false;
            }
            if(votes.get(person).getSecondVotes() == mostAggreances){
                tie = true;
            }
            if (votes.get(person).getSecondVotes() > mostAggreances){
                mostName = person;
                mostAggreances = votes.get(person).getSecondVotes();
                tie = false;
            }
            if(votes.get(person).getThirdVotes() == mostAggreances){
                tie = true;
            }
            if (votes.get(person).getThirdVotes() > mostAggreances){
                mostName = person;
                mostAggreances = votes.get(person).getThirdVotes();
                tie = false;
            }

        }
        if (tie){
            return Optional.empty();
        }
        return Optional.of(mostName);
    }
}
