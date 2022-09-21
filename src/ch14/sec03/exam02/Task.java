package ch14.sec03.exam02;

public class Task implements Runnable {
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { 
				
			}
		}
	}

}
