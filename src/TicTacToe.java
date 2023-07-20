// Pseudocode:
/*
* Clear board
* Display board
* Ask user for row
* Ask user for column
* Check if move is valid
* If valid, place move and display board
* Check if player wins
* If player wins, display winner
* If no winner, check if tie
* If tie, display tie
* If no tie, switch player
* Repeat from ask user for row step until winner or tie
* Ask if they want to play again
* If yes, switch player to X and return to top
* If no, exit
* */

import java.util.Scanner;

public class TicTacToe {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        clearBoard();
        boolean playAgain;

        do {
            display();
            playGame();
            playAgain = SafeInput.getYNConfirm(new Scanner(System.in), "Play again? (Y/N) or (y/n)");
            if (playAgain) {
                clearBoard();
                currentPlayer = "X";
            }
        } while (playAgain);
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" "));
    }

    private static boolean isWin(String player) {
        return (isRowWin(player) || isColWin(player) || isDiagonalWin(player));
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        if (((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)))) {
            return true;
        }
        return false;
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void playGame() {
        Scanner in = new Scanner(System.in);
        int row, col;

        while (true) {
            row = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter row (1-3)", 1, 3);
            col = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter column (1-3)", 1, 3);

            row--;
            col--;

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                display();

                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    break;
                }

                if (currentPlayer.equals("X")){
                    currentPlayer = "O";
                }
                else {
                    currentPlayer = "X";
                }
            }
            else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}