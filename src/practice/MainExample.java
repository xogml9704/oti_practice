package practice;

public class MainExample {
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<5; i++) {
					System.out.println("스레드 1 실행");
					try { Thread.sleep(500); } catch (Exception e) { }
				}
			}
		});
		
		thread.start();
		for(int i=0; i<5; i++) {
			System.out.println("스레드 2 실행");
			try { Thread.sleep(1000); } catch (Exception e) { }
		}
	}
}