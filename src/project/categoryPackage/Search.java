package project.categoryPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Search {
    // Field
    private Scanner scanner = new Scanner(System.in);
    public Connection conn;
    
    // Constructor
    public Search() {
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");
            // 연결
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@kosa402.iptime.org:50031/orcl",
                    "younghun",
                    "oracle"
                );
        } catch (Exception e) {
            exit();
        }
    }
    
    public void searchBoard() {
        System.out.println("검색 방법을 선택해 주세요 .");
        System.out.println("1. 통합 검색 | 2. 카테고리 검색 ");
        System.out.print("-> ");
        String choice = scanner.nextLine();
        IntegrationDAO iDao = new IntegrationDAO();
        CategoryDAO cDao = new CategoryDAO();
        switch(choice) {
            case "1" : 
                System.out.print("무엇을 검색할 것입니까? ");
                String input = scanner.nextLine(); 
                iDao.Integration(input);
                break;
            case "2" : 
                cDao.category();
                break;
        }
    }
    // Method
    public void exit() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        Search search = new Search();
        search.searchBoard();
    }
}
