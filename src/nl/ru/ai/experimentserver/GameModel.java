package nl.ru.ai.experimentserver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that represents a model for the game
 * @author Tessa Beinema
 *
 */
public class GameModel extends Observable
{
	private int round;
	private int MAXROUNDS;
	private int player1RoundScore;
	private int player1TotalScore;
	private String player1LastChoice;
	private int player2RoundScore;
	private int player2TotalScore;
	private String player2LastChoice;
	private boolean player1Chose;
	private boolean player2Chose;

	public static int GAME_MODE=2;// 1 for human vs human, 2 for computer vs human
	
	/**
	 * Constructor for a new game model
	 */
	public GameModel ()
	{
		this.round = 1;
		this.MAXROUNDS = 97;
		this.player1RoundScore = 0;
		this.player1TotalScore = 0;
		this.player1LastChoice = "-";
		this.player2RoundScore = 0;
		this.player2TotalScore = 0;
		this.player2LastChoice = "-";
		this.player1Chose = false;
		this.player2Chose = false;
	}
	
	/**
	 * Method to increase the round
	 * @param n
	 */
	public void increaseRound(int n)
	{
		this.round += n;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	/**
	 * Method that returns the round
	 * @return
	 */
	public int getRound()
	{
		return this.round;
	}
	
	public int getMaxRounds()
	{
		return this.MAXROUNDS;
	}
	
	/**
	 * Setter for the player 1 chose boolean
	 * @param choice
	 */
	public void setPlayer1Chose(boolean choice)
	{
		this.player1Chose = choice;
		setChanged();
		notifyObservers();
		clearChanged();
		
		
	}
	
	/**
	 * Getter for the player 1 chose boolean
	 * @return Player 1 chose;
	 */
	public boolean getPlayer1Chose()
	{
		return player1Chose;
	}
	
	/**
	 * Setter for the player 2 chose boolean
	 * @param choice
	 */
	public void setPlayer2Chose(boolean choice)
	{
		this.player2Chose = choice;
		setChanged();
		notifyObservers();
		clearChanged();
	}
	
	/**
	 * Getter for the player 2 chose boolean
	 * @return Player 2 chose;
	 */
	public boolean getPlayer2Chose()
	{
		return player2Chose;
	}
	
	/**
	 * Method that returns the round score of player 1
	 * @return Player 1's score for the round
	 */
	public int getPlayer1RoundScore() 
	{
		return this.player1RoundScore;
	}

	/**
	 * Setter for the player 1 round score
	 * @param player1RoundScore
	 */
	public void setPlayer1RoundScore(int player1RoundScore) 
	{
		this.player1RoundScore = player1RoundScore;
	}

	/**
	 * Method that returns the total score for player 1
	 * @return player 1 total score
	 */
	public int getPlayer1TotalScore() 
	{
		return this.player1TotalScore;
	}

	/**
	 * Setter for the total score for player 1
	 * @param player1TotalScore
	 */
	public void setPlayer1TotalScore(int player1TotalScore) 
	{
		this.player1TotalScore = player1TotalScore;
	}

	/**
	 * Getter for the last choice of player 1
	 * @return Player 1 last choice
	 */
	public String getPlayer1LastChoice() 
	{
		return this.player1LastChoice;
	}

	/**
	 * Setter for the last choice of player 1
	 * @param player1LastChoice
	 */
	public void setPlayer1LastChoice(String player1LastChoice) 
	{
		this.player1LastChoice = player1LastChoice;
		if(GAME_MODE==2)
			mockPlayer2Turn();
	}

	private void mockPlayer2Turn() {
		Random random=new Random();
		boolean player2BeforePlayer1=random.nextBoolean();
		String player2choice=random.nextBoolean()?"A":"B";
		if(player2BeforePlayer1)
		{
			mockSetPlayer2LastChoice(player2choice);
		}
		else
		{
			Timer timer=new Timer();
			int delay=random.nextInt(2000)+500;
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					mockSetPlayer2LastChoice(player2choice);
				}
			},delay);
		}
	}

	private void mockSetPlayer2LastChoice(String player2choice) {
		try {
			Robot robot=new Robot();
			if(player2choice.equals("A"))
				robot.keyPress(KeyEvent.VK_K);
		if(player2choice.equals("B"))
				robot.keyPress(KeyEvent.VK_L);

		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method that returns the round score of player 2
	 * @return Player 2's score for the round
	 */
	public int getPlayer2RoundScore() 
	{
		return player2RoundScore;
	}

	/**
 * Setter for the round score for player 2
 * @param player2RoundScore
 */
	public void setPlayer2RoundScore(int player2RoundScore) 
	{
		this.player2RoundScore = player2RoundScore;
	}


	public int getPlayer2TotalScore() 
	{
		return player2TotalScore;
	}

	/**
	 * Method that returns the total score for player 2
	 * @return player 2 total score
	 */
	public void setPlayer2TotalScore(int player2TotalScore) 
	{
		this.player2TotalScore = player2TotalScore;
	}
	
	/**
	 * Getter for the last choice of player 2
	 * @return Player 2 last choice
	 */
	public String getPlayer2LastChoice() 
	{
		return player2LastChoice;
	}
	
	/**
	 * Setter for the last choice of player 2
	 * @param player2LastChoice
	 */
	public void setPlayer2LastChoice(String player2LastChoice) 
	{
		this.player2LastChoice = player2LastChoice;
	}

	public void refresh() {
		setChanged();
		notifyObservers();
		clearChanged();
		
	}

	CameraDisplay camera;
	public void setCamera(CameraDisplay cameraDisplay) {
		camera=cameraDisplay;
	}
}
