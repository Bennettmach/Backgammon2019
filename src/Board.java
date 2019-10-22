/**
 * The board class keeps track of 24 spaces (the skinny triangles on 
 * a regular board) and the bar for each player. It keeps track of 
 * these values and determines whether a given move is legal or not.
 */
public class Board {

	// TODO: decide which private member variables the Board class needs, and declare them here.
	private int[] numberOfPieces =  new int[26];

	// suggestion - for the 24 spaces, I suggest an array that holds the number
	// of pieces on each square. One player will have positive numbers, and
	// the other will have negative numbers. So if space (5) contains 3, that
	// means that there are 3 white pieces trying to move to higher numbered
	// spaces, and if space (7) contains -2, that means that there are 2 black
	// pieces trying to move to lower numbered spaces.

	// Locations 0 and 25 are the bars (penalty zones) for the two teams - if
	// the "negative" team is trying to move its pieces to smaller numbers,
	// then moving them to 0 or less actually removes them from the board - they
	// don't go to position 0. On the other hand if a "positive-moving" piece is
	// captured, it goes to position 0, the farthest point from it's goal of 25 or
	// more.

	/**
	 * constructor - set up the starting locations of the pieces.
	 */
	public Board()
	{
		//--------------------
		numberOfPieces[1] = 2;
        numberOfPieces[6] = -5;
        numberOfPieces[8] = -3;
        numberOfPieces[12] = 5;
        numberOfPieces[13] = -5;
        numberOfPieces[17] = 3;
        numberOfPieces[19] = 5;
        numberOfPieces[24] = -2;
		//--------------------
	}

	/**
	 * toString - create a string representing the state of the board.
	 * @return a string containing the board state.
	 * for example, it might look like:
	 * 0 (BAR) O
	 * 1
	 * 2 OO
	 * 3 OOO
	 * 4 XX
	 * 5
	 * 6 XXXXX
	 * ....
	 * 23 O
	 * 24 XX
	 * 25 (BAR) XX
	 */
	public String toString()
	{
		String result = "";
		//--------------------
		for (int i = 0; i < 26; i++)
        {
            result = result + i+" ";
            if (i == 0 || i == 25)
            {
                result = result + "(BAR) ";
            }
            if (numberOfPieces[i] > 0)
            {
                for (int j = 0; j < numberOfPieces[i]; j++)
                {
                    result = result + "O";
                }
            }
			if (numberOfPieces[i] < 0)
            {
                for (int j = 0; j > numberOfPieces[i]; j--)
                {
                    result = result + "X";
                }
            }
            result = result + "\n";
        }
		//--------------------
		return result;
	}

	/**
	 * playerHasPieceAtLocation - determines whether the player has at
	 * least one chip at the given space.
	 * @param whichPlayer - this can be -1 or 1.
	 * @param location - the number of the space in question.
	 * @return whether (true/false) the player has a chip of his/her own
	 * color at this space.
	 */
	public boolean playerHasPieceAtLocation(int whichPlayer, int location)
	{
		boolean hasPiece = false;
		//--------------------
		if (whichPlayer == 1)
		{
			if (numberOfPieces[location] > 0)
			{
				hasPiece = true;
			}
		}
		if (whichPlayer == -1)
		{
			if (numberOfPieces[location] < 0)
			{
				hasPiece = true;
			}
		}
		//--------------------
		return hasPiece;
	}

	/**
	 * isLegal - determines whether a chip at the given space can move
	 * the desired number of spaces
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be
	 * a move up or down, depending on what chip is in the starting space)
	 * @return whether (true/false) the player is allowed to make such a move.
	 * precondition: yes, there's at least one chip in the space.
	 * postcondition: the board is unchanged.
	 */
	public boolean isLegal(int startingSpace, int numSpaces)
	{
		boolean legal = false;
		//--------------------
		if (numberOfPieces[startingSpace] > 0)
		{
			if (startingSpace+numSpaces >= 25)
			{
				for (int i = 0; i < 19; i++)
				{
					if (numberOfPieces[i] > 0)
					{
						return false;
					}
					else
					{
						legal = true;
					}
				}
			}
			if ((numberOfPieces[startingSpace+numSpaces] >= 0) || (numberOfPieces[startingSpace+numSpaces] == -1))
			{
				legal = true;
			}
		}
		if (numberOfPieces[startingSpace] < 0)
		{
			if (startingSpace+numSpaces <= 0)
			{
				for (int i = 24; i > 6; i--)
				{
					if (numberOfPieces[i] < 0)
					{
						return false;
					}
					else
					{
						legal = true;
					}
				}
			}
			if ((numberOfPieces[startingSpace-numSpaces] <= 0) || (numberOfPieces[startingSpace-numSpaces] == 1))
			{
				legal = true;
			}
		}
		//--------------------
		return legal;
	}

