import java.util.Scanner;

/**
 * Created by Pedrum on 1/29/2017.
 */


//This class is used to run the game between human and AI
public class Play {
    public static void Run(State __start) {
        State _currentState;
        Player _playerAI;
        boolean _flag;
        Tic.TYPE _humanType;
        _currentState = __start;
        _flag = true;
        if (isFirst()) {
            _humanType = Tic.TYPE.X;
            _playerAI = new Player(0, Tic.TYPE.O,  0);
        }
        else{
            _humanType = Tic.TYPE.O;
            _playerAI = new Player(0, Tic.TYPE.X,  0);
            _currentState = _playerAI.nextMove(_currentState, false);
        }

        while (_flag){
            Graphics.Print(_currentState);
            _currentState = getMove(_currentState);
            Graphics.Print(_currentState);
            if (!checkEnd(_currentState, _humanType)){
                _currentState = _playerAI.nextMove(_currentState, false);
                Graphics.Print(_currentState);
                _flag = !checkEnd(_currentState, _humanType);
            }
            else{
                _flag =false;
            }

        }
    }


    //checks if the game ends. The second input determines the human type, which based
    //on it the output displays win or loss
    private static boolean checkEnd(State __state, Tic.TYPE __playerType){
        if (Evaluate.IsWin(__state, __playerType)){
            System.out.println("You WIN !!!");
            return true;
        }
        else if  (Evaluate.IsLoss(__state, __playerType)){
            System.out.println("You Lose !!!");
            return true;
        }
        else if  (Evaluate.IsTie(__state)){
            System.out.println("There is a tie !!!");
            return true;
        }
        return false;
    }

    //gets the next state based on user input
    private static State getMove(State __start) {
        boolean _flag;
        int _move;
        _move = getMoveInt();
        _flag = __start.isTaken(_move-1);
        while(_flag){
            System.out.println("That location is taken. Choose another number");
            _move = getMoveInt();
            _flag = __start.isTaken(_move-1);
        }
        return __start.getAction(_move).end;
    }

    //gets the user input for the next move
    private static int getMoveInt(){
        Scanner scan = new Scanner(System.in);
        int _move;
        _move =-1;
        boolean _flag;
        _flag =true;
        while (_flag){
            System.out.println("Select move from 1-9. Where 1 is top-left and 9 is bottom right");
            try{
                _move = scan.nextInt();
                _flag =false;
            }
            catch (Exception ex){
                System.out.println("Invalid input");
                scan.nextLine();
            }
        }
        return _move;
    }

    //Determines if user wants to play first
    private static boolean isFirst() {
        String _input;
        Scanner scan = new Scanner(System.in);
        boolean first;
        System.out.println("The model is trained to play as both first and second player");
        System.out.println("Play as first player? (y/n)");

        _input = scan.next();
        if (_input.toUpperCase().equals("Y")) {
            return true;
        } else if (_input.toUpperCase().equals("N")) {
            return false;
        } else {
            System.out.println("Invalid input. Proceeding as first player");
            return true;
        }
    }
}

