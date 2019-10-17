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
	private boolean gameIsStillPlaying = true;
	private boolean p1Turn = false;
	private boolean p2Turn = false;

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
		myBoard = new Board();
		dCup = new DiceCup();
		System.out.println("Player 1 is: O Player 2 is: X");
		System.out.print("Player 1 name: ");
		p1name = keyReader.nextLine();
		System.out.print("Player 2 name: ");
		p2name = keyReader.nextLine();
		while (gameIsStillPlaying) {
			p1Turn = true;
			while (p1Turn)
			{
				System.out.println(p1name + " is rolling...");
				System.out.println(myBoard);
				dCup.roll();
				System.out.println(dCup);
				System.out.println("From which row would you like to move a piece?");
				p1row = keyReader.nextInt();
				while (!myBoard.playerHasPieceAtLocation(1, p1row))
				{
					System.out.println("Please enter a valid number");
					System.out.println("From which row would you like to move a piece?");
					p1row = keyReader.nextInt();
				}
				while (myBoard.playerHasPieceAtLocation(1, p1row))
				{
					while (dCup.hasMovesLeft()) {
						dCup.options();
						int choice = keyReader.nextInt();
						if (dCup.isLegal(choice))
						{
							if (myBoard.isLegal(p1row, choice))
							{
								myBoard.makeMove(p1row, choice);
								System.out.println(myBoard);
								System.out.println(dCup);
							}
						}
					}
				}
			}
		}
	}
}
