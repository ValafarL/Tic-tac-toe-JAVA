import java.util.Scanner;

public class Main {
    static char  [][] BOARD = {
            {' ','|',' ','|',' '},
            {'-','-','-','-','-'},
            {' ','|',' ','|',' '},
            {'-','-','-','-','-'},
            {' ','|',' ','|',' '}};
    final static byte BOARD_LINES = 5;// (space=3 + separators - = 2)=5
    final static byte BOARD_COLUMNS = 5;// (space=3 + separators | = 2)=5
    final static char X = 'X';
    final static char O = 'O';

    public static void main(String[] args) {
        char option = 'A';

        while(true){
            if(option == 'A'){
                option = startGame();
            }
            else if(option == 'S'){
               loopGame();
               option = 'A';
            }
            else if(option == 'Q'){
                System.out.println("game closed");
                break;
            }
            else{
                System.out.println("incorrect option " + option);
            }
        }
    }
    public static void displayBoard(){
        for (int i = 0; i < BOARD_LINES; i++){
            for (int j = 0; j < BOARD_COLUMNS; j++){
                if(i/2 == 0){
                    System.out.print(BOARD[i][j]);
                }
                else{
                    System.out.print(BOARD[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char startGame(){
        System.out.println("--- S start game");
        System.out.println("--- Q quit game");
        return getInput();
    }

    public static char getInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Option --> ");
        char userInput = scanner.next().charAt(0);
        System.out.println("digitou " + userInput);
        return userInput;
    }
    public static void loopGame(){
        int turns = 0; //pair X turn, odd O turn
        char currentToken = X;
        int userInputLine = 0;
        int userInputColumn = 0;

        while(true){
            displayBoard();
            System.out.println("current turn "+turns);
            if(turns%2 == 0){
                System.out.println("--- It's X turn!");
                currentToken = X;
            }
            else{
                System.out.println("--- It's O turn!");
                currentToken = O;
            }
            System.out.println("--- choose a line between [0,1,2] ---> ");
            userInputLine = handleLoopGameInput();

            System.out.println("--- choose a column between [0,1,2] ---> ");
            userInputColumn = handleLoopGameInput();

            updateBoard(userInputLine, userInputColumn, currentToken);
            if(checkWinner()){
                displayBoard();
                System.out.println("--- " + currentToken + " is the winner");
                break;
            }
            turns++;
        }
    }
    public static int handleLoopGameInput(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            char userInput = scanner.next().charAt(0);
            if(userInput == '0'){
                return 0; //correct position on the board because of - and |
            }
            else if(userInput == '1'){
                return 2; //correct position on the board because of - and |
            }
            else if(userInput == '2'){
                return 4; //correct position on the board because of - and |
            }
            else{
                System.out.println("Incorrect Option Try again: ");
            }
        }
    }

    public static void updateBoard(int line, int column, char token){
        BOARD[line][column] = token;
    }

    public static boolean checkWinner() {
        // check lines
        for (int i = 0; i < BOARD_LINES; i += 2) {
            if (BOARD[i][0] == BOARD[i][2] && BOARD[i][2] == BOARD[i][4] && BOARD[i][0] != ' ') {
                return true;
            }
        }

        // check columns
        for (int j = 0; j < BOARD_COLUMNS; j += 2) {
            if (BOARD[0][j] == BOARD[2][j] && BOARD[2][j] == BOARD[4][j] && BOARD[0][j] != ' ') {
                return true;
            }
        }

        // check diagonals
        if ((BOARD[0][0] == BOARD[2][2] && BOARD[2][2] == BOARD[4][4] && BOARD[0][0] != ' ') ||
                (BOARD[0][4] == BOARD[2][2] && BOARD[2][2] == BOARD[4][0] && BOARD[0][4] != ' ')) {
            return true;
        }

        return false;
    }
}