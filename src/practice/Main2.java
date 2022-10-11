package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main2 {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 연결하기
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl",
                    "java",
                    "oracle"
                    );
            
            String sql = new StringBuilder()
                    .append("delete from boards where bdate=? ")
                    .toString();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setDate(1, null);
            // PreparedStatement 닫기
            pstmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    // 연결 끊기
                    conn.close();
                } catch (SQLException e) {
                    
                }
            }
        }
    }
}