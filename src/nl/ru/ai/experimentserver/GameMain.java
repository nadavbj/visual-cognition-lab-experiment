package nl.ru.ai.experimentserver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.Date;

/**
 * Main class for the game
 * @author Tessa Beinema
 *
 */
public class GameMain implements KeyListener {
	private BufferedWriter writer;
	private GameModel model;
	private GameControl control;
	
	/**
	 * Constructor for a new game
	 */
	public GameMain () {
		//Create the writer that writes to a txt file
		try {
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Users/Omer/eclipse_java_workspace/Experiment/eventlog.txt"), "utf-8"));
		}
		catch (Exception e) {
			System.out.println("Error in writer");
		}
		
		this.model = new GameModel ();
		this.control = new GameControl (this.model, this.writer);
		
		GameDisplay viewPlayer1 = new GameDisplay (this.model, 1);
		viewPlayer1.setVisible(true);
		viewPlayer1.addKeyListener(this);
		GameDisplay viewPlayer2 = new GameDisplay (this.model, 2);
		viewPlayer2.setVisible(true);
		viewPlayer2.addKeyListener(this);	
		
		this.model.addObserver(viewPlayer1);
		this.model.addObserver(viewPlayer2);
	}
	
	/**
	 * Function that implements keylistener for the key-pressed event
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
			case 'a': 
				if(!this.control.getPlayer1Chose()) {
					this.model.setPlayer1LastChoice("A");
					this.control.setPlayer1Chose(true);
					//System.out.println('a');
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER1" + "\tChoice: " + this.model.getPlayer1LastChoice() + "\n");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case 's': 
				if(!this.control.getPlayer1Chose()) {
					this.model.setPlayer1LastChoice("B"); 
					this.control.setPlayer1Chose(true);
					//System.out.println('s'); 
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER1" + "\tChoice: " + this.model.getPlayer1LastChoice() + "\n");
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				break;
			case 'k': 
				if(!this.control.getPlayer2Chose()) {
					this.model.setPlayer2LastChoice("A"); 
					this.control.setPlayer2Chose(true);
					//System.out.println('k'); 
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER2" + "\tChoice: " + this.model.getPlayer2LastChoice() + "\n");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				}
				break;
			case 'l': 
				if(!this.control.getPlayer2Chose()) {
					this.model.setPlayer2LastChoice("B");
					this.control.setPlayer2Chose(true);
					//System.out.println('l'); 
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER2" + "\tChoice: " + this.model.getPlayer2LastChoice() + "\n");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
