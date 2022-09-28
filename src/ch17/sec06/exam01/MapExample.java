package ch17.sec06.exam01;

import java.util.ArrayList;
import java.util.List;

public class MapExample {
	public static void main(String[] args) {
		List <Student> studentlist = new ArrayList<>();
		
		studentlist.add(new Student("홍길동", 85));
		studentlist.add(new Student("홍길동", 92));
		studentlist.add(new Student("홍길동", 87));
		
		// Student를 score 스트림으로 변환
		studentlist.stream()
			.mapToInt(s -> s.getScore())
			.forEach(score -> System.out.println(score));
	}
}
