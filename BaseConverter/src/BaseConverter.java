/**
 * BaseConverter.java converts any number with a valid base [2, 16] into another valid base
 * @version November 5, 2018
 * @author 20george
 * my extra is to print (to the console) "initial number + base + input base = number in the base of the difference between the input and output bases plus one + base + new output base"
 * note the extra only works on valid input and output bases
 */

import java.util.Scanner;
import java.io.*;

public class BaseConverter {
	private final static String value = "0123456789ABCDEF";
public BaseConverter() {
		// nothing.
	}	
public static void main(String[] args) {
		BaseConverter app = new BaseConverter();
		app.inputConvertPrintWrite();
	}
/**
 * converts the number in the base fromBase to base 10 
 * @param num String
 * @param fromBase String
 * @return int - the number converted into base 10
 */
public static int strToInt(String num, String fromBase) {
		int exp = 0;
		int fB = Integer.parseInt(fromBase);
		int numBase10 = 0;
		while (num.length() > 0){
			int digit = value.indexOf(num.charAt(num.length() - 1));
			numBase10 += Math.pow(fB, exp) * digit;
			num = num.substring(0, num.length() - 1);
			exp++;
		}
		return numBase10; 
	}
/**
 * Takes a number in base 10 (the number returned from the strToInt method) and converts it into a toBase number
 * @param num int
 * @param toBase int
 * @return String - number converted into toBase
 */
public String intToStr(int num, int toBase) {
	String output = "";
	while(num > 0) {
		output = value.charAt(num % toBase) + output;
		num /= toBase;
	}
	return output;
} 
/**
 * gets data from the values file, calls on the other methods and prints a final statement with the converted base numbers to the console and to a new file
 * also filters out bad data with invalid bases
 */
public void inputConvertPrintWrite() {
	//ArrayList<String> values = new ArrayList <String>(); //declaring an array
	Scanner in; 
	String[] line;
	try {
		in = new Scanner(new File("values10.dat"));
		PrintWriter pw = new PrintWriter(new FileWriter("/Users/20george/eclipse-workspace/BaseConverter/converted.dat"));
		while(in.hasNext()) {
			//contents of line: ["number to convert", "fromBase", "toBase"]
			line = in.nextLine().split("\t");
			if (Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16) {
				System.out.println("Invalid input base " + line[1]);
				pw.write("\n");
			}
			else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16) {
				System.out.println("Invalid output base " + line[2]);
				pw.write("\n");
			}
			else {
				System.out.println(line[0] + " base " + line[1] + " = " + intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]))+ " base " + line[2]);
				//the following line is my extra
				System.out.println(line[0] + " base " + line[1] + " = " + intToStr(strToInt(line[0], line[1]), (Math.abs(Integer.parseInt(line[2]) - Integer.parseInt(line[1])) + 1)) + " base " + (Math.abs(Integer.parseInt(line[2]) - Integer.parseInt(line[1])) + 1));
				pw.write(line[0] + "\t " + line[1] + "\t" + intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2])) + "\t" + line[2] + "\n");
			}
		}
		pw.close();
		in.close();
	} 
	catch (Exception e) {
		e.printStackTrace();
	}
  }
}

