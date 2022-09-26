package practice;

import java.util.TreeSet;

public class MainExample {
	public static void main(String[] args) {
		// 비교자를 제공한 TreeSet 컬렉션 생성
		TreeSet<Main> treeSet = new TreeSet<Main>(new MainComparator());
		
		// 객체 저장
		treeSet.add(new Main("포도", 3000));
		treeSet.add(new Main("수박", 10000));
		treeSet.add(new Main("딸기", 6000));
		
		// 객체를 하나씩 가져오기
		for(Main main : treeSet) {
			System.out.println(main.name + " : " + main.price);
		}
	}
}