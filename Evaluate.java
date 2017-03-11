/**
 * Created by Pedrum on 1/28/2017.
 */

import java.util.*;

//evalutes the state and determines if it is a winning or losing state
public  class Evaluate {

    public static boolean IsWin(State __state, Tic.TYPE __type){
        int[][] _table;

        _table = convert2Table(__state, __type);
        return isWin(_table);
    }

    public static boolean IsLoss(State __state, Tic.TYPE __type){
        int[][] _table;
        Tic.TYPE _type;
        if (__type == Tic.TYPE.X) {
            _type= Tic.TYPE.O;
        }
        else{
            _type= Tic.TYPE.X;
        }
        _table = convert2Table(__state, _type);
        return isWin(_table);
    }

    public static boolean IsTie(State __state){


        if (__state.tics.size() ==9) {
            return true;
        }
        else{
            return false;
        }

    }

    private static boolean isWin(int[][] __table){
        for (int i = 0 ;i < 3; i++){
            if (isColumn(i, __table)){
                return true;
            }
            if (isRow(i, __table)){
                return true;
            }
        }
        if (isDiagonal(__table)){
            return true;
        }
        return false;
    }
    private static boolean isColumn(int __i, int[][] __table){
        if (__table[0][__i] == 1 &&
                __table[1][__i] == 1 &&
                __table[2][__i] == 1){
            return true;
        }
        else{
            return false;
        }
    }


    private static boolean isRow(int __i, int[][] __table){
        if (__table[__i][0] == 1 &&
                __table[__i][1] == 1 &&
                __table[__i][2] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    private static boolean isDiagonal( int[][] __table){
        if (__table[0][0] == 1 &&
                __table[1][1] == 1 &&
                __table[2][2] == 1){
            return true;
        }
        else if  (__table[2][0] == 1 &&
                        __table[1][1] == 1 &&
                        __table[0][2] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    private static int[][] convert2Table(State __state, Tic.TYPE __type){
        int[][] _output;
        _output = new int[3][3];

        for (int i = 0; i < 9; i ++){
            if (__state.ticAt(i) == null){
                _output[(int)i/3][i % 3] = 0;
            }
            else if(__state.ticAt(i).type == __type){
                _output[(int)i/3][i % 3] = 1;
            }
            else{
                _output[(int)i/3][i % 3] = 0;
            }

        }
        return _output;
    }
}
