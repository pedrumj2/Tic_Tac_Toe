/**
 * Created by Pedrum on 1/28/2017.
 */
import java.util.*;
public class Train {


    private Player player1;
    private Player player2;
    private int converegeTarget;

    public Train(double __epsilon, double __alpha){


        player1 = new Player(__epsilon, Tic.TYPE.X, __alpha);
        player2 = new Player(__epsilon, Tic.TYPE.O, __alpha);
        converegeTarget = 1000;

    }



    public State run(State __state, boolean __print){
        int i;
        i =0;
        while (!run(__print, __state)){
            i++;
        }

        System.out.println("Model convereged in " + i + " runs.");
        return __state;
    }


    //returns true if convereged
    private boolean run(boolean __print , State __state){
        State _curState;
        Player _curPlayer;
        _curState = player1.startMove(__state);
        _curPlayer = player1;
        _curPlayer = nextPlayer(_curPlayer);
        _curState = _curPlayer.startMove(_curState);
        while (!_curState.isEnd){
            _curPlayer = nextPlayer(_curPlayer);
            _curState = _curPlayer.nextMove(_curState, nextPlayer(_curPlayer).prevWasBest);
            if (__print){
                Graphics.Print(_curState);
            }
        }
        _curPlayer = nextPlayer(_curPlayer);
        _curPlayer.updateEnd(_curState, nextPlayer(_curPlayer).prevWasBest);
       // System.out.println(_curPlayer.countConvereged);
        if (_curPlayer.countConvereged > converegeTarget &&
                nextPlayer(_curPlayer).countConvereged > converegeTarget){

            return true;
        }
        else{
            return false;
        }

    }
    private Player nextPlayer(Player __player){
        if (__player.type == Tic.TYPE.X){
            return player2;
        }
        else{
            return player1;
        }
    }




}
