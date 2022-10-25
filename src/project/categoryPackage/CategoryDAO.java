package project.categoryPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CategoryDAO {
    private Scanner scanner = new Scanner(System.in);
    public void category() {
        //입력 받기
        System.out.println();
        System.out.println("검색하실 카테고리를 선택해주세요. ");
        System.out.println("---------------------------");
        try {
            String sql = "" +
                    "SELECT category_no, category_name " +
                    "FROM category " +
                    "ORDER BY category_no ";
            Search search = new Search();
            PreparedStatement pstmt = search.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                CategoryDtoBig cDtoB = new CategoryDtoBig();
                cDtoB.setCategory_no(rs.getInt("category_no"));
                cDtoB.setCategory_name(rs.getString("category_name"));
                System.out.print(cDtoB.getCategory_no() + "." + cDtoB.getCategory_name() + "   ");
            }
            System.out.println();
            System.out.print("-> ");
            int choice = Integer.parseInt(scanner.nextLine());
            CategoryDAO cDao = new CategoryDAO();
            cDao.subcategory(choice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void subcategory(int choice) {
        try {
            String sql = "" +
                    "SELECT sub_no, sub_name " +
                    "FROM subcategory s, category c " +
                    "WHERE s.category_no = c.category_no " +
                    "AND c.category_no = ? " +
                    "ORDER BY c.category_no ";
            Search search = new Search();
            PreparedStatement pstmt = search.conn.prepareStatement(sql);
            pstmt.setInt(1, choice);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                SubCategoryDto sDto = new SubCategoryDto();
                sDto.setSub_no(rs.getInt("sub_no"));
                sDto.setSub_name(rs.getString("sub_name"));
                System.out.print(sDto.getSub_no() + "." + sDto.getSub_name() + "   ");
            }
            System.out.println();
            System.out.print("-> ");
            int choice2 = Integer.parseInt(scanner.nextLine());
            CategoryDAO cDao = new CategoryDAO();
            cDao.CategoryBoard(choice2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void CategoryBoard(int choice2) {
        try {
            String sql = "" +
                    "SELECT b.book_no, book_name, book_publisher, book_price, max(author_name) a_max, avg(review_score) r_avg, count(d.user_id) d_count " +
                    "FROM books b, author_book ab, authors a, reviews r, dibs d, subcategory s " +
                    "WHERE b.book_no = ab.book_no " +
                    "AND a.author_no = ab.author_no " +
                    "AND r.book_no(+) = b.book_no " +
                    "AND d.book_no(+) = b.book_no " +
                    "AND s.sub_no = b.sub_no " +
                    "AND b.sub_no = ? " +
                    "GROUP BY b.book_no, book_name, book_publisher, book_price ";
            Search search = new Search();
            PreparedStatement pstmt = search.conn.prepareStatement(sql);
            pstmt.setInt(1, choice2);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                CategoryBoardDTO cDto = new CategoryBoardDTO();
                
                cDto.setBook_no(rs.getInt("book_no"));
                cDto.setBook_name(rs.getString("book_name"));
                cDto.setBook_publisher(rs.getString("book_publisher"));
                cDto.setBook_price(rs.getInt("book_price"));
                cDto.setA_max(rs.getString("a_max"));
                cDto.setR_avg(rs.getInt("r_avg"));
                cDto.setD_count(rs.getInt("d_count"));
                
                System.out.print("책 번호 : " + cDto.getBook_no() + " | ");
                System.out.print("책 이름 : " + cDto.getBook_name() + " | ");
                System.out.print("책 배급사 : " + cDto.getBook_publisher() + " | ");
                System.out.print("책 가격 : " + cDto.getBook_price() + " | ");
                System.out.println();
                System.out.print("작가 이름 : " + cDto.getA_max() + " | ");
                System.out.print("평점 평균 : " + cDto.getR_avg() + " | ");
                System.out.print("댓글 수 : " + cDto.getD_count() + " | ");
                System.out.println();
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}