/*
 * Assignment 1
 * Question Part 1
 * Written By Lorenzo Velasque Guerrero
 * COMP 249 
 * Due 2021/02/08
 * 
 * This is the GUIframe class, it declares and defines the needed components for the "window" of our game
 * It also starts the game. 
 * */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JFrame for our Snake and Ladders game.
 */
public class GUIframe extends JFrame {
	LadderAndSnakePanel mainGame;

	/**
	 * Initializes all the preferred settings for our window or JFrame.
	 */
	public GUIframe(int players) {
		mainGame = new LadderAndSnakePanel(players);

		this.add(mainGame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("Snake And Ladders by Lorenzo Velasque Guerrero");
		this.setSize(600, 600); // the real size of the entire window is 639 since top bar is 31px and bot is 8px
		this.setResizable(false);
		this.setVisible(true);
		this.setAlwaysOnTop(true);// I set this true since we don't want to always see the game window even after typing something in the console
		mainGame.start();// starts the game

	}

}
