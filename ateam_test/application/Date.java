/**
 * Date.java created by ateam66 in Milk Weight project - this displays a user interface of the milk
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
 * 
 * Date - This creates a date instance with a year, month and day
 * @author Yiduo Wang
 *
 */
public class Date implements Comparable<Date> {
	private int year; // year of the date
	private int month; // month of the date
	private int day; // day of the date

	/**
	 * Constructor of Date class that creates a Date object for a specified
	 * year, month and day
	 * @param year - year of the date
	 * @param month - month of the date
	 * @param day - day of the date
	 */
	public Date(int year, int month, int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}

	/**
	 * Default constructor of Date class that creates a new date object
	 */
	public Date() {
		this(-1, -1, -1);
	}

	/**
	 * Getter of year
	 * @return year of the date
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Setter of year
	 * @param year - year for this date
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Getter of month
	 * @return month of the date
	 */
	public int getMonth() {
		return this.month;
	}

	/**
	 * Setter of month
	 * @param month - month for this date
	 * @return true if month is valid and is successfully changed;
	 *         false otherwise.
	 */
	public boolean setMonth(int month) {
		if (month == -1) {
			this.month = -1;
			return true;
		}
		if (month > 0 && month <= 12) {
			this.month = month;
			return true;
		} else return false;
	}

	/**
	 * Getter of day
	 * @return day of the date
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Setter of day
	 * @param day - day to be set for this date
	 * @return true if day is valid for this month and successfully changed
	 *         false otherwise
	 */
	public boolean setDay(int day) {
		if (day == -1) {
			this.day = -1;
			return true;
		} else if (day <= 0) {
			return false;
		} else {
			switch (month) {
			case -1:
			// Jan, Mar, May, July, Aug, Oct, Dec have 31 days
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if (day <= 31) {
					this.day = day;
					return true;
				} else return false;
			// Apr, June, Sept, Nov have 30 days
			case 4:
			case 6:
			case 9:
			case 11:
				if (day <= 30) {
					this.day = day;
					return true;
				} else return false;
			// Feb has 28 days if not leap year, 29 days if leap year
			case 2:
				if (day <= (isLeapYear() ? 29 : 28)) {
					this.day = day;
					return true;
				} else return false;

			default:
				return false;
			}
		}
	}

	/**
	 * Determines if year is leap year
	 * @return true if leap year; false otherwise
	 */
	private boolean isLeapYear() {
		// determine if year is leap year and return the corresponding value
		if (year % 4 != 0) return false;
		if (year % 100 == 0 && year % 400 != 0) return false;
		else return true;
	}

	/**
	 * Compares to other date based on year, month, day
	 * @param o - date to be compared to
	 * @return -1 if less than, 0 if equals, and 1 if greater than
	 */
	@Override
	public int compareTo(Date o) {
		return this.year != o.getYear() ? this.year - o.getYear()
				: (this.month != o.getMonth() ? this.month - o.getMonth()
						: (this.day != o.getDay() ? this.day - o.getDay() : 0));
	}

	/**
	 * convert data to the string with the format month/day/year
	 */
	@Override
	public String toString() {
		return String.format("%d-%d-%d", year, month, day);
	}
}
