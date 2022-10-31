package project.categoryPackage.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookServer {
	ServerSocket serverSocket;
	ExecutorService threadPool = Executors.newFixedThreadPool(100);

	
	public void start() throws IOException {
		serverSocket = new ServerSocket(50001);
		Thread thread = new Thread(() -> {
			try {
				while (true) {
					//클라이언트와 통신할 수 있는 서버 소켓
					Socket socket = serverSocket.accept();
					SocketClient sc = new SocketClient(this, socket);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});

		thread.start();
	}

	public void stop() {
		try {
			serverSocket.close();
			threadPool.shutdownNow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BookServer bookServer = new BookServer();
		try {
			bookServer.start();
			Scanner sc = new Scanner(System.in);
			while (true) {
				String key = sc.nextLine();
				if (key.toLowerCase().equals("q")) {
					break;
				}
			}
			sc.close();
			bookServer.stop();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
