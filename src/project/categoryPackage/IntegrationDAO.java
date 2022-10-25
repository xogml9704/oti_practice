package project.categoryPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class IntegrationDAO {
    private Scanner scanner = new Scanner(System.in);
    
    public void Integration(String bookname) {
        try {
            String sql = "" +
                    "SELECT b.book_no, book_name, book_publisher, book_price, max(author_name) a_max, avg(review_score) r_avg, count(d.user_id) d_count " +
                    "FROM books b, author_book ab, authors a, reviews r, dibs d " +
                    "WHERE b.book_no = ab.book_no " +
                    "AND a.author_no = ab.author_no " +
                    "AND r.book_no(+) = b.book_no " +
                    "AND d.book_no(+) = b.book_no " +
                    "AND (book_name LIKE ? OR author_name LIKE ? OR book_publisher LIKE ?) " +
                    "GROUP BY b.book_no, book_name, book_publisher, book_price";
            Search search = new Search();
            PreparedStatement pstmt = search.conn.prepareStatement(sql);
            pstmt.setString(1, "%"+bookname+"%");
            pstmt.setString(2, "%"+bookname+"%");
            pstmt.setString(3, "%"+bookname+"%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                IntegrationDto iDto = new IntegrationDto();
                iDto.setBook_no(rs.getInt("book_no"));
                iDto.setBook_name(rs.getString("book_name"));
                iDto.setBook_publisher(rs.getString("book_publisher"));                
                iDto.setBook_price(rs.getInt("book_price"));
                iDto.setA_max(rs.getString("a_max"));
                iDto.setR_avg(rs.getInt("r_avg"));
                iDto.setD_count(rs.getInt("d_count"));
                
                System.out.print("책 번호 : " + iDto.getBook_no() + " | ");
                System.out.print("책 이름 : " + iDto.getBook_name() + " | ");
                System.out.print("책 배급사 : " + iDto.getBook_publisher() + " | ");
                System.out.print("책 가격 : " + iDto.getBook_price() + " | ");
                System.out.println();
                System.out.print("작가 이름 : " + iDto.getA_max() + " | ");
                System.out.print("평점 평균 : " + iDto.getR_avg() + " | ");
                System.out.print("댓글 수 : " + iDto.getD_count() + " | ");
                System.out.println();
                System.out.println();
            }
            rs.close();
            pstmt.close();
            System.out.println("몇 번 책을 선택하시겠습니까? ");
            System.out.print("-> ");
            int bookno = Integer.parseInt(scanner.nextLine());
            Bookboard Bboard = new Bookboard();
            Bboard.board(bookno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
