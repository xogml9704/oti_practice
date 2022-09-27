package ch17.sec01.exam01;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] args) {
		// Set 컬렉션 생성
		Set<String> set = new HashSet<>();
		set.add("홍길동");
		set.add("신용권");
		set.add("감자바");
		
		// 외부 반복자 이용
		for(String item : set) {
			System.out.println(item);
		}
		System.out.println();
		// 외부 반복자 이용2
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			System.out.println(item);
		}
		System.out.println();
		
		// Stream을 이용한 요소 반복 처리
		Stream<String> stream = set.stream();
		stream.forEach(name -> System.out.println(name));
		
		System.out.println();
		
		// 내부 반복자 이용
		stream = set.stream();
		stream.forEach((item) -> {
			System.out.println(item);
		});
		
	}
}
