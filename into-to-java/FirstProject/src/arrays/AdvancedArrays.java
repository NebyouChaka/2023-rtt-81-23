package arrays;

import java.util.Arrays;

public class AdvanceArrays {
	
	public static void main(String[] arg ){
		
		double[] numbers = {6.0, 4.4, 1.9, 2.9, 3.4, 3.5} ;
		String[] strings = { "one", "two", "three"};
		
		
		double min = numbers[0];
		double max = numbers[0];
		
		for(double number : numbers) {
			if(number < min) {
				min = number;
			}
			if (number > max) {
		    }
		}
		System.out.println("min = " + min);
		System.out.println("max = " + max);
		
		// string is immutable so toUpperCase does Not modify the string itself
		String str1 = "abc";
		str1 .toUpperCase();
		//Array library WILL modify the array
		Arrays.sort(strings);
		System.out.println(Arrays.toString(strings));
		
	}
}
}
