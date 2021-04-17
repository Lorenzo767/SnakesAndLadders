import java.awt.Image;

import javax.swing.ImageIcon;

/*
 * Assignment 1
 * Question Part 1
 * Written By Lorenzo Velasque Guerrero
 * COMP 249 
 * Due 2021/02/08
 * 
 * This is the player class it defines all the player's components 
 * and has all the setters and getters for it's necessary components
 * */
/**
 * <p>
 * Player has all the unique attributes for each player in the game. It holds
 * all the necessary methods and attributes for the <b>LadderAndSnakePanel</b>
 * class to easily render each player's graphics.
 * </p>
 * 
 * @author Lorenzo Velasque Guerrero 40176510
 * @param num      is used for showing the user which player is which
 * @param row      the row is the player on
 * @param col      the column the player is currently on
 * @param position which position the player is on in the board
 * @see LadderAndSnake
 */
public class Player {
	private int position;
	private int num, row, col;
	private ImageIcon piece;

	/**
	 * Initializes position and num
	 */
	public Player(int num, int position) {
		this.position = position;
		this.num = num;
	}

	/**
	 * 
	 * @return num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Sets num
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * 
	 * @return position of the player
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Sets the position of a player
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * Takes an integer increment and increases the player's position by that much
	 * 
	 * @param increment
	 */
	public void increasePosition(int increment) {
		position = position + increment;
	}

	/**
	 * 
	 * @return piece - the imageIcon of a player
	 */
	public ImageIcon getPiece() {
		return piece;
	}

	/**
	 * Calculates the row the player is on and returns it
	 * 
	 * @return row
	 */
	public int getRow() {
		row = (position - 1) / 10;
		return row;
	}

	/**
	 * Sets the player's ImageIcon piece
	 */
	public void setPiece(ImageIcon piece) {
		this.piece = piece;
	}

	/**
	 * Calculates and returns the column a player is at on the game board.
	 */
	public int getCol() {
		if (position % 10 == 0) // check if on a 10's if so make col = 10
			col = 10;
		else
			col = position % 10;// if they are not on a ten's then by mod 10 the appropriate column is gotten
		if (!(getRow() % 2 == 0))// if on an odd row then just reverse the col numbers you got from previous the
									// else statement
			col = col + (9 - (2 * (col - 1)));
		return col;
		/*
		 * The math for reversing the series of 1 incrementing to 9 by 1 is done below:
		 * 
		 * 1 2 3 4 5 6 7 8 9 10 => 10 9 8 7 6 5 4 3 2 1
		 * 
		 * The RHS is really: 
		 * 1 + 9, 2 + 7, 3 + 5, 4 + 3, 5 + 1, 6 + -1, 7 + -3, 8 + -5,
		 * 9 + -7, 10 + -1 This gives the series
		 *  9, 7, 5, 3, 
		 *  etc. which decreases by 2
		 * each increment starting at 9 Therefore, we must add this series to the
		 * original 1 to 10 series in order to get the 10 to 1 series.
		 */
	} 

}
