package project.categoryPackage.Service;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import project.categoryPackage.CartsDAO;
import project.categoryPackage.CartsDTO.CartsBoardDTO;

public class CartsService {
    public static void main(String[] args) {
        /*
        SearchService ca = new SearchService();
        JSONObject jsonObject = ca.Dibs(1, "taehee");
        JSONArray dataArr = jsonObject.getJSONArray("data");
        
        for(int i=0; i< dataArr.length(); i++) {
            System.out.println(dataArr.get(i).toString());
        }
        */
    }
    
    // 장바구니 목록 json 파일로 보내줌
    public JSONObject CartsBoard(Connection conn, String user_id) {
    	CartsDAO cDao = new CartsDAO();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "CartsBoard");
    	JSONArray dataArr = new JSONArray();
    	
    	ArrayList<CartsBoardDTO> list = cDao.CartsBoard(conn, user_id);
    	for(CartsBoardDTO cDto : list) {
    		JSONObject jo = new JSONObject();
    		jo.put("book_name", cDto.getBook_name());
    		jo.put("book_publisher", cDto.getBook_publisher());
    		jo.put("book_price", cDto.getBook_price());
    		jo.put("book_store", cDto.getBook_store());
    		jo.put("cart_qty", cDto.getCart_qty());
    		jo.put("b_c", cDto.getB_c());
    		dataArr.put(jo);
    	}
    	
    	sendObject.put("data", dataArr);
    	return sendObject;
    }
    
    // 장바구니 추가 후 1 = 성공 JSON 포맷으로 전송
    public JSONObject CartsBoardPlus(Connection conn, String user_id, int book_no, int cart_qty) {
    	CartsDAO cDao = new CartsDAO();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "CartsBoardPlus");
    	int result = cDao.CartsBoardPlus(conn, user_id, book_no, cart_qty);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
    // 장바구니 전체 삭제 후 1 = 성공 JSON 포맷으로 전송
    public JSONObject CartsBoardAllDelete(Connection conn, String user_id) {
    	CartsDAO cDao = new CartsDAO();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "CartsBoardAllDelete");
    	int result = cDao.CartsBoardAllDelete(conn, user_id);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
    // 장바구니 부분 삭제 후 1 = 성공 JSON 포맷으로 전송
    public JSONObject CartsBoardDelete(Connection conn, String user_id, int book_no) {
    	CartsDAO cDao = new CartsDAO();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "CartsBoardDelete");
    	int result = cDao.CartsBoardDelete(conn, user_id, book_no);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
    // 장바구니 상품 수량 변경 후 1 = 성공 JSON 포맷 전송
    public JSONObject CartsBoardQty(Connection conn, int cart_qty, String user_id, int book_no) {
    	CartsDAO cDao = new CartsDAO();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "CartsBoardQty");
    	int result = cDao.CartsBoardQty(conn, cart_qty, user_id, book_no);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
}