	/**
	 * makeMove - moves one chip from the given space by the specified amount;
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be
	 * a move up or down, depending on what chip is in the starting space)
	 * precondition: there is a chip at the starting space
	 * postcondition: the chip may be moved to a different space, or off the board.
	 * If the chip lands on a single enemy piece, that piece is sent to its bar.
	 */
	public void makeMove(int startingSpace, int numSpacesToMove)
	{
		//--------------------
		if (numberOfPieces[startingSpace] > 0)
		{
			if (startingSpace+numSpacesToMove >= 25)
			{
				numberOfPieces[startingSpace] -= 1;
			}
			else
			{
				if (numberOfPieces[startingSpace+numSpacesToMove] == -1)
				{
					numberOfPieces[startingSpace+numSpacesToMove] += 1;
					numberOfPieces[25] -= 1;
					numberOfPieces[startingSpace] -= 1;
					numberOfPieces[startingSpace+numSpacesToMove] += 1;
				}
				else
				{
					numberOfPieces[startingSpace] -= 1;
					numberOfPieces[startingSpace+numSpacesToMove] += 1;
				}
			}
		}
		if (numberOfPieces[startingSpace] < 0)
		{
			if (startingSpace-numSpacesToMove <= 0)
			{
				numberOfPieces[startingSpace] += 1;
			}
			else
			{
				if (numberOfPieces[startingSpace-numSpacesToMove] == 1)
				{
					numberOfPieces[startingSpace-numSpacesToMove] -= 1;
					numberOfPieces[0] += 1;
					numberOfPieces[startingSpace] += 1;
					numberOfPieces[startingSpace-numSpacesToMove] -= 1;
				}
				else
				{
					numberOfPieces[startingSpace] += 1;
					numberOfPieces[startingSpace-numSpacesToMove] -= 1;
				}
			}
		}
		//--------------------
	}
	public boolean p1CanMoveOffBoard(int row, int move)
	{
		boolean yes = true;
		if (row - move < 1)
			for (int i = 6;i<numberOfPieces.length;i++)
				if (numberOfPieces[i] > 0)
				{
					yes = false;
				}
		return yes;
	}

	public boolean p2CanMoveOffBoard(int row, int move)
	{
		boolean yes = true;
		if (row + move >24)
			for (int i = 18;i>0;i--)
				if (numberOfPieces[i] < 0)
				{
					yes = false;
				}
		return yes;
	}

	public boolean p1canMove(int location, DiceCup dc)
	{
		boolean p1canMove = true;
		for (int i = 0; i <= 3; i++)
			if (numberOfPieces[location + dc.getDie()[i]] < 0)
			{
				p1canMove = false;
			}
		return p1canMove;
	}

	public boolean p2canMove(int location, DiceCup dc)
	{
		boolean p2canMove = true;
		for (int i = 0; i <= 3; i++)
			if (numberOfPieces[location - dc.getDie()[i]] > 0)
			{
				p2canMove = false;
			}
		return p2canMove;
	}
	/**
	 * gameIsOver - determines whether either player has removed all
	 * his/her pieces from the board.
	 * @return - whether (true/false) the game is over.
	 */
	public boolean gameIsOver()
	{
		boolean gameOver;
		boolean p1HasPieces = false;
		boolean p2HasPieces = false;

		//--------------------
		for (int i = 0; i < 26; i++)
		{
			if (numberOfPieces[i] > 0)
				p1HasPieces=true;
			if (numberOfPieces[i] < 0)
				p2HasPieces=true;
		}
		if (p1HasPieces && p2HasPieces)
			gameOver = false;
		else
			gameOver = true;
		//--------------------
		return gameOver;
	}
}