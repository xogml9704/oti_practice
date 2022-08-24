package ch03.sec08;

public class BitLogicExample {
	public static void main(String[] args) {
		System.out.println("45 & 25 = " + (45 & 25));
		System.out.println("45 | 25 = " + (45 | 25));
		System.out.println("45 ^ 25 = " + (45 ^ 25));
		System.out.println("~45 = " + ~45);
		System.out.println("---------------------------------------------");
		
		byte receiveDate = -128;
		
		// 방법 1 : 논리곱 연산의로 Unsigned 정수 얻기
		int unsignedInt1 = receiveDate & 255;
		System.out.println(unsignedInt1);
		
		// 방법 2 : 자바 API를 이용해서 Unsigned 정수 얻기
		int unsignedInt2 = Byte.toUnsignedInt(receiveDate);
		System.out.println(unsignedInt2);
		
		int test = 136;
		byte btest = (byte) test;
		System.out.println(btest);
	}
}