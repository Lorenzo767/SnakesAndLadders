
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Assignment 1
 * Question Part 1
 * Written By Lorenzo Velasque Guerrero
 * COMP 249
 * Due 2021/02/08
 *
 * This is the JPanel class to display the game as played in real time.
 * As a player moves so does it's piece on the board. This class works with the LadderAndSnake
 * class to display the game. It uses the JFrame GUIframe class to have a window that it can put
 * the graphics into.
 * */
/**
 * LadderAndSnakePanel handles all the graphics excluding the JFrame portion.
 *
 * <p>
 * This class uses JPanel to create a nice GUI for the Snakes and Ladders game.
 * It shows the game as it is being played in real time and is interactive
 * however limited.
 * </p>
 *
 * @author Lorenzo Velasque Guerrero
 * @param game   This is a LadderAndSnake object which holds the game logic and
 *               which LadderAndSnakePanel uses to realistically portray the
 *               game
 * @param button this rolls the die for a player's turn
 */
//dice to position 
public class LadderAndSnakePanel extends JPanel {
	private LadderAndSnake game;
	private JButton button;
	private Image background;

	/**
	 * Initializes all GUI components and overrides actionPerformed.
	 *
	 * @param p is the number of players which is taken from the Driver class and
	 *          used to create a new game
	 */
	public LadderAndSnakePanel(int p) {
		this.game = new LadderAndSnake(p);
		ImageIcon d = new ImageIcon(this.getClass().getResource("die S.png")); // imageIcon for the die
		this.setLayout(null);// Allows for the button to be moved freely
		button = new JButton();
		button.setIcon(d);
		button.setSize(30, 30);
		button.setLocation(10, 526);
		this.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		});

		ImageIcon ii = new ImageIcon(this.getClass().getResource("bck.png"));
		ImageIcon bluey = new ImageIcon(this.getClass().getResource("blue.png"));
		ImageIcon green = new ImageIcon(this.getClass().getResource("green.png"));
		ImageIcon purple = new ImageIcon(this.getClass().getResource("purple.png"));
		ImageIcon red = new ImageIcon(this.getClass().getResource("red.png"));

		background = ii.getImage();

		switch(p) {
			case 2:
				game.setImageIcons(new ImageIcon[] { bluey, green});
				break;
			case 3:
				game.setImageIcons(new ImageIcon[] { bluey, green, purple, });
				break;
			case 4:
				game.setImageIcons(new ImageIcon[] { bluey, green, purple, red });
				break;
		}
	}

	/**
	 * Calls start() from SnakesAndLadders when the button is clicked and updates
	 * the graphics depending on how the game plays out.
	 */
	protected void buttonClicked() {
		if (game.getWinner() != true) {
			game.play();
			repaint();
		}
	}

	/**
	 * Calls decide() from SnakesAndLadders to start the game.
	 */
	public void start() {
		game.decide();
	}

	/**
	 * PaintComponent can be "called" through repaint(). It dictates what will be
	 * painted on the JPanel.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // the fuck this do?

		g2d.drawImage(background, 0, 0, this.getWidth(), 561 - 40, null);

		/*
		 * loops in order of the players array, if the player is on the board, it draws
		 * the image of the player depending on the row and column they are in.
		 */
		for (Player p : game.getPlayers()) {
			if (p.getPosition() != 0) {

				g2d.drawImage(p.getPiece().getImage(), p.getCol() * 55 - 25, 450 + 7 * p.getNum() - p.getRow() * 50, 35,
						35, this);
			}
			/*
			 * The second argument is the x value and the third is the image's y value.
			 * p.getCol() * 55 because the tiles' centers are about that far apart from each
			 * other.
			 *
			 * Similarly, this is why we have p.getRow() * 50 for part of the y-value. There
			 * is - 25 to account for the extra space on the JPanel that are not part of any
			 * tile. 450 is added to the y-value to account for the fact that the pieces
			 * start at the bottom hence in a large positive y value. The p.getRow() * 50 is
			 * negatively added since we are rising (negative y-value).
			 *
			 * p.getNum() this is only for slight off set in y-position in for each player.
			 * With this offset we can see the players better.
			 */
		}
	}

}
