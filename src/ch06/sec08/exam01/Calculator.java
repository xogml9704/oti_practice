package ch06.sec08.exam01;

import java.util.Scanner;

public class Calculator {
	//리턴값이 없는 메소드 선언
	void powerOn() {
		System.out.println("전원을 켭니다.");
	}
	
	//리턴값이 없는 메소드 선언
	void powerOff() {
		System.out.println("전원을 끕니다.");
	}
	
	// 호출 시 두 정수 값을 전달 받고.
	// 호출한 곳으로 결과값 int를 리턴하는 메소드 선언
	
	int plus(int x, int y) {
		int result = x + y;
		return result; // 리턴값 지정
	}
	
	// 호출 시 두 정수 값을 전달 받고,
	// 호출한 곳으로 결과값 double을 리턴하는 메소드 선언
	
	double divide(int x, int y) {
		double result = (double) x / (double) y;
		return result; // 리턴값 지정
	}
	
	long power(int a, int b) {
		int result = a;
		for(int i=1; i<b; i++) {
			result *= a;
		}
		return result;
	}
	
	long factorial(int n) {
		int result = 1;
		for(int i=1; i<=n; i++) {
			result *= i;
		}
		return result;
	}
	
	void divide2() {
		Scanner sc = new Scanner(System.in);
		String[] word = sc.nextLine().split(" ");
		double a = Double.parseDouble(word[0]);
		int b = Integer.parseInt(word[1]);
		
		System.out.println(a / b);
		
	}
	
	int random(int x, int y) {
		int num = (int)(Math.random()*(y-x+1))+ x;
		return num;
	}
}
