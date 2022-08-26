package ch05.sec09;

public class ArrayCopyExample {
	public static void main(String[] args) {
		// 길이 3인 배열
		String[] oldStringArray = { "java", "array", "copy"};
		// 길이 5인 배열을 새로 생성
		String[] newStringArray = new String[5];
		// 배열 항목 복사
		System.arraycopy(oldStringArray, 0, newStringArray, 0, oldStringArray.length);
		
		for(int i=0; i<newStringArray.length; i++) {
			System.out.printf("%s, ", newStringArray[i]);
		}
		// 배열 항목 출력
		
	}
}
