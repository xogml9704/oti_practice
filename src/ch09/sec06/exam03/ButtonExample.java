package ch09.sec06.exam03;

public class ButtonExample {
	
	public static void main(String[] args) {
		// Button 객체 생성
		Button btnOk = new Button();
		Button btnCancel = new Button();
		
		// Button Click Event 자리 클래스 (Listener 클래스) 선언
		class OkListener implements Button.ClickListener {
			@Override
			public void onClick() {
				System.out.println("ok 버튼을 클릭했습니다.");
			}
		}
		
		class CancelListener implements Button.ClickListener {
			@Override
			public void onClick() {
				System.out.println("cancel 버튼을 클릭했습니다.");
			}
		}
		
		// Button 객체에 Click Event 자리 객체 설정
		btnOk.setClickListener(new OkListener());
		btnCancel.setClickListener(new CancelListener());
		
		// Button이 클릭되었을 때
		btnOk.click();
		btnCancel.click();
	}
}
