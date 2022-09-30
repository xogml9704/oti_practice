package ch18.sec06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class CharacterConvertStreamExample2 {
	public static void main(String[] args) {
		try {
			OutputStream os = new FileOutputStream("C:/Temp/test.txt");
			Writer writer = new OutputStreamWriter(os, "UTF-8");
			String str = "문자 변환 스트림을 사용합니다.";
			writer.write(str);
			writer.flush();
			writer.close();
			
			InputStream is = new FileInputStream("C:/Temp/test.txt");
			Reader reader = new InputStreamReader(is, "UTF-8");
			char[] data = new char[20];
			int num = reader.read(data);
			String str2 = new String(data, 0, num);
			System.out.println(str2);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
