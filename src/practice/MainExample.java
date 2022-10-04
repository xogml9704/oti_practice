package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainExample {
	public static void main(String[] args) {
		List<Main> list = new ArrayList<>();
		list.add(new Main("홍길동", "남", 92));
		list.add(new Main("김수영", "여", 87));
		list.add(new Main("감자바", "남", 95));
		list.add(new Main("오해영", "여", 93));
		
		// 남학생만 묶어 List 생성
		List<Main> maleList = list.stream()
				.filter(s -> s.getSex().equals("남"))
				.collect(Collectors.toList());
		
		List<Main> maleList2 = list.stream()
				.filter(s -> s.getSex().equals("남"))
				.toList();
		
		maleList.stream()
			.forEach(s -> System.out.println("maleList : " + s.getName()));
		
		System.out.println();
		
		maleList2.stream()
			.forEach(s -> System.out.println("maleList2 : " + s.getName()));
		
		System.out.println();
		
		List<Main> femaleList = list.stream()
				.filter(s -> s.getSex().equals("여"))
				.collect(Collectors.toList());
		
		femaleList.stream()
			.forEach(s -> System.out.println("femaleList : " + s.getName()));
		
		System.out.println();
		
		Map<String, Integer> map = list.stream()
				.collect(
						Collectors.toMap(10, 10)
								s -> s.getName(),
								s -> s.getScore()
								)
						);
		System.out.println(map);
		
		Map<String, List<Main>> map2 = list.stream()
				.collect(
						Collectors.groupingBy(s -> s.getSex())
						);
		
		List<Main> maleList3 = map2.get("남");
		maleList3.stream().forEach(s -> System.out.println(s.getName()));
		System.out.println();

	}
}