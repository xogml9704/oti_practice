package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.CategoryDTO.CategoryBoardDTO;
import project.categoryPackage.CategoryDTO.CategoryDTO;
import project.categoryPackage.CategoryDTO.SubCategoryDTO;

public class CategoryDAO {
    // 카테고리 페이지 조회
    public static ArrayList<CategoryDTO> category(Connection conn) {
        ArrayList<CategoryDTO> CategoryList = new ArrayList<CategoryDTO>();
        try {
            String sql = "" +
                    "SELECT category_no, category_name " +
                    "FROM category " +
                    "ORDER BY category_no ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                CategoryDTO cDto = new CategoryDTO();
                cDto.setCategory_no(rs.getInt("category_no"));
                cDto.setCategory_name(rs.getString("category_name"));
                CategoryList.add(cDto);
            }
            rs.close();
            pstmt.close();
            
            // 출력 나중에 삭제 해줘야 함.
            for(CategoryDTO c : CategoryList) {
                System.out.println(c);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CategoryList;
    }
    
    // 카테고리 상세 페이지 조회
    public static ArrayList<SubCategoryDTO> subcategory(Connection conn, int choice) {
        ArrayList<SubCategoryDTO> SubCategoryList = new ArrayList<SubCategoryDTO>();
        try {
            String sql = "" +
                    "SELECT sub_no, sub_name " +
                    "FROM subcategory s, category c " +
                    "WHERE s.category_no = c.category_no " +
                    "AND c.category_no = ? " +
                    "ORDER BY c.category_no ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, choice);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                SubCategoryDTO sDto = new SubCategoryDTO();
                sDto.setSub_no(rs.getInt("sub_no"));
                sDto.setSub_name(rs.getString("sub_name"));
                SubCategoryList.add(sDto);
            }
            rs.close();
            pstmt.close();
            // 보여주기 위한 나중에 삭제~
            for(SubCategoryDTO s : SubCategoryList) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
               e.printStackTrace();
            }
        }
        return SubCategoryList;
    }
    
    // 카테고리 내 책 간이 목록
    public static ArrayList<CategoryBoardDTO> CategoryBoard(Connection conn, int choice) {
        ArrayList<CategoryBoardDTO> CategoryBoardlist = new ArrayList<CategoryBoardDTO>();
        try {
            String sql = "" +
                    "SELECT b.book_no, book_name, book_publisher, book_price, max(author_name) a_max, avg(review_score) r_avg, count(review_date) r_count,  count(d.user_id) d_count " +
                    "FROM books b, author_book ab, authors a, reviews r, dibs d, subcategory s " +
                    "WHERE b.book_no = ab.book_no " +
                    "AND a.author_no = ab.author_no " +
                    "AND r.book_no(+) = b.book_no " +
                    "AND d.book_no(+) = b.book_no " +
                    "AND s.sub_no = b.sub_no " +
                    "AND b.sub_no = ? " +
                    "GROUP BY b.book_no, book_name, book_publisher, book_price ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, choice);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                CategoryBoardDTO cbDto = new CategoryBoardDTO();
                cbDto.setBook_no(rs.getInt("book_no"));
                cbDto.setBook_name(rs.getString("book_name"));
                cbDto.setBook_publisher(rs.getString("book_publisher"));
                cbDto.setBook_price(rs.getInt("book_price"));
                cbDto.setA_max(rs.getString("a_max"));
                cbDto.setR_avg(rs.getInt("r_avg"));
                cbDto.setR_count(rs.getInt("r_count"));
                cbDto.setD_count(rs.getInt("d_count"));
                CategoryBoardlist.add(cbDto);
            }
            rs.close();
            pstmt.close();
            
            for(CategoryBoardDTO c : CategoryBoardlist) {
                System.out.println(c);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CategoryBoardlist;
    }
}