/**
 * GUI.java created by ateam66 in Milk Weight project - this displays a user interface of the milk
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
public class GUI {

  private static final int WINDOW_WIDTH = 800; // width of the window that users see
  private static final int WINDOW_HEIGHT = 640; // height of the window that users see
  private static final String APP_TITLE = "Milk Weight Analyzer"; // title of the program

  private CheeseFactory factory; // factory that stores all farms
  private FileManager file; // file that manages reading and exporting of files
  private DataManager dataManager; // data analysis such as get reports and min/max/avg

  /**
   * Constructor of the class that initiates a factory and a file.
   * Creates a dataManager that handles data analysis based on the file
   * @param factory - factory of all farms
   * @param file - file to be read/exported
   */
  public GUI(CheeseFactory factory, FileManager file) {
    this.factory = factory;
    this.file = file;
    dataManager = new DataManager(factory);
  }

  /**
   * An inner class to store the a date-weight pair for table display 
   * in show stats 1 farm
   * 
   * @author Kexin Chen
   *
   */
  public class DataForTable {
    private String date; // date of the milk data for farm
    private String weight; // weight of the milk data

    /**
     * Constructor that creates data object for table
     * based on given date and weight
     * @param date
     * @param weight
     */
    public DataForTable(String date, String weight) {
      this.date = date;
      this.weight = weight;
    }

    /**
     * Getter for date
     * @return date of the data
     */
    public String getDate() {
      return this.date;
    }

    /**
     * Getter for weight
     * @return weight of the milk data
     */
    public String getWeight() {
      return this.weight;
    }
  }
  
  /**
   * DataForPercent - inner class to store the a name-percent pair for table display 
   * showing percent share for all farms
   * 
   * @author Xi(Chelsea) Chen
   *
   */
  public class DataForPercent {
	  private String name; // name of the farm
	  private String percent; // percent share of this farm

	  /**
	   * Constructor that creates a new data object for percent
	   * @param name
	   * @param percent
	   */
	  public DataForPercent(String name, String percent) {
		  this.name = name;
	      this.percent = percent;
	  }

	  /**
	   * Getter for name of the farm
	   * @return name of the farm
	   */
	  public String getName() {
		  return this.name;
	  }

	  /**
	   * Getter for percent share of the farm
	   * @return percent share of the farm
	   */
	  public String getPercent() {
		  return this.percent;
	  }
  }

  /**
   * 
   * DataForFarmReport - inner class to store the month, weight, percent pair for table 
   * displaying farm report in GUI
   * 
   * @author Xi(Chelsea) Chen
   *
   */
  	public class DataForFarmReport {
	    private String month;// name of farm
	    private String weight; // weight of data
	    private String percent; // percent share of farm

	    /**
	     * Constructor that creates a new data object for
	     * given month, weight and percent
	     * @param month
	     * @param weight
	     * @param percent
	     */
	    public DataForFarmReport(String month, String weight, String percent) {
	      this.month = month;
	      this.weight = weight;
	      this.percent = percent;
	    }

	    /**
	     * Getter for month 
	     * @return each month
	     */
	    public String getMonth() {
	      return this.month;
	    }

	    /**
	     * Getter for weight of the data
	     * @return weight of the data
	     */
	    public String getWeight() {
	      return this.weight;
	    }
	    
	    /**
	     * Getter for percent of the milk data
	     * @return percent of the data
	     */
	    public String getPercent() {
	      return this.percent;
	    }
	  }
  
  /**
   * 
   * DataForAnnualReport - inner class to store the name, weight, percent pair for table 
   * displaying annual report in GUI
   * @author Xi(Chelsea) Chen
   *
   */
  public class DataForOtherReport {
	    private String name; // name of farm
	    private String weight; // weight of data
	    private String percent; // percent share of farm

	    /**
	     * Constructor that creates a new data object for
	     * given name, weight and percent
	     * @param name
	     * @param weight
	     * @param percent
	     */
	    public DataForOtherReport(String name, String weight, String percent) {
	      this.name = name;
	      this.weight = weight;
	      this.percent = percent;
	    }

	    /**
	     * Getter for name
	     * @return name of the farm
	     */
	    public String getName() {
	      return this.name;
	    }

	    /**
	     * Getter for weight
	     * @return weight of the milk data
	     */
	    public String getWeight() {
	      return this.weight;
	    }
	    
	    /**
	     * Getter for percent
	     * @return percent share of farm
	     */
	    public String getPercent() {
	      return this.percent;
	    }
	  }

  /**
   * Displays a pop-up help message for user guidance
   * 
   * @author Chelsea Chen
   * @param primaryStage - the previous stage
   */
  public void help(Stage primaryStage) {
    VBox v1 = new VBox();
    Label helpMessage = new Label("What can this program do? " + "\n\n");
    helpMessage.setFont(new Font("Verdana", 14));
    Label helpMessage2 = new Label("After you input your file, you can choose to "
        + "display min/max/average milk weight by month for" + "\n"
        + "a specific farm and year, min/max/average milk weight "
        + "for all months for a specific month/year, " + "\n"
        + "each farm's percent of the total for all farms, and each farm's share "
        + "for a specific month or year. " + "\n" + "You can also generate farm "
        + "report, annual report, monthly report, and date range report." + "\n\n");
    Label helpMessage3 = new Label("What if I need to edit the data?" + "\n\n");
    helpMessage3.setFont(new Font("Verdana", 14));
    Label helpMessage4 = new Label("You can certainly do that! :) You may add, edit and "
        + "remove existing data for a given farm, year," + "\n" + "month, and day, after "
        + "inputing a file" + "\n\n");

    Button gotIt = new Button("Got it!");

    v1.getChildren().addAll(helpMessage, helpMessage2, helpMessage3, helpMessage4, new Label(""),
        gotIt);

    Scene newScene = new Scene(v1, 600, 250);
    final Stage popUp = new Stage();
    popUp.initModality(Modality.APPLICATION_MODAL);
    gotIt.setOnAction(e -> exitCurrScreen(popUp));
    popUp.initOwner(primaryStage);
    popUp.setScene(newScene);
    popUp.show();
  }

  /**
   * Method that exits the current screen (returns the the previous screen)
   * 
   * @author Chelsea Chen
   * @param currStage - current stage
   */
  public void exitCurrScreen(Stage currStage) {
    currStage.close();
  }

  /**
   * Displays a window to choose option to analyze data.
   * 
   * @author Yumeng Bai
   * @param primaryStage - previous stage
   */
  public void chooseOptions(Stage primaryStage) {
    // Main layout is Border Pane example (top,left,center,right,bottom)
    BorderPane root = new BorderPane();
    Label message = new Label("Choose an option below to start analyzing your" + " milk data:");
    message.setFont(new Font("Arial", 30));
    root.setTop(message);

    VBox v1 = new VBox();

    Image image1 = new Image("chart.png");
    ImageView imageView1 = new ImageView(image1);
    imageView1.setFitWidth(75);
    imageView1.setFitHeight(75);

    VBox vb1 = new VBox();
    vb1.getChildren().addAll(imageView1, new Label("Show statistics"));
    Button b1 = new Button("", vb1);

    Image image2 = new Image("piechart.png");
    ImageView imageView2 = new ImageView(image2);
    imageView2.setFitWidth(75);
    imageView2.setFitHeight(75);

    VBox vb2 = new VBox();
    vb2.getChildren().addAll(imageView2, new Label("Show % share"));
    Button b2 = new Button("", vb2);

    v1.getChildren().addAll(b1, new Label(""), new Label(""), new Label(""), new Label(""),
        new Label(""), new Label(""), b2);

    VBox v2 = new VBox();
    Image image3 = new Image("edit.png");
    ImageView imageView3 = new ImageView(image3);
    imageView3.setFitWidth(75);
    imageView3.setFitHeight(75);

    VBox vb3 = new VBox();
    vb3.getChildren().addAll(imageView3, new Label("Edit data"));
    Button b3 = new Button("", vb3);

    Image image4 = new Image("report.jpg");
    ImageView imageView4 = new ImageView(image4);
    imageView4.setFitWidth(75);
    imageView4.setFitHeight(75);

    VBox vb4 = new VBox();
    vb4.getChildren().addAll(imageView4, new Label("Get reports"));
    Button b4 = new Button("", vb4);

    v2.getChildren().addAll(b3, new Label(""), new Label(""), new Label(""), new Label(""),
        new Label(""), new Label(""), b4);

    AnchorPane ap1 = new AnchorPane();
    AnchorPane.setTopAnchor(v1, 200.0);
    AnchorPane.setLeftAnchor(v1, 200.0);
    root.setLeft(ap1);
    ap1.getChildren().addAll(v1);

    AnchorPane ap2 = new AnchorPane();
    AnchorPane.setTopAnchor(v2, 200.0);
    AnchorPane.setRightAnchor(v2, 200.0);
    root.setRight(ap2);
    ap2.getChildren().addAll(v2);


    Scene secScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    Stage secScreen = new Stage();

    b1.setOnAction(e -> oneOrMoreFarmsStats(secScreen));
    b2.setOnAction(e -> oneOrMoreFarmsPercent(secScreen));
    b3.setOnAction(e -> changeDataOptions(secScreen));
    b4.setOnAction(e -> generateReport(secScreen));

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(secScreen));

    root.setBottom(returns);
    secScreen.setScene(secScene);
    secScreen.show();
  }

  /**
   * Shows a window that let user choose one farm or all farm for display statistics.
   * 
   * @author Chelsea Chen
   * @param primaryStage - previous stage
   */
  public void oneOrMoreFarmsStats(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label message =
        new Label("Would you like to analyze statistics for a specific farm, " + "or all farms?");
    message.setFont(new Font("Arial", 20));
    VBox mess = new VBox();
    Image chart = new Image("chart.png", 75, 75, false, false);
    ImageView chartImage = new ImageView(chart);
    mess.getChildren().addAll(message, chartImage);
    root.setTop(mess);

    Image farm = new Image("farm_single.png", 75, 75, false, false);
    ImageView farmImage = new ImageView(farm);
    VBox v1 = new VBox();
    v1.getChildren().addAll(farmImage, new Label("A single farm"));
    Button b1 = new Button("", v1);

    Image manyFarms = new Image("many_farms.png", 75, 75, false, false);
    ImageView manyFarmsImage = new ImageView(manyFarms);
    VBox v2 = new VBox();
    v2.getChildren().addAll(manyFarmsImage, new Label("All farms"));
    Button b2 = new Button("", v2);

    AnchorPane ap1 = new AnchorPane();
    AnchorPane.setTopAnchor(b1, 300.0);
    AnchorPane.setLeftAnchor(b1, 200.0);
    root.setLeft(ap1);
    ap1.getChildren().addAll(b1);

    AnchorPane ap2 = new AnchorPane();
    AnchorPane.setTopAnchor(b2, 300.0);
    AnchorPane.setRightAnchor(b2, 150.0);
    root.setRight(ap2);
    ap2.getChildren().addAll(b2);

    Scene farmScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    Stage farmScreen = new Stage();

    b1.setOnAction(e -> showStatsOneFarm(farmScreen));
    b2.setOnAction(e -> chooseDatesAllFarms(farmScreen));

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(farmScreen));

    farmScreen.initModality(Modality.APPLICATION_MODAL);
    farmScreen.initOwner(primaryStage);
    farmScreen.setScene(farmScene);
    farmScreen.show();
  }

  /**
   * Shows a window that let user choose one farm or all farm for display percent share.
   * 
   * @author Chelsea Chen
   * @param primaryStage - previous stage
   */
  public void oneOrMoreFarmsPercent(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label message =
        new Label("Would you like to see percent share for a specific farm, " + "or all farms?");
    message.setFont(new Font("Arial", 20));
    VBox mess = new VBox();
    Image percent = new Image("piechart.png", 75, 75, false, false);
    ImageView percentImage = new ImageView(percent);
    mess.getChildren().addAll(message, percentImage);
    root.setTop(mess);

    Image farm = new Image("farm_single.png", 75, 75, false, false);
    ImageView farmImage = new ImageView(farm);
    VBox v1 = new VBox();
    v1.getChildren().addAll(farmImage, new Label("A single farm"));
    Button b1 = new Button("", v1);

    Image manyFarms = new Image("many_farms.png", 75, 75, false, false);
    ImageView manyFarmsImage = new ImageView(manyFarms);
    VBox v2 = new VBox();
    v2.getChildren().addAll(manyFarmsImage, new Label("All farms"));
    Button b2 = new Button("", v2);

    AnchorPane ap1 = new AnchorPane();
    AnchorPane.setTopAnchor(b1, 300.0);
    AnchorPane.setLeftAnchor(b1, 200.0);
    root.setLeft(ap1);
    ap1.getChildren().addAll(b1);

    AnchorPane ap2 = new AnchorPane();
    AnchorPane.setTopAnchor(b2, 300.0);
    AnchorPane.setRightAnchor(b2, 150.0);
    root.setRight(ap2);
    ap2.getChildren().addAll(b2);

    Scene farmScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    Stage farmScreen = new Stage();

    b1.setOnAction(e -> showPercentShareOneFarm(farmScreen));
    b2.setOnAction(e -> showPercentShareAllFarms(farmScreen));

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(farmScreen));

    farmScreen.setScene(farmScene);
    farmScreen.show();
  }

  /**
   * Shows a window that let user choose to add, edit or remove data.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void changeDataOptions(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label message = new Label("Would you like to add, edit or remove data?");
    message.setFont(new Font("Arial", 30));
    root.setTop(message);

    VBox v1 = new VBox();
    Image add = new Image("add.jpg", 75, 75, false, false);
    v1.getChildren().addAll(new ImageView(add), new Label("Add data"));
    Button b1 = new Button("", v1);

    VBox v2 = new VBox();
    Image edit = new Image("edit1.png", 75, 75, false, false);
    v2.getChildren().addAll(new ImageView(edit), new Label("Edit data"));
    Button b2 = new Button("", v2);

    VBox v3 = new VBox();
    Image remove = new Image("remove.png", 75, 75, false, false);
    v3.getChildren().addAll(new ImageView(remove), new Label("Remove data"));
    Button b3 = new Button("", v3);

    AnchorPane ap1 = new AnchorPane();
    AnchorPane.setTopAnchor(b1, 300.0);
    AnchorPane.setLeftAnchor(b1, 150.0);
    root.setLeft(ap1);
    ap1.getChildren().addAll(b1);

    AnchorPane ap2 = new AnchorPane();
    AnchorPane.setTopAnchor(b2, 300.0);
    AnchorPane.setRightAnchor(b2, 80.0);
    root.setCenter(ap2);
    ap2.getChildren().addAll(b2);

    AnchorPane ap3 = new AnchorPane();
    AnchorPane.setTopAnchor(b3, 300.0);
    AnchorPane.setRightAnchor(b3, 200.0);
    root.setRight(ap3);
    ap3.getChildren().addAll(b3);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    final Stage stage = new Stage();

    b1.setOnAction(e -> addOrEditData("add", stage));
    b2.setOnAction(e -> addOrEditData("edit", stage));
    b3.setOnAction(e -> removeData(stage));

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(stage));

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Prompts user to enter data they would like to add or edit.
   * 
   * @author Chelsea Chen
   * @param action       - whether add or edit
   * @param primaryStage - previous stage
   */
  public void addOrEditData(String action, Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label prompt =
        new Label("Please enter the milk information you would like to " + action + " below: ");
    prompt.setFont(new Font("Arial", 15));
    root.setTop(prompt);

    VBox v1 = new VBox();
    HBox h1 = new HBox();
    Label farmName = new Label("Please enter the farm id: ");
    TextField nameInput = new TextField();
    h1.getChildren().addAll(farmName, nameInput);

    HBox h2 = new HBox();
    Label date = new Label("Please select the date of the milk data: ");
    List<String> days = new ArrayList<String>();
    for (int i = 1; i <= 31; i++) {
      days.add(Integer.toString(i));
    }
    ComboBox<String> cb1 = new ComboBox<String>(FXCollections.observableArrayList(days));
    cb1.setPromptText("Day");
    
    ObservableList<String> months =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12");
    ComboBox<String> cb2 = new ComboBox<String>(months);
    cb2.setPromptText("Month");

    TextField year = new TextField();
    year.setPromptText("Enter a year");
    h2.getChildren().addAll(date, cb1, cb2, year);

    HBox h3 = new HBox();
    Label milkWeight = new Label("Please enter the new milk weight data: ");
    TextField milkInput = new TextField();
    h3.getChildren().addAll(milkWeight, milkInput);

    Button submit = new Button("Submit");

    //Displays a failure message if error encounters
    Label fail = new Label();
   
    v1.getChildren().addAll(h1, h2, h3, submit, fail);
    root.setCenter(v1);

    Scene scene = new Scene(root, 700, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String id = nameInput.getText();
    	String day = cb1.getSelectionModel().getSelectedItem();
    	String month = cb2.getSelectionModel().getSelectedItem();
    	String yr = year.getText();
    	String weight = milkInput.getText();
    	String addDate = yr + "-" + month + "-" + day;
    	if (action.equals("add")) {
    		// add data
    		if (factory.insertSingleData(id, addDate, weight)) {
    			fail.setText("");
    			// displays success message
    			success(stage);
    		}
    		else {
    			fail.setText("Error encountered! Unable to insert this data.");
    		}
    	}
    	else {
    		// edit data
    		if (factory.editSingleData(id, addDate, weight)) {
    			fail.setText("");
    			// displays success message
    			success(stage);
    		}
    		else {
    			fail.setText("Error encountered! Unable to edit this data.");
    		}
    	}
    });

    // creates return button
    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(stage));

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Prompts user to enter information to remove a specific milk data
   * 
   * @author Chelsea Chen
   * @param primaryStage - previous stage
   */
  public void removeData(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label message =
        new Label("Please enter the milk information you would like to " + "remove below: ");
    message.setFont(new Font("Arial", 15));
    root.setTop(message);

    VBox v1 = new VBox();
    HBox h1 = new HBox();
    Label farmName = new Label("Please enter the farm id: ");
    TextField nameInput = new TextField();
    h1.getChildren().addAll(farmName, nameInput);

    HBox h2 = new HBox();
    Label date = new Label("Please select the date of the milk data: ");
    List<String> days = new ArrayList<String>();
    for (int i = 1; i <= 31; i++) {
      days.add(Integer.toString(i));
    }
    ComboBox<String> cb1 = new ComboBox<String>(FXCollections.observableArrayList(days));
    cb1.setPromptText("Day");
    
    ObservableList<String> months =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12");
    ComboBox<String> cb2 = new ComboBox<String>(months);
    cb2.setPromptText("Month");

    TextField year = new TextField();
    year.setPromptText("Enter a year");
    h2.getChildren().addAll(date, cb1, cb2, year);

    Button submit = new Button("Submit");

    //Displays a failure message if error encounters
    Label fail = new Label();
    
    v1.getChildren().addAll(h1, h2, submit, fail);
    root.setCenter(v1);

    Scene scene = new Scene(root, 700, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String id = nameInput.getText();
    	String day = cb1.getSelectionModel().getSelectedItem();
    	String month = cb2.getSelectionModel().getSelectedItem();
    	String yr = year.getText();
    	String removeDate = yr + "-" + month + "-" + day;
    	// remove data from factory
    	if (factory.removeSingleData(id, removeDate)) {
    		fail.setText("");
    		// displays a success message
    		success(stage);
    	}
    	else {
    		fail.setText("Error encountered! Unable to remove this data.");
    	}
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(stage));

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Displays a success message for updating milk data information
   * 
   * @author Chelsea Chen
   * @param primaryStage - previous stage
   */
  public void success(Stage primaryStage) {
    BorderPane root = new BorderPane();
    root.setTop(new Label(""));

    VBox v1 = new VBox();
    Label success = new Label("Success!! The milk data information has been updated.");
    success.setFont(new Font("Arial", 30));
    Image smile = new Image("smile.jpg", 100, 100, false, false);
    v1.getChildren().addAll(success, new ImageView(smile));
    root.setCenter(v1);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    final Stage stage = new Stage();

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Displays statistics in a table for one farm for a specific date.
   * 
   * @author Kexin Chen
   * @param primaryStage - previous stage
   */
  @SuppressWarnings("unchecked")
public void showStatsOneFarm(Stage primaryStage) {
    Stage stage = new Stage();
    BorderPane root = new BorderPane();

    Label label1 = new Label("Farm");
    TextField nameInput = new TextField();
    nameInput.setPromptText("Enter a farm id");
    Label label2 = new Label("'s data on");

    // populate the list of days in a month
    List<String> dayList = new ArrayList<String>();
    for (int i = 1; i <= 31; i++) {
      dayList.add(Integer.toString(i));
    }
    ComboBox<String> days = new ComboBox<String>(FXCollections.observableArrayList(dayList));
    days.setPromptText("Day");

    ObservableList<String> monthList =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12");
    ComboBox<String> months = new ComboBox<String>(monthList);
    months.setPromptText("Month");

    TextField year_tf = new TextField();
    year_tf.setPromptText("Enter a year");
    Button submit = new Button("OK");

    // will use the farm id, year, month, day information to
    // obtain data to populate the table

    HBox hbox_top = new HBox(5);
    hbox_top.getChildren().addAll(label1, nameInput, label2, days, months, year_tf, submit);
    Label fail = new Label();
    VBox vbox = new VBox();
    vbox.getChildren().addAll(hbox_top, fail);
    root.setTop(vbox);

    @SuppressWarnings("rawtypes")
	TableView table = new TableView<>();
    TableColumn<String, Data> column1 = new TableColumn<>("Date");
    column1.setCellValueFactory(new PropertyValueFactory<>("date"));

    TableColumn<String, Data> column2 = new TableColumn<>("Weight");
    column2.setCellValueFactory(new PropertyValueFactory<>("weight"));

    table.getColumns().add(column1);
    table.getColumns().add(column2);
    table.setPlaceholder(new Label("No rows to display"));
    root.setCenter(table);

    HBox hbox_bottom = new HBox(100);
    // click button and add/edit/remove
    Button button1 = new Button("add");
    button1.setOnAction(e -> addOrEditData("add", stage));
    Button button2 = new Button("edit");
    button2.setOnAction(e -> addOrEditData("edit", stage));
    Button button3 = new Button("remove");
    button3.setOnAction(e -> removeData(stage));

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));

    hbox_bottom.getChildren().addAll(returns, button1, button2, button3);
    root.setBottom(hbox_bottom);

    submit.setOnAction(e -> {     
    	try {
        	// get farm id
    		String id = nameInput.getText();
    		// get user input year -> will throw exception if invalid
    		Integer year = Integer.parseInt(year_tf.getText());
    		// get user input month
    		Integer month = Integer.parseInt(months.getSelectionModel().getSelectedItem());
    		// get user input day
    		Integer day = Integer.parseInt(days.getSelectionModel().getSelectedItem());
    		
    		// get list of date-weight pair to fill table
    		DataForTable tableData = getTableData(e, id, year, month, day);
    		// fill the table
    		if(tableData != null) {
    			table.getItems().clear();
    			// add the current data
    			table.getItems().add(tableData);
    			// delete "failure" message
    			fail.setText("");
    		}
    		else {
    			table.getItems().clear();
    			// provide a warning message that look up failed
    			fail.setText("Failed to look up this specific farm id and weight information");
    		}
    	} catch (NumberFormatException er) {
    		fail.setText("Invalid user input date. Please try again.");
    	}
    });

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Helper method to get the row of data for a farm on one day
   * 
   * @param e - current action event
   * @param id - id of the farm
   * @param year - year to be look up
   * @param month - month to be look up
   * @param day - day to be look up
   * @return - Data to add in the table
   * @author Kexin Chen
   */
  private DataForTable getTableData(Event e, String id, int year, int month, int day) {
    Farm f = factory.getFarmFromID(id);
    if (f != null) {
    	TreeMap<Date, Integer> farmData = f.getData();
    	Set<Date> dateSet = farmData.keySet(); // all the date information this farm has

    	for (Date d : dateSet) {
    		// get the farm date information given a year and month
    		// get the weight information for that date
    		// add the date-weight pair into tableData list
    		if (d.getYear() == year && d.getMonth() == month && d.getDay() == day) {
    			String date = d.toString();
    			String weight = farmData.get(d).toString();
    			DataForTable tableRowData = new DataForTable(date, weight);
    			return tableRowData;
    		}
    	}
    }
    return null;
  }

  /**
   * Shows a window that let user choose dates to display statistics for all farms.
   * 
   * @author Yumeng Bai
   * @param primaryStage - previous stage
   */
  public void chooseDatesAllFarms(Stage primaryStage) {
    BorderPane root2_2 = new BorderPane();
    VBox v1 = new VBox();
    Label message =
        new Label("Please select the month and year you would like the program to analyze"
            + " the milk data of: ");
    message.setFont(new Font("Arial", 14));
    root2_2.setTop(message);
    HBox h1 = new HBox();
    ObservableList<String> months =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12");
    ComboBox<String> cb2_2_1 = new ComboBox<String>(months);
    cb2_2_1.setPromptText("Month");
    
    Button b1 = new Button("Submit");
    
    TextField year = new TextField();
    year.setPromptText("Enter a year");
    h1.getChildren().addAll(cb2_2_1, year, b1);

	Label error = new Label();
	v1.getChildren().addAll(h1, error);

    AnchorPane ap2_2_1 = new AnchorPane();
    AnchorPane.setTopAnchor(v1, 50.0);
    AnchorPane.setLeftAnchor(v1, 50.0);
    ap2_2_1.getChildren().addAll(v1);

    root2_2.setCenter(ap2_2_1);

    Scene scene2_2 = new Scene(root2_2, 600, 300);
    final Stage stageB = new Stage();
    stageB.initModality(Modality.APPLICATION_MODAL);
    stageB.initOwner(primaryStage);
    stageB.setScene(scene2_2);
    stageB.show();

    b1.setOnAction(e -> {
    	try {
    		Integer month = Integer.parseInt(cb2_2_1.getSelectionModel().getSelectedItem());
    		// get user year -> will throw exception if cannot be convert into Integer
    		Integer yr = Integer.parseInt(year.getText());
    		displayStatisticsAllFarms(stageB, month, yr);
    	} catch (NumberFormatException er) {
        	error.setText("Invalid user date input. Please try again.");
        }
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root2_2.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(stageB));
  }

  /**
   * Displays statistics (Max, Min, Average) for all farms
   * 
   * @author Yumeng Bai
   * @param previousStage
   */
  public void displayStatisticsAllFarms(Stage previousStage, Integer month, Integer year) {
    BorderPane root = new BorderPane();
    Label message = new Label();
	root.setTop(message);
    
    Date d = new Date(year, month, -1);
    application.Data maxData = dataManager.getMonthlyMax(d);
    application.Data minData = dataManager.getMonthlyMin(d);
    application.Data avgData = dataManager.getMonthlyAverage(d);
    
    Integer max = 0;
    Integer min = 0;
    Double average = 0.0;
    
    try {
    	// obtain max, min and average from data manager
    	// can throw exception if max/min/average from data set is
    	// not valid (i.e. non-existent)
    	max = Integer.parseInt(maxData.getWeight());
    	min = Integer.parseInt(minData.getWeight());
    	average = Double.parseDouble(avgData.getWeight());
    
    	String yr = year.toString();
    	String mon = month.toString();
    	message.setText("Here's the statistic for " + yr + "-" 
    			+ mon + " for all farms: ");
    	message.setFont(new Font("Arial", 20));
    
    	GridPane gPane = new GridPane();
    
    	gPane.add(new Label("           "), 4, 0);
    	gPane.add(new Label("           "), 8, 0);
    	gPane.add(new Label("       MAX:"), 4, 1);
    	gPane.add(new Label("       " + max.toString()), 8, 1);
    	gPane.add(new Label("           "), 4, 2);
    	gPane.add(new Label("           "), 8, 1);
    	gPane.add(new Label("           "), 4, 3);
    	gPane.add(new Label("           "), 8, 3);
    	gPane.add(new Label("           "), 4, 4);
    	gPane.add(new Label("       MIN:"), 4, 5);
    	gPane.add(new Label("       " + min.toString()), 8, 5);
    	gPane.add(new Label("           "), 4, 6);
    	gPane.add(new Label("           "), 4, 7);
    	gPane.add(new Label("           "), 8, 7);
    	gPane.add(new Label("           "), 4, 8);
    	gPane.add(new Label("       AVG:"), 4, 9);
    	gPane.add(new Label("       " + average.toString()), 8, 9);
    	gPane.add(new Label("           "), 4, 10);
    	root.setCenter(gPane);
    	
    } catch (NumberFormatException e) {
    	message.setText("Invalid user date input -> could not compute statistics. "
    			+ "Please double check.");
    	message.setFont(new Font("Arial", 20));
    }

    Scene scene2_2 = new Scene(root, 600, 300);
    final Stage stageB_A = new Stage();
    stageB_A.initModality(Modality.APPLICATION_MODAL);
    stageB_A.initOwner(previousStage);
    stageB_A.setScene(scene2_2);
    stageB_A.show();

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    root.setBottom(returns);
    returns.setOnAction(e -> exitCurrScreen(stageB_A));

  }

  /**
   * Shows a window that let user input farm id to display percent share for one farm.
   * 
   * @author Chelsea Chen
   * @param primaryStage - previous stage
   */
  public void showPercentShareOneFarm(Stage primaryStage) {
    BorderPane root = new BorderPane();
    HBox h1 = new HBox();
    VBox v1 = new VBox();
    Label message = new Label("Please enter the farm id: ");
    TextField nameInput = new TextField();
    
    Button submit = new Button("submit");
    
    ObservableList<String> months =
            FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                    "7", "8", "9", "10", "11", "12");
    ComboBox<String> mon = new ComboBox<String>(months);
    mon.setPromptText("Month");
        
        
    TextField year = new TextField();
    year.setPromptText("Enter a year");
    
    h1.getChildren().addAll(nameInput, mon, year);
    v1.getChildren().addAll(message, h1, submit);
    root.setTop(v1);

    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String id = nameInput.getText();
    	String yr = year.getText();
    	String month = mon.getSelectionModel().getSelectedItem();
    	showPercentOneFarmHelper(stage, id, yr, month);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();

  }

  /**
   * Displays percent share for one farm.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void showPercentOneFarmHelper(Stage primaryStage, String id, String yr, String month) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    root.setCenter(message);
    float perc;
    try {
    	// will cause a NumberFormatException if year is invalid (non-numerical)
    	int year = Integer.parseInt(yr);
    	int mon = Integer.parseInt(month);
    
    	Farm farm = factory.getFarmFromID(id);
    	if (farm != null) {
    		int curWeight = farm.getTotalWeightOfMonth(year, mon);
    		int totWeight = factory.getTotalWeightOfMonth(year, mon);
    		// calculate percentage
    		perc =  (float) curWeight / totWeight * 100;
    		if (curWeight != 0 && totWeight != 0) {
    			message.setText("Here's the percent share by farm " + id 
    				+ " for the given year " + yr + " and month " + month + ": "
    				+ Float.toString(perc) + "%");
    		}
    		//current weight and total weight is 0 -> data does not exist
    		else {
    			message.setText("User-input year and month is non-existent in data set."
    					+ " Percentage cannot be computed.");
    		}
    	}
    	// null farm -> farm does not exist, displays error messages
    	else {
    		message.setText("Invalid Farm ID! Please double check and try again.");
    	}
    } catch (NumberFormatException e) {
    	message.setText("Invalid user input. Please try again.");
    }
    
    Scene scene = new Scene(root, 600, 200);
    final Stage stage = new Stage();

    Button gotIt = new Button("Got it!");
    gotIt.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(gotIt);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Displays percent share for all farms with a pie chart
   * 
   * @authors Yumeng Bai & Chelsea Chen
   * @param primaryStage - prev stage
   */
  @SuppressWarnings("unchecked")
  public void showPercentShareAllFarms(Stage primaryStage) {
	  BorderPane root2_3 = new BorderPane();
	  Label percentShare = new Label("Percent share of each farm");
	  percentShare.setFont(new Font("Arial", 25));
	  root2_3.setTop(percentShare);
	  HBox h1 = new HBox();
    
	  @SuppressWarnings("rawtypes")
	  TableView table = new TableView<>();
	  TableColumn<String, Data> column1 = new TableColumn<>("Farm Name");
	  column1.setCellValueFactory(new PropertyValueFactory<>("name"));

	  TableColumn<String, Data> column2 = new TableColumn<>("Percentage");
	  column2.setCellValueFactory(new PropertyValueFactory<>("percent"));

	  table.getColumns().add(column1);
	  table.getColumns().add(column2);
	  table.setPlaceholder(new Label("No rows to display"));
    
	  int totWeight = factory.getTotalWeight();
	  TreeMap<String, Farm> data = factory.getDataFromFarms();
	  ObservableList<List<String>> tableData = FXCollections.observableArrayList();
	  for (Farm f: data.values()) {
		  int weight = f.getTotalWeight();
		  // calculate percent
		  float percent = (float) weight / totWeight * 100;
		  String perc = Float.toString(percent);
		  String id = f.getId();
		  DataForPercent d = new DataForPercent(id, perc);
		  // populate the table with percent share
		  table.getItems().add(d);
		  
		  // add data to an ArrayList so can be added to pie chart data
		  // and displayed in a pie chart
		  List<String> eachData = new ArrayList<String>();
		  eachData.add(id);
		  eachData.add(perc);
		  tableData.add(eachData);
	  }
	  // generate a pie chart with each farm percent share
	  ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
	  for (List<String> rowData : tableData) {
		  String name = rowData.get(0);
		  double percent = Double.parseDouble(rowData.get(1));
		  pieChartData.add(new PieChart.Data(name, percent));
	  }
	  final PieChart pieChart = new PieChart(pieChartData);
	  pieChart.setTitle("Percent Share for All Farms");

	  h1.getChildren().addAll(table, pieChart);
	  root2_3.setCenter(h1);

	  Scene scene2_3 = new Scene(root2_3, WINDOW_WIDTH, WINDOW_HEIGHT);
	  final Stage stageC = new Stage();
	  stageC.initModality(Modality.APPLICATION_MODAL);
	  stageC.initOwner(primaryStage);
	  stageC.setScene(scene2_3);
	  stageC.show();

	  Image returnToPrev = new Image("return.png", 30, 30, false, false);
	  ImageView returnPrev = new ImageView(returnToPrev);
	  Button returns = new Button("return to previous screen", returnPrev);
	  root2_3.setBottom(returns);
	  returns.setOnAction(e -> exitCurrScreen(stageC));
  }

  /**
   * Generate reports. Shows a window that let user choose which report (farm report, annual report,
   * monthly report, date_range report) to generate.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void generateReport(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Label message = new Label("Please select a type of report to generate: ");
    message.setFont(new Font("Arial", 30));
    root.setTop(message);

    VBox v1 = new VBox();

    Image farm = new Image("farm_single.png", 75, 75, false, false);
    VBox vb1 = new VBox();
    vb1.getChildren().addAll(new ImageView(farm), new Label("Farm report"));
    Button farmReport = new Button("", vb1);

    Image annual = new Image("annual.png", 75, 75, false, false);
    VBox vb2 = new VBox();
    vb2.getChildren().addAll(new ImageView(annual), new Label("Annual report"));
    Button annualReport = new Button("", vb2);

    v1.getChildren().addAll(farmReport, new Label(""), new Label(""), new Label(""), new Label(""),
        new Label(""), new Label(""), annualReport);

    VBox v2 = new VBox();

    Image month = new Image("month.png", 75, 75, false, false);
    VBox vb3 = new VBox();
    vb3.getChildren().addAll(new ImageView(month), new Label("Monthly report"));
    Button monthlyReport = new Button("", vb3);

    Image dateRange = new Image("date_range.png", 75, 75, false, false);
    VBox vb4 = new VBox();
    vb4.getChildren().addAll(new ImageView(dateRange), new Label("Date-Range report"));
    Button dateRangeReport = new Button("", vb4);

    v2.getChildren().addAll(monthlyReport, new Label(""), new Label(""), new Label(""),
        new Label(""), new Label(""), new Label(""), dateRangeReport);

    AnchorPane ap1 = new AnchorPane();
    AnchorPane.setTopAnchor(v1, 200.0);
    AnchorPane.setLeftAnchor(v1, 200.0);
    root.setLeft(ap1);
    ap1.getChildren().addAll(v1);

    AnchorPane ap2 = new AnchorPane();
    AnchorPane.setTopAnchor(v2, 200.0);
    AnchorPane.setRightAnchor(v2, 200.0);
    root.setRight(ap2);
    ap2.getChildren().addAll(v2);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    final Stage stage = new Stage();

    farmReport.setOnAction(e -> farmReportHelper(stage));
    annualReport.setOnAction(e -> annualReportHelper(stage));
    monthlyReport.setOnAction(e -> monthlyReportHelper(stage));
    dateRangeReport.setOnAction(e -> dateRangeReportHelper(stage));

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Shows a window that let user enter farm id and year to generate farm report.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void farmReportHelper(Stage primaryStage) {
    BorderPane root = new BorderPane();
    HBox h1 = new HBox();
    HBox h2 = new HBox();
    VBox v1 = new VBox();
    Label name = new Label("Please enter a valid farm id: ");
    TextField nameInput = new TextField();
    h1.getChildren().addAll(name, nameInput);
    
    Label year = new Label("Please enter a valid year: ");
    TextField yearInput = new TextField();
    h2.getChildren().addAll(year, yearInput);
    
    Button submit = new Button("submit");
    v1.getChildren().addAll(h1, h2, submit);
    
    root.setTop(v1);

    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String farmName = nameInput.getText();
    	String yr = yearInput.getText();
    	generateFarmReport(stage, farmName, yr);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Generates farm report that shows total milk weight and percent share in a table.
   * 
   * FARM REPORT: 
   * Prompt user for a farm id and year (or use all available data)
   * Then, display the total milk weight and percent of the total of all farm for each month.
   * Sort, the list by month number 1-12, show total weight, then that farm's percent of 
   * the total milk received for each month.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  @SuppressWarnings("unchecked")
public void generateFarmReport(Stage primaryStage, String id, String yr) {
    BorderPane root = new BorderPane();
	Label message = new Label();
    message.setFont(new Font("Arial", 25));
    root.setTop(message);
    
    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
	final Stage stage = new Stage();
	 
    
    try {
    	message.setText(id + "'s statistics in year " + yr + ": ");
    	@SuppressWarnings("rawtypes")
		TableView tableView = new TableView();

    	TableColumn<String, String> column1 = new TableColumn<>("Month");
    	column1.setCellValueFactory(new PropertyValueFactory<>("month"));
    	column1.setMinWidth(200);

    	TableColumn<String, String> column2 = new TableColumn<>("Total Milk Weight");
    	column2.setCellValueFactory(new PropertyValueFactory<>("weight"));
    	column2.setMinWidth(200);

    	TableColumn<String, String> column3 = new TableColumn<>("Percent total of All Farms");
    	column3.setCellValueFactory(new PropertyValueFactory<>("percent"));
    	column3.setMinWidth(200);

    	tableView.getColumns().add(column1);
    	tableView.getColumns().add(column2);
    	tableView.getColumns().add(column3);
    	tableView.setPlaceholder(new Label("No rows to display"));
    	
    	VBox vbox = new VBox(tableView);
    	root.setCenter(vbox);
    	
    	// invalid user input year will cause a number format exception
    	Integer year = Integer.parseInt(yr); 
  
    	// fill the table with report data: farm id, weight and percent 
    	// share for this year for this particular farm
    	String[][] farm = dataManager.farmReport(id, year);
    	for (int i = 1; i < farm.length; i++) {
    		String mon = farm[i][0];
    		String weight = farm[i][1];
    		String percent = farm[i][2];
    		DataForFarmReport d = new DataForFarmReport(mon, weight, percent);
    		tableView.getItems().add(d);
    	}
    	
    	// create export and return buttons
    	HBox hbox = new HBox(400);
    	Image exportImage = new Image("export.png", 30, 30, false, false);
    	ImageView exportView = new ImageView(exportImage);
    	Button export = new Button("export to file", exportView);
    	export.setOnAction(e -> outputFarm(stage, id, year));

    	Image returnToPrev = new Image("return.png", 30, 30, false, false);
    	ImageView returnPrev = new ImageView(returnToPrev);
    	Button returns = new Button("return to previous screen", returnPrev);
    	returns.setOnAction(e -> exitCurrScreen(stage));
    	hbox.getChildren().addAll(returns, export);
    	root.setBottom(hbox);
    	 
    } catch (NumberFormatException e) {
    	message.setText("Invalid user input date! Please try again.");
    } catch (Exception e) {
    	message.setText("Could not find data for this farm in this year. Please try again");
    }
    
    stage.initModality(Modality.APPLICATION_MODAL);
	stage.initOwner(primaryStage);
	stage.setScene(scene);
	stage.show();
  }

  /**
   * Let user enter a year to generate annual report.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void annualReportHelper(Stage primaryStage) {
    BorderPane root = new BorderPane();
    VBox v1 = new VBox();
    Label message = new Label("Please enter a valid year: ");
    TextField yearInput = new TextField();
    Button submit = new Button("submit");
    v1.getChildren().addAll(message, yearInput, submit);
    root.setCenter(v1);

    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String year = yearInput.getText();
    	generateAnnualReport(stage, year);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Generates annual report that shows total milk weight and percent share for all farms in a
   * specific year in a table.
   * 
   * ANNUAL REPORT:
   * Ask for year.
   * Then display list of total weight and percent of total weight of 
   * all farms by farm for the year.
   * Sort by Farm ID, or you can allow the user to select display ascending 
   * or descending by weight.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  @SuppressWarnings("unchecked")
public void generateAnnualReport(Stage primaryStage, String year) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font("Arial", 25));
    
    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    final Stage stage = new Stage();
    
    try {
    	message.setText("All farm's statistics in year " + year);
    	root.setTop(message);
    	@SuppressWarnings("rawtypes")
		TableView tableView = new TableView();

    	TableColumn<String, String> column1 = new TableColumn<>("Farm ID");
    	column1.setCellValueFactory(new PropertyValueFactory<>("name"));
    	column1.setMinWidth(200);

    	TableColumn<String, String> column2 = new TableColumn<>("Total Milk Weight");
    	column2.setCellValueFactory(new PropertyValueFactory<>("weight"));
    	column2.setMinWidth(200);

    	TableColumn<String, String> column3 = new TableColumn<>("Percent total of All Farms");
    	column3.setCellValueFactory(new PropertyValueFactory<>("percent"));
    	column3.setMinWidth(200);

    	tableView.getColumns().add(column1);
    	tableView.getColumns().add(column2);
	    tableView.getColumns().add(column3);
	    tableView.setPlaceholder(new Label("No rows to display"));

	    VBox vbox = new VBox(tableView);
	    root.setCenter(vbox);
	    
	    // invalid user input year will cause a number format exception
    	Integer yr = Integer.parseInt(year);
    	
    	// fill the table with report data: farm id, weight and percent 
    	// share for this year for this particular farm
    	String[][] annual = dataManager.annualReport(yr);
    	for (int i = 1; i < annual.length; i++) {
    		String id = annual[i][0];
    		String weight = annual[i][1];
    		String percent = annual[i][2];
    		DataForOtherReport d = new DataForOtherReport(id, weight, percent);
    		tableView.getItems().add(d);
    	}	 
    	
    	// Create export and return button
        HBox hbox = new HBox(400);
        Image exportImage = new Image("export.png", 30, 30, false, false);
        ImageView exportView = new ImageView(exportImage);
        Button export = new Button("export to file", exportView);
        export.setOnAction(e -> outputAnnual(stage, yr));

        Image returnToPrev = new Image("return.png", 30, 30, false, false);
        ImageView returnPrev = new ImageView(returnToPrev);
        Button returns = new Button("return to previous screen", returnPrev);
        returns.setOnAction(e -> exitCurrScreen(stage));
        hbox.getChildren().addAll(returns, export);
        root.setBottom(hbox);
        
    } catch (NumberFormatException e) {
    	message.setText("Invalid user input date! Please try again.");
    } catch (Exception e) {
    	message.setText("Could not find data for this farm in this year. Please try again");
    }   
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
    
  }

  /**
   * Let user select a specific month and year for monthly report.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void monthlyReportHelper(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    VBox v1 = new VBox();
    Label message = new Label("Please select/enter a valid month and year: ");
    TextField yearInput = new TextField();
    yearInput.setPromptText("Please enter a valid year");

    ObservableList<String> months =
            FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                    "7", "8", "9", "10", "11", "12");
    ComboBox<String> cb = new ComboBox<String>(months);
    cb.setPromptText("Months");
    
    Button submit = new Button("submit");
    
    v1.getChildren().addAll(message, cb, yearInput, submit);
    root.setCenter(v1);
    
    submit.setOnAction(e -> {
    	String month = cb.getSelectionModel().getSelectedItem();
    	String yr = yearInput.getText();
    	generateMonthlyReport(stage, month, yr);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Generates monthly report that shows total milk weight and percent share of 
   * all farms in a specific month and year in a table.
   * 
   * MONTHLY REPORT
   * Ask for year and month.
   * Then, display a list of totals and percent of total by farm.
   * The list must be sorted by Farm ID, or you can prompt for ascending or 
   * descending by weight. 
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  @SuppressWarnings("unchecked")
public void generateMonthlyReport(Stage primaryStage, String mon, String yr) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font("Arial", 25));
    
    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    final Stage stage = new Stage();

    try {
    	message.setText("All farm's statistics in month " + mon +
    		" of " + yr + ": ");
    	root.setTop(message);
    	@SuppressWarnings("rawtypes")
		TableView tableView = new TableView();

    	TableColumn<String, String> column1 = new TableColumn<>("Farm ID");
    	column1.setCellValueFactory(new PropertyValueFactory<>("name"));
    	column1.setMinWidth(200);

    	TableColumn<String, String> column2 = new TableColumn<>("Total Milk Weight");
    	column2.setCellValueFactory(new PropertyValueFactory<>("weight"));
    	column2.setMinWidth(200);

    	TableColumn<String, String> column3 = new TableColumn<>("Percent total of All Farms");
    	column3.setCellValueFactory(new PropertyValueFactory<>("percent"));
    	column3.setMinWidth(200);

    	tableView.getColumns().add(column1);
    	tableView.getColumns().add(column2);
    	tableView.getColumns().add(column3);
    	tableView.setPlaceholder(new Label("No rows to display"));

    	VBox vbox = new VBox(tableView);
    	root.setCenter(vbox);
    	
    	// invalid user input year will cause a number format exception
    	Integer year = Integer.parseInt(yr);
    	Integer month = Integer.parseInt(mon);
    	
    	// fill the table with report data: farm id, weight and percent 
    	// share for this year for this particular farm
    	String[][] monthly = dataManager.monthlyReport(year, month);
    	for (int i = 1; i < monthly.length; i++) {
    		String id = monthly[i][0];
    		String weight = monthly[i][1];
    		String percent = monthly[i][2];
    		DataForOtherReport d = new DataForOtherReport(id, weight, percent);
    		tableView.getItems().add(d);
    	}
    	// create export and return buttons
        HBox hbox = new HBox(400);
        Image exportImage = new Image("export.png", 30, 30, false, false);
        ImageView exportView = new ImageView(exportImage);
        Button export = new Button("export to file", exportView);
        export.setOnAction(e -> outputMonthly(stage, year, month));

        Image returnToPrev = new Image("return.png", 30, 30, false, false);
        ImageView returnPrev = new ImageView(returnToPrev);
        Button returns = new Button("return to previous screen", returnPrev);
        returns.setOnAction(e -> exitCurrScreen(stage));
        hbox.getChildren().addAll(returns, export);
        root.setBottom(hbox);
        
    } catch (NumberFormatException e) {
    	message.setText("Invalid user input date! Please try again.");
    } catch (Exception e) {
    	message.setText("Could not find data for this farm in this year. Please try again");
    }
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Let user select the start and end date for date range report
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void dateRangeReportHelper(Stage primaryStage) {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    Label message = new Label("Please select the start and end date for the report: ");

    HBox h1 = new HBox(10);
    HBox h2 = new HBox(10);
    VBox v1 = new VBox(30);
    Label l1 = new Label("Start date: ");
    Label l2 = new Label("End date: ");
    
    TextField dayInput1 = new TextField();
    dayInput1.setPromptText("Day");
    
    ObservableList<String> months =
        FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12");
    ComboBox<String> cb1 = new ComboBox<String>(months);
    cb1.setPromptText("Months");
    
    TextField yearInput1 = new TextField();
    yearInput1.setPromptText("Please enter a valid year");
    
    TextField dayInput2 = new TextField();
    dayInput2.setPromptText("Day");
    
    ComboBox<String> cb2 = new ComboBox<String>(months);
    cb2.setPromptText("Months");

    TextField yearInput2 = new TextField();
    yearInput2.setPromptText("Please enter a valid year");

    h1.getChildren().addAll(l1, dayInput1, cb1, yearInput1);
    h2.getChildren().addAll(l2, dayInput2, cb2, yearInput2);
    
    Button submit = new Button("submit");
    v1.getChildren().addAll(message, h1, h2, submit);
    root.setCenter(v1);
    
    submit.setOnAction(e -> {
    	// get both start day, month, year and end day, month, year
    	String y1 = yearInput1.getText();
    	String m1 = cb1.getSelectionModel().getSelectedItem();
    	String d1 = dayInput1.getText();
    	String y2 = yearInput2.getText();
    	String m2 = cb2.getSelectionModel().getSelectedItem();
    	String d2 = dayInput2.getText();
    	generateDateRangeReport(stage, y1, m1, d1, y2, m2, d2);
    });
    
    // add a return button
    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);
    
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Generates date_range report that shows total milk weight and percent share from a 
   * specific start date to a specific end date for all farms in a table.
   * 
   * DATE RANGE REPORT
   * Prompt user for start date (year-month-day) and end month-day,
   * Then display the total milk weight per farm and the percentage of the total for 
   * each farm over that date range.
   * The list must be sorted by Farm ID, or you can prompt for ascending or descending 
   * order by weight or percentage.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  @SuppressWarnings("unchecked")
public void generateDateRangeReport(Stage primaryStage, String y1, String m1, 
		  String d1, String y2, String m2, String d2) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font("Arial", 25));
    root.setTop(message);
    
    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    final Stage stage = new Stage();

    try {
    	message.setText("All farm's statistics from " + y1 + "-" + m1 + "-" + d1 +
    			" to " + y2 + "-" + m2 + "-" + d2);
    	@SuppressWarnings("rawtypes")
		TableView tableView = new TableView<String>();

    	TableColumn<String, String> column1 = new TableColumn<>("Farm ID");
    	column1.setCellValueFactory(new PropertyValueFactory<>("name"));
    	column1.setMinWidth(200);

    	TableColumn<String, String> column2 = new TableColumn<>("Total Milk Weight");
    	column2.setCellValueFactory(new PropertyValueFactory<>("weight"));
    	column2.setMinWidth(200);

    	TableColumn<String, String> column3 = new TableColumn<>("Percent total of All Farms");
    	column3.setCellValueFactory(new PropertyValueFactory<>("percent"));
    	column3.setMinWidth(200);

    	tableView.getColumns().add(column1);
    	tableView.getColumns().add(column2);
    	tableView.getColumns().add(column3);

    	tableView.setPlaceholder(new Label("No rows to display"));

    	VBox vbox = new VBox(tableView);
    	root.setCenter(vbox);
    	
    	// invalid user input date will cause a number format exception
    	Integer year1 = Integer.parseInt(y1);
    	Integer year2 = Integer.parseInt(y2);
    	Integer month1 = Integer.parseInt(m1);
    	Integer month2 = Integer.parseInt(m2);
    	Integer day1 = Integer.parseInt(d1);
    	Integer day2 = Integer.parseInt(d2);
    	
    	Date date1 = new Date(year1, month1, day1);
    	Date date2 = new Date(year2, month2, day2);
    	
    	// fill the table with report data: farm id, weight and percent 
    	// share for this year for this particular farm
    	String[][] dateRange = dataManager.dateRangeReport(date1, date2);
    	for (int i = 1; i < dateRange.length; i++) {
    		String id = dateRange[i][0];
    		String weight = dateRange[i][1];
    		String percent = dateRange[i][2];
    		DataForOtherReport d = new DataForOtherReport(id, weight, percent);
    		tableView.getItems().add(d);
    	}
    	
    	// create export and return buttons
        HBox hbox = new HBox(400);
        Image exportImage = new Image("export.png", 30, 30, false, false);
        ImageView exportView = new ImageView(exportImage);
        Button export = new Button("export to file", exportView);
        export.setOnAction(e -> outputDateRange(stage, date1, date2));

        Image returnToPrev = new Image("return.png", 30, 30, false, false);
        ImageView returnPrev = new ImageView(returnToPrev);
        Button returns = new Button("return to previous screen", returnPrev);
        returns.setOnAction(e -> exitCurrScreen(stage));
        hbox.getChildren().addAll(returns, export);
        root.setBottom(hbox);
        
    } catch (NumberFormatException e) {
    	message.setText("Invalid user input date! Please try again.");
    } catch (Exception e) {
    	message.setText("Could not find data for this farm in this year. Please try again");
    }
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Let user enter a file name as the output file for Farm Report. 
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void outputFarm(Stage primaryStage, String id, Integer year) {
    BorderPane root = new BorderPane();
    VBox v1 = new VBox();
    Label message = new Label("Please provide the name of the output file: ");
    TextField output = new TextField();
    Button submit = new Button("submit");
    v1.getChildren().addAll(message, output, submit);

    root.setCenter(v1);
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String outputFile = output.getText();
    	processFarmFile(stage, outputFile, id, year);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * This processes the Farm Report and saves it to the output file the user wants.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void processFarmFile(Stage primaryStage, String output, String id, Integer year) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font ("Arial", 15));
    root.setCenter(message);

    try {
    	String[][] farmReport = dataManager.farmReport(id, year);
    	file.setOutputFile(output);
    	boolean success = file.writeToFile(farmReport, "Farm Report");
    	if (success) { // if successfully prints to file -> display success message
    		message.setText("Your farm report is successfully generated to the output file you requested."
                + "\n" + "You can find the " + output + " file in the same folder.");
    		Label goodBye =
    				new Label("Thank you so much for using the Milk Analyzer!" + "\n" 
    						+ "Have a great day!");
    		goodBye.setFont(new Font("Arial", 15));
    		Image smile = new Image("smile.jpg", 50, 50, false, false);

    		VBox v1 = new VBox();
    		v1.getChildren().addAll(message, new Label(""), goodBye, new ImageView(smile));

    		root.setCenter(v1);
    	}
    	// write to file failed -> display failure message
    	else {
    		message.setText("Save file failed! Invalid output name");
    	}
    } catch (Exception e) {
    	message.setText("Error occured. Unable to print to file.");
    }
    
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    // create return and exit buttons
    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    Image exit = new Image("exit.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    ImageView exitImage = new ImageView(exit);
    Button returns = new Button("return to previous screen", returnPrev);
    Button exitProgram = new Button("exit", exitImage);

    HBox h1 = new HBox(300);
    h1.getChildren().addAll(returns, exitProgram);

    returns.setOnAction(e -> exitCurrScreen(stage));
    exitProgram.setOnAction(e -> Platform.exit());

    root.setBottom(h1);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Let user enter a file name as the output file for Annual Report. 
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void outputAnnual(Stage primaryStage, Integer year) {
    BorderPane root = new BorderPane();
    VBox v1 = new VBox();
    Label message = new Label("Please provide the name of the output file: ");
    TextField output = new TextField();
    Button submit = new Button("submit");
    v1.getChildren().addAll(message, output, submit);

    root.setCenter(v1);
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	// get the output file name
    	String outputFile = output.getText();
    	processAnnualFile(stage, outputFile, year);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }
  
  /**
   * This processes the Annual Report and saves it to the output file the user wants.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void processAnnualFile(Stage primaryStage, String output, Integer year) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font ("Arial", 15));
    root.setCenter(message);

    try {
    	String[][] annualReport = dataManager.annualReport(year);
    	file.setOutputFile(output);
    	boolean success = file.writeToFile(annualReport, "Annual Report");
    	if (success) { // success message
    		message.setText("Your annual report is successfully generated to the output file you requested."
                + "\n" + "You can find the " + output + " file in the same folder.");
    		Label goodBye =
    				new Label("Thank you so much for using the Milk Analyzer!" + "\n" 
    						+ "Have a great day!");
    		goodBye.setFont(new Font("Arial", 15));
    		Image smile = new Image("smile.jpg", 50, 50, false, false);

    		VBox v1 = new VBox();
    		v1.getChildren().addAll(message, new Label(""), goodBye, new ImageView(smile));

    		root.setCenter(v1);
    	}
    	// write to file failed -> failure message
    	else {
    		message.setText("Save file failed! Invalid output name");
    	}
    } catch (Exception e) {
    	message.setText("Error occured. Unable to print to file.");
    }
    
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    // create return and exit buttons
    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    Image exit = new Image("exit.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    ImageView exitImage = new ImageView(exit);
    Button returns = new Button("return to previous screen", returnPrev);
    Button exitProgram = new Button("exit", exitImage);

    HBox h1 = new HBox(300);
    h1.getChildren().addAll(returns, exitProgram);

    returns.setOnAction(e -> exitCurrScreen(stage));
    exitProgram.setOnAction(e -> Platform.exit());

    root.setBottom(h1);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }
  
  /**
   * Let user enter a file name as the output file for Monthly Report. 
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void outputMonthly(Stage primaryStage, Integer year, Integer month) {
    BorderPane root = new BorderPane();
    VBox v1 = new VBox();
    Label message = new Label("Please provide the name of the output file: ");
    TextField output = new TextField();
    Button submit = new Button("submit");
    v1.getChildren().addAll(message, output, submit);

    root.setCenter(v1);
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String outputFile = output.getText();
    	processMonthlyFile(stage, outputFile, year, month);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }
  
  /**
   * This processes the Monthly Report and saves it to the output file the user wants.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void processMonthlyFile(Stage primaryStage, String output, Integer year, Integer month) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font ("Arial", 15));
    root.setCenter(message);

    try {
    	String[][] monthlyReport = dataManager.monthlyReport(year, month);
    	file.setOutputFile(output);
    	boolean success = file.writeToFile(monthlyReport, "Monthly Report");
    	if (success) { // success message
    		message.setText("Your monthly report is successfully generated to the output file you requested."
                + "\n" + "You can find the " + output + " file in the same folder.");
    		Label goodBye =
    				new Label("Thank you so much for using the Milk Analyzer!" + "\n" 
    						+ "Have a great day!");
    		goodBye.setFont(new Font("Arial", 15));
    		Image smile = new Image("smile.jpg", 50, 50, false, false);

    		VBox v1 = new VBox();
    		v1.getChildren().addAll(message, new Label(""), goodBye, new ImageView(smile));

    		root.setCenter(v1);
    	}
    	// write to file failed -> failure message
    	else {
    		message.setText("Save file failed! Invalid output name");
    	}
    } catch (Exception e) {
    	message.setText("Error occured. Unable to print to file.");
    }
    
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    // add return and exit buttons
    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    Image exit = new Image("exit.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    ImageView exitImage = new ImageView(exit);
    Button returns = new Button("return to previous screen", returnPrev);
    Button exitProgram = new Button("exit", exitImage);

    HBox h1 = new HBox(300);
    h1.getChildren().addAll(returns, exitProgram);

    returns.setOnAction(e -> exitCurrScreen(stage));
    exitProgram.setOnAction(e -> Platform.exit());

    root.setBottom(h1);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Let user enter a file name as the output file for Date Range Report. 
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void outputDateRange(Stage primaryStage, Date d1, Date d2) {
    BorderPane root = new BorderPane();
    VBox v1 = new VBox();
    Label message = new Label("Please provide the name of the output file: ");
    TextField output = new TextField();
    Button submit = new Button("submit");
    v1.getChildren().addAll(message, output, submit);

    root.setCenter(v1);
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    submit.setOnAction(e -> {
    	String outputFile = output.getText();
    	processDateRangeFile(stage, outputFile, d1, d2);
    });

    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    Button returns = new Button("return to previous screen", returnPrev);
    returns.setOnAction(e -> exitCurrScreen(stage));
    root.setBottom(returns);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }
  
  /**
   * This processes the Date Range Report and saves it to the output file the user wants.
   * 
   * @author Chelsea Chen
   * @param primaryStage - prev stage
   */
  public void processDateRangeFile(Stage primaryStage, String output, Date d1, Date d2) {
    BorderPane root = new BorderPane();
    Label message = new Label();
    message.setFont(new Font ("Arial", 15));
    root.setCenter(message);
    
    try {
    	String[][] dateRangeReport = dataManager.dateRangeReport(d1, d2);
    	file.setOutputFile(output);
    	boolean success = file.writeToFile(dateRangeReport, "Date Range Report");
    	if (success) { // success message
    		message.setText("Your date range report is successfully generated to the output file you requested."
                + "\n" + "You can find the " + output + " file in the same folder.");
    		Label goodBye =
    				new Label("Thank you so much for using the Milk Analyzer!" + "\n" 
    						+ "Have a great day!");
    		goodBye.setFont(new Font("Arial", 15));
    		Image smile = new Image("smile.jpg", 50, 50, false, false);

    		VBox v1 = new VBox();
    		v1.getChildren().addAll(message, new Label(""), goodBye, new ImageView(smile));

    		root.setCenter(v1);
    	}
    	// write to file failed -> failure message
    	else {
    		message.setText("Save file failed! Invalid output name");
    	}
    } catch (Exception e) {
    	message.setText("Error occured. Unable to print to file.");
    }
    
    Scene scene = new Scene(root, 600, 400);
    final Stage stage = new Stage();

    // create return and exit buttons
    Image returnToPrev = new Image("return.png", 30, 30, false, false);
    Image exit = new Image("exit.png", 30, 30, false, false);
    ImageView returnPrev = new ImageView(returnToPrev);
    ImageView exitImage = new ImageView(exit);
    Button returns = new Button("return to previous screen", returnPrev);
    Button exitProgram = new Button("exit", exitImage);

    HBox h1 = new HBox(300);
    h1.getChildren().addAll(returns, exitProgram);

    returns.setOnAction(e -> exitCurrScreen(stage));
    exitProgram.setOnAction(e -> Platform.exit());
    root.setBottom(h1);

    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initOwner(primaryStage);
    stage.setScene(scene);
    stage.show();
  }
}
