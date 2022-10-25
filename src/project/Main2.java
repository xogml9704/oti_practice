package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main2 {
    public void test01() {
        System.out.println("jdbc 프로그램 시작");
        Connection conn = null;
        Statement stmt = null;
        try {
//           Class.forName("oracle.jdbc.driver.OracleDriver"); 
           // 1. oracle과 연결할 수 있는 드라이버 로딩
           Class.forName("oracle.jdbc.OracleDriver"); // 위의 클래스를 상속한 클래스
           // 2. db 접속 (url, user, password)
           conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50031:orcl", "younghun", "oracle");
           System.out.println("DB 접속 성공");
           if (conn == null) {
              System.out.println("conn이 NULL");
           } else {
              System.out.println(conn);
           }
           
        } catch (ClassNotFoundException e) {
           // 1 fail
           System.out.println("DB 드라이버 로딩 실패");
           e.printStackTrace();
        } catch (SQLException e) {
           // 2 fail
           System.out.println("DB 접속 실패");
           e.printStackTrace();
        } catch (Exception e) {
           System.out.println("모르는 오류");
           e.printStackTrace();
        } finally {
           try {
              if (conn != null) {
                 conn.close();
              }
           } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
           }
        }
     }
    
    public static void main(String[] args) {
        Main2 main2 = new Main2();
        main2.test01();
    }
}
