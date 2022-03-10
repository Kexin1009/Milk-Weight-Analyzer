/**
 * Main.java created by ateam66 - Milk Weight project GUI This displays a user interface of the milk
 * project that could analyze milk data based on user input file. User can display relevant
 * statistics, add/edit/remove existing data, and save the report to an output file. 
 *
 * Authors: 1. Yumeng Bai LEC001 bai54@wisc.edu 2. Xi (Chelsea) Chen LEC002 xchen783@wisc.edu 3.
 * Ruiwen Wang LEC002 rwang436@wisc.edu 4. Yiduo Wang LEC001 ywang2292@wisc.edu 5. Kexin Chen LEC002
 * kchen264@wisc.edu
 * 
 * Date: 4/30/2020
 * 
 * Course: CS400 Semester: Spring 2020
 *
 * Other Credits: N/A
 *
 * Known Bugs: N/A
 */

package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Main - Main GUI that creates and displays a user interactive screen so users can use Milk
 * Analyzer program to analyze the data.
 * 
 * @authors Yumeng Bai, Chelsea Chen, Ruiwen Wang, Yiduo Wang, Kexin Chen
 *
 */
public class Main extends Application {

  private static final int WINDOW_WIDTH = 900; // width of the window that users see
  private static final int WINDOW_HEIGHT = 640; // height of the window that users see
  private static final String APP_TITLE = "Milk Weight Analyzer"; // title of the program
  private FileManager file; // file to read/export files 
  private CheeseFactory factory; // factory that stores all farms
  private GUI gui; // GUI that displays several screens to allow user-interaction to display data

  /**
   * Start method that displays the primary (main) stage user will see
   * 
   * @author Chelsea Chen
   * @param primary stage - first stage to be displayed
   * @throws Exception
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    BorderPane root = new BorderPane();
    HBox h1 = new HBox();
    Label welcome = new Label("Welcome to Milk Weight Analyzer!");
    welcome.setFont(new Font("Arial", 36));
    Image milk = new Image("milk1.jpg", 100, 100, false, false);
    ImageView milkImage = new ImageView(milk);
    // add welcome message and milk image in a horizontal box
    // and set to the top of the window
    h1.getChildren().addAll(welcome, milkImage);
    root.setTop(h1);

    HBox h2 = new HBox();
    VBox v1 = new VBox();
    Label description = new Label("This program aims to help localize and report on milk "
        + "that has been sold to them by their farmers, based on the input file you provide."
        + "\n\n\n");
    description.setFont(new Font("Arial", 15));
    Label fileMess = new Label("Please provide the name of the milk data file（.csv）you would"
        + " like this program to analyze: " + "\n" + "Press submit to load file");

    TextField fileInput = new TextField();
    fileInput.setPromptText("Press submit to load file");

    Button submit = new Button("Submit");
    
    // add message label and textfield to a HBox and then to a VBox with button.
    h2.getChildren().addAll(fileMess, fileInput);
    v1.getChildren().addAll(description, h2, submit);
    root.setCenter(v1);

    // displays a success or failure message to load input file
    final Label message = new Label();
    v1.getChildren().add(message);
    submit.setOnAction(e -> {
    	String fileName = fileInput.getText();
    	if (!fileName.isEmpty()) {
    		FileManager file = new FileManager(fileName);
    		boolean success = readFile(e, file);
    		if (!success) {
    	      message.setText("File is invalid! Please enter a valid file name.");
    		}
    		else {
    		  message.setText("Success! Valid file name.");
    		  gui.chooseOptions(primaryStage);
    		}
    	} else {
    		message.setText("Please enter a file name.");
    	}
    });
    
    HBox h3 = new HBox();
    Image exitImage = new Image("exit.png", 50, 50, false, false);
    Button exit = new Button("Exit", new ImageView(exitImage));
    exit.setOnAction(e -> Platform.exit());

    // Displays a help button so user can get some help if unsure
    // what to do in this program. The actual help window is in GUI.java
    Image helpImage = new Image("help.png", 50, 50, false, false);
    Button help = new Button("Help Me!", new ImageView(helpImage));
    help.setOnAction(e -> gui.help(primaryStage));
    h3.getChildren().addAll(exit, help);

    root.setBottom(h3);

    Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(mainScene);
    primaryStage.show();

  }
  
  /**
   * This loads the file from user-input file name
   * Initiates a file, a factory, displays GUI screens for this
   * factory and file and insert data from the file into the factory.
   * 
   * @param e - prev action event
   * @param file - file that will read the input file
   * @return true if data from the file are "read" and saved into the factory
   *         false otherwise.
   */
  public boolean readFile(Event e, FileManager file) {
	  this.file = file;
	  this.factory = new CheeseFactory();
	  this.gui = new GUI(this.factory, this.file);
	  return this.factory.insertData(file);
  }
  
  /**
   * Launch the program.
   * 
   * @param args - input arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
