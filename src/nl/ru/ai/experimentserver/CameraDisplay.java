package nl.ru.ai.experimentserver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class for the camera display
 * 
 * @author Tessa Beinema
 *
 */
public class CameraDisplay extends Thread {


	public static final int CAMERA_VIEW_SIZE = 500;
	private VideoCapture capture1;
	private VideoCapture capture2;
	private int FRAMEWIDTH = 1280;
	private int FRAMEHEIGHT = 1024;
	private String path;
	private boolean isRecording=false;
	int player1x,player1y,player2x,player2y;

	/**
	 * Constructor for a new camera display (loads the opencv library)
	 */
	public CameraDisplay(String path, int player1x, int player1y, int player2x, int player2y) {
		System.loadLibrary("opencv_java2413");
		this.path = path;
		this.player1x=player1x;
		this.player1y=player1y;
		this.player2x=player2x;
		this.player2y=player2y;
	}

	/**
	 * Method that creates the display frames with their contents.
	 */
	public void run() {
		setPriority(Thread.MAX_PRIORITY);

		// Display frame 1
		JFrame frame1 = new JFrame("Camera 1");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(this.FRAMEWIDTH, this.FRAMEHEIGHT);
		frame1.setLocation(player1x-CAMERA_VIEW_SIZE,player1y);

		// Display frame 2
		JFrame frame2 = new JFrame("Camera 2");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(this.FRAMEWIDTH, this.FRAMEHEIGHT);
		frame1.setLocation(player2x- CAMERA_VIEW_SIZE,player2y);

		// Display panels
		CameraPanel cameraPanel1 = new CameraPanel();
		frame1.setContentPane(cameraPanel1);
		frame1.setVisible(true);

		CameraPanel cameraPanel2 = new CameraPanel();
		frame2.setContentPane(cameraPanel2);
		frame2.setVisible(true);

		StartCameraMessageFrame startCameraMessageFrame1=
				new StartCameraMessageFrame(player1x+50,player1y+200);
		StartCameraMessageFrame startCameraMessageFrame2=
				new StartCameraMessageFrame(player2x+50,player2y+200);

		while (!startCameraMessageFrame1.clicked||!startCameraMessageFrame2.clicked){
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
startCameraMessageFrame1.dispose();
startCameraMessageFrame2.dispose();
		System.out.println("Starting capture");
        startRecording();
		// Display the webcam streams
		this.displayWebcamStream(cameraPanel1, cameraPanel2);
	}
	public void startRecording(){
		isRecording=true;
	}
	public void stopRecording(){
		isRecording=false;
	}

	/**
	 * Method that captures the webcam stream and displays it with faces
	 * detected.
	 * 
	 * @param frame;
	 *            The main frame.
	 * @param cameraPanel;
	 *            Panel that contains the camera images
	 * @param faceDetector;
	 *            Classifier for face dectection.
	 */
	public void displayWebcamStream(CameraPanel cameraPanel1, CameraPanel cameraPanel2) {
		Mat webcamImage1 = new Mat();
		Mat webcamImage2 = new Mat();
		System.out.println("Opening streams...");
		System.out.println("Videocapture 1 starting..");
		this.capture1 = new VideoCapture(1);
		this.capture1.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, CAMERA_VIEW_SIZE);// this.FRAMEWIDTH);
		this.capture1.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_VIEW_SIZE);// this.FRAMEHEIGHT);
		System.out.println("Videocapture 2 starting..");
		this.capture2 = new VideoCapture(0);
		this.capture2.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, CAMERA_VIEW_SIZE);// this.FRAMEWIDTH);
		this.capture2.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_VIEW_SIZE);// this.FRAMEHEIGHT);
		System.out.println("Streams opened.");

		Date date;
		// If a webcam has been started..
		if (this.capture1.isOpened() && this.capture2.isOpened()) {
			while (true) {
				this.capture1.read(webcamImage1);
				this.capture2.read(webcamImage2);

				// If images have been read from the webcam
				if (!webcamImage1.empty() && !webcamImage2.empty()) {
					// Display on camera panel
					cameraPanel1.MatToBufferedImage(webcamImage1);
					cameraPanel1.repaint();

					cameraPanel2.MatToBufferedImage(webcamImage2);
					cameraPanel2.repaint();

					if(isRecording) {

						date = new Date();
						try {
							ImageIO.write(cameraPanel1.getBufferedImage(), "BMP", new File(path + "/cam1/" + date.getHours()
									+ "-" + date.getMinutes() + "-" + date.getSeconds() + "-" + date.getTime() + ".bmp"));
							ImageIO.write(cameraPanel2.getBufferedImage(), "BMP", new File(path + "/cam2/" + date.getHours()
									+ "-" + date.getMinutes() + "-" + date.getSeconds() + "-" + date.getTime() + ".bmp"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("ERROR: No webcam input available.");
					break;
				}
			}
		} else {
			System.out.println("ERROR: Failed to open one of the webcams.");
		}
		return;
	}


}
