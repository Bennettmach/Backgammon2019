import java.util.Scanner;
/**
 * The Referee class keeps track of the board, any dice, and all interactions
 * with the players. It keeps track of whose turn it is, displays the board,
 * rolls dice, and asks the users to make their moves. 
 */
public class Referee {

	// TODO: decide which private member variables the Referee should have and declare them here.
	// suggestion: the dice are an array of integers, typically 1-6, where 0 means unrolled or 
	// used up already.
	private Scanner keyReader = new Scanner(System.in);
	private String p1name;
	private String p2name;
	private Board myBoard;
	private DiceCup dCup;
	private int p1row;
	private int p2row;
	private boolean gameIsStillPlaying = true;
	private boolean p1Turn = false;
	private boolean p2Turn = false;
	private boolean p1FirstTurn = true;
	private boolean p2FirstTurn = false;

	/**
	 * constructor - set up the board and players 
	 */
	// TODO: you write the Referee's constructor

	/**
	 * playGame - the main game loop. Roll the dice, ask the user for a
	 * move, determine whether it is legal, and then execute the move.
	 * Repeat for any remaining dice.
	 */
	public void playGame() {
		// TODO: you write the Referee's playGame method.
		// starting turn
		myBoard = new Board();
		dCup = new DiceCup();
		System.out.println("Welcome to Backgammon!");
		System.out.println("\t**RULES**");
		System.out.println("1. Move your pieces to the end zone\n2. You must have all of your pieces within the closest six spots to your end to begin taking them off the board");
		System.out.print("Player 1 name: ");
		p1name = keyReader.nextLine();
		System.out.print("Player 2 name: ");
		p2name = keyReader.nextLine();
		//start game
		while (gameIsStillPlaying) {
			p1Turn = true;
			// player 1 turn
			while (p1Turn) {
				// if first turn then roll dice an print board
				if (p1FirstTurn) {
					System.out.println(p1name + " is rolling...");
					System.out.println(myBoard);
					dCup.roll();
					System.out.println(dCup);
					System.out.println(p1name+" is: O's\t"+p2name+" is: X's");
					p1FirstTurn = false;
					p2FirstTurn = true;
				}
				// if no moves left
				if (!dCup.hasMovesLeft())
				{
					p2Turn = true;
					p1Turn = false;
					break;
				}
				// ask if p1 wants to move piece
				System.out.println(p1name+", From which row would you like to move a piece?");
				p1row = keyReader.nextInt();
				// if invalid choice
				while (!myBoard.playerHasPieceAtLocation(1, p1row)|| p1row > 25 || p1row <0) {
					System.out.println("Please enter a valid number");
					System.out.println(p1name+", From which row would you like to move a piece?");
					p1row = keyReader.nextInt();
				}
				// if there are no available moves at that location
				while(!myBoard.p1canMove(p1row,dCup))
				{
					System.out.println("You cannot move a piece from this location");
					System.out.println(p1name+", From which row would you like to move a piece?");
					p1row = keyReader.nextInt();
				}
				// if valid choice
				if (myBoard.playerHasPieceAtLocation(1, p1row)) {
					// if hasMovesLeft = 0
					if (!p1Turn)
						break;
					// if hasMovesLeft > 0
					while (dCup.hasMovesLeft()) {
						// print moves and ask which move
						dCup.options();
						int choice = keyReader.nextInt();
						// check if move goes off the board and if they are allowed to go off
						if (!myBoard.p1CanMoveOffBoard(p1row,choice))
						{
							System.out.println("You cannot move off the board yet");
							break;
						}
						// if move can be made
						if (dCup.isLegal(choice))
						{
							// if move can be made
							if (myBoard.isLegal(p1row, choice))
							{
								// make move, print board, print player's pieces
								dCup.moveMade(choice);
								myBoard.makeMove(p1row, choice);
								System.out.println(myBoard);
								System.out.println(dCup);
								System.out.println(p1name+" is: O's\t"+p2name+" is: X's");
								break;
							}
						}
					}
				}
			}
			// p2 turn
			while (p2Turn) {
				// if p2 first turn roll dice
				if (p2FirstTurn) {
					System.out.println(p2name + " is rolling...");
					System.out.println(myBoard);
					dCup.roll();
					System.out.println(dCup);
					System.out.println(p1name+" is: O's\t"+p2name+" is: X's");
					p2FirstTurn = false;
					p1FirstTurn = true;
				}
				if (!dCup.hasMovesLeft())
				{
					p1Turn = true;
					p2Turn = false;
					break;
				}
				// ask if p2 wants to move a piece
				System.out.println(p2name+", From which row would you like to move a piece?");
				p2row = keyReader.nextInt();
				// if invalid choice
				while (!myBoard.playerHasPieceAtLocation(-1, p2row)|| p2row > 25 || p2row <0) {
					System.out.println("Please enter a valid number");
					System.out.println(p2name+", From which row would you like to move a piece?");
					p2row = keyReader.nextInt();
				}
				// if there are no available moves at that location
				while(!myBoard.p2canMove(p2row,dCup))
				{
					System.out.println("You cannot move a piece from this location");
					System.out.println(p2name+", From which row would you like to move a piece?");
					p2row = keyReader.nextInt();
				}
				//if valid choice
				if (myBoard.playerHasPieceAtLocation(-1, p2row)) {
					// if hasMovesLeft = 0
					if (!p2Turn)
						break;
					// if hasMovesLeft > 0
					// if no moves left
					while (dCup.hasMovesLeft()) {
						dCup.options();
						int choice = keyReader.nextInt();
						// check if move goes off the board and if they are allowed to go off
						if (!myBoard.p2CanMoveOffBoard(p2row,choice))
						{
							System.out.println("You cannot move off the board yet");
							break;
						}
						// print moves and ask which move
						if (dCup.isLegal(choice)) {
							if (myBoard.isLegal(p2row, choice))
							{
								// make move, print board, print player's pieces
								dCup.moveMade(choice);
								myBoard.makeMove(p2row, choice);
								System.out.println(myBoard);
								System.out.println(dCup);
								System.out.println(p1name+" is: O's\t"+p2name+" is: X's");
								break;
							}
						}
					}
				}
			}
		}
	}
}