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
    public static ArrayList<BoardDTO> Board(Connection conn, int bookno) {
        ArrayList<BoardDTO> BoardList = new ArrayList<BoardDTO>();
        try {
            String sql = "SELECT book_name, book_detail, book_publisher, book_price, book_store, book_page, book_lang " +
                    "FROM books " + 
                    "WHERE book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookno);
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
                BoardList.add(bDto);
            }
            rs.close();
            pstmt.close();
            
            // 나중에 지워야하는 부분
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
    // 상세 검색 내 작가를 출력하는 부분 ( 작가는 복수가 될 수 있음 )
    public static ArrayList<WriterDTO> Writer(Connection conn, int bookno) {
        ArrayList<WriterDTO> WriterList = new ArrayList<WriterDTO>();
        try {
            String sql = "" + 
                    "SELECT author_name, author_detail " +
                    "FROM books b, author_book ab, authors a " +
                    "WHERE b.book_no = ab.book_no " +
                    "AND ab.author_no = a.author_no " +
                    "AND b.book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookno);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                WriterDTO wDto = new WriterDTO();
                wDto.setAuthor_name(rs.getString("author_name"));
                wDto.setAuthor_detail(rs.getString("author_detail"));
                WriterList.add(wDto);
            }
            rs.close();
            pstmt.close();
            
            for(WriterDTO w : WriterList) {
                System.out.println(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return WriterList;
    }
    
    // 상세 검색 내 리뷰 출력 부분 (리뷰는 복수가 될 수 있음)
    public ArrayList<ReviewDTO> Review(Connection conn, int bookno) {
        ArrayList<ReviewDTO> ReviewList = new ArrayList<ReviewDTO>();
        try {
            String sql = "" +
                    "SELECT substr(user_id, 1, 2) user_id, review_date, review_content, review_score " +
                    "FROM books b, reviews r " +
                    "WHERE b.book_no = r.book_no " +
                    "AND b.book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookno);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                ReviewDTO rDto = new ReviewDTO();
                rDto.setUser_id(rs.getString("user_id"));
                rDto.setReview_date(rs.getDate("review_date"));
                rDto.setReview_content(rs.getString("review_content"));
                rDto.setReview_score(rs.getInt("review_score"));
                ReviewList.add(rDto);
            }
            rs.close();
            pstmt.close();
            
            for(ReviewDTO r : ReviewList) {
                System.out.println(r);
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
        return ReviewList;
    }
    
}
