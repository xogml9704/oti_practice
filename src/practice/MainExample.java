package practice;

import java.util.Arrays;

public class MainExample {
	public static void main(String[] args) {
		String data = "자바";
		
		byte[] arr1 = data.getBytes();
		System.out.println("arr1 : " + Arrays.toString(arr1));
		
	}
}