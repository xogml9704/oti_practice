package project.categoryPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    // private static Connection conn;
    
    public static Connection getConnection() {
        Connection conn = null;
        if (conn == null) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                conn = DriverManager.getConnection("jdbc:oracle:thin:@kosa402.iptime.org:50031:orcl", "younghun",
                        "oracle");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}