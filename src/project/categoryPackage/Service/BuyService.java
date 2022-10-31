package project.categoryPackage.Service;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.JSONObject;

import project.categoryPackage.BuyDAO;
import project.categoryPackage.BuyDTO.UserOrder;
import project.categoryPackage.BuyDTO.UserOrderDetail;

public class BuyService {
    
    // 세부 페이지에서 구매를 눌렀을떄 보내는 json 포맷
    public JSONObject Buy(Connection conn, UserOrder userOrder, ArrayList<UserOrderDetail> list) {
    	BuyDAO bDao = new BuyDAO();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "buy");
    	boolean result = bDao.Buy(conn, userOrder, list);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
    /* <buy 서비스를 요청할 때 클라이언트가 서버로 보내는 json형식>
	{"command" : "buy", "data" : {"userOrder" : {"orderNo : "주문번호값", "orderDate : "주문날짜값" .....}         
	,  "orderDetailList" : [{"orderNo : "주문번호값", "bookNo" : "책번호값", "odQty" :  "주문수량값"}, {}, {} ...]}}
    */

}
