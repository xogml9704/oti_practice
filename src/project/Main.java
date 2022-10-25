package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    // Field
    private Scanner scanner = new Scanner(System.in);
    private Connection conn;
    
    // Constructor
    public Main() {
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 연결하기
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@kosa402.iptime.org:50031/orcl",
                    "younghun",
                    "oracle"
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Method
    public void list() {
        System.out.println();
        
        System.out.println("**사이트 이름**                               사용자 : **아이디** ");
        System.out.println();
        
        System.out.println("   -------------------------------------------------------------");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                       내용 부분                             |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   |                                                           |");
        System.out.println("   -------------------------------------------------------------");
        
        
        // 메인 메뉴 출력
        mainMenu();
    }
    
    public void mainMenu() {
        System.out.println();
        System.out.println("   -------------------------------------------------------------");
        System.out.println("                             **메뉴 부분**                        ");
        String menuNo = scanner.nextLine();
        System.out.println();
        
        switch(menuNo) {
            
        }
    }
    
    public static void main(String[] args) {
        Main main = new Main();
        main.list();
    }
}