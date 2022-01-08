/** A Markov decision Rock-Paper-Scissors player.
  *
  * @author Nade Bai
  */

public class MarkovBot implements RoShamBot {

  // math.random?
  // stats for the game (you win / tie / computer win)
  // we use a Markov Chain for the AI of our computer
  private int[][] markovChain; // used just for the prev to current throws prediction
  private int nbThrows = 0;
  private Action last = null;

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

  public void updateMarkovChain(Action prev, Action next) {
    markovChain[prev.ordinal()][next.ordinal()] += 1;
  }

  public Action getNextMove(Action lastOpponentMove) {
    if (nbThrows < 1) {
      // first move => we can't use Markov Chain prediction
      // so we use a random on the Item list
      init();
      updateMarkovChain(last, lastOpponentMove);
      nbThrows++;
      return Action.ROCK;
    }
    updateMarkovChain(last, lastOpponentMove);
    nbThrows++;
    // we try to predict next Item choosen by the user by reading data in our Markov Chain
    // for the prev entry in the array
    int nextIndex = 0;

    for (int i = 0; i < 5; i++) {
      int prevIndex = lastOpponentMove.ordinal();

      if (markovChain[prevIndex][i] > markovChain[prevIndex][nextIndex]) {
        nextIndex = i;
      }
    }
    last = lastOpponentMove;

    // Item probably played by the user is in nextIndex
    Action predictedNext = Action.values()[nextIndex];

    if(predictedNext == Action.SCISSORS){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.ROCK;
            }
            else{
                return Action.SPOCK;
            }
        }
    else if(predictedNext == Action.ROCK){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.PAPER;
            }
            else{
                return Action.SPOCK;
            }
        }
    else if(predictedNext == Action.PAPER){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.SCISSORS;
            }
            else{
                return Action.LIZARD;
            }
        }
    else if(predictedNext == Action.LIZARD){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.SCISSORS;
            }
            else{
                return Action.ROCK;
            }
        }
    else{
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.PAPER;
            }
            else{
                return Action.LIZARD;
            }
        
        }

    // we choose amongst item for which this probably item loses
  }


}
