package ch09.sec02.exam01;

public class A {
	// Field // 필드
	int field;
	// Constructor // 생성자
	A() {
		B b = new B();
		b.methodB();
	}
	// Method // 메소드
	void methodA() {
		B b = new B();
		b.methodB();
	}
	// Nested Class
	public class B {
		// Field
		// Constructor
		// Method
		public void methodB() {
			System.out.println("methodB() 실행");
		}
		
	}
	
}
