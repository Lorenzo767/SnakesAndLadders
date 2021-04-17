
/*
 * Assignment 1
 * Question Part 1
 * Written By Lorenzo Velasque Guerrero
 * COMP 249
 * Due 2021/02/08
 * 
 * This is the game-logic class called LadderAndSnake. It handles all the necessary components 
 * so that the game works properly. It doesn't include the player's components but it takes each player's
 * components from the Player class and uses them to interact with the board and it's components. All components
 * of the board are defined here.
 * */
import javax.swing.ImageIcon;

/**
 * <p>
 * LadderAndSnake contains all the logic for the Snakes and Ladders Game. It
 * initializes the board and players using methods from the <b>Player</b> class.
 * </p>
 * 
 * @param players an array of Player objects whose order is the order of
 *                players' turns
 * @param board   This is a 1-D array where an index is a position on a board
 *                and the value inside is the movement taken when a player is on
 *                that position
 */

public class LadderAndSnake {
	private int numOfPlayers, currentPlayer, board[];
	private Player players[];
	private boolean winner = false;

	/**
	 * This constructor initializes the board and the players
	 * 
	 * @param p how many players the user wants to play with
	 */
	public LadderAndSnake(int p) {
		numOfPlayers = p;

		players = new Player[p];
		board = new int[101];
		currentPlayer = 0; 
		// hard coding the positions of snakes and ladders.
		board[1] = 37;
		board[4] = 10;
		board[9] = 22;
		board[16] = -10;
		board[21] = 21;
		board[28] = 56;
		board[36] = 8;
		board[48] = -18;
		board[51] = 16;
		board[62] = -43;
		board[63] = -3;
		board[71] = board[80] = 20;
		board[93] = -25;
		board[95] = -71;
		board[97] = -21;
		board[98] = -20;

		for (int i = 0; i < numOfPlayers; i++) {
			players[i] = new Player(i, 0);
		}
	}

	/**
	 * Sets the image icons of each player. This is used in the LadderAndSnakePanel
	 * class.
	 * 
	 * @param imageArray array of ImageIcon objects
	 */
	public void setImageIcons(ImageIcon[] imageArray) {
		if (imageArray.length == numOfPlayers) {
			System.out.println(true);
			for (int i = 0; i < numOfPlayers; i++) {
				players[i].setPiece(imageArray[i]);
			}
		}
		System.out.println(false);
	}

	/**
	 * 
	 * @return numOfPlayers
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}

	/**
	 * 
	 * @return players
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * 
	 * @return players[currentPlayer] - the player whose turn it is.
	 */
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}
	
	public boolean getWinner() {
		return winner;
		
	}

	/**
	 * 
	 * @return a random integer between 1 and 6, inclusively.
	 */
	public int flipDice() {

		return (int) (Math.random() * 6) + 1;// the multiplication gives a random number 0-5
												// with the plus one it gives 1-6
	}

	/**
	 * Decides the order of players' turns.
	 */
	public void decide() {
		System.out.println("Rolling dice to decide play order");
		currentPlayer = 0;
		boolean tie = false;
		do {
			int playerRoll;
			for (int i = 0; i < numOfPlayers; i++) {
				playerRoll = flipDice();
				players[i].setPosition(playerRoll);
				System.out.printf("Player %d has rolled a %d\n", (i + 1), playerRoll);
			}

			// bubble sort the player's according to who has the highest roll
			for (int i = 0; i < players.length; i++) {
				for (int j = i + 1; j < players.length; j++) {
					if (players[i].getPosition() == players[j].getPosition()) {
						System.out.println("There is a tie... Re-Rolling");
						tie = true;
						break;
					}
					if (players[i].getPosition() < players[j].getPosition()) {
						Player temp = players[i];
						players[i] = players[j];
						players[j] = temp;
						tie = false;
					}
				}
				if (tie) 
					break;		
			}
		} while (tie);
		System.out.print("Reached a final decision on play order: ");
		for (int i = 0; i < players.length; i++) {
			players[i].setPosition(0);// resets the player's position as they were used to store the roll for
										// deciding the order
			System.out.print("Player " + (players[i].getNum() + 1) + ", ");
		}
		System.out.println("\n\nThe game shall now begin...");

	}

	/**
	 * Main snakes and ladders game loop.
	 */
	public void play() {
		int playerRoll;

		playerRoll = flipDice();
		players[currentPlayer].increasePosition(playerRoll);

		System.out.printf("\nPlayer #%d got a %d.", players[currentPlayer].getNum() + 1, playerRoll);

		if (players[currentPlayer].getPosition() > 100) {
			int overOneHundred = players[currentPlayer].getPosition() - 100; // calculates and stores how much the
																				// player got over 100
			players[currentPlayer].setPosition(100 - overOneHundred);// sets the player's position appropriately to how
																		// much they went over 100
			
			System.out.printf("\nPlayer #%d went over by %d ", players[currentPlayer].getNum() + 1,
					(100 - players[currentPlayer].getPosition()), players[currentPlayer].getPosition());
		}
		
		System.out.printf("They are now at square %d.\n", players[currentPlayer].getPosition());//displays player's position right after their roll
		
		//game logic for if the player lands on a ladder or a snake 
		if (board[players[currentPlayer].getPosition()] != 0) {
			int movementOnBoard = board[players[currentPlayer].getPosition()];
			players[currentPlayer].increasePosition(movementOnBoard);
			if (movementOnBoard < 0) {
				System.out.println("Uh Oh! You landed on a snake! You go down " + movementOnBoard + " squares to "
						+ players[currentPlayer].getPosition() + "...");
			}
			if (movementOnBoard > 0) {
				System.out.println("!?! You landed on a ladder! You go up " + movementOnBoard + " squares to "
						+ players[currentPlayer].getPosition() + ".");
			}
		}

		if (players[currentPlayer].getPosition() == 100) {
			winner = true;
			System.out.printf("\nPlayer #%d is the winner!!!", (players[currentPlayer].getNum() + 1));
		}
		
		if (currentPlayer < players.length - 1)// bug fix: - 1
			currentPlayer++;
		else
			currentPlayer = 0;
	}

}
