/**
 * DataManager.java created by ateam66 in Milk Weight project - this displays a user interface of the milk
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

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * DataManager - Defines operations on data manipulations and forming the
 * required data for the visualizer.
 * 
 * @author Yumeng Bai & Ruiwen Wang
 *
 */
public class DataManager {

	// initializing fields
	CheeseFactory factory;

	/**
	 * This constructor initialize the factory with the input factory.
	 * 
	 * @param factory
	 */
	public DataManager(CheeseFactory factory) {
		this.factory = factory;
	}

	/**
	 * find the milk data of a certain month
	 * 
	 * @param date - find data based on the specified date
	 * @return milk data of a specific month
	 */
	private TreeMap<String, Farm> findDataOfMonth(Date date) {
		TreeMap<String, Farm> milkDataFromFarms = factory.getDataFromFarms();
		TreeMap<String, Farm> milkDataOfMonth = new TreeMap<String, Farm>();

		// go through the milk data of each farm
		milkDataFromFarms.forEach((s, f) -> {
			Set<Entry<Date, Integer>> dataSetOfFarm = f.getData().entrySet();
			Iterator<Entry<Date, Integer>> itr = dataSetOfFarm.iterator();
			TreeMap<Date, Integer> validDataForFarm = new TreeMap<Date, Integer>();
			while (itr.hasNext()) {
				Entry<Date, Integer> curr = itr.next();
				if (curr.getKey().getMonth() == date.getMonth()) {
					validDataForFarm.put(curr.getKey(), curr.getValue());
				}
			}
			Farm fnew = new Farm();
			fnew.setId(f.getId());
			fnew.setData(validDataForFarm);
			milkDataOfMonth.put(s, fnew);
		});
		return milkDataOfMonth;
	}

	/**
	 * get the average milk data of a certain month
	 * 
	 * @param date - find data based on the specified date
	 * @return Data - average milk data of the month
	 */
	public Data getMonthlyAverage(Date date) {
		TreeMap<String, Farm> milkDataOfTheMonth = findDataOfMonth(date);
		String avg = getAvg(milkDataOfTheMonth);
		return new Data(date, avg);
	}

	/**
	 * get the minimum milk data of this month
	 * 
	 * @param date - find data based on the specified date
	 * @return the minimum milk data (milk weight / contributions)
	 */
	public Data getMonthlyMin(Date date) {
		TreeMap<String, Farm> milkDataOfTheMonth = findDataOfMonth(date);
		return getMin(milkDataOfTheMonth);

	}

	/**
	 * get the maximum milk data of this month
	 * 
	 * @param date - find data based on the specified date
	 * @return the maximum milk data (milk weight / contributions)
	 */
	public Data getMonthlyMax(Date date) {
		TreeMap<String, Farm> milkDataOfTheMonth = findDataOfMonth(date);
		return getMax(milkDataOfTheMonth);
	}

	/**
	 * find the milk data of a certain farm and date
	 * 
	 * @param date   - find data based on the specified date
	 * @param farmID - find data based on the input farmID
	 * @return the milk data of each farm at a certain date
	 */
	private TreeMap<String, Farm> findDataOfFarmOfMont(Date date, String farmID) {
		TreeMap<String, Farm> milkDataFromFarms = factory.getDataFromFarms();
		TreeMap<String, Farm> milkDataOfTheMonth = new TreeMap<String, Farm>();
		Farm farm = milkDataFromFarms.get(farmID);
		Set<Entry<Date, Integer>> dataSetOfFarm = farm.getData().entrySet();

		// go through the milk data of each farm
		Iterator<Entry<Date, Integer>> itr = dataSetOfFarm.iterator();
		TreeMap<Date, Integer> dataOfTheMonth = new TreeMap<Date, Integer>();
		while (itr.hasNext()) {
			Entry<Date, Integer> curr = itr.next();
			if (curr.getKey().getMonth() == date.getMonth()) {
				dataOfTheMonth.put(curr.getKey(), curr.getValue());
			}
		}
		Farm newF = new Farm();
		newF.setId(farmID);
		newF.setData(dataOfTheMonth);
		milkDataOfTheMonth.put(farmID, newF);
		return milkDataOfTheMonth;
	}

	/**
	 * find the average milk data of a certain farm and date
	 * 
	 * @param date   - find data based on the specified date
	 * @param farmID - find data based on the input farmID
	 * @return the average milk data of each farm at a certain date
	 */
	public Data getMonthlyAverageForFarm(Date date, String farmID) {
		TreeMap<String, Farm> milkDataOfTheMonth = findDataOfFarmOfMont(date, farmID);
		return new Data(date, getAvg(milkDataOfTheMonth));
	}

