// Keng Xiong

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;


public class basicStatCalc {
  public static void main(String[] args) throws IOException {
    Scanner kb = new Scanner(System.in);
    ArrayList<Double> dataSet = fillList(args[0]);
    double mean, median, midpoint, stdDev, iqr;
    int choice;

    do {
      choice = menu(kb);
      switch(choice) {
        case 1:
                mean = computeMean(dataSet);
                System.out.println("Mean: " + mean + "\n");
                break;

        case 2:
                median = computeMedian(dataSet);
                System.out.println("Median: " + median + "\n");
                break;

        case 3:
                stdDev = computeStandardDeviation(dataSet);
                System.out.println("Standard Deviation: " + stdDev + "\n");
                break;
        case 4:
                System.out.println("Good Bye");
                break;
        default:
                System.out.println("Enter a valid choice");
                break;
      }
    }while(choice != 4);
  }

  public static double computeMean(final ArrayList<Double> dataSet) {
      if (dataSet == null || dataSet.size() == 0) {
        throw new IllegalArgumentException("Bad params computeMean");
      }

      Collections.sort(dataSet);

      double value = 0;
      for(int i = 0; i < dataSet.size(); i++) {
        value += dataSet.get(i);
      }

      double mean = value / dataSet.size();

      return mean;
  }

    public static double computeMedian(final ArrayList<Double> dataSet) {
    if (dataSet == null || dataSet.size() == 0) {
      throw new IllegalArgumentException("Bad params computeMedian");
    }

    Collections.sort(dataSet);

    int middle = dataSet.size() / 2;
    double median = 0.0;

    if (dataSet.size() % 2 != 0) {
      median = dataSet.get(middle);
    } else {
      median = (dataSet.get(middle - 1) + dataSet.get(middle)) / 2;
    }

    return median;
  }

    public static double computeStandardDeviation(final ArrayList<Double> dataSet) {
    if (dataSet == null || dataSet.size() == 0) {
      throw new IllegalArgumentException("Bad params computeStandardDeviation");
    }

    double mean, sum, result, stDev;

    mean = computeMean(dataSet);

    ArrayList<Double> devAList = new ArrayList<Double>(dataSet.size());
    ArrayList<Double> sqrAList = new ArrayList<Double>(dataSet.size());

    double meanTemp = 0.0;
    for (int i = 0; i < dataSet.size(); i++) {
      meanTemp = dataSet.get(i) - mean;
      devAList.add(meanTemp);
    }

    double sqrTemp = 0.0;
    for (int j = 0; j < dataSet.size(); j++) {
      sqrTemp = (devAList.get(j) * devAList.get(j));
      sqrAList.add(sqrTemp);
    }

    sum = 0.0;
    for (int k = 0; k < dataSet.size(); k++) {
      sum = sum + sqrAList.get(k);
    }

    result = sum / (dataSet.size() - 1);

    stDev = Math.sqrt(result);

    return stDev;
  }

  public static int menu(final Scanner kb) {
    if(kb == null) {
      throw new IllegalArgumentException("Scanner is null");
    }

    int num;

    do {
			System.out.println("Enter a menu choice:");
			System.out.println("1) Compute Mean");
			System.out.println("2) Compute Median");
			System.out.println("3) Compute Standard Deviation");
			System.out.println("4) Quit");
			System.out.print("Choice --> ");
			num = Integer.parseInt(kb.nextLine());
		} while (num < 1 || num > 4);

    System.out.println();

		return num;
  }

  public static ArrayList<Double> fillList(final String data) throws IOException {
    Scanner fileReader = new Scanner(new File(data));
    int totalRecords = 0;
    while(fileReader.hasNext()) {
      fileReader.nextLine();
      totalRecords++;
    }
    fileReader.close();

    ArrayList<Double> dataSet = new ArrayList<Double>(totalRecords);

    fileReader = new Scanner(new File(data));

    while(fileReader.hasNext()) {
      dataSet.add(Double.parseDouble(fileReader.nextLine()));
    }
    fileReader.close();

    return dataSet;
  }
}
