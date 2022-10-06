package ch19.sec07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {
    // 필드
    ChatServer chatServer;
    Socket socket;
    String clientIp;
    String chatName;
    DataInputStream dis;
    DataOutputStream dos;
    
    // 생성자
    public SocketClient(ChatServer chatServer, Socket socket) {
        try {
            this.chatServer = chatServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            InetSocketAddress isa =
                    (InetSocketAddress) socket.getRemoteSocketAddress();
            this.clientIp = isa.getHostName();
            receive();
        } catch (IOException e) { 
        }
    }
    // 메소드 : JSON 받기
    public void receive() {
        chatServer.threadPool.execute(() -> {
            try {
                while(true) {
                    String receiveJson = dis.readUTF();
                    
                    JSONObject jsonObject = new JSONObject(receiveJson);
                    String command = jsonObject.getString("command");
                    
                    switch(command) {
                        case "incoming" :
                            this.chatName = jsonObject.getString("data");
                            chatServer.sendToAll(this, "들어오셨습니다.");
                            chatServer.addSocketClient(this);
                            break;
                        case "message" :
                            String message = jsonObject.getString("data");
                            chatServer.sendToAll(this, message);
                            break;
                    }
                }
            } catch (IOException e) {
                chatServer.sendToAll(this, "나가셨습니다.");
                chatServer.removeSocketClient(this);
            }
        });
    }
    
    
    public void send(String json) {
        try {
            dos.writeUTF(json);
            dos.flush();
        } catch (IOException e) {
        }
    }
    
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {}
    }
}
