package practice;

public class Person {
	private String id;
	private String name;
	
	public Person(String id) {
		this.id = id;
		System.out.println("Member(String id)");
	}
	
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
		System.out.println("Member(String id, String name)");
	}
	
	@Override
	public String toString() {
		String info = "{ id : " + id + ", name : " + name + " }";
		return info;
	}
}
