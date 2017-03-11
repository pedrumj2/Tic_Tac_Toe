import java.util.Scanner;


public class Main {
    private static double  epsilon;
    private static double  alpha;
    private static double tieReward;
    public static void main(String[] args) {
        boolean _flag;
        Scanner scan = new Scanner(System.in);
        String _input;
        State _start;
        getInput();
        _start =trainModel();
        _flag =true;
        while (_flag){
            Play.Run(_start);
            System.out.println("Play again? (y/n)");
            _input = scan.next();
            if (_input.toUpperCase().equals("N")) {
                _flag = false;
            }
        }
    }

    private static State trainModel(){
        State _start;
        Train _train;

        System.out.println("The model will be trained by playing against itself until it converges");
        System.out.println("Please wait ");

        _start = new State();
        _train =new Train(epsilon, alpha);
        _start = _train.run( _start, false);
        return _start;


    }
    private static void getInput(){
        Scanner scan = new Scanner(System.in);
        try{
            System.out.println("Choose epsilon (suggested 0.1): ");
            epsilon = scan.nextDouble();
        }
        catch (Exception ex){
            System.out.println("Invalid input, epsilon should be numeric value between [0, 1]");
            System.out.println("Proceeding with default value of epsilon = 0.1");
            epsilon = 0.1;
        }

         scan = new Scanner(System.in);
        try{
            System.out.println("Choose reward value for a tie. 0 would make it equivilant");
            System.out.println("to a loss and 1 would make it equivilant to a win  (suggested 0.5): ");
            tieReward = scan.nextDouble();
        }
        catch (Exception ex){
            System.out.println("Invalid input, reward should be numeric value between [0, 1]");
            System.out.println("Proceeding with default value of reward = 0.5");
            tieReward = 0.5;
        }

    }
}
