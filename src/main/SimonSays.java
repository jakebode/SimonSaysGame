package main;

import java.util.Random;

public class SimonSays {
	private static int highScore = 0;
    private String game;
    private int score;
    private final String ALPHA = "ABCDEFGHIJKLMNOPQRSTRUVWXYZ1234567890";

    public SimonSays() {
        game = "";
        score = 0;
    }
    
    public int getHighScore() {return highScore;}
    public String getLastChar() {return game.substring(game.length() - 1);}
    public String getSequence() {return game;}
    public int getCurrScore() {return score;}

    public void resetGame() {
    	game = "";
    	score = 0;
    }
    
    public void nextChar() {
    	String next = randomChar();
    	game += next;
    }

    private String randomChar() {
    	Random rn = new Random();
    	int rand = rn.nextInt(ALPHA.length());
    	return String.valueOf(ALPHA.charAt(rand));
    }
    
    public boolean doesInputMatch(String s) {
    	if (s.toUpperCase().equals(game)) {
    		score++;
    		return true;
    	}
    	
    	highScore = score > highScore ? score : highScore;
    	return false;
    }
    
}
