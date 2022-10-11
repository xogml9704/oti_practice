package ch20.oracle.sec06;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardWithFileInsertExample {
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
            String sql = "insert into boards (bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata) values (seq_bno.nextval, ?, ?, ?, sysdate, ?, ?)";
            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"bno", "btitle"});
            
            pstmt.setString(1, "연습 용");
            pstmt.setString(2, "함박눈이 내려요");
            pstmt.setString(3, "winter");
            pstmt.setString(4, "photo1.jpg");
            pstmt.setBlob(5, new FileInputStream("src/ch20/oracle/sec06/photo1.jpg"));
            
            // SQL 문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("지정된 행 수 : " + rows);
            
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
