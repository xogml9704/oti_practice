package ch20.oracle.sec07;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardUpdateExample {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 연결하기
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl",
                    "java", // 계정
                    "oracle" // 패스워드
                    );
            System.out.println("연결 성공");
            
            // DB 작업
            String sql = new StringBuilder()
                    .append("UPDATE boards SET ")
                    .append("btitle=?, ")
                    .append("bcontent=?, ")
                    .append("bfilename=?, ")
                    .append("bfiledata=? ")
                    .append("WHERE bno=?")
                    .toString();
            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, "눈 사람");
            pstmt.setString(2, "눈으로 만든 사람");
            pstmt.setString(3, "photo2.jpg");
            pstmt.setBlob(4, new FileInputStream("src/ch20/oracle/sec07/photo2.jpg"));
            pstmt.setInt(5, 15);
            /*
            pstmt.setString(1, "photo2.jpg");
            pstmt.setBlob(2, new FileInputStream("src/ch20/oracle/sec07/photo2.jpg"));
            pstmt.setInt(3, 16);
            */
            // SQL 문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("수정된 행 수 : " + rows);
            
            if(rows == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if(rs.next()) {
                    int bno = rs.getInt(1);
                    System.out.println("저장된 bno : " + bno);
                    String btitle = rs.getString(2);
                    System.out.println("저장된 btitle : " + btitle);
                }
                rs.close();
            }
            
            // PreparedStatement 닫기
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
      //  } catch (SQLException e) {
      //      e.printStackTrace();
        } finally {
            if (conn != null) {
                // DB 연결 끊기
                try {
                    conn.close();
                } catch (SQLException e) {
                }
                System.out.println("연결 끊김");
                
            }
        }
        
    }
}
