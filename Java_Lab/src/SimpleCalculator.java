import java.util.Scanner;
import java.io.*;

public class SimpleCalculator {

	public static void main(String[] args) {
		
		String[] data = new String[3];
		Scanner sc = new Scanner(System.in);
		String temp = sc.next();
		int plusOrMinus = 0;
		int result = 0;
		int opd1, opd2 = 0;
		SimpleCalculator cal = new SimpleCalculator(){};
		
		sc.close();
		
		for(int i=0; i<temp.length(); i++) {
			if(temp.charAt(i) == '+') {
				data[0] = temp.split("\\+")[0];
				data[1] = "+";
				data[2] = temp.split("\\+")[1];
				plusOrMinus = 1;
			}
		}
		
		
		
		if(plusOrMinus == 0) {
			data[0] = temp.split("-")[0];
			data[1] = "-";
			data[2] = temp.split("-")[1];
			opd1 = Integer.parseInt(data[0]);
			opd2 = Integer.parseInt(data[2]);
			result = opd1-opd2;
		}
		else {
			opd1 = Integer.parseInt(data[0]);
			opd2 = Integer.parseInt(data[2]);
			result = opd1+opd2;
		} 
		
		try {
			cal.checkException(data, result, opd1, opd2);
			System.out.println(result);
		}
		catch(AddZeroException e) {
			System.out.println("AddZeroException");
		}
		catch(SubtractZeroException e) {
			System.out.println("SubtractZeroException");
		}
		catch(OutOfRangeException e) {
			System.out.println("OutOfRangeException");
		}
	}
	
	public void checkException(String[] strArr, int result, int input1, int input2) throws AddZeroException, SubtractZeroException, OutOfRangeException{
		if(Integer.parseInt(strArr[0]) == 0 || Integer.parseInt(strArr[2]) == 0) {
			if(strArr[1] == "+") throw new AddZeroException();
			else throw new SubtractZeroException();
		}
		
		else if((result < 0 || result > 1000) || (input1 < 0 || input1 > 1000) || (input2 < 0 || input2 > 1000)) throw new OutOfRangeException();
		else return;
	}
}

class AddZeroException extends Exception {}

class SubtractZeroException extends Exception {}

class OutOfRangeException extends Exception {}