	/**
	 * find the minimum milk data of a certain farm and date
	 * 
	 * @param date   - find data based on the specified date
	 * @param farmID - find data based on the input farmID
	 * @return the minimum milk data of each farm at a certain date
	 */
	public Data getMonthlyMinForFarm(Date date, String farmID) {
		TreeMap<String, Farm> milkDataOfTheMonth = findDataOfFarmOfMont(date, farmID);
		return getMin(milkDataOfTheMonth);

	}

	/**
	 * find the maximum milk data of a certain farm and date
	 * 
	 * @param date   - find data based on the specified date
	 * @param farmID - find data based on the input farmID
	 * @return the maximum milk data of each farm at a certain date
	 */
	public Data getMonthlyMaxForFarm(Date date, String farmID) {
		TreeMap<String, Farm> milkDataOfTheMonth = findDataOfFarmOfMont(date, farmID);
		return getMax(milkDataOfTheMonth);
	}

	/**
	 * get data sorted by month from each farm
	 * 
	 * @return sorted data
	 */
	public ArrayList<TreeMap<String, Data>> getDataSortedByField() {
		ArrayList<TreeMap<String, Data>> sortedByMonth = new ArrayList<TreeMap<String, Data>>();
		TreeMap<String, Farm> milkDataFromFarms = factory.getDataFromFarms();
		Set<Entry<String, Farm>> dataSet = milkDataFromFarms.entrySet();
		Iterator<Entry<String, Farm>> itr = dataSet.iterator();

		// go through the milk data of each farm
		while (itr.hasNext()) {
			Entry<String, Farm> curr = itr.next();
			TreeMap<Date, Integer> data = curr.getValue().getData();
			Iterator<Entry<Date, Integer>> dataItr = data.entrySet().iterator();
			while (dataItr.hasNext()) {
				Entry<Date, Integer> currData = dataItr.next();
				int index = currData.getKey().getMonth() - 1;
				sortedByMonth.get(index).put(curr.getKey(),
						new Data(currData.getKey(), currData.getValue().toString()));
			}
		}
		return sortedByMonth;
	}

	/**
	 * get the data in a certain date range
	 * 
	 * @param d1 - start date of the date range
	 * @param d2 - end date of the date range
	 * @return milk data in the certain range
	 */
	private TreeMap<String, Farm> getDataInDateRange(Date d1, Date d2) {
		TreeMap<String, Farm> milkDataFromFarms = factory.getDataFromFarms();
		TreeMap<String, Farm> milkDataInRange = new TreeMap<String, Farm>();

		// go through the milk data of each farm
		milkDataFromFarms.forEach((s, f) -> {
			Set<Entry<Date, Integer>> dataSetOfFarm = f.getData().entrySet();
			Iterator<Entry<Date, Integer>> itr = dataSetOfFarm.iterator();
			TreeMap<Date, Integer> validDataForFarm = new TreeMap<Date, Integer>();
			while (itr.hasNext()) {
				Entry<Date, Integer> curr = itr.next();
				if (inDateRange(curr.getKey(), d1, d2)) {
					validDataForFarm.put(curr.getKey(), curr.getValue());
				}
			}
			Farm fnew = new Farm();
			fnew.setId(f.getId());
			fnew.setData(validDataForFarm);
			milkDataInRange.put(s, fnew);
		});
		return milkDataInRange;
	}

	/**
	 * get average milk data in a certain date range
	 * 
	 * @param d1 - start date of the date range
	 * @param d2 - end date of the date range
	 * @return average milk data in the certain range
	 */
	public Data getAverageInDateRange(Date d1, Date d2) {
		TreeMap<String, Farm> milkDataInRange = getDataInDateRange(d1, d2);
		return new Data(d1, getAvg(milkDataInRange));
	}

