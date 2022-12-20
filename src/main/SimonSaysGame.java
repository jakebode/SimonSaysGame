package main;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class SimonSaysGame {
	public final JFrame mainFrame;
	public final SimonSays game;
	public JPanel gamePanel;
	public boolean gameOver = false;
	
	public SimonSaysGame(SimonSays game) {
		this.game = game;
		mainFrame = new JFrame("Simon Says");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 500);
		mainFrame.setResizable(false);
		mainFrame.setBackground(Color.BLACK);
	}
	
	public void play() {
		addTitle();
	    addGamePanel();
	    startButton();
	    showGUI();
	}
	
	public void showGUI() { mainFrame.setVisible(true); }
	
	private void addTitle() {
		JPanel label = new JPanel();
		label.setBackground(Color.BLACK);
		label.setSize(500, 100);
		label.setBorder(new EmptyBorder(20, 50, 20, 50));
		JLabel name = new JLabel("Title", SwingConstants.CENTER);
		name.setText("Simon Says");
		name.setVerticalAlignment(JLabel.TOP);
	    name.setFont(new Font("Courier", Font.BOLD, 40));
	    name.setForeground(Color.WHITE);
	    label.add(name);
	    mainFrame.add(label, BorderLayout.NORTH);
	}
	
	private void addGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.BLACK);
		gamePanel.setBorder(new EmptyBorder(100, 100, 200, 100));
	}
	
	public void startButton() {
		JLabel directions = new JLabel("<html>How to play: Enter the "
				+ "sequence of characters shown in the given order to"
				+ " test your memory. Good luck!</html>");
		directions.setVerticalAlignment(JLabel.TOP);
		directions.setPreferredSize(new Dimension(400, 100));
		directions.setFont(new Font("Courier", Font.PLAIN, 20));
		directions.setForeground(Color.WHITE);
	    gamePanel.add(directions);
		
		JButton start = new JButton(gameOver ? "Play Again" : "Start Game");
		start.setFont(new Font("Tahoma", Font.BOLD, 20));
		start.setBackground(Color.WHITE);
		start.setPreferredSize(new Dimension(200, 100));
		start.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		resetGUI();
	    		game.resetGame();
	    		game.nextChar();
	    		displayGame();
	    	}
	    });
		gamePanel.add(start);
	    
		if (gameOver) {
			JLabel score = new JLabel("Your Score: " + game.getCurrScore() + "  |  High Score: " + game.getHighScore());
			score.setForeground(Color.WHITE);
			score.setFont(new Font("Courier", Font.PLAIN, 15));
		    gamePanel.add(score, BorderLayout.SOUTH);
		}
		
		gameOver = false;
		mainFrame.add(gamePanel, BorderLayout.CENTER);
	}
	
	public void displayGame() {
	    JLabel nextLabel = new JLabel("Next Character: ");
	    nextLabel.setVerticalAlignment(JLabel.TOP);
	    nextLabel.setFont(new Font("Courier", Font.BOLD, 30));
	    nextLabel.setForeground(Color.WHITE);
	    gamePanel.add(nextLabel);

	    JLabel nextChar = new JLabel(game.getLastChar());
	    nextChar.setFont(new Font("Courier", Font.BOLD, 45));
	    nextChar.setForeground(Color.WHITE);
	    gamePanel.add(nextChar);

	    JLabel enterLabel = new JLabel("Enter the sequence: ");
	    enterLabel.setForeground(Color.WHITE);
	    gamePanel.add(enterLabel);
	    JTextField input = new JTextField(15);
	    input.setFont(new Font("Courier", Font.PLAIN, 15));
	    gamePanel.add(input);

	    JButton enterButton = new JButton("Enter");
	    enterButton.setFont(new Font("Tahoma", Font.BOLD, 10));
	    enterButton.setBackground(Color.WHITE);
	    enterButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String s = input.getText();
	    		resetGUI();
	    		if (!game.doesInputMatch(s)) {
	    			gameOver = true;
	    			startButton();
	    		} else {
	    			game.nextChar();
	    			displayGame();
	    		}
	    	}
	    });
	    gamePanel.add(enterButton);
	    
	    JLabel currScore = new JLabel("Score: " + game.getCurrScore() + "  | ");
	    currScore.setForeground(Color.WHITE);
	    currScore.setFont(new Font("Courier", Font.PLAIN, 15));
	    gamePanel.add(currScore);
	    JLabel highScore = new JLabel("High Score: " + game.getHighScore());
	    highScore.setForeground(Color.WHITE);
	    highScore.setFont(new Font("Courier", Font.PLAIN, 15));
	    gamePanel.add(highScore);
	    
	    mainFrame.setLayout(new BorderLayout());
	    mainFrame.add(gamePanel, BorderLayout.SOUTH);
	}
	
	public void resetGUI() {
		gamePanel.removeAll();
		gamePanel.revalidate();
		gamePanel.repaint();
	}

}
