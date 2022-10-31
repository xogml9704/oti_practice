package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.BookboardDTO.BoardDTO;
import project.categoryPackage.BookboardDTO.ReviewDTO;
import project.categoryPackage.BookboardDTO.WriterDTO;

public class Bookboard {
    // 상세 검색 메소드
    public ArrayList<BoardDTO> Board(Connection conn, int book_no) {
        ArrayList<BoardDTO> BoardList = new ArrayList<BoardDTO>();
        try {
            String sql = "SELECT book_name, book_detail, book_publisher, book_price, book_store, book_page, book_lang " +
                    "FROM books " + 
                    "WHERE book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book_no);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                BoardDTO bDto = new BoardDTO();
                bDto.setBook_name(rs.getString("book_name"));
                bDto.setBook_detail(rs.getString("book_detail"));
                bDto.setBook_publisher(rs.getString("book_publisher"));
                bDto.setBook_price(rs.getInt("book_price"));
                bDto.setBook_store(rs.getString("book_store"));
                bDto.setBook_page(rs.getInt("book_page"));
                bDto.setBook_lang(rs.getString("book_lang"));
                
                // 상세 페이지의 작가 정보
                ArrayList<WriterDTO> writerlist = new ArrayList<WriterDTO>();
                try {
                    String sql2 = "" + 
                            "SELECT author_name, author_detail " +
                            "FROM books b, author_book ab, authors a " +
                            "WHERE b.book_no = ab.book_no " +
                            "AND ab.author_no = a.author_no " +
                            "AND b.book_no = ? ";
                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(1, book_no);
                    ResultSet rs2 = pstmt.executeQuery();
                    while(rs2.next()) {
                        WriterDTO wDto = new WriterDTO();
                        wDto.setAuthor_name(rs2.getString("author_name"));
                        wDto.setAuthor_detail(rs2.getString("author_detail"));
                        writerlist.add(wDto);
                    }
                    rs2.close();
                } catch (Exception e) {
                	e.printStackTrace();
                }
                
                // 상세 페이지의 리뷰 정보
                ArrayList<ReviewDTO> reviewlist = new ArrayList<ReviewDTO>();
                try {
                	String sql3 = "" +
                			"SELECT user_id, to_char(review_date, 'yyyy-mm-dd') review_date, review_content, review_score " +
                            "FROM books b, reviews r " +
                            "WHERE b.book_no = r.book_no " +
                            "AND b.book_no = ? ";
                	pstmt = conn.prepareStatement(sql3);
                	pstmt.setInt(1, book_no);
                	ResultSet rs3 = pstmt.executeQuery();
                    while(rs3.next()) {
                    	ReviewDTO rDto = new ReviewDTO();
                    	rDto.setUser_id(rs3.getString("user_id"));
                    	rDto.setReview_date(rs3.getString("review_date"));
                        rDto.setReview_content(rs3.getString("review_content"));
                        rDto.setReview_score(rs3.getInt("review_score"));
                        reviewlist.add(rDto);
                    }
                    rs3.close();
                } catch (Exception e) {
                	e.printStackTrace();
                }
                bDto.setAuthor(writerlist);
                bDto.setReview(reviewlist);
                BoardList.add(bDto);
            }
            rs.close();
            pstmt.close();
            
			for(BoardDTO b : BoardList) {
				System.out.println(b);
			}
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return BoardList;
    }
    
    // 상세 페이지에서 책 찜하는 메소드
    public int Dibs(Connection conn, int book_no, String user_id) {
        int result = 0;
        try {
            String sql = "INSERT INTO dibs VALUES (?, ?) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, book_no);
            pstmt.setString(2, user_id);
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
}
