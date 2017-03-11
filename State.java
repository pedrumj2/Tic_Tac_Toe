/**
 * Created by Pedrum on 1/28/2017.
 */

import sun.util.resources.ca.CalendarData_ca;

import java.util.*;
//It is assumed that the computer is always circle
public class State {
    public ArrayList<Tic> tics;
    public Tic.TYPE turn;
    private Tic.TYPE nTurn;

    private ArrayList<Action> actions;
    private double valueX;
    private double valueO;
    public boolean isEnd;
    private int N =0;

    //used to initialize start state
    public State(){
        tics =new ArrayList<Tic>();
        turn = Tic.TYPE.X;
        setValidActions();
    }

    //initializes state based on prev state and action
    public State(State __prev, Action __actTaken){

        tics =(ArrayList<Tic>)__prev.tics.clone();

        tics.add(__actTaken.tic);
        turn = getTurn(__actTaken.tic);
        if (turn == Tic.TYPE.X){
            nTurn = Tic.TYPE.O;
        }
        else{
            nTurn = Tic.TYPE.X;
        }
        setValidActions();

        if (Evaluate.IsWin(this, turn)){
            setValue(turn,  1);
            setValue(nTurn,  0);
            isEnd = true;
        }
        else if (Evaluate.IsLoss(this, turn)){
            setValue(turn,  0);
            setValue(nTurn,  1);
            isEnd = true;
        }
        else if (Evaluate.IsTie(this)){
            setValue(turn,  0.5);
            setValue(nTurn,  0.5);
            isEnd = true;
        }
        else{
            setValue(turn,  0.5);
            setValue(nTurn,  0.5);
            isEnd = false;
        }
    }

    public double value(Tic.TYPE __type){
        if (__type == Tic.TYPE.X){
            return valueX;
        }
        else{
            return valueO;
        }
    }

    public void setValue(Tic.TYPE __type, double __value){
        if (__type == Tic.TYPE.X){
             valueX = __value;
        }
        else{
            valueO = __value;
        }

    }



    public Action bestAction (Tic.TYPE __turn){
        double _max;
        Action _output;
        _output = null;
        _max =-1;
        for (int i =0; i < actions.size(); i ++){
            if (actions.get(i).end.value(__turn) > _max){
                _output = actions.get(i);
                _max = _output.end.value(__turn);
            }
        }
        return _output;
    }

    public boolean update(double __reward, double __alpha, Tic.TYPE __type){
        double _output;
        N++;
        //setValue(__type,  value(__type)    + __alpha*(__reward - value(__type)));
        _output =  1/(double)(N)*(__reward - value(__type));
        setValue(__type,  value(__type)    + 1/(double)(N)*(__reward - value(__type)));
        if (_output < 0.001){
            return true;
        }
        else{
            return false;
        }
    }

    public Action randAction (double __randNum){
        Action _output;
        int _chosen;
        _chosen = (int)(__randNum*actions.size());
        _output = actions.get(_chosen);
        return _output;
    }

    //determines the current types turn based on previous type
    private Tic.TYPE getTurn(Tic __prev){
        if (__prev.type == Tic.TYPE.X){
            return Tic.TYPE.O;
        }
        else{
            return Tic.TYPE.X;
        }
    }

    //based on the tics on board sets the acctions array which is a list of
    //valid actions to take form this state
    private void setValidActions(){
        actions = new ArrayList<Action>();
        for (int i  =0; i < 9; i ++){
            if (!isTaken(i)){
                addAction(i);
            }
        }
    }

    private void addAction(int __loc){
        Action _action;
        _action =new Action(turn, __loc, this);
        actions.add(_action);
    }
    public boolean isTaken(int __loc){
        if (ticAt(__loc) != null){
            return true;
        }
        else{
            return false;
        }
    }

    //returns the tic object based on __loc.
    //__loc: zero based index of tic location [0-8]
    public Tic ticAt(int __loc){
        for (int i =0 ; i <tics.size(); i++){
            if (tics.get(i).loc ==__loc){
                return tics.get(i);
            }
        }
        return null;
    }

    //note the input is a value between 1 and 9
    public Action getAction(int __loc){
        int _occupied;
        _occupied =0;
        __loc = __loc -1;
        for (int i = 0 ; i <__loc; i++){
            if (isTaken(i)){
                _occupied ++;
            }
        }

        return actions.get(__loc - _occupied);
    }






}
