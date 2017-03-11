/**
 * Created by Pedrum on 1/28/2017.
 */
public class Action {

    public Tic tic;
    public State start;
    public State end;

    public Action(Tic.TYPE __type, int __loc, State __start){

        tic = new Tic(__type, __loc);
        start = __start;

        end = new State(__start, this);
    }





}
