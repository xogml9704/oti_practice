package practice;

public class Person2 {
	public Person getMember1(Main main) {
		String id = "winter";
		Person person = main.create(id);
		return person;
	}
	
	public Person getMember2(Main2 main) {
		String id = "winter";
		String name = "한겨울";
		Person person = main.create(id, name);
		return person;
	}
}
