package nl.ru.ai.experimentserver;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;


/**
 * Class for the camera display
 *
 * @author Tessa Beinema
 *
 */
public class CameraDisplay extends Thread {


	public static final int CAMERA_VIEW_SIZE = 500;
	private static final int FRAMES_PER_SECOND = 30;// TODO: check again frames rate
	private VideoCapture capture1;
	private VideoCapture capture2;
	private IMediaWriter videoWriter1,videoWriter2;
	private int FRAMEWIDTH = 1280;
	private int FRAMEHEIGHT = 1024;
	private String path;
	private boolean isRecording=false;
	private boolean isDisplaying=false;
	int player1x,player1y,player2x,player2y;
	long startTime;

	/**
	 * Constructor for a new camera display (loads the opencv library)
	 */
	public CameraDisplay(String path, int player1x, int player1y, int player2x, int player2y) {
		System.loadLibrary("opencv_java341");
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
		frame1.setLocation(player1x-CAMERA_VIEW_SIZE-200,player1y);
		frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Display frame 2
		JFrame frame2 = new JFrame("Camera 2");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(this.FRAMEWIDTH, this.FRAMEHEIGHT);
		frame1.setLocation(player2x- CAMERA_VIEW_SIZE-200,player2y);

		// Display panels
		CameraPanel cameraPanel1 = new CameraPanel();
		frame1.setContentPane(cameraPanel1);
		frame1.setVisible(true);

		CameraPanel cameraPanel2 = new CameraPanel();
		frame2.setContentPane(cameraPanel2);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
		// Display the webcam streams
		this.startWebcamStream(cameraPanel1, cameraPanel2);
		startRecording();
		for(int secondsLeft=3;secondsLeft>=1;secondsLeft--){
			String messge = "המשחק יתחיל בעוד " + secondsLeft + "שניות";
			startCameraMessageFrame1.showMessage(messge);
			startCameraMessageFrame2.showMessage(messge);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		startCameraMessageFrame1.dispose();
		startCameraMessageFrame2.dispose();
		startDisplaying();
		System.out.println("Starting capture");
	}
	public void startRecording(){
		isRecording=true;
		startTime = System.nanoTime();
			}
	public void stopRecording(){
		videoWriter1.close();
		videoWriter2.close();
		isRecording=false;
	}
	public void startDisplaying(){
		isDisplaying=true;
	}
	public void stopDisplaying(){
		isDisplaying=false;
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
	public void startWebcamStream(CameraPanel cameraPanel1, CameraPanel cameraPanel2) {
		new Thread(()->{
			setPriority(Thread.MAX_PRIORITY);
			Mat webcamImage1 = new Mat();
			Mat webcamImage2 = new Mat();
			System.out.println("Opening streams...");
			System.out.println("Videocapture 1 starting..");
			this.capture1 = new VideoCapture(1);
			this.capture1.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, CAMERA_VIEW_SIZE);// this.FRAMEWIDTH);
			this.capture1.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_VIEW_SIZE);// this.FRAMEHEIGHT);
			videoWriter1 =ToolFactory.makeWriter(path +"/video1.mp4");
			videoWriter1.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
					CAMERA_VIEW_SIZE, CAMERA_VIEW_SIZE);
			System.out.println("Videocapture 2 starting..");
			this.capture2 = new VideoCapture(0);
			this.capture2.set(Videoio.CV_CAP_PROP_FRAME_WIDTH, CAMERA_VIEW_SIZE);// this.FRAMEWIDTH);
			this.capture2.set(Videoio.CV_CAP_PROP_FRAME_HEIGHT, CAMERA_VIEW_SIZE);// this.FRAMEHEIGHT);
			videoWriter2 = ToolFactory.makeWriter(path +"/video2.mp4");
			videoWriter2.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4,
					CAMERA_VIEW_SIZE, CAMERA_VIEW_SIZE);
			System.out.println("Streams opened.");

			Date date;
			// If a webcam has been started..
			if (this.capture1.isOpened() && this.capture2.isOpened()) {
				while (true) {
					this.capture1.read(webcamImage1);
					this.capture2.read(webcamImage2);

					// If images have been read from the webcam
					if (!webcamImage1.empty() && !webcamImage2.empty()) {
						cameraPanel1.MatToBufferedImage(webcamImage1);
						cameraPanel2.MatToBufferedImage(webcamImage2);

						if(isDisplaying) {
							// Display on camera panel
							cameraPanel1.repaint();

							cameraPanel2.repaint();
						}
						if(isRecording) {
if(!videoWriter1.isOpen()){
	videoWriter1.open();
	videoWriter2.open();
}
							date = new Date();
							try {
								videoWriter1.encodeVideo(0, cameraPanel1.getBufferedImage(), System.nanoTime() - startTime,
										TimeUnit.NANOSECONDS);
								videoWriter2.encodeVideo(0, cameraPanel2.getBufferedImage(), System.nanoTime() - startTime,
										TimeUnit.NANOSECONDS);
								ImageIO.write(cameraPanel1.getBufferedImage(), "JPEG", new File(path + "/cam1/" + date.getHours()
										+ "-" + date.getMinutes() + "-" + date.getSeconds() + "-" + date.getTime() + ".jpeg"));
								ImageIO.write(cameraPanel2.getBufferedImage(), "JPEG", new File(path + "/cam2/" + date.getHours()
										+ "-" + date.getMinutes() + "-" + date.getSeconds() + "-" + date.getTime() + ".jpeg"));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} else {
						System.out.println("ERROR: No webcam input available.");
						break;
					}
					try {
						Thread.currentThread().sleep(33);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("ERROR: Failed to open one of the webcams.");
			}
			return;
		}).start();
	}


}
