import java.util.Scanner;
public class VotingMachine {

    private static ElectionData eData = new ElectionData(new MostFirstVotesStrategy());

    private static Scanner input = new Scanner(System.in);

    public VotingMachine(){

    }

    public static void main(String[] args) {

        System.out.println(eData.getCandidates());
        String answer = "";
        while(!answer.toLowerCase().contains("q")){
            System.out.println("Do you want to [n]ominate someone, [v]ote for someone, change winner [s]trategy, see who [w]on, or [q]uit?");
             answer = input.nextLine();
            if(answer.toLowerCase().startsWith("n")){
                System.out.println("Who would you like to nominate?");
                String person = input.nextLine();
                try{
                    eData.nominateCandidate(person);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if (answer.toLowerCase().startsWith("v")) {
                System.out.println("Please list your first choice:");
                String firsted = input.nextLine();
                System.out.println("Please list your second choice:");
                String seconded = input.nextLine();
                System.out.println("Please list your third choice:");
                String thirded = input.nextLine();
                try{
                    eData.submitVote(firsted, seconded, thirded);
                }
                catch(Exception e){
                    if(e.getMessage().contains("has not been nominated")){
                        CandidateNotNominatedException newE = (CandidateNotNominatedException) e;
                        System.out.println(e.getMessage());
                        System.out.println("Would you like to nominate the candidate? [y]es/[n]o");
                        String toNominate = input.nextLine();
                        if (toNominate.toLowerCase().startsWith("y")){
                            try {
                                eData.nominateCandidate(newE.getCandidate());
                            } catch (AlreadyNominatedException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    else if(e.getMessage().contains("was voted for more than once")){
                        System.out.println(e.getMessage());
                    }
                }
            }
            else if (answer.toLowerCase().startsWith("s")) {
                System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?");
                String newStratType = input.nextLine();
                if(newStratType.toLowerCase().startsWith("f")){
                    eData.setStrategy(new MostFirstVotesStrategy());
                }
                else if(newStratType.toLowerCase().startsWith("a")){
                    eData.setStrategy(new MostAgreeableStrategy());
                }
                else{
                    System.out.println("You have input an invalid response, restarting...");
                }
            }
            else if (answer.toLowerCase().startsWith("w")) {
                if(eData.calculateWinner().isPresent()){
                    System.out.println(eData.calculateWinner().get());
                }
                else{
                    System.out.println("No clear winner under the current strategy.");
                }

            }


        }
    }

}
