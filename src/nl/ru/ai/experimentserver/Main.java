package nl.ru.ai.experimentserver;
import javax.swing.JOptionPane;

public class Main {

  /**
   * Main method for the experiment program
   * @param args name, path, player1 x&y, player2 x&y
   * @author Tessa Beinema
   */
  public static void main(String[] args) {
	  String property = System.getProperty("java.library.path");
	  System.out.println(property);


	  if (args.length != 6){
          System.err.println("ERROR: please insert participants names: name1_name2, results folder directory, player 1 x & y and player2 x & y");
          System.exit(1);
      }
      String names = args[0];
      String results_path = args[1];
      String path = results_path +"\\"+ names;
      int player1x=Integer.parseInt(args[2]),player1y=Integer.parseInt(args[3]),player2x=Integer.parseInt(args[4]),player2y=Integer.parseInt(args[5]);

	  GameModel model=setUpGame(path, names,player1x,player1y,player2x,player2y);
	  setUpCamera(path,model,player1x,player1y,player2x,player2y);
  }
  
  public static GameModel setUpGame(String path, String names, int player1x, int player1y, int player2x, int player2y) {
	  GameMain game = new GameMain(path, names,player1x,player1y,player2x,player2y);
	  return game.model;
  }
  
  public static void setUpCamera(String path, GameModel model, int player1x, int player1y, int player2x, int player2y) {
	  CameraDisplay camera = new CameraDisplay(path,player1x,player1y,player2x,player2y);
	  camera.start();
	  model.addObserver(camera);
  }
}
