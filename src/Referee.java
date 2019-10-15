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
	
	/**
	 * constructor - set up the board and players 
	 */
	// TODO: you write the Referee's constructor
	
	/**
	 * playGame - the main game loop. Roll the dice, ask the user for a
	 * move, determine whether it is legal, and then execute the move. 
	 * Repeat for any remaining dice.
	 */
	public void playGame()
	{
		// TODO: you write the Referee's playGame method.
		myBoard = new Board();
		System.out.print("Player 1 name: ");
		p1name = keyReader.nextLine();
		System.out.print("Player 2 name: ");
		p2name = keyReader.nextLine();
		System.out.println(myBoard);


	}
}
