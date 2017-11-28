package nl.ru.ai.experimentserver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 * Class for the camera display
 * @author Tessa Beinema
 *
 */
public class CameraDisplay extends Thread {
	
	private VideoCapture capture1;
	private VideoCapture capture2;
	private int FRAMEWIDTH = 640;
	private int FRAMEHEIGHT = 480;
	private String path;

	/**
	 * Constructor for a new camera display (loads the opencv library)
	 */
	public CameraDisplay (String path) {
		System.loadLibrary("opencv_java2413");
		this.path = path;
		
	}
	
	/**
	 * Method that creates the display frames with their contents.
	 */
	public void run() {
		//Display frame 1
		JFrame frame1 = new JFrame ("Camera 1");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(this.FRAMEWIDTH, this.FRAMEHEIGHT);
		
		//Display frame 2
		JFrame frame2 = new JFrame ("Camera 2");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(this.FRAMEWIDTH, this.FRAMEHEIGHT);
		frame2.setLocation(0,450);

		//Display panels
		CameraPanel cameraPanel1 = new CameraPanel();
		frame1.setContentPane(cameraPanel1);
		frame1.setVisible(true);
		
		CameraPanel cameraPanel2 = new CameraPanel();
		frame2.setContentPane(cameraPanel2);
		frame2.setVisible(true);
		
		System.out.println("Starting capture");
		//Display the webcam streams
		this.displayWebcamStream(cameraPanel1, cameraPanel2);
	}
	
	/**
	 * Method that captures the webcam stream and displays it with faces detected.
	 * @param frame; The main frame.
	 * @param cameraPanel; Panel that contains the camera images
	 * @param faceDetector; Classifier for face dectection.
	 */
	public void displayWebcamStream(CameraPanel cameraPanel1, CameraPanel cameraPanel2) {
		Mat webcamImage1 = new Mat();
		Mat webcamImage2 = new Mat();
		System.out.println("Opening streams...");
		System.out.println("Videocapture 1 starting..");
		this.capture1 = new VideoCapture(1);
		this.capture1.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, this.FRAMEWIDTH);
		this.capture1.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, this.FRAMEHEIGHT);
		System.out.println("Videocapture 2 starting..");
		this.capture2 = new VideoCapture(0);
		this.capture2.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, this.FRAMEWIDTH);
		this.capture2.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, this.FRAMEHEIGHT);
		System.out.println("Streams opened.");


		Date date;		
		//If a webcam has been started..
		if (this.capture1.isOpened() && this.capture2.isOpened()) {
			while (true) {
				this.capture1.read(webcamImage1);
				this.capture2.read(webcamImage2);
				
				//If images have been read from the webcam 
				if (!webcamImage1.empty() && !webcamImage2.empty()) {
					//Display on camera panel
					cameraPanel1.MatToBufferedImage(webcamImage1);
					cameraPanel1.repaint();
					
					cameraPanel2.MatToBufferedImage(webcamImage2);
					cameraPanel2.repaint();
					
					date = new Date();
					try 
					{
						ImageIO.write(cameraPanel1.getBufferedImage(), "BMP", new File(path + "/cam1/" + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + "-" + date.getTime() +  ".bmp"));
						ImageIO.write(cameraPanel2.getBufferedImage(), "BMP", new File(path + "/cam2/" + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + "-" + date.getTime() +  ".bmp"));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				else {
					System.out.println ("ERROR: No webcam input available.");
					break;
				}
			}
		}
		else {
			System.out.println("ERROR: Failed to open one of the webcams.");
		}
		return;
	}
	

}
