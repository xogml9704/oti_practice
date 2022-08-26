package ch05.sec07;

public class MultidimensionalArrayByValueListExample {
	public static void main(String[] args) {
		// 2차원 배열 생성
		int[][] scores = {
				{ 80, 90, 96 },
				{ 76, 88 }
		};
		
		// 배열의 길이
		System.out.println("1차원 배열 길이(반의 수) : " + scores.length);
		System.out.println("2차원 배열 길이(첫 번째 반의 학생 수) : " + scores[0].length);
		System.out.println("2차원 배열 길이(두 번쨰 반의 학생 수) : " + scores[1].length);
		
		// 첫 번쨰 반의 세 번쨰 학생의 점수 읽기
		System.out.println("첫 번째 반의 세 번째 학생의 점수 : " + scores[0][2]);
		
		// 두 번째 반의 두 번쨰 학생의 점수 읽기
		System.out.println("두 번째 반의 두 번쨰 학생의 점수 : " + scores[1][1]);
		// 첫 번째 반의 평균 점수 구하기
		int class1_sum = 0;
		for(int i=0; i<scores[0].length; i++) {
			class1_sum += scores[0][i];
		}
		double class1_avg = (double) class1_sum / scores[0].length;
		System.out.println("첫 번쨰 반의 평균 점수 : " + class1_avg);
				
		// 두 번쨰 반의 평균 점수 구하기
		int class2_sum = 0;
		for(int i=0; i<scores[1].length; i++) {
			class2_sum += scores[1][i];
		}
		double class2_avg = (double) class2_sum / scores[1].length;
		System.out.println("두 번째 반의 평균 점수 : " + class2_avg);
		// 전체 학생의 평균 점수 구하기
		
		int total_Student = 0;
		int total_Sum = 0;
		
		for(int i=0; i<scores.length; i++) {
			total_Student += scores[i].length;
			for(int k=0; k<scores[i].length; k++) {
				total_Sum += scores[i][k];
			}
		}
		double totalAvg = (double) total_Sum / total_Student;
		System.out.println("전체 학생의 평균 : " + totalAvg);
		
		
		
	}
}
