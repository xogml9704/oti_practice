package ch09.sec03.exam01;

public class A {
	// Nested Class
	static class B {
		void methodB() {
			System.out.println("methodB() 실행");
		}
	}
	
	// Field
	B field1 = new B();
	static B field2 = new B();
	
	// Constructor
	A() {
		B field1 = new B();
	}
	
	// Method
	void method1() {
		B field1 = new B();
	}
	
	static void method2() {
		B field1 = new B();
	}
}
