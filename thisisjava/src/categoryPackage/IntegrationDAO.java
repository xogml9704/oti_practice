package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.IntegrationDTO.IntegrationDTO;

public class IntegrationDAO {
    // 통합 검색 메소드
    public static ArrayList<IntegrationDTO> Integration(Connection conn, String bookname) {
        ArrayList<IntegrationDTO> IntegrationList = new ArrayList<IntegrationDTO>();
        try {
            String sql = "" +
                    "SELECT b.book_no, book_name, book_publisher, book_price, max(author_name) a_max, avg(review_score) r_avg, count(review_date) r_count, count(d.user_id) d_count " +
                    "FROM books b, author_book ab, authors a, reviews r, dibs d " +
                    "WHERE b.book_no = ab.book_no " +
                    "AND a.author_no = ab.author_no " +
                    "AND r.book_no(+) = b.book_no " +
                    "AND d.book_no(+) = b.book_no " +
                    "AND (book_name LIKE ? OR author_name LIKE ? OR book_publisher LIKE ?) " +
                    "GROUP BY b.book_no, book_name, book_publisher, book_price";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%"+bookname+"%");
            pstmt.setString(2, "%"+bookname+"%");
            pstmt.setString(3, "%"+bookname+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                IntegrationDTO iDto = new IntegrationDTO();
                iDto.setBook_no(rs.getInt("book_no"));
                iDto.setBook_name(rs.getString("book_name"));
                iDto.setBook_publisher(rs.getString("book_publisher"));                
                iDto.setBook_price(rs.getInt("book_price"));
                iDto.setA_max(rs.getString("a_max"));
                iDto.setR_avg(rs.getInt("r_avg"));
                iDto.setR_count(rs.getInt("r_count"));
                iDto.setD_count(rs.getInt("d_count"));
                IntegrationList.add(iDto);
            }
            rs.close();
            pstmt.close();
            
            // 보여주기 나중에 삭제
            for(IntegrationDTO i : IntegrationList) {
                System.out.println(i);
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
        return IntegrationList;
    }
}
