package ch09.sec04.exam03;

public class A {
	// 메소드
	public void method1(int arg) {
		// 로컬 변수
		int var = 1;
		
		class B {
			void method2() {
				System.out.println("arg : "+ arg); // final 특성을 받는다.
				System.out.println("var : "+ var);
				
				// arg = 3;
				// var = 3;
			}
		}
		
		
		// arg = 2;
		// var = 2;
		
		System.out.println("arg : "+ arg);
		System.out.println("var : "+ var);
	}
	
	public static void main(String[] args) {
		A a = new A();
		a.method1(3);
	}
	
}
