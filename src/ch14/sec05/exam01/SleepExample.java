package ch14.sec05.exam01;

import java.awt.Toolkit;

public class SleepExample {
	public static void main(String[] args) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for(int i=0; i<10; i++) {
			toolkit.beep();
			try {
				System.out.println("소리~");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
