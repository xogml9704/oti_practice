package ch09.sec07.exam03;

public class ButtonExample {
	
	public static void main(String[] args) {
		// Button 객체 생성
		Button btnOk = new Button();
		Button btnCancel = new Button();
		
		// Button Click Event 자리 클래스 (Listener 클래스) 선언
		btnOk.setClickListener(new Button.ClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Ok 버튼이 클릭되었습니다.");
			}
		});
		
		btnCancel.setClickListener(new Button.ClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Cancel 버튼이 클릭되었습니다.");
			}
		});
		
		// Button이 클릭되었을 때
		btnOk.click();
		btnCancel.click();
	}
}
