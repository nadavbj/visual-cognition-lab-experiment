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
	private JLabel playerLabel = new JLabel();
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
		this.setSize(400, 600);
		this.setResizable(false);
		this.setLocation(740, 0);
		this.setVisible(true);
		
		//Create the content of the infopanel
		JPanel infoPanel = new JPanel();
		infoPanel.setSize(500,500);
		this.setContentPane(infoPanel);
		infoPanel.setVisible(true);
		
		//Display the right player info on the infopanel ("RED" or "GREEN");
		
		if (player == 1) {
			playerLabel.setText("<html><br><h1><br>את/ה שחקן מספר <font size='10'>1</font></h1><br><br><br><html>");
		}
		else if(player == 2) {
			playerLabel.setText("<html><br><h1><br>את/ה שחקן מספר <font size='10'>2</font></h1><br><br><br><html>");
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
			this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer1LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer1RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
		}
		else if (player == 2) {
			this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer2LastChoice() +  "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer2RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
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
			playerLabel.setText("<html><br><h1><br>את/ה שחקן מספר <font size='10'>1</font></h1><br><br><br><html>");
			if (this.model.getRound() > this.model.getMaxRounds()) {
				this.scoresLabel.setText("<html><h2><br><br>END OF GAME! :)" + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer1LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer1RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br>בבקשה בצע/י בחירתך" + "<br><br></html>");
				System.exit(0);
			}
			else {
				if (this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer1LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer1RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
				}
				else if (this.model.getPlayer1Chose() && !this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer1LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer1RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>בחרת ב-(" + this.model.getPlayer1LastChoice() + ")" + "</font><br><font size='6'> אנא המתן/י לתגובת היריב.." + "</font><br><br></html>");
				}
				else if (!this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer1LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer1RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
				}
				else
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer1LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer1RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer1TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer2TotalScore() + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
				}
			}
		}
		if (this.player == 2) {
			playerLabel.setText("<html><br><h1><br>את/ה שחקן מספר <font size='10'>2</font></h1><br><br><br><html>");
			if (this.model.getRound() > this.model.getMaxRounds()) {
				this.scoresLabel.setText("<html><h2><br><br>END OF GAME! :)" + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer2LastChoice() + "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer2RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer2TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer1TotalScore() + " </h2><br>בבקשה בצע/י בחירתך" + "<br><br></html>");
			}
			else {
				if (this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer2LastChoice() +  "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer2RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer2TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer1TotalScore() + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
				}
				else if (!this.model.getPlayer1Chose() && this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer2LastChoice() +  "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer2RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer2TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer1TotalScore()  + " </h2><br><font size='6'>בחרת ב-(" + this.model.getPlayer2LastChoice() + ")" + "</font><br><font size='6'> אנא המתן/י לתגובת היריב.." + "</font><br><br></html>");
				}
				else if (this.model.getPlayer1Chose() && !this.model.getPlayer2Chose())
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer2LastChoice() +  "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer2RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer2TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer1TotalScore()  + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
				}
				else
				{
					this.scoresLabel.setText("<html><h2><br><br>סבב מספר: " + this.model.getRound() + "<br>בחירתך בסבב הקודם: " + this.model.getPlayer2LastChoice() +  "<br>ניקוד שצברת בסבב הקודם: " + this.model.getPlayer2RoundScore() + "<br>סך הניקוד המצטבר שלך: " + this.model.getPlayer2TotalScore() + "<br>סך הניקוד המצטבר של היריב/ה: " + this.model.getPlayer1TotalScore()  + " </h2><br><font size='6'>בבקשה בצע/י בחירתך" + "</font><br><br></html>");
				}
			}
		}
		this.playerLabel.repaint();
		this.scoresLabel.repaint();
	}

}
