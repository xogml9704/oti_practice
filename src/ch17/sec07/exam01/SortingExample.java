package ch17.sec07.exam01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingExample {
	public static void main(String[] args) {
		// List 컬렉션 생성
		List<Student> studentlist = new ArrayList<>();
		studentlist.add(new Student("홍길동", 30));
		studentlist.add(new Student("신용권", 10));
		studentlist.add(new Student("유미선", 20));
		
		// 점수를 기준으로 오름차순으로 정렬한 새 스트림 얻기
		studentlist.stream()
			.sorted( )
			.forEach(s -> System.out.println(s.getName() + " : " + s.getScore()));
		
		System.out.println();
		
		// 점수를 기준으로 내림차순으로 정렬한 새 스트림 얻기
		studentlist.stream()
			.sorted(Comparator.reverseOrder())
			.forEach(s -> System.out.println(s.getName() + " : " + s.getScore()));
	}
}
