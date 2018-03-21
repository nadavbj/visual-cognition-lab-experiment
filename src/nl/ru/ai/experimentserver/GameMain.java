package nl.ru.ai.experimentserver;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.*;

import org.apache.commons.io.*;

import java.util.Date;

/**
 * Main class for the game
 * @author Tessa Beinema
 *
 */
public class GameMain implements KeyListener {
	private BufferedWriter writer;
	public GameModel model;
	private GameControl control;
	
	private File cam1_folder;
	private File cam2_folder;
	
	/**
	 * Constructor for a new game
	 */
	public GameMain (String path, String names) {
		//Create the writer that writes to a txt file
		try {
			File newDir = new File(path);
			if (newDir.exists()){
				System.err.println("Error: participants directory already exist. please try with other names.");
				System.exit(1);
			}
			newDir.mkdir();
			cam1_folder = new File(path + "/cam1/");
			cam2_folder = new File(path + "/cam2/");
			cam1_folder.mkdir();
			cam2_folder.mkdir();
			this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + "eventlog_"+ names +".txt"), "utf-8"));
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
					//System.out.println('a');
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER1" + "\tChoice: " + this.model.getPlayer1LastChoice() + "\n");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
					
					this.control.setPlayer1Chose(true);
				}
				break;
			case 's': 
				if(!this.control.getPlayer1Chose()) {
					
					this.model.setPlayer1LastChoice("B"); 
					//System.out.println('s'); 
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER1" + "\tChoice: " + this.model.getPlayer1LastChoice() + "\n");
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
					
					this.control.setPlayer1Chose(true);
				}
				break;
			case 'k': 
				if(!this.control.getPlayer2Chose()) {
					
					this.model.setPlayer2LastChoice("A"); 
					//System.out.println('k'); 
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER2" + "\tChoice: " + this.model.getPlayer2LastChoice() + "\n");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
					
					this.control.setPlayer2Chose(true);
				}
				break;
			case 'l': 
				if(!this.control.getPlayer2Chose()) {
					
					this.model.setPlayer2LastChoice("B");
					//System.out.println('l'); 
					Date d = new Date();
					try {
						this.writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tKEYPRESSED" + "\tPLAYER2" + "\tChoice: " + this.model.getPlayer2LastChoice() + "\n");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}

					this.control.setPlayer2Chose(true);
				}
				break;
			case ' ':
				this.control.refresh();
				break;
			case '$':
				try {
					FileUtils.cleanDirectory(cam1_folder);
					FileUtils.cleanDirectory(cam2_folder);
					System.out.println("the directories are now clean.");
				} catch (IOException e1) {
					System.out.println("Error: can't clean directories. try again.");
					e1.printStackTrace();
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
