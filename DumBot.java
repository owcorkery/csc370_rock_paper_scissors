
/** A mimicry-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
public class DumBot implements RoShamBot {
    
    /** Returns the same action as the opponent's previous action.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public int[] opponentActions;

    public void setActions(){
        this.opponentActions = new int[5];
        for (int i = 0; i < 5; i++){
            this.opponentActions[i] = 0;
        }
    }

    public Action getNextMove(Action lastOpponentMove) {
        setActions();
        if(lastOpponentMove == Action.SCISSORS){
            this.opponentActions[2] += 1;
            double coinFlip = Math.random();
            if (coinFlip <= 0.){
                return Action.ROCK;
            }
            else{
                return Action.SPOCK;
            }
        }
        else if(lastOpponentMove == Action.ROCK){
            this.opponentActions[0] += 1;
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.PAPER;
            }
            else{
                return Action.SPOCK;
            }
        }
        else if(lastOpponentMove == Action.PAPER){
            this.opponentActions[1] += 1;
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.SCISSORS;
            }
            else{
                return Action.LIZARD;
            }
        }
        else if(lastOpponentMove == Action.LIZARD){
            this.opponentActions[3] += 1;
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.SCISSORS;
            }
            else{
                return Action.ROCK;
            }
        }
        else{
            this.opponentActions[4] += 1;
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                return Action.PAPER;
            }
            else{
                return Action.LIZARD;
            }
        
        }
    }
    
}