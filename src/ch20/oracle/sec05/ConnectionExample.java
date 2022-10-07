package ch20.oracle.sec05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {
    public static void main(String[] args) {
        /*
        Connection conn = null;
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 연결하기
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.0.15:1521/orcl",
                    "java",
                    "oracle"
                    );
            System.out.println("연결 성공");
            
            // DB 작업
            // -- 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
        */
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@192.168.0.15:1521/orcl", "java", "oracle");) {
            System.out.println("연결 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        
    }
}
