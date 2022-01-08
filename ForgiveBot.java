
/** A mimicry-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
public class ForgiveBot implements RoShamBot {
    
    /** Uses the Forgiving Tit-For-Tat strategy from the Iterated Prisoner's
      * Dilemma Problem
      * 
      * Returns the opponent's last move as long as they continue to cooperate
      * and return this Bot's last move. If the opponent defects (changes from
      * the tit-for-tat strategy and returns a different move) twice in a row,
      * the bot defects as well and returns whatever move that would beat their
      * last move.
      *
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */

    // List that keeps count of how many of each move the opponent played
    public int[] opponentActionsCount;
    // List that keeps count of how many of each move the bot played
    public int[] actionsCount;
    // Keeps track of last move the bot played
    public Action[] actions;
    // Keeps track of how many times in a row the opponenet defects
    public int count;

    public void setActions(){
        this.actions = new Action[1];
        this.actionsCount = new int[5];
        this.opponentActionsCount = new int[5];
        for (int i = 0; i < 5; i++){
            this.opponentActionsCount[i] = 0;
            this.actionsCount[i] = 0;
        }
        this.actions[0] = Action.ROCK;
        this.count = 0;
    }

    public Action getNextMove(Action lastOpponentMove) {
        setActions();
        if (this.actions[0] != lastOpponentMove){
            this.count +=1;
            if (this.count >= 2){
                if(lastOpponentMove == Action.SCISSORS){
                    this.opponentActionsCount[2] += 1;
                    double coinFlip = Math.random();
                    if (coinFlip <= 0.){
                        return Action.ROCK;
                    }
                    else{
                       return Action.SPOCK;
                    }
                }
                else if(lastOpponentMove == Action.ROCK){
                    this.opponentActionsCount[0] += 1;
                    double coinFlip = Math.random();
                    if (coinFlip <= 0.5){
                        return Action.PAPER;
                    }
                    else{
                        return Action.SPOCK;
                    }
                }
                else if(lastOpponentMove == Action.PAPER){
                    this.opponentActionsCount[1] += 1;
                    double coinFlip = Math.random();
                    if (coinFlip <= 0.5){
                        return Action.SCISSORS;
                    }
                    else{
                        return Action.LIZARD;
                    }
                }
                else if(lastOpponentMove == Action.LIZARD){
                    this.opponentActionsCount[3] += 1;
                    double coinFlip = Math.random();
                    if (coinFlip <= 0.5){
                        return Action.SCISSORS;
                    }
                    else{
                        return Action.ROCK;
                    }
                }
                else{
                    this.opponentActionsCount[4] += 1;
                    double coinFlip = Math.random();
                    if (coinFlip <= 0.5){
                        return Action.PAPER;
                    }
                    else{
                        return Action.LIZARD;
                    }
        
                }
            }
            else{
                return lastOpponentMove;
            }
        
        }
        else{
            this.count = 0;
            return lastOpponentMove;
        }
    
    }
}