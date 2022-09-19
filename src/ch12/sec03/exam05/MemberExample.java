package ch12.sec03.exam05;

public class MemberExample {
	public static void main(String[] args) {
		Member m1 = new Member("fall", "단풍이");
		Member m2 = new Member("fall", "단풍이");
		
		System.out.println(m1);
		System.out.println(m2);
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		
		System.out.println(m1.equals(m2));
	}
}
