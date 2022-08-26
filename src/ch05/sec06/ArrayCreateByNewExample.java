package ch05.sec06;

public class ArrayCreateByNewExample {
	public static void main(String[] args) {
		// 배열 변수 선언과 배열 생성
		int[] arr1 = new int[5];
		// 배열 항목의 초기값 출력
		for(int i=0; i<arr1.length; i++) {
			System.out.print("arr1[" + i + "] : " + arr1[i] + ", ");
		}
		System.out.println();
		// 배열 항목의 값 변경
		arr1[0] = 10;
		arr1[1] = 20;
		arr1[2] = 30;
		arr1[3] = 40;
		arr1[4] = 50;
		
		// 배열 항목의 변경 값 출력
		for(int i=0; i<arr1.length; i++) {
			System.out.print("arr1[" + i + "] : " + arr1[i] + ", ");
		}
		System.out.println();
		// 배열 변수 선언과 배열 생성
		double[] arr2 = new double[4];
		
		// 배열 항목의 초기값 출력
		for(int i=0; i<arr2.length; i++) {
			System.out.print("arr2[" + i + "] : " + arr2[i] + ", ");
		}
		System.out.println();
		
		// 배열 항목의 값 변경
		arr2[0] = 0.1;
		arr2[1] = 2.2;
		for(int i=2; i<arr2.length; i++) {
			arr2[i] = i+1;
		}
		
		// 배열 항목의 변경 값 출력
		for(int i=0; i<arr2.length; i++) {
			System.out.print("arr2[" + i + "] : " + arr2[i] + ", ");
		}
		System.out.println();
		
		// 배열 변수 선언과 배열 생성
		String[] arr3 = new String[4];
		
		// 배열 항목의 초기값 출력
		for(int i=0; i<arr3.length; i++) {
			System.out.print("arr3[" + i + "] : " + arr3[i] + ", ");
		}
		System.out.println();
		// 배열 항목의 값 변경
		arr3[0] = "1997년";
		arr3[1] = "4월";
		arr3[2] = "2일";
		arr3[3] = "남자";
		
		// 배열 항목의 변경값 출력
		for(int i=0; i<arr3.length; i++) {
			System.out.print("arr3[" + i + "] : " + arr3[i] + ", ");
		}
		System.out.println();
	}
}
