public class ReactionBot implements RoShamBot {

     // math.random?
    // stats for the game (you win / tie / computer win)
    private int[] stats = new int[] {0, 0, 0};
    // we use a Markov Chain for the AI of our computer
    private int[][] markovChain; // used just for the prev to current throws prediction
    private int nbThrows = 0;
    private Action last = null;
    private Action templast = null;

    public void init() {
        nbThrows = 0;
        int length = 5;
        markovChain = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                markovChain[i][j] = 0;
            }
        }
        last = Action.ROCK;
    }

    public void updateMarkovChain(Action opMove, Action userMove){
        markovChain[opMove.ordinal()][userMove.ordinal()] += 1;
    }

    public Action getNextMove(Action lastOpponentMove){
        if (nbThrows < 1) {
            init();
            nbThrows++;
            templast = Action.ROCK;
            return Action.ROCK;
        }
        updateMarkovChain(lastOpponentMove, last);
        last = templast;
        nbThrows++;

        int nextIndex = 0;

        for (int i = 0; i < 5; i++) {
            int prevIndex = last.ordinal();

            if (markovChain[i][prevIndex] > markovChain[nextIndex][prevIndex]) {
                nextIndex = i;
            }
        }
        

        Action predictedNext = Action.values()[nextIndex];

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