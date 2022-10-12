package ch20.oracle.sec10;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class ProcedureCallExample {
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
            String sql = "{ call user_create(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            
            cstmt.setString(1, "summer");
            cstmt.setString(2, "한여름");
            cstmt.setString(3, "12345");
            cstmt.setInt(4, 26);
            cstmt.setString(5, "summer@mycompany.com");
            cstmt.registerOutParameter(6, Types.INTEGER);
            
            cstmt.execute();
            
            int rows = cstmt.getInt(6);
            System.out.println("저장된 행 수 : " + rows);
            cstmt.close();
        
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
