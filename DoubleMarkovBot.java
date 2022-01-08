/** A Markov decision Rock-Paper-Scissors player.
  *
  * Creates two Markov Chains to keep track of the probabilities
  * of the opponent's next move. First chain keeps track of how
  * the opponent's last move affects their next move and the second
  * Markov Chain keeps track of how the bot's last move affects the
  * opponent's next move.
  *
  * @author Nade Bai and Owen Corkery
  */


public class DoubleMarkovBot implements RoShamBot {

    private int[][] markovChain1;
    //keeps track of how opponent's last move affects the opponent's next move
    private int[][] markovChain2; 
    //keeps track of how bot's last move affects the opponent's next move
    private int round = 0;
    //keeps track of opponent's last move
    private Action last1 = null;
    //keeps track of user's last move
    private Action last2 = null;
    //temp variable for storing user's last move
    private Action templast = null;

    public void init() {
        round = 0;
        int length = 5;
        markovChain1 = new int[length][length];
        markovChain2 = new int[length][length];
        //initializes both chains to be filled with 0s
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                markovChain1[i][j] = 0;
                markovChain2[i][j] = 0;
            }
        }
        //sets both last moves to Rock
        last1 = Action.ROCK;
        last2 = Action.ROCK;
    }

    //adds each time the opponent makes a move after their previous move
    public void updateMarkovChain1(Action prev, Action next){
        markovChain1[prev.ordinal()][next.ordinal()] += 1;
    }

    //adds each time the opponent makes a move after the bot's previous move
    public void updateMarkovChain2(Action opMove, Action userMove){
        markovChain2[opMove.ordinal()][userMove.ordinal()] += 1;
    }


    public Action getNextMove(Action lastOpponentMove){
        if (round < 1) {
            init();
            round++;
            updateMarkovChain1(last1, lastOpponentMove);
            templast = Action.ROCK;
            return Action.ROCK;
        }
        updateMarkovChain2(lastOpponentMove, last2);
        updateMarkovChain1(last1, lastOpponentMove);
        last2 = templast;
        round++;

        int nextIndex = 0;

        //goes through the Markov Chains and finds the possible next move with the highest probability by adding up index in both chains
        for (int i = 0; i < 5; i++) {
            int prevIndex1 = lastOpponentMove.ordinal();
            int prevIndex2 = last2.ordinal();
            

            if ((markovChain2[i][prevIndex2] + markovChain1[prevIndex1][i]) > (markovChain2[nextIndex][prevIndex2] + markovChain1[prevIndex1][nextIndex])) {
                nextIndex = i;
            }
        }
        last1 = lastOpponentMove;

        //returns the predicted move with the highest probability
        Action predictedNext = Action.values()[nextIndex];


        //goes through each possible predicted move and returns a move that would beat it
        if(predictedNext == Action.SCISSORS){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                templast = Action.ROCK;
                return Action.ROCK;
            }
            else{
                templast = Action.SPOCK;
                return Action.SPOCK;
            }
        }
        else if(predictedNext == Action.ROCK){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                templast = Action.PAPER;
                return Action.PAPER;
            }
            else{
                templast = Action.SPOCK;
                return Action.SPOCK;
            }
        }
        else if(predictedNext == Action.PAPER){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                templast = Action.SCISSORS;
                return Action.SCISSORS;
            }
            else{
                templast = Action.LIZARD;
                return Action.LIZARD;
            }
        }
        else if(predictedNext == Action.LIZARD){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                templast = Action.SCISSORS;
                return Action.SCISSORS;
            }
            else{
                templast = Action.ROCK;
                return Action.ROCK;
            }
        }
        else{
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                templast = Action.PAPER;
                return Action.PAPER;
            }
            else{
                templast = Action.LIZARD;
                return Action.LIZARD;
            }
        
        }
    }
}
