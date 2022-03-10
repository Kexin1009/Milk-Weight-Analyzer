/**
 * FileManager.java created by ateam66 in Milk Weight project that helps display a user interface of the milk
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * FileManager - This class handles the reading and writing of a file
 * 
 * @authors Yiduo Wang, Chelsea Chen, Yumeng Bai
 *
 */
public class FileManager {
	public String inputFile; // input file name
	public String outputFile; // output file name

	/**
	 * Constructor - initiates a new FileManager with a specific
	 * input file and output file name
	 * @param inputFile - name of the input file
	 * @param outputFile - name of the output file
	 */
	public FileManager(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	/**
	 * Constructor - initiates a new FileManager with a specific
	 * input file name
	 * @param inputFile - name of the input file
	 */
	public FileManager(String inputFile) {
		this(inputFile, null);
	}

	/**
	 * Default constructor that initiates a new FileManager with null
	 * input and output file names
	 */
	public FileManager() {
		this(null, null);
	}

	/**
	 * Sets the output file of the FileManager 
	 * @param outputFile - name of the output file
	 */
	public void setOutputFile (String outputFile){
		this.outputFile = outputFile;
	}
	
	/**
	 * Read in a user-provided input file and save it in TreeMap<String, Farm> farms.
	 * @param farms - a collection of farms that will save the milk data
	 * @return true if reading file was successful, false otherwise
	 * @author Xi(Chelsea) Chen
	 */
	public boolean readFile(TreeMap<String, Farm> farms) {
		List<List<String>> data = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(inputFile));) {
			scanner.useDelimiter(",");
			while (scanner.hasNextLine()) {
		        data.add(getDataFromLine(scanner.nextLine()));
		    }
			for(int i = 1; i < data.size(); i++) {
				List<String> cur = data.get(i);
				String strDate = cur.get(0);
				String id = cur.get(1);
				String weight = cur.get(2);
				Farm farm = farms.get(id);
				if (farm == null) { // farm does not exist in farms yet
					// create a new farm
					farm = new Farm();
					farm.setId(id);
					if (farm.insertMilkForDate(strDate, weight)) {
						// successfully inserted data for this farm
						// then add farm in the collection of farms
						farms.put(id, farm);
					}
				} else { 
					// farm already exists in farms -> just add the data to this farm
					// and not to the collection of farms
					farm.insertMilkForDate(strDate, weight);
				}
			}
			scanner.close();
			// NOTE: error lines in file are automatically IGNORED 
			// when added into the factory
			return true;
		}
		catch (IOException e) {
			System.out.println("Invalid input file name");
			return false;
		}
	}

	/**
	 * Private helper method to get data from every line
	 * @param line - every line in the csv file
	 * @return List<String> of data in line
	 * @author Chelsea Chen
	 */
	private List<String> getDataFromLine(String line) {
		final String DELIMITER = ",";
		List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(DELIMITER);
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }
	    return values;
	}

	/**
	  *  write a report to a specific file
	  * 
	  * @param report     - the string array containing the information of the report
	  * @param reportName - the report name
	  * @return - true if data is successfully written into the output file, false
	  *          otherwise
	  * @author Yumeng Bai
	  */
	 public boolean writeToFile(String[][] report, String reportName) {
		 try {
			 PrintWriter writer = new PrintWriter(outputFile);
			 writer.println(reportName);
			 for (int i = 0; i < report.length; i++) {
				 for (int j = 0; j < report[i].length; j++) {
					 writer.print(report[i][j] + "   ");
				 }
				 writer.println();
			 }
			 writer.close();
			 return true;
		 } catch (FileNotFoundException e) { 
			 // prompt if file name is invalid and return false
			 System.out.println("Invalid output file name");
			 return false;
		 } 
	 }
}
