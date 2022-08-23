package ch03.sec05;

import java.util.Scanner;

public class InfinityAndNaNCheckExample {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("x : ");
		int x = Integer.parseInt(sc.nextLine());
		
		System.out.print("y : ");
		double y = Double.parseDouble(sc.nextLine());
		
		// double z = x / y;
		double z = x % y;
		
		if(Double.isInfinite(z) || Double.isNaN(z)) {
			System.out.println("값 산출 불가");
		}
		else {
			System.out.println(z);
			System.out.println("z + 2 : " + (z+2));
		}
		// int result = 5 / 0; // 실행 오류가 생긴다.
		double result = 5 / 0.0; // 정상 실행은 되지만 값은 infinite, 또는 NaN
		System.out.println(result);
	}
}
