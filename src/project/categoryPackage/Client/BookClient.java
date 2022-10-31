package project.categoryPackage.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.JSONObject;

public class BookClient {
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	String userId;

	public static void main(String[] args) {
		try {
			BookClient bookClient = new BookClient();
			bookClient.connect();
			bookClient.userId = null;
			bookClient.receive();
			
			Scanner sc = new Scanner(System.in);
			while(true) {
				System.out.println("안녕하세요. OTI문고에 오신것을 환영합니다.");
				if(bookClient.userId == null) {
					System.out.print("1.로그인");
				}
				else {
					System.out.print("1.로그아웃");
				}
				
				System.out.println("2.");
			}
			
//			sc.close();
//			bookClient.unConnect();
		} catch (Exception e) {
		}

	}

	public void connect() throws IOException {
		//서버 주소
		socket = new Socket("localhost", 50001);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
	}

	public void unConnect() throws IOException {
		socket.close();
	}

	// 서버로부터 데이터 받는 쓰레드 -> 서버가 날리는 json의 command에 따라 알아서 화면에 데이터 출력
	public void receive() {
		Thread thread = new Thread(() -> {
			try {
				while (true) {
					String json = dis.readUTF();
					JSONObject root = new JSONObject(json);
					// 서버에서 클라이언트로 보내는 json의 command
					String command = root.getString("command");
					// command에 따라 data 파싱이 달라짐
					switch(command) {
					case "adminBookListByBookName" :
						// data객체에는 command에 맞는 요청데이터가 들어있음
						JSONObject data = root.getJSONObject("data");
						// 파싱 및 화면에 전달받은 데이터 알맞게 출력
						break;
					}
					
				}
			} catch (IOException e) {
				System.exit(0);
			}
		});
		thread.start();
	}

	public void send(String json) {
		try {
			dos.writeUTF(json);
			dos.flush();
		} catch (IOException e) {
		}
	}
	
	
	public static JSONObject adminBookListByBookName() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", "adminBookListByBookName");
		
		// 요청 기능에 필요한 데이터 넣을 data 객체
		JSONObject jsonDataObject = new JSONObject();
		
		
		jsonObject.put("data", jsonDataObject);
		
		return jsonObject;
	} 

}
