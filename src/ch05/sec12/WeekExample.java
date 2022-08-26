package ch05.sec12;

import java.util.Calendar;

public class WeekExample {
	public static void main(String[] args) {
		// Week 열거 타입 변수 선언
		Week today = null;
		
		// 열거값 대입
		// today = Week.THURSDAY;
		Calendar now = Calendar.getInstance();
		
		// 오늘의 요일 얻기
		int week = now.get(Calendar.DAY_OF_WEEK);
		
		// 숫자를 열거 상수로 변환해서 변수에 대입
		switch(week) {
			case 1 : today = Week.SUNDAY;     break;
			case 2 : today = Week.MONDAY;     break;
			case 3 : today = Week.TUESDAY;    break;
			case 4 : today = Week.WEDNESDAY;  break;
			case 5 : today = Week.THURSDAY;   break;
			case 6 : today = Week.FRIDAY;     break;
			case 7 : today = Week.SATURDAY;   break;
		}
		// 열거값 비교
		if (today == Week.FRIDAY) {
			System.out.println("오늘은 금요일 입니다.");
		} else {
			System.out.println("금요일이 아닙니다.");
		}
	}
}
