import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The dice cup is a class that takes care of rolling two dice, determining whether 
 * doubles were rolled, and keeping track of which moving numbers have been used.
 *
 */
public class DiceCup {

	private int die1, die2;
	private int[] availableMoves = new int[4];
	// this will be four numbers, though positions
	// 2 and 3 will often be zero.
	
	public DiceCup()
	{
		//--------------------
		// TODO: insert your code here.
		//The game should work without this method
		//--------------------
	}
	/**
	 * calculateAvailableMoves - based on the information stored in die1 and die2,
	 * determines which moves could be made by the player and stores them in an
	 * array. This is pretty straightforward, if you don't roll doubles.
	 * If the dice are doubles, then the player gets 4 iterations of the value of
	 *  the faces on the dice.
	 * 
	 * So if die1 = 5 and die2 = 2, then availableMoves is [5,2,0,0]. (in any order)
	 * If die1 = 3 and die3 = 3, then availableMoves is [3,3,3,3]. 
	 */
	public void calculateAvailableMoves()
	{
		//--------------------
		if (isDoubles())
		{
			availableMoves[0]=die1;
			availableMoves[1]=die2;
			availableMoves[2]=die2;
			availableMoves[3]=die1;
		}
		else
		{
			availableMoves[0]=die1;
			availableMoves[1]=die2;
			availableMoves[2]=0;
			availableMoves[3]=0;
		}
		//--------------------
	}
	/**
	 * pick two numbers for the dice, and copy these to the available moves. In the
	 * case of doubles, there are four instances. For instance, if the
	 * dice are 3 and 5, then availableMoves = [3, 5, 0, 0]. If the dice are 6 and 6, then 
	 * availableMoves = [6, 6, 6, 6].
	 */
	public void roll()
	{
		//--------------------
		// Hint: (int)(Math.random()*10) gives a random number from 0 to 9, inclusive.
		// Hint: write calculateAvailableMoves() before you write this one.
		die1=(int)(Math.random()*6+1);
		die2=(int)(Math.random()*6+1);
		calculateAvailableMoves();
		//--------------------
	}
	public int[] getDie()
	{
		int [] die = availableMoves;
		for (int i = 0; i < availableMoves.length; i++)
		{
			die[i] = availableMoves[i];
		}
		return die;
	}
	/**
	 * returns a string describing the dice, the available (non-zero) moves and whether this 
	 * was doubles.
	 * For example, if the player just rolled a 2 and a 5 the board might look like:
	 *   +-+ +-+
	 *   |2| |5|
	 *   +-+ +-+
	 *   Available: 2, 5
	 *   (although availableMoves might be [2, 5, 0, 0].)
	 * If the player rolled a pair of threes and has already used one three, the board
	 * might look like:
	 *   +-+ +-+
	 *   |3| |3| doubles
	 *   +-+ +-+
	 *   Available: 3, 3, 3
	 * 	(although availableMoves might be [0, 3, 3, 3].)
	 * @return a string describing the state of the dice and available non-zero moves.
	 */
	public String toString()
	{
		String result = "+-+ +-+\n|"+die1+"| |"+die2+"| \n";
		//--------------------
		result = result + "+-+ +-+\n";
		if (isDoubles())
		{
			result = result + "Doubles \n";
		}
		if(isDoubles())
		{
			result = result + "Available Moves: "+availableMoves[0]+", "+availableMoves[1]+", "+availableMoves[2]+ ", "+availableMoves[3];
		}
		else
		{
			result=result+ "Available Moves: "+availableMoves[0]+", "+availableMoves[1];
		}
		//--------------------
		return result;
		
	}
	/**
	 * isLegal - given a proposed move, determines whether this number is an option.
	 * @param amountToMove
	 * @return - whether the player can move this amount.
	 */
	public boolean isLegal(int amountToMove)
	{
		boolean legal = false;
		//--------------------
		for (int availableMove : availableMoves)
		{
			if (amountToMove == availableMove)
			{
				if (amountToMove == 0)
				{
					return legal;
				} else
					legal = true;
			}
		}
		//--------------------
		return legal;
	}
	/**
	 * isDoubles - indicates whether the two dice match.
	 * @return
	 */
	public boolean isDoubles()
	{
		boolean doubles = false;
		//--------------------
		if (die1==die2)
		{
			doubles = true;
		}
		//--------------------
		return doubles;
	}
	
	/**
	 * finds the first instance of the amountToMove in availableMoves and resets
	 * it to zero.
	 * @param amountToMove
	 * precondition: amountToMove is a legal move.
	 */
	public void moveMade(int amountToMove)
	{
		//--------------------
		for (int i = 3;i>=0;i--)
		{
			if (availableMoves[i] == amountToMove)
			{
				availableMoves[i]=0;
				break;
			}
		}
		//--------------------
	
	}
	
	/**
	 * hasMovesLeft - indicates whether there are still non-zero
	 * values in availableMoves.
	 * @return whether moves are still available.
	 */
	public boolean hasMovesLeft()
	{
		boolean hasMoves = false;
		//--------------------
		for (int availableMove : availableMoves)
		{
			if (availableMove > 0)
			{
				hasMoves = true;
				break;
			}
		}
		//--------------------
		return hasMoves;
	}
	
	//----------------------
	// FOR TESTING & DEBUGGING ONLY
	/**
	 * sets the dice to a and b, and calculates the availableMoves.
	 * @param a
	 * @param b
	 */
	public void debugSetDice(int a, int b)
	{
		die1=a;
		die2=b;
		calculateAvailableMoves();
	}
	public int[] debugGetDice()
	{
		int[] dice = {die1, die2};
		return dice;
	}
	
	public int[] debugGetAvailableMoves()
	{
		return availableMoves;
	}

	/**
	 * prints out what the players moves are.
	 */
	public void options()
	{
		System.out.print("Do you want to move ");
		for (int i = 0; i<4; i++)
		{
			if (availableMoves[i]>0)
			{
				System.out.print(availableMoves[i]+" ");
			}
		}
		System.out.println("");
	}
}