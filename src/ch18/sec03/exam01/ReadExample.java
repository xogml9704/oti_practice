package ch18.sec03.exam01;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
	public static void main(String[] args) {
		/*
		InputStream is = null;
		try {
			is = new FileInputStream("C:/Temp/test1.db");
			while(true) {
				int data = is.read();
				if(data == -1) break;
				System.out.println(data);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				}
		}
		*/
		
		try ( InputStream is = new FileInputStream("C:/Temp/test1.db")) {
			while(true) {
				int data = is.read();
				if(data == -1) break;
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
