package ch12.sec05;

public class StringBuilderExample {
	public static void main(String[] args) {
		// 메소드 체이닝 패턴
		String data = new StringBuilder()
				.append("DEF")
				.insert(3, "ABC")
				.delete(1, 2)
				.toString();
		System.out.println(data);
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("DEF");
		sb.insert(3, "ABC");
		sb.delete(1,2);
		sb.toString();
		System.out.println(sb);
	}
}