	/**
	 * detect whether the milk data of a certain day is within the data range
	 * 
	 * @param day    - the day that provides the
	 * @param dayMin - the day that provides the
	 * @param dayMax - the day that provides the
	 * @return
	 */
	private boolean inDateRange(Date day, Date dayMin, Date dayMax) {
		if (day.compareTo(dayMin) >= 0 && day.compareTo(dayMax) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * get minimum milk data in a certain date range
	 * 
	 * @param d1 - start date of the date range
	 * @param d2 - end date of the date range
	 * @return minimum milk data in the certain range
	 */
	public Data getMinInDateRange(Date d1, Date d2) {
		TreeMap<String, Farm> milkDataInRange = getDataInDateRange(d1, d2);
		return getMin(milkDataInRange);
	}

	/**
	 * get maximum milk data in a certain date range
	 * 
	 * @param d1 start date of the date range
	 * @param d2 end date of the date range
	 * @return maximum milk data in the certain range
	 */
	public Data getMaxInDateRange(Date d1, Date d2) {
		TreeMap<String, Farm> milkDataInRange = getDataInDateRange(d1, d2);
		return getMax(milkDataInRange);
	}

	/**
	 * calculate average milk data of a certain set of data
	 * 
	 * @param dataMap - certain set of data that is going to be analyzed
	 * @return average milk data of a certain set of data
	 */
	private String getAvg(TreeMap<String, Farm> dataMap) {
		Set<Entry<String, Farm>> dataSet = dataMap.entrySet();
		double tally = 0.0;
		long sum = 0;
		Iterator<Entry<String, Farm>> itr = dataSet.iterator();

		// go through the milk data of the input data set
		while (itr.hasNext()) {
			Entry<String, Farm> curr = itr.next();
			TreeMap<Date, Integer> data = curr.getValue().getData();
			Iterator<Entry<Date, Integer>> dataItr = data.entrySet().iterator();
			while (dataItr.hasNext()) {
				Entry<Date, Integer> currData = dataItr.next();
				tally++;
				sum += currData.getValue();
			}
		}
		double avg = sum / tally;
		return Double.toString(avg);
	}

	/**
	 * calculate minimum milk data of a certain set of data
	 * 
	 * @param dataMap - certain set of data that is going to be analyzed
	 * @return minimum milk data of a certain set of data
	 */
	private Data getMin(TreeMap<String, Farm> dataMap) {
		Set<Entry<String, Farm>> dataSet = dataMap.entrySet();
		Data minData = new Data(null, "");
		int minWeight = 0;
		int tally = 0;
		Iterator<Entry<String, Farm>> itr = dataSet.iterator();

		// go through the milk data of the input data set
		while (itr.hasNext()) {
			Entry<String, Farm> curr = itr.next();
			TreeMap<Date, Integer> data = curr.getValue().getData();
			Iterator<Entry<Date, Integer>> dataItr = data.entrySet().iterator();
			while (dataItr.hasNext()) {
				Entry<Date, Integer> currData = dataItr.next();

				// when the first data is analyzed
				if (tally == 0) {
					minData = new Data(currData.getKey(), currData.getValue().toString());
					minWeight = currData.getValue();
					tally++;
				}

				// when the second and later data is analyzed
				if (currData.getValue() < minWeight) {
					minData = new Data(currData.getKey(), currData.getValue().toString());
					minWeight = currData.getValue();
				}
			}
		}
		return minData;
	}

	/**
	 * calculate maximum milk data of a certain set of data
	 * 
	 * @param dataMap - certain set of data that is going to be analyzed
	 * @return maximum milk data of a certain set of data
	 */
	private Data getMax(TreeMap<String, Farm> dataMap) {
		Set<Entry<String, Farm>> dataSet = dataMap.entrySet();
		Data maxData = new Data(null, "");
		int maxWeight = 0;
		Iterator<Entry<String, Farm>> itr = dataSet.iterator();

		// go through the milk data of the input data set
		while (itr.hasNext()) {
			Entry<String, Farm> curr = itr.next();
			TreeMap<Date, Integer> data = curr.getValue().getData();
			Iterator<Entry<Date, Integer>> dataItr = data.entrySet().iterator();

			while (dataItr.hasNext()) {
				Entry<Date, Integer> currData = dataItr.next();
				// compare the data with the current max milk data
				if (currData.getValue() > maxWeight) {
					maxData = new Data(currData.getKey(), currData.getValue().toString());
					maxWeight = currData.getValue();
				}
			}
		}
		return maxData;
	}

	/**
	 * This generates a farm report:
	 * FARM REPORT
	 * Prompt user for a farm id and year (or use all available data)
	 * Then, display the total milk weight and percent of the total of all farm for each month.
	 * Sort, the list by month number 1-12, show total weight, then that farm's percent of the total milk received for each month.
	 * 
	 * @param farmId - farm id to look up
	 * @param year - use input year
	 * @return String[][] storing farm report of statistics.
	 */
	public String[][] farmReport(String farmId, int year) {
		Farm farm = factory.getFarmFromID(farmId);
		String[][] returnArr = new String[13][3];
		returnArr[0][0] = "Months";
		returnArr[0][1] = "Weight";
		returnArr[0][2] = "Percentage";
		for (int i = 0; i < 12; i++) {
			int month = i+1;
			int allFarmTotalWeightOfMonth = factory.getTotalWeightOfMonth(year, month);
			returnArr[i+1][0] = Integer.toString(month);
			returnArr[i+1][1] = Integer.toString(farm.getTotalWeightOfMonth(year, month));
			// calculate percent share for this farm for each month
			returnArr[i+1][2] = Double
					.toString(100 * farm.getTotalWeightOfMonth(year, month) / (allFarmTotalWeightOfMonth + 0.0));
		}
		return returnArr;
	}

	/**
	 * This generates annual report:
	 * ANNUAL REPORT 
	 * Ask for year.
	 * Then display list of total weight and percent of total weight of all farms by farm for the year.
	 * Sort by Farm ID, or you can allow the user to select display ascending or descending by weight.
	 * 
	 * @param year - year to compute the farm statistics
	 * @return String[][] Annual report storing statistics
	 */
	public String[][] annualReport(int year) {
		int numFarm = factory.getDataFromFarms().size();
		String[][] returnArr = new String[numFarm+1][3];
		returnArr[0][0] = "Farm ID";
		returnArr[0][1] = "Weight";
		returnArr[0][2] = "Percentage";
		int i = 1;
		double totalWeight = factory.getTotalWeightOfYear(year) + 0.0;
		for (Farm f : factory.getDataFromFarms().values()) {
			returnArr[i][0] = f.getId();
			int farmWeight = f.getTotalWeightOfYear(year);
			returnArr[i][1] = Integer.toString(farmWeight);
			// calculate percent share for each farm this year
			double percentage = 100 * farmWeight / totalWeight;
			returnArr[i][2] = Double.toString(percentage);
			i++;
		}
		return returnArr;
	}

	/**
	 * This generates a monthly report:
	 * MONTHLY REPORT
	 * Ask for year and month.
	 * Then, display a list of totals and percent of total by farm.
	 * The list must be sorted by Farm ID, or you can prompt for ascending or descending by weight. 
	 * 
	 * @param year - year of the monthly report
	 * @param month - month of the monthly report
	 * @return String[][] monthly report of all farm statistics
	 */
	public String[][] monthlyReport(int year, int month) {
		int numFarm = factory.getDataFromFarms().size();
		String[][] returnArr = new String[numFarm + 1][3];
		returnArr[0][0] = "Farm ID";
		returnArr[0][1] = "Weight";
		returnArr[0][2] = "Percentage";
		int i = 1;
		double totalWeight = factory.getTotalWeightOfMonth(year, month) + 0.0;
		for (Farm f : factory.getDataFromFarms().values()) {
			returnArr[i][0] = f.getId();
			int farmWeight = f.getTotalWeightOfMonth(year, month);
			returnArr[i][1] = Integer.toString(farmWeight);
			// calculate percent share for each farm this month
			double percentage = 100 * farmWeight / totalWeight;
			returnArr[i][2] = Double.toString(percentage);
			i++;
		}
		return returnArr;
	}

	/**
	 * This generates a date range report:
	 * DATE RANGE REPORT
	 * Prompt user for start date (year-month-day) and end month-day,
	 * Then display the total milk weight per farm and the percentage of the total for each farm over that date range.
	 * The list must be sorted by Farm ID, or you can prompt for ascending or descending order by weight or percentage.
	 * 
	 * @param dayMin - start date for date range report
	 * @param dayMax - end date for date range report
	 * @return String[][] all farm statistics within the specified date
	 */
	public String[][] dateRangeReport(Date dayMin, Date dayMax) {
		int numFarm = factory.getDataFromFarms().size();
		String[][] returnArr = new String[numFarm + 1][3];
		returnArr[0][0] = "Farm ID";
		returnArr[0][1] = "Weight";
		returnArr[0][2] = "Percentage";
		// get all data within date
		TreeMap<String,Farm> inRangeData = getDataInDateRange(dayMin,dayMax);
		int i = 1;
		int[] farmWeights = new int[numFarm + 1];
		double totalWeight = 0.0;
		for(Farm f: inRangeData.values()) {
			returnArr[i][0]= f.getId();
			int farmWeight = f.getTotalWeight();
		   	farmWeights[i]= farmWeight;
		   	// calculate total weight within date range
		   	totalWeight += farmWeight;
		   	returnArr[i][1] = Integer.toString(farmWeight);
		   	i++;
		}
		// calculate each percentage
		for(int j = 1; j < numFarm + 1; j++) {
			double percentage = 100 * farmWeights[j]/totalWeight;
			returnArr[j][2]= Double.toString(percentage);
		}
		return returnArr;
	}

}
