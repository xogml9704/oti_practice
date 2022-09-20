package practice;

import lombok.Data;

@Data
public class Main {
	private String id;
	private String name;
	private int age;
	
	public Main(String id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}