package practice;

import java.util.Arrays;
import java.util.List;

public class MainExample {
	public static void main(String[] args) {
		List<Main> list = Arrays.asList(
				new Main("홍길동", 92),
				new Main("신용권", 95),
				new Main("감자바", 88)
				);
		
		int sum1 = list.stream()
				.mapToInt(Main :: getScore)
				.sum();
		
		int sum2 = list.stream()
				.map(Main :: getScore)
				.reduce(0, (a, b) -> a + b);
		
		System.out.println("sum1 : " + sum1);
		System.out.println("sum2 : " + sum2);
	}
}