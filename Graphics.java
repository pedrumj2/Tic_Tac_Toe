/**
 * Created by Pedrum on 1/28/2017.
 */

//this is used to print the tic tac toe board
public class Graphics {
    public static void Print(State __state){
        Tic _tic;

        for (int i = 0; i < 3; i ++){
            _tic = __state.ticAt(i);
            printtic(_tic);

        }
        System.out.println("|");

        for (int i = 3; i < 6; i ++){
            _tic = __state.ticAt(i);
            printtic(_tic);

        }
        System.out.println("|");

        for (int i = 6; i < 9; i ++){
            _tic = __state.ticAt(i);
            printtic(_tic);

        }
        System.out.println("|");

        System.out.println("");
        System.out.println("");
    }


    private static void printtic(Tic __tic){
        if (__tic ==null){
            System.out.print("|   ");
        }
        else{
            if (__tic.type == Tic.TYPE.X){
                System.out.print("| X ");

            }
            else{
                System.out.print("| O ");
            }
        }

    }
}
