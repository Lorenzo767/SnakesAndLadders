import java.util.Scanner;
/*
 * Assignment 1
 * Question Part 2
 * COMP 249 
 * Written By Lorenzo Velasque Guerrero
 * Due 2021/02/08
 */

/**
 * Driver prompts the user to input a valid number of players and handles errors.
 * 
 * @param numOfPlayers must be an int between 2 and 4
 * @author <40176510> Lorenzo Velasque Guerrero
 */

public class Driver {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int errorCount = 0;
		
		
		//welcome banner
		System.out.println("----------------------------------");
		System.out.println("     SNAKES AND LADDERS GAME \n        By: Lorenzo V.G.");
		System.out.println("----------------------------------");
		
		//prompt for # of players
		System.out.print("Hello, please input the number of people playing (must be between 2 and 4 inclusively): ");
		int numOfPlayers = input.nextInt();
		
		//handles error if numOfPlayers is not within 2 and 4
		while(!(numOfPlayers >= 2 && numOfPlayers <= 4)) {
			errorCount++;
			System.out.printf("Bad attempt #%d you have %d left\nThe number of players must be between 2 and 4: ", errorCount, 4-(errorCount));
			numOfPlayers = input.nextInt();
			if((errorCount+1) == 4) { //I use errorCount+1 symbol since if not the user has 5 tries. He gets 5 tries because the very first try is not counted as an error.  
				System.out.print("Program will now terminate.");
				System.exit(0);
			}
		}
		input.close();
		//makes new GUIframe object with the valid number of players	
		GUIframe m = new GUIframe(numOfPlayers);	
	




	}

}
