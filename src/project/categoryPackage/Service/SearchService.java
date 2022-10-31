package project.categoryPackage.Service;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import project.categoryPackage.Bookboard;
import project.categoryPackage.CategoryDAO;
import project.categoryPackage.IntegrationDAO;
import project.categoryPackage.BookboardDTO.BoardDTO;
import project.categoryPackage.CategoryDTO.CategoryBoardDTO;
import project.categoryPackage.CategoryDTO.CategoryDTO;
import project.categoryPackage.CategoryDTO.SubCategoryDTO;
import project.categoryPackage.IntegrationDTO.IntegrationDTO;

public class SearchService {
	/*
	// 확인
	public static void main(String[] args) {
		SearchService s = new SearchService();
		s.BookBoard(ConnectionProvider.getConnection(), 11);
	}
	*/
	
    
    // 클라이언트에게 보낼 mainCategory json 포맷
    public JSONObject mainPageCategory(Connection conn) {
        CategoryDAO cDao = new CategoryDAO();
        JSONObject sendObject = new JSONObject();
        sendObject.put("command", "mainPageCategory");
        JSONArray dataArr = new JSONArray();
        
        ArrayList<CategoryDTO> list = cDao.category(conn);
        for(CategoryDTO cDto : list) {
            JSONObject jo = new JSONObject();
            jo.put("category_no", cDto.getCategory_no());
            jo.put("category_name", cDto.getCategory_name());
            dataArr.put(jo);
        }
        
        sendObject.put("data", dataArr);
        return sendObject;
    }
    
    // 클라이언트에게 보낼 subCategory json 포맷
    public JSONObject subPageCategory(Connection conn, int category_no) {
        CategoryDAO cDao = new CategoryDAO();
        JSONObject sendObject = new JSONObject();
        sendObject.put("command", "subPageCategory");
        JSONArray dataArr = new JSONArray();
        
        ArrayList<SubCategoryDTO> list = cDao.subcategory(conn, category_no);
        for(SubCategoryDTO scDto : list) {
            JSONObject jo = new JSONObject();
            jo.put("sub_no", scDto.getSub_no());
            jo.put("sub_name", scDto.getSub_name());
            dataArr.put(jo);
        }
        sendObject.put("data", dataArr);
        return sendObject;
    }
    
    // 클라이언트에게 보낼 카테고리 간이 목록 json 포맷
    public JSONObject CategoryBoard(Connection conn, int sub_no) {
        CategoryDAO cDao = new CategoryDAO();
        JSONObject sendObject = new JSONObject();
        sendObject.put("command", "CategoryBoard");
        JSONArray dataArr = new JSONArray();
        
        ArrayList<CategoryBoardDTO> list = cDao.CategoryBoard(conn, sub_no);
        for(CategoryBoardDTO cbDto : list) {
            JSONObject jo = new JSONObject();
            jo.put("book_no", cbDto.getBook_no());
            jo.put("book_name", cbDto.getBook_name());
            jo.put("book_publisher", cbDto.getBook_publisher());
            jo.put("book_price", cbDto.getBook_price());
            jo.put("review_avg", cbDto.getReviews_avg());
            jo.put("author_name", cbDto.getAuthor_name());
            jo.put("hash_id", cbDto.getHash_id());
            dataArr.put(jo);
        }
        sendObject.put("data", dataArr);
        return sendObject;
    }
    
    // 클라이언트에게 보낼 통합 검색 목록 json 포맷
    public JSONObject IntegrationBoard(Connection conn, String search) {
        IntegrationDAO iDao = new IntegrationDAO();
        JSONObject sendObject = new JSONObject();
        sendObject.put("command", "IntegrationBoard");
        JSONArray dataArr = new JSONArray();
        
        ArrayList<IntegrationDTO> list = iDao.Integration(conn,search);
        for(IntegrationDTO iDto : list) {
            JSONObject jo = new JSONObject();
            jo.put("book_no", iDto.getBook_no());
            jo.put("book_name", iDto.getBook_name());
            jo.put("book_publisher", iDto.getBook_publisher());
            jo.put("book_price", iDto.getBook_price());
            jo.put("review_avg", iDto.getReviews_avg());
            jo.put("author_name", iDto.getAuthor_name());
            jo.put("hash_id", iDto.getHash_id());
            dataArr.put(jo);
        }
        sendObject.put("data", dataArr);
        return sendObject;
    }
    
    // 책에 대한 상세검색 정보를 보낼 json 포맷
    public JSONObject BookBoard(Connection conn, int book_no) {
        Bookboard bDao = new Bookboard();
        JSONObject sendObject = new JSONObject();
        sendObject.put("command", "BookBoard");
        JSONArray dataArr = new JSONArray();
        
        ArrayList<BoardDTO> list = bDao.Board(conn, book_no);
        for(BoardDTO bDto : list) {
            JSONObject jo = new JSONObject();
            jo.put("book_name", bDto.getBook_name());
            jo.put("book_detail", bDto.getBook_detail());
            jo.put("book_publisher", bDto.getBook_publisher());
            jo.put("book_price", bDto.getBook_price());
            jo.put("book_store", bDto.getBook_store());
            jo.put("book_page", bDto.getBook_page());
            jo.put("book_lang", bDto.getBook_lang());
            jo.put("author", bDto.getAuthor());
            jo.put("review", bDto.getReview());
            dataArr.put(jo);
        }
        sendObject.put("data", dataArr);
        return sendObject;
        
    }
    
    // 상세 페이지에서 책 찜한 후 결과 보내주기
    public JSONObject Dibs(Connection conn, int book_no, String user_id) {
        Bookboard bDao = new Bookboard();
        JSONObject sendObject = new JSONObject();
        sendObject.put("command", "Dibs");
        int result = bDao.Dibs(conn, book_no, user_id);
        
        sendObject.put("data", result);
        return sendObject;
    }
}
