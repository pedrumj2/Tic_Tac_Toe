/**
 * Created by Pedrum on 1/29/2017.
 */
import java.util.*;
public class Player {

    private State prevState;
    private State currState;
    private Random rand;
    private double epsilon;
    public boolean prevWasBest;
    public Tic.TYPE type;
    private double alpha;
    public int countConvereged;
    public Player( double __epsilon, Tic.TYPE __type, double __alpha){
        epsilon   = __epsilon;
        type = __type;
        alpha = __alpha;
        rand =new Random();
        countConvereged =0;
    }

    //make sure this is called for first move. If not then last state of previous run might
    //get updated.
    public State startMove(State __startState){
        currState = __startState;

        boolean _isBest;

        _isBest = isBest();

        if (_isBest){

            currState = __startState.bestAction(type).end;
        }
        else{
            currState = __startState.randAction(rand.nextDouble()).end;
        }

        prevWasBest = _isBest;
        return currState;
    }
    public State nextMove(State __opponent, boolean __opBest){
        prevState = currState;
        boolean _isBest;
        boolean _convereged;

        _isBest = isBest();

        if (_isBest){

            currState = __opponent.bestAction(type).end;
        }
        else{
            currState = __opponent.randAction(rand.nextDouble()).end;
        }
        if (prevWasBest && __opBest){
            _convereged =   prevState.update(currState.value(type),alpha, type);
            updateConverged(_convereged);
        }
        prevWasBest = _isBest;
        return currState;
    }

    //updates the number of simultanious times we have convereged
    private void updateConverged(boolean __converged){
        if (__converged){
            countConvereged ++;
        }
        else{
            countConvereged = 0;
        }
    }
    public void updateEnd(State __endState,boolean __opBest ){
        boolean _convereged;
        if (__opBest && prevWasBest){
            _convereged =currState.update(__endState.value(type),alpha, type);
            updateConverged(_convereged);
        }

    }
    private boolean isBest(){
        double _randNum;

        _randNum = rand.nextDouble();
        if (_randNum > epsilon) {
            return true;
        }
        else {
            return false;
        }
    }
}
