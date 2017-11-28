package nl.ru.ai.experimentserver;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that displays the game for one player
 * @author Tessa Beinema
 *
 */
public class GameDisplay extends JFrame implements Observer {
	private GameModel model;
	private int player; 
	private JLabel scoresLabel;
	
	/**
	 * Constructor
	 * @param model
	 * @param player; Number for the player, either 1 or 2
	 */
	public GameDisplay (GameModel model, int player) {
		this.model = model;
		this.player = player;
		
		//Set various settings of the JFrame
		this.setTitle("Experiment");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 600);
		this.setResizable(false);
		this.setLocation(740, 0);
		this.setVisible(true);
		
		//Create the content of the infopanel
		JPanel infoPanel = new JPanel();
		infoPanel.setSize(600,800);
		this.setContentPane(infoPanel);
		infoPanel.setVisible(true);
		
		//Display the right player info on the infopanel ("RED" or "GREEN");
		JLabel playerLabel = new JLabel();
		if (player == 1) {
			playerLabel.setText("<html><br><h1><br>You are the <font size='10' color=#01DF01>GREEN</font> player!</h1><br><br><br><html>");
		}
		else if(player == 2) {
			playerLabel.setText("<html><br><h1><br>You are the <font size='10' color=#DF0101>RED</font> player!</h1><br><br><br><html>");
		}
		playerLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
		infoPanel.add(playerLabel);
		
//		//Display the 'score-values' image on the infopanel
//		BufferedImage image;
//		try {
//			image = ImageIO.read(new File("src/nl/ru/ai/experimentServer/ScoreTable.png"));
//			JLabel picLabel = new JLabel(new ImageIcon(image));
//			infoPanel.add(picLabel);
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
		//Display the scores, choices, etc
		this.scoresLabel = new JLabel();
		if (player == 1) {
			this. scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer1LastChoice() + "<br>Your last score was: " + this.model.getPlayer1RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'A', to choose 'B' press 'S')</html>");
		}
		else if (player == 2) {
			this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer2LastChoice() +  "<br>Your last score was: " + this.model.getPlayer2RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'K', to choose 'B' press 'L')</html>");
		}
		infoPanel.add(this.scoresLabel);
	}

	/**
	 * Unused paint function that is required by JFrame
	 */
	public void paint(Graphics g) 
	{ }
	
	/**
	 * Update function that repaints the (updated) scoreslabel when called
	 */
	public void update(Observable arg0, Object arg1) 
	{
		if (this.player == 1) {
			if (this.model.getRound() > this.model.getMaxRounds()) {
				this.scoresLabel.setText("<html><h2><br><br>END OF GAME! :)" + "<br>Your last choice: " + this.model.getPlayer1LastChoice() + "<br>Your last score was: " + this.model.getPlayer1RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br>Please make your choice.." + "<br><br>(To choose 'A' press 'A', to choose 'B' press 'S')</html>");
			}
			else {
				if (this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer1LastChoice() + "<br>Your last score was: " + this.model.getPlayer1RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'A', to choose 'B' press 'S')</html>");
				}
				else if (this.model.getPlayer1Chose() && !this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer1LastChoice() + "<br>Your last score was: " + this.model.getPlayer1RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>You have chosen (" + this.model.getPlayer1LastChoice() + ")" + "</font><br><font size='6'> wait for the other player.." + "</font><br><br>(To choose 'A' press 'A', to choose 'B' press 'S')</html>");
				}
				else if (!this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer1LastChoice() + "<br>Your last score was: " + this.model.getPlayer1RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'A', to choose 'B' press 'S')</html>");
				}
				else
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer1LastChoice() + "<br>Your last score was: " + this.model.getPlayer1RoundScore() + "<br>Your total score is: " + this.model.getPlayer1TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'A', to choose 'B' press 'S')</html>");
				}
			}
		}
		if (this.player == 2) {
			if (this.model.getRound() > this.model.getMaxRounds()) {
				this.scoresLabel.setText("<html><h2><br><br>END OF GAME! :)" + "<br>Your last choice: " + this.model.getPlayer2LastChoice() + "<br>Your last score was: " + this.model.getPlayer2RoundScore() + "<br>Your total score is: " + this.model.getPlayer2TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer1TotalScore() + " </h2><br>Please make your choice.." + "<br><br>(To choose 'A' press 'K', to choose 'B' press 'L')</html>");
			}
			else {
				if (this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer2LastChoice() +  "<br>Your last score was: " + this.model.getPlayer2RoundScore() + "<br>Your total score is: " + this.model.getPlayer2TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer1TotalScore() + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'K', to choose 'B' press 'L')</html>");
				}
				else if (!this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer2LastChoice() +  "<br>Your last score was: " + this.model.getPlayer2RoundScore() + "<br>Your total score is: " + this.model.getPlayer2TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer1TotalScore()  + " </h2><br><font size='6'>You have chosen (" + this.model.getPlayer2LastChoice() + ")" + "</font><br><font size='6'> wait for the other player.." + "</font><br><br>(To choose 'A' press 'K', to choose 'B' press 'L')</html>");
				}
				else if (this.model.getPlayer1Chose() && !this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer2LastChoice() +  "<br>Your last score was: " + this.model.getPlayer2RoundScore() + "<br>Your total score is: " + this.model.getPlayer2TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer1TotalScore()  + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'K', to choose 'B' press 'L')</html>");
				}
				else
				{
					this.scoresLabel.setText("<html><h2><br><br>Round: " + this.model.getRound() + "<br>Your last choice: " + this.model.getPlayer2LastChoice() +  "<br>Your last score was: " + this.model.getPlayer2RoundScore() + "<br>Your total score is: " + this.model.getPlayer2TotalScore() + "<br>Your opponents' score is: " + this.model.getPlayer1TotalScore()  + " </h2><br><font size='6'>Please make your choice.." + "</font><br><br>(To choose 'A' press 'K', to choose 'B' press 'L')</html>");
				}
			}
		}
		this.scoresLabel.repaint();
	}

}
