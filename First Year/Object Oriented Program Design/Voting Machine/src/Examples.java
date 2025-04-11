import org.junit.Test;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class Examples {
    ElectionData eData;

    public Examples(){
        eData = new ElectionData(new MostFirstVotesStrategy());
    }

    @Test
    public void testOneVote(){
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        assertEquals(Optional.of("gompei"), this.eData.calculateWinner());
    }

    @Test(expected=AlreadyNominatedException.class)
    public void testCandidateAlreadyNominated() throws AlreadyNominatedException{
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("gompei");

        fail("did not throw exception properly");
    }

    @Test
    public void mostAgreeNot(){
        try {
            eData.setStrategy(new MostAgreeableStrategy());
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("bristaco", "husky", "gompei");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("bristaco", "husky", "gompei");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("bristaco", "husky", "gompei");
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        assertEquals(Optional.of("husky"),this.eData.calculateWinner());
    }

    @Test(expected=CandidateNotNominatedException.class)
    public void submitVoteTest() throws AlreadyNominatedException, MoreThanOnceException, CandidateNotNominatedException {
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("husky");
        this.eData.nominateCandidate("bristaco");
        this.eData.submitVote("Brendon", "husky", "bristaco");
        fail("did not throw exception properly");
    }

    @Test(expected=MoreThanOnceException.class)
    public void submitVoteTestMore() throws AlreadyNominatedException, MoreThanOnceException, CandidateNotNominatedException {
        this.eData.nominateCandidate("gompei");
        this.eData.nominateCandidate("husky");
        this.eData.nominateCandidate("bristaco");
        this.eData.submitVote("gompei", "gompei", "bristaco");

        fail("did not throw exception properly");
    }

    @Test
    public void badGetter(){
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.getCandidates().clear();
        }
        catch(Exception e){
            fail(e.getMessage());
        }

        assertEquals(Optional.of("gompei"), eData.calculateWinner());

    }

    @Test
    public void exactly50(){
        try {
            this.eData.nominateCandidate("gompei");
            this.eData.nominateCandidate("husky");
            this.eData.nominateCandidate("bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("gompei", "husky", "bristaco");
            this.eData.submitVote("bristaco", "husky", "gompei");
            this.eData.submitVote("bristaco", "husky", "gompei");
        }
        catch(Exception e){
            fail(e.getMessage());
        }
        assertEquals(eData.calculateWinner(), Optional.empty());
    }

}

