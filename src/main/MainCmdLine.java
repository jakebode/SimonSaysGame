package main;

import java.util.Scanner;

public class MainCmdLine {
	public static void main(String[] args) {
		SimonSays game = new SimonSays();
		
		boolean play = true;
		Scanner scanner = new Scanner(System.in);
		while (play) {
			playTurn(game);
			System.out.println("give the sequence: ");
			String input = scanner.nextLine();
			if (!game.doesInputMatch(input)) {
				System.out.println("Correct sequence: " + game.getSequence());
				System.out.println("Game over! Score: " + game.getCurrScore());
				if (game.getCurrScore() == game.getHighScore()) {
					System.out.println("High score!");
				}
				System.out.print("play again? (y/n) ");
				String playAgain = scanner.nextLine();
				if (playAgain.equals("y") || playAgain.equals("Y")) {
					game.resetGame();
				} else {
					System.out.println("\nThanks for playing!");
					play = false;
				}
			}
		}
		scanner.close();
	}
	
	public static void playTurn(SimonSays game) {
		for (int i = 0; i < 100; i++) {
			System.out.println("\n");	
		}
		game.nextChar();
		System.out.println("Next char: " + game.getLastChar());
	}
}
