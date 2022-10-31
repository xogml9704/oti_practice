package project.categoryPackage;

import java.util.Scanner;

public class Search {
    // Field
    private Scanner scanner = new Scanner(System.in);
    
    // Constructor
    
    public void searchBoard() {
        System.out.println("검색 방법을 선택해 주세요 .");
        System.out.println("1. 통합 검색 | 2. 카테고리 검색 ");
        System.out.println("3. 카테고리 검색(메인 카테고리) | 4. 카테고리 검색(서브 카테고리) ");
        System.out.println("5. 통합과 카테고리 검색을 통해 들어간 세부 검색 ");
        System.out.println("6. 세부 검색에서 작가 데이터 출력 ");
        System.out.println("7. 세부 검색에서 리뷰 데이터 출력 ");
        System.out.println("8. 장바구니 기본 목록 출력  ");
        
        System.out.print("-> ");
        String choice = scanner.nextLine();
        IntegrationDAO iDao = new IntegrationDAO();
        CategoryDAO cDao = new CategoryDAO();
        Bookboard bDao = new Bookboard();
        CartsDAO caDao = new CartsDAO();
        switch(choice) {
            case "1" : 
                iDao.Integration(ConnectionProvider.getConnection(), "1일");
                break;
            case "2" : 
                cDao.category(ConnectionProvider.getConnection());
                break;
            case "3" :
                cDao.subcategory(ConnectionProvider.getConnection(), 1);
                break;
            case "4" :
                cDao.CategoryBoard(ConnectionProvider.getConnection(), 1);
                break;
            case "5" :
                bDao.Board(ConnectionProvider.getConnection(), 1);
                break;
            case "6" :
                bDao.Writer(ConnectionProvider.getConnection(), 20);
                break;
            case "7" :
                bDao.Review(ConnectionProvider.getConnection(), 20);
                break;
            case "8" :
                caDao.CartsBoard(ConnectionProvider.getConnection(), "taehee");
        }
    }
    
    public static void main(String[] args) {
        Search search = new Search();
        search.searchBoard();
    }
}
