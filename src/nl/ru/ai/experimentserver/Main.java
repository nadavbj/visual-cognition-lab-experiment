package nl.ru.ai.experimentserver;

public class Main {

	/**
	 * Main method for the experiment program
	 * 
	 * @param args
	 * @author Tessa Beinema
	 */
	public static void main(String[] args) {
		String property = System.getProperty("java.library.path");
		System.out.println(property);
		setUpGame();
		setUpCamera();
	}

	public static void setUpGame() {
		GameMain game = new GameMain();
	}

	public static void setUpCamera() {
		CameraDisplay camera = new CameraDisplay();
		camera.start();
	}
}
