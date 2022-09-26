package ch15.sec07;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapExample {
	public static void main(String[] args) {
		// Map 컬렉션 생성
		Map<Integer, String> map = Collections.synchronizedMap(new HashMap<>());
		
		// HashMap은 두 스레드가 동시에 put() 메소드를 호출할 수 있기 떄문에
		// 경합이 발생하고 결국은 하나만 저장되기 떄문이다.
		// Map<Integer, String> map = new HashMap<>();
		
		// 작업 스레드 객체 생성
		Thread threadA = new Thread() {
			@Override
			public void run() {
				// 객체 1000개 추가
				for(int i=1; i<=1000; i++) {
					map.put(i, "내용"+i);
				}
			}
		};
		
		//작업 스레드 객체 생성
		Thread threadB = new Thread() {
			@Override
			public void run() {
				// 객체 1000개 추가
				for(int i=1001; i<=2000; i++) {
					map.put(i, "내용"+i);
				}
			}
		};
		
		// 작업 스레드 실행
		threadA.start();
		threadB.start();
		
		// 작업 스레드들이 모두 종료될 때까지 메인 스레드를 기다리게 함
		try {
			threadA.join();
			threadB.join();
		} catch (Exception e) {
		}
		
		// 저장된 총 객체 수 얻기
		int size = map.size();
		System.out.println("총 객체 수 : " + size);
		System.out.println();
	}
}
