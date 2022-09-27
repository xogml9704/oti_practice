package ch16.sec05.exam02;

public class MethodReferenceExample {
	public static void main(String[] args) {
		/*
		Person person = new Person();
		person.ordering(String :: compareToIgnoreCase);
		
		person.ordering((a, b) -> {
			return a.compareToIgnoreCase(b);
		});
		*/
		
		String a = "a";
		String b = "b";
		
		System.out.println(b.compareToIgnoreCase(a));
	}
}
