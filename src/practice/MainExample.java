package practice;

import java.util.LinkedList;
import java.util.Queue;

public class MainExample {
	public static void main(String[] args) {
		// Queue 컬렉션 생성
		Queue<Main> mes = new LinkedList<>();
		
		// 메시지 넣기
		mes.offer(new Main("sendMail", "홍길동"));
		mes.offer(new Main("sendSMS", "신용권"));
		mes.offer(new Main("sendKakaotalk", "감자바"));
		
		// 메시지를 하나씩 꺼내어 처리
		while(!mes.isEmpty()) {
			Main main = mes.poll();
			switch(main.command) {
				case "sendMail":
					System.out.println(main.to + "님에게 메일을 보냅니다.");
					break;
				case "sendSMS":
					System.out.println(main.to + "님에게 SMS를 보냅니다.");
					break;
				case "sendKakaotalk":
					System.out.println(main.to + "님에게 카카오톡을 보냅니다.");
					break;
			}
		}
	}
}