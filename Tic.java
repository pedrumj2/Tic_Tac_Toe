/**
 * Created by Pedrum on 1/28/2017.
 */
public class Tic {
    public enum TYPE{
        X,
        O
    }
    public TYPE type;
    public int loc;
    public Tic(TYPE __type, int __loc){
        loc = __loc;
        type = __type;
    }



}
