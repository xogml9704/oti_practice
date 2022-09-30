package ch18.sec07.exam01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferExample {
	public static void main(String[] args) {
		try {
			// 버퍼가 없는 스트림 생성
			String originalFilePath1 = BufferExample.class.getResource("originalFile1.jpg").getPath();
			String targetFilePath1 = "C:/Temp/targetFile1.jpg";
			
			FileInputStream fis1 = new FileInputStream(originalFilePath1);
			FileOutputStream fos1 = new FileOutputStream(targetFilePath1);
			
			// 버퍼가 있는 스트림 생성
			String originalFilePath2 = BufferExample.class.getResource("originalFile2.jpg").getPath();
			String targetFilePath2 = "C:/Temp/targetFile1.jpg";
			
			FileInputStream fis2 = new FileInputStream(originalFilePath1);
			BufferedInputStream bis = new BufferedInputStream(fis2);
			
			FileOutputStream fos2 = new FileOutputStream(targetFilePath1);
			BufferedOutputStream bos = new BufferedOutputStream(fos2);
			
			// 복사 시간 측정
			long nonBufferTime = copy(fis1, fos1);
			System.out.println("버퍼 미사용 \t" + nonBufferTime + " ns");
			
			long BufferTime = copy(bis, bos);
			System.out.println("버퍼 사용 \t" + BufferTime + " ns");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static long copy(InputStream is, OutputStream os) throws Exception {
		long start = System.nanoTime();
		
		while(true) {
			int data = is.read();
			if(data == -1) break;
			os.write(data);
		}
		
		os.flush();
		os.close();
		
		
		long end = System.nanoTime();
		return (end - start);
	}
}
