/**
 * Data.java created by ateam66 in Milk Weight project - this displays a user interface of the milk
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

/**
   * A class to store the a date-weight pair
   * @author Kexin Chen
   *
   */
  public class Data{
    private Date date; // stores date of the data
    private String weight; // stores weight of the data
    
    /**
     * Constructor that creates a data object
     * with a date and weight
     * @param date
     * @param weight
     */
    public Data(Date date, String weight) {
      this.date = date;
      this.weight = weight;
    }
    
    /**
     * Getter of date
     * @return date of the data
     */
    public Date getDate() {
      return this.date;
    }
    
    /**
     * Getter of weight
     * @return weight of the milk data
     */
    public String getWeight() {
      return this.weight;
    }
  }
