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

// Import scanner
import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        // Clear board and initialize playAgain variable
        clearBoard();
        boolean playAgain;

        do {
            // Display board and play game
            display();
            playGame();
            // Ask if they want to play again
            playAgain = SafeInput.getYNConfirm(new Scanner(System.in), "Play again? (Y/N) or (y/n)");
            // If yes, clear board and switch player to X
            if (playAgain) {
                clearBoard();
                currentPlayer = "X";
            }
        } while (playAgain);
    }

    // Clear the board by setting all cells to " "
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    // Display the Tic-Tac-Toe board
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

    // Check if a move is valid (within the board and the cell is not already taken
    private static boolean isValidMove(int row, int col) {
        return (row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" "));
    }

    // Check if the specified player has won the game
    private static boolean isWin(String player) {
        return (isRowWin(player) || isColWin(player) || isDiagonalWin(player));
    }

    // Check if the specified player has won in any row
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Check if the specified player has won in any column
    private static boolean isColWin(String player) {
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Check if the specified player has won in any diagonal
    private static boolean isDiagonalWin(String player) {
        if (((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) || (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)))) {
            return true;
        }
        return false;
    }

    // Check if the game is a tie (all cells are filled)
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

    // Play the game
    private static void playGame() {
        Scanner in = new Scanner(System.in);
        int row, col;

        while (true) {
            // Ask user for row and column
            row = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter row (1-3)", 1, 3);
            col = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter column (1-3)", 1, 3);

            // Adjust row and column to match array indices
            row--;
            col--;

            // Check if move is valid otherwise ask again
            if (isValidMove(row, col)) {
                // Place move and display board
                board[row][col] = currentPlayer;
                display();

                // Check if player wins or if it's a tie
                if (isWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    break;
                }

                // Switch player
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