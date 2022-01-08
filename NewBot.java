
/** A mimicry-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
public class NewBot implements RoShamBot {
    
    /** Returns the same action as the opponent's previous action.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */

    public Action getNextMove(Action lastOpponentMove, Action lastMove, boolean didWin) {
        if(didWin == true){
            double coinFlip = Math.random();
            if (coinFlip <= 0.5){
                    return lastMove;
                }
                else{
                    return lastOpponentMove;
                }

        }
        else{
            if(lastOpponentMove == Action.SCISSORS){
                double coinFlip = Math.random();
                if (coinFlip <= 0.5){
                    return Action.ROCK;
                }
                else{
                    return Action.SPOCK;
                }
            }
            else if(lastOpponentMove == Action.ROCK){
                double coinFlip = Math.random();
                if (coinFlip <= 0.5){
                    return Action.PAPER;
                }
                else{
                    return Action.SPOCK;
                }
            }
            else if(lastOpponentMove == Action.PAPER){
                double coinFlip = Math.random();
                if (coinFlip <= 0.5){
                    return Action.SCISSORS;
                }
                else{
                    return Action.LIZARD;
                }
            }
            else if(lastOpponentMove == Action.LIZARD){
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
        }
    }
    
}