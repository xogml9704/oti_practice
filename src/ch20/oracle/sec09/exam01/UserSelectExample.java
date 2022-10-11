package ch20.oracle.sec09.exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSelectExample {
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
            String sql = "select userid, username, userpassword, userage, useremail from users where userid=?";
            // PreparedStatement 얻기 및 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, "winter");
            
            // SQL 문 실행
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                /*
                String userId = rs.getString("userid");
                String userName = rs.getString("username");
                String userPassword = rs.getString("userpassword");
                int userAge = rs.getInt(4);
                String userEmail = rs.getString("useremail");
                
                printUser(userId, userName, userPassword, userAge, userEmail);
                */
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setUserAge(rs.getInt("userAge"));
                user.setUserEmail(rs.getString("userEmail"));
                System.out.println(user);
                // printUser(user);
            }
            rs.close();
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
    /*
    public static void printUser(String userId, String userName, String userPassword, int userAge, String userEmail) {
        System.out.println("userId : " + userId);
        System.out.println("userName : " + userName);
        System.out.println("userPassword : " + userPassword);
        System.out.println("userAge : " + userAge);
        System.out.println("userEmail : " + userEmail);

    }
    */
    
    public static void printUser(User user) {
        System.out.println("userId : " + user.getUserId());
        System.out.println("userName : " + user.getUserName());
        System.out.println("userPassword : " + user.getUserPassword());
        System.out.println("userAge : " + user.getUserAge());
        System.out.println("userEmail : " + user.getUserEmail());
    }
}
