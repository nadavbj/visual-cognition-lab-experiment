package nl.ru.ai.experimentserver;
import javax.swing.JOptionPane;

public class Main {

  /**
   * Main method for the experiment program
   * @param args
   * @author Tessa Beinema
   */
  public static void main(String[] args) {
	  String property = System.getProperty("java.library.path");
	  System.out.println(property);


	  if (args.length != 1){
          System.err.println("ERROR: please insert participants names: name1_name2");
          System.exit(1);
      }
      String names = args[0];
      String results_path = "Results/";
      String path = results_path + names;

	  GameModel model=setUpGame(path, names);
	  setUpCamera(path,model);
  }
  
  public static GameModel setUpGame(String path, String names) {
	  GameMain game = new GameMain(path, names);
	  return game.model;
  }
  
  public static void setUpCamera(String path,GameModel model) {
	  CameraDisplay camera = new CameraDisplay(path);
	  camera.start();
	  model.addObserver(camera);
  }
}
