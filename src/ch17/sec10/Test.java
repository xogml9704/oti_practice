package ch17.sec10;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
		// 예외 발생(java.util.NoSuchElementException
		
		double avg = list.stream()
				.mapToInt(Integer :: intValue)
				.average()
				.getAsDouble();
	}
}
