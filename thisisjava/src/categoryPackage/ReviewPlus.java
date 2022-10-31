package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import project.categoryPackage.ReviewPlusDTO.ReviewPlusDTO;

public class ReviewPlus {
    public boolean ReviewBoardPlus(Connection conn, ReviewPlusDTO reviewplusDTO) {
        try {
            String sql = "INSERT INTO reviews " +
                    "VALUES (seq_review_no.nextval, sysdate, ?, ?, ?, ? ) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, reviewplusDTO.getReview_content());
            pstmt.setInt(2, reviewplusDTO.getReview_score());
            pstmt.setString(3, reviewplusDTO.getUser_id());
            pstmt.setInt(4, reviewplusDTO.getBook_no());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return true;
    }
}
