package project.categoryPackage.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import project.categoryPackage.ConnectionProvider;
import project.categoryPackage.BuyDTO.UserOrder;
import project.categoryPackage.BuyDTO.UserOrderDetail;
import project.categoryPackage.Service.BuyService;
import project.categoryPackage.Service.CartsService;
import project.categoryPackage.Service.ReviewPlusService;
import project.categoryPackage.Service.SearchService;

public class SocketClient {
	BookServer bookServer;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	//연결된 고객ip
	String clientIp;

	public SocketClient(BookServer bookServer, Socket socket) {
		try {
			this.bookServer = bookServer;
			this.socket = socket;
			this.dis = new DataInputStream(socket.getInputStream());
			this.dos = new DataOutputStream(socket.getOutputStream());
			InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			this.clientIp = socketAddress.getHostName();
			receive();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 클라이언트 요청({"command": "", data = {}}
	public void receive() {
		bookServer.threadPool.execute(() -> {
			try {
				while (true) {
					String receiveJson = dis.readUTF();
					JSONObject jsonObject = new JSONObject(receiveJson);
					String command = jsonObject.getString("command");
					
					JSONObject data = jsonObject.getJSONObject("data");
					switch (command) {
					// 장바구니 목록
					case "CartsBoard" :
						data = jsonObject.getJSONObject("data");
		                  send(new CartsService().CartsBoard(ConnectionProvider.getConnection(), data.getString("user_id")).toString());
		                  break;
		            // 장바구니 추가
					case "CartsBoardPlus" :
					    data = jsonObject.getJSONObject("data");
					    send(new CartsService().CartsBoardPlus(ConnectionProvider.getConnection(), data.getString("user_id"), data.getInt("book_no"), data.getInt("cart_qty")).toString());
					    break;
					
					// 장바구니 전체 삭제
					case "CartsBoardAllDelete" :
					    data = jsonObject.getJSONObject("data");
					    send(new CartsService().CartsBoardAllDelete(ConnectionProvider.getConnection(), data.getString("user_id")).toString());
					    break;
					    
					// 장바구니 부분 삭제    
					case "CartsBoardDelete" :
					    data = jsonObject.getJSONObject("data");
                        send(new CartsService().CartsBoardDelete(ConnectionProvider.getConnection(), data.getString("user_id"), data.getInt("book_no")).toString());
                        break;
                    
                    // 장바구니 상품 수량 변경    
					case "CartsBoardQty" :
                        data = jsonObject.getJSONObject("data");
                        send(new CartsService().CartsBoardQty(ConnectionProvider.getConnection(), data.getInt("cart_qty"), data.getString("user_id"), data.getInt("book_no")).toString());
                        break;
                        
                    // 메인 카테고리 
					case "mainPageCategory" :
                        data = jsonObject.getJSONObject("data");
                        send(new SearchService().mainPageCategory(ConnectionProvider.getConnection()).toString());
                        break;
                        
                    // 서브 카테고리 
					case "subPageCategory" :
                        data = jsonObject.getJSONObject("data");
                        send(new SearchService().subPageCategory(ConnectionProvider.getConnection(), data.getInt("category_no")).toString());
                        break;
                        
                    // 카테고리 목록 
					case "CategoryBoard" :
                        data = jsonObject.getJSONObject("data");
                        send(new SearchService().CategoryBoard(ConnectionProvider.getConnection(), data.getInt("sub_no")).toString());
                        break;
                        
                    // 통합 검색 목록     
					case "IntegrationBoard" :
                        data = jsonObject.getJSONObject("data");
                        send(new SearchService().IntegrationBoard(ConnectionProvider.getConnection(), data.getString("search")).toString());
                        // search 의 값은 제목, 출판사, 작가 이름을 통합적으로 검색하기 위한 String 값.
                        break;
                    
                    // 책에 대한 상세 검색 정보 
					case "BookBoard" :
                        data = jsonObject.getJSONObject("data");
                        send(new SearchService().BookBoard(ConnectionProvider.getConnection(), data.getInt("book_no")).toString());
                        break;
                        
                    // 상세 페이지에서 책 찜한 결과
					case "Dibs" :
                        data = jsonObject.getJSONObject("data");
                        send(new SearchService().Dibs(ConnectionProvider.getConnection(), data.getInt("book_no"), data.getString("user_id")).toString());
                        break;
                        
                    // 리뷰 작성  
                    case "ReviewBoardPlus" :
                        data = jsonObject.getJSONObject("data");
                        send(new ReviewPlusService().ReviewBoardPlus(ConnectionProvider.getConnection(), data.getString("review_content"), data.getInt("review_score"), data.getString("user_id"), data.getInt("book_no")).toString());
                        break;
                    
                    // 리뷰가 있는지 확인 (리뷰 작성 관련)
                    case "ReviewCheck" :
                        data = jsonObject.getJSONObject("data");
                        send(new ReviewPlusService().ReviewCheck(ConnectionProvider.getConnection(), data.getString("user_id"), data.getInt("book_no")).toString());
                        break;
                   
                    // 책 구매 한적이 있는지 확인 (리뷰 작성 관련)
                    case "OrderCheck" :
                        data = jsonObject.getJSONObject("data");
                        send(new ReviewPlusService().OrderCheck(ConnectionProvider.getConnection(), data.getString("user_id"), data.getInt("book_no")).toString());
                        break;
                     
                    // 책 구매
                    case "Buy" :
                        UserOrder order = new UserOrder();
                        JSONObject jo = data.getJSONObject("userOrder");
                        order.setUser_id(jo.getString("user_id"));
                        order.setOrder_receivename(jo.getString("order_receivename"));
                        order.setOrder_tel(jo.getString("order_tel"));
                        order.setOrder_address(jo.getString("order_address"));
                        order.setOrder_memo(jo.getString("order_memo"));
                        
                        JSONArray jsonarray = data.getJSONArray("orderDetailList");
                        ArrayList<UserOrderDetail> list = new ArrayList<>();
                        for (int i=0; i <jsonarray.length(); i++) {
                            JSONObject jo1 = jsonarray.getJSONObject(i);
                            UserOrderDetail uod = new UserOrderDetail();
                            uod.setBook_no(jo1.getInt("book_no"));
                            uod.setOd_qty(jo1.getInt("od_qty"));
                            list.add(uod);
                        }
                        send(new BuyService().Buy(ConnectionProvider.getConnection(), order, list).toString());
                        break;
                        
                        /*
                           JSONArray jsonArray = data.getJSONArray("orderDetailList");
                          ArrayList<adminOrderDetail> list = new ArrayList<>();
                      for(int i = 0; i < jsonArray.length(); i++) {
                         JSONObject jo = jsonArray.getJSONObject(i);
                         adminOrderDetail od = new adminOrderDetail();
                         od.setBook_no(jo.getInt("bookNo"));
                         od.setOd_qty(jo.getInt("odQty"));
                         list.add(od);
                  }
                         */
					}
				}
			} catch (IOException e) {
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
		} catch (IOException e) {
		}
	}
}
