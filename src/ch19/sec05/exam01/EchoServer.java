package ch19.sec05.exam01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    private static ServerSocket serverSocket;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------");
        System.out.println("서버를 종료하려면 q 또는 Q를 입력하고 Enter키를 입력하세요. ");
        System.out.println("--------------------------------------------------");
        
        // TCP 서버 사용
        startServer();
        
        // 키보드 사용
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            String key = scanner.nextLine();
            if(key.toLowerCase().equals("q")) {
                break;
            }
        }
        scanner.close();
        
        stopServer();
        
    }
    
    public static void startServer() {
        // 작업 스레드 정의
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // ServerSocket 생성 및 Port 바인딩
                    serverSocket = new ServerSocket(50001);
                    System.out.println("[서버] 시작됨 ");
                    
                    while(true) {
                        System.out.println("\n[서버] 연결 요청을 기다림\n");
                        // 연결 수락
                        Socket socket = serverSocket.accept();
                        
                        executorService.execute(() -> {
                            try {
                                // 연결된 클라이언트 정보 얻기
                                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                                String clientIp = isa.getHostString();
                                System.out.println("[서버] " + clientIp + " 의 연결 요청을 수락함 ");
                                // ---------------
                                // 데이터 받기
                                DataInputStream dis = new DataInputStream(socket.getInputStream());
                                String message = dis.readUTF();
                                
                                // 데이터 보내기
                                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                                dos.writeUTF(message);
                                dos.flush();
                                System.out.println("[서버] 받은 데이터를 다시 보냄 : " + message);
                                // --------------
                                // 연결 끊기
                                socket.close();
                                System.out.println("[서버] " + clientIp + " 의 연결을 끊음");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                    
                } catch (Exception e) {
                    System.out.println("[서버] " + e.getMessage());
                }
            }
        };
        
        // 스레드 시작
        thread.start();
    }
    
    public static void stopServer() {
        // ServerSocket을 닫고 Port 언바인딩
        try {
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            
        }
    }
}
