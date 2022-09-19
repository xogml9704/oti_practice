package ch12.sec03.exam03;

public class Smartphone {
	private String company;
	private String os;
	
	public Smartphone(String company, String os) {
		this.company = company;
		this.os = os;
	}
	@Override
	public String toString() {
		return company + ", " + os;
	}
}
