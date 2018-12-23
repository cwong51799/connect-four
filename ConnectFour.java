/* Christopher Wong (#111386693)
 * CSE 114
 */
import java.util.*;
public class ConnectFour {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int chosen; // the column chosen
		String player = "R";
		int nTurns = 0;
		int turn = 1;
		boolean continueInput;
		boolean gameStatus = true;
		String[][] board = new String[7][7];
		for (int i=0;i<6;i++)  // makes the board empty
			for (int j=0;j<7;j++) 
				board[i][j] = " ";
		for (int j=0;j<7;j++) // consider the invisible bottom to be filled
			board[6][j] = ".";
		while(gameStatus) {
			continueInput = true;
			for (int i=0;i<6;i++) { 	// print out the board
				System.out.print("|");
				for (int j=0;j<7;j++) {
					System.out.print(board[i][j]+"|");
				}
				System.out.println();
			}
			if (turn == 1) {
				System.out.print("Drop a red disk at column (0-6): ");
				turn++;
				player = "R";
			}
			else if (turn == 2) {
				System.out.print("Drop a yellow disk at column (0-6): ");
				turn--;
				player = "Y";
			}
			do {
			try {
				chosen = input.nextInt();
				for (int i=0;i<7;i++) { // place their spot in the appropriate spot
					if (board[0][chosen] == "R" || board[0][chosen] == "Y") {
						System.out.print("That spot is already taken.\nPlease enter a DIFFERENT column (0-6): ");
						chosen = input.nextInt();
					}
					if (board[i+1][chosen] == "R" || board[i+1][chosen] == "Y" || board[i+1][chosen] == ".") {
						board[i][chosen] = player;
						nTurns++;
						break;
					}
				}
			continueInput = false;
			}
			catch(InputMismatchException ex) {
				System.out.print("That's not even a number. Please try again: ");
				input.nextLine();
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				System.out.print("Out of bounds input. Please try again: ");
				input.nextLine();
			}
			}while(continueInput);
			// CHECK FOR WINNINGS
			int countRed = 0;
			int countYellow = 0;
			for (int i=0;i<7;i++) { // horizontal victory
				for (int j=0;j<7;j++) {
					if (board[i][j] == "R") {
						countRed++;
						countYellow = 0;
					}
					if (board[i][j] == "Y") {
						countYellow++;
						countRed = 0;
					}
					if (board[i][j] == " ") {
						countRed = 0;
						countYellow = 0;
					}
					if (countRed == 4) {
						System.out.println("Red wins.");
						gameStatus = false;
						break;
					}
					if (countYellow == 4) {
						System.out.println("Yellow wins.");
						gameStatus = false;
						break;
					}
				}
				countRed=0; //otherwise it would count over rows
				countYellow=0;
			}
			for (int i=0;i<7;i++) {// vertical victory
				for (int j=0;j<7;j++) {
					if (board[j][i] == "R") {
						countRed++;
						countYellow = 0;
					}
					if (board[j][i] == "Y") {
						countYellow++;
						countRed = 0;
					}
					if (board[j][i] == " ") {
						countRed = 0;
						countYellow = 0;
					}
					if (countRed == 4) {
						System.out.println("Red wins.");
						gameStatus = false;
						break;
					}
					if (countYellow == 4) {
						System.out.println("Yellow wins.");
						gameStatus = false;
						break;
					}
				}
				countRed = 0;
				countYellow = 0;
			}
			for (int i=3; i<6; i++){
		        for (int j=0; j<4; j++){
		            if (board[i][j] == player && board[i-1][j+1] == player && board[i-2][j+2] == player && board[i-3][j+3] == player) {
		            	if (player == "R") {
		            		System.out.println("Red wins.");
		            		gameStatus = false;
		            		break;
		            	}
		            	if (player == "Y") {
		            		System.out.println("Yellow wins.");
		            		gameStatus = false;
		            		break;
		            	}
		            }
		               
		        }
			}
			for (int i=3; i<6; i++){
		        for (int j=3; j<7; j++){
		            if (board[i][j] == player && board[i-1][j-1] == player && board[i-2][j-2] == player && board[i-3][j-3] == player) {
		            	if (player == "R") {
		            		System.out.println("Red wins.");
		            		gameStatus = false;
		            		break;
		            	}
		            	if (player == "Y") {
		            		System.out.println("Yellow wins.");
		            		gameStatus = false;
		            		break;
		            	}
		            }     
		        }
			}
			if (nTurns==42) { // 42 is the max amount of turns.
				System.out.println("The game ended in a draw.");
				gameStatus = false;
			}
		}
		input.close();
		for (int i=0;i<6;i++) { 	// print out the board
			System.out.print("|");
			for (int j=0;j<7;j++) {
				System.out.print(board[i][j]+"|");
			}
			System.out.println();
		}
	}
}
