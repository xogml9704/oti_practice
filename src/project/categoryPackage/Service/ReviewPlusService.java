package project.categoryPackage.Service;

import java.sql.Connection;

import org.json.JSONObject;

import project.categoryPackage.ReviewPlus;

public class ReviewPlusService {
    // 클라이언트에게 보낼 리뷰 작성 성공했다는 json 포맷
    public JSONObject ReviewBoardPlus(Connection conn, String review_content, int review_score, String user_id, int book_no) {
    	ReviewPlus rDao = new ReviewPlus();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "ReviewBoardPlus");
    	int result = rDao.ReviewBoardPlus(conn, review_content, review_score, user_id, book_no);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
    // 리뷰가 있는지 확인후 json 포맷으로 확인 값 리턴
    public JSONObject ReviewCheck(Connection conn, String user_id, int book_no) {
    	ReviewPlus rDao = new ReviewPlus();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "ReviewCheck");
    	boolean result = rDao.ReviewCheck(conn, user_id, book_no);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }
    
    // 책을 구매헀는지 확인후 json 포맷으로 확인 값 리턴
    public JSONObject OrderCheck(Connection conn, String user_id, int book_no) {
    	ReviewPlus rDao = new ReviewPlus();
    	JSONObject sendObject = new JSONObject();
    	sendObject.put("command", "OrderCheck");
    	boolean result = rDao.OrderCheck(conn, user_id, book_no);
    	
    	sendObject.put("data", result);
    	return sendObject;
    }

}
