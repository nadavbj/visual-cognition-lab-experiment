package nl.ru.ai.experimentserver;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Date;

/**
 * Class for a game controller
 * @author Tessa Beinema
 *
 */
public class GameControl
{
	private BufferedWriter writer;
	private GameModel model;
	
	/**
	 * Constructor for a new gamecontroller
	 * @param model
	 * @param writer
	 */
	public GameControl (GameModel model, BufferedWriter writer) {
		this.writer = writer;
		this.model = model;
	}
	
	/**
	 * Function that updates the totalscores and the roundscores
	 */
	public void updateScore() {
		if (this.model.getPlayer1Chose() && this.model.getPlayer2Chose()) {
			
			if(this.model.getPlayer1LastChoice() == this.model.getPlayer2LastChoice()) {	
			
				if(this.model.getPlayer1LastChoice() == "A") {
					this.model.setPlayer1RoundScore(3);
					this.model.setPlayer1TotalScore(this.model.getPlayer1TotalScore() + 3);
					this.model.setPlayer2RoundScore(2);
					this.model.setPlayer2TotalScore(this.model.getPlayer2TotalScore() + 2);
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER1" + "\tChoice: " + this.model.getPlayer1LastChoice() + "\tRound: " + this.model.getRound() + "\tRoundscore: " + this.model.getPlayer1RoundScore() + "\tTotalscore: " + this.model.getPlayer1TotalScore() + "\n");
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER2" + "\tChoice: " + this.model.getPlayer2LastChoice() + "\tRound: " + this.model.getRound() + "\tRoundscore: " + this.model.getPlayer2RoundScore() + "\tTotalscore: " + this.model.getPlayer2TotalScore() + "\n");
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (this.model.getPlayer1LastChoice() == "B") {
					this.model.setPlayer1RoundScore(2);
					this.model.setPlayer1TotalScore(this.model.getPlayer1TotalScore() + 2);
					this.model.setPlayer2RoundScore(3);
					this.model.setPlayer2TotalScore(this.model.getPlayer2TotalScore() + 3);
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER1" + "\tChoice: " + this.model.getPlayer1LastChoice() + "\tRound: " + this.model.getRound() + "\tRoundscore: " + this.model.getPlayer1RoundScore() + "\tTotalscore: " + this.model.getPlayer1TotalScore() + "\n");
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER2" + "\tChoice: " + this.model.getPlayer2LastChoice() + "\tRound: " + this.model.getRound() + "\tRoundscore: " + this.model.getPlayer2RoundScore() + "\tTotalscore: " + this.model.getPlayer2TotalScore() + "\n");
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			else {
				this.model.setPlayer1RoundScore(0);
				this.model.setPlayer1TotalScore(this.model.getPlayer1TotalScore() + 0);
				this.model.setPlayer2RoundScore(0);
				this.model.setPlayer2TotalScore(this.model.getPlayer2TotalScore() + 0);
				Date d = new Date();
				try {
					this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER1" + "\tChoice: " + model.getPlayer1LastChoice() + "\tRound: " + this.model.getRound() + "\tRoundscore: " + model.getPlayer1RoundScore() + "\tTotalscore: " + model.getPlayer1TotalScore() + "\n");
					this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER2" + "\tChoice: " + model.getPlayer2LastChoice() + "\tRound: " + this.model.getRound() + "\tRoundscore: " + model.getPlayer2RoundScore() + "\tTotalscore: " + model.getPlayer2TotalScore() + "\n");
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			//System.out.println("Player 1: " + model.getPlayer1LastChoice() + " " + model.getPlayer1RoundScore() + " " + model.getPlayer1TotalScore());
			//System.out.println("Player 2: " + model.getPlayer2LastChoice() + " " + model.getPlayer2RoundScore() + " " + model.getPlayer2TotalScore());
			this.model.increaseRound(1);
			try {
				this.writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			setPlayer1Chose(false);
			setPlayer2Chose(false);
		}
	}
	
	/**
	 * Getter for the player 1 chose boolean
	 * @return player 1 chose
	 */
	public boolean getPlayer1Chose () {
		return this.model.getPlayer1Chose();
	}
	
	/**
	 * Setter for the player 1 chose boolean, and if player 2 chose the scores are updated
	 * @param bool
	 */
	public void setPlayer1Chose (boolean bool) {
		this.model.setPlayer1Chose(bool);
		updateScore();
	}
	
	/**
	 * Getter for the player 2 chose boolean
	 * @return player 1 chose
	 */
	public boolean getPlayer2Chose () {
		return this.model.getPlayer2Chose();
	}
	
	/**
	 * Setter for the player 2 chose boolean
	 * @param bool
	 */
	public void setPlayer2Chose (boolean bool) {
		this.model.setPlayer2Chose(bool);
		updateScore();
	}

	public void refresh() {
		this.model.refresh();
		
	}
}
