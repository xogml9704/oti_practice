package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.CategoryDTO.CategoryBoardDTO;
import project.categoryPackage.CategoryDTO.CategoryDTO;
import project.categoryPackage.CategoryDTO.SubCategoryDTO;
import project.categoryPackage.IntegrationDTO.AuthorDTO;
import project.categoryPackage.IntegrationDTO.HashDTO;

public class CategoryDAO {
	
    // 카테고리 페이지 조회
    public ArrayList<CategoryDTO> category(Connection conn) {
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
    public ArrayList<SubCategoryDTO> subcategory(Connection conn, int category_no) {
        ArrayList<SubCategoryDTO> SubCategoryList = new ArrayList<SubCategoryDTO>();
        try {
            String sql = "" +
                    "SELECT sub_no, sub_name " +
                    "FROM subcategory s, category c " +
                    "WHERE s.category_no = c.category_no " +
                    "AND c.category_no = ? " +
                    "ORDER BY c.category_no ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, category_no);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                SubCategoryDTO sDto = new SubCategoryDTO();
                sDto.setSub_no(rs.getInt("sub_no"));
                sDto.setSub_name(rs.getString("sub_name"));
                SubCategoryList.add(sDto);
            }
            rs.close();
            pstmt.close();
            
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
    public ArrayList<CategoryBoardDTO> CategoryBoard(Connection conn, int sub_no) {
        ArrayList<CategoryBoardDTO> CategoryBoardlist = new ArrayList<CategoryBoardDTO>();
        try {
			String sql = "SELECT b.book_no, b.book_name, b.book_publisher, b.book_price, avg(review_score) reviews_avg " +
					"FROM books b, author_book ab, authors a, reviews r, dibs d, book_hash h " +
					"WHERE b.book_no = ab.book_no " +
					"AND a.author_no = ab.author_no " +
					"AND r.book_no(+) = b.book_no " +
					"AND d.book_no(+) = b.book_no " +
					"AND h.book_no(+) = b.book_no " +
					"AND b.sub_no = ? " +
					"GROUP BY b.book_no, b.book_name, b.book_publisher, b.book_price ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sub_no);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                CategoryBoardDTO cbDto = new CategoryBoardDTO();
				cbDto.setBook_no(rs.getInt("book_no"));
				cbDto.setBook_name(rs.getString("book_name"));
				cbDto.setBook_publisher(rs.getString("book_publisher"));
				cbDto.setBook_price(rs.getInt("book_price"));
				cbDto.setReviews_avg(rs.getInt("reviews_avg"));
				int book_no = cbDto.getBook_no();
				
				// 이 책 번호로 등록된 저자 정보
				ArrayList<AuthorDTO> authorlist = new ArrayList<AuthorDTO>();
				try {
					String sql2 = "SELECT author_name " +
							"FROM books b, author_book ab, authors a " +
							"WHERE b.book_no = ab.book_no " +
							"AND a.author_no = ab.author_no " +
							"AND b.book_no = ? ";
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, book_no);
					
					ResultSet rs2 = pstmt.executeQuery();
					
					while(rs2.next()) {
						AuthorDTO aDto = new AuthorDTO();
						aDto.setAuthor_name(rs2.getString("author_name"));
						authorlist.add(aDto);
					}
					rs2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// 이 책 번호로 등록된 해시태그 정보
				ArrayList<HashDTO> hashlist = new ArrayList<HashDTO>();
				try {
					String sql3 = "SELECT hash_id " +
							"FROM books b, book_hash h " +
							"WHERE b.book_no = h.book_no " +
							"AND b.book_no = ? ";
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, book_no);
					
					ResultSet rs3 = pstmt.executeQuery();
					while(rs3.next()) {
						HashDTO hDto = new HashDTO();
						hDto.setHash_id(rs3.getString("hash_id"));
						hashlist.add(hDto);
					}
					rs3.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				cbDto.setAuthor_name(authorlist);;
				cbDto.setHash_id(hashlist);
				CategoryBoardlist.add(cbDto);
			}
			rs.close();
			pstmt.close();
			
			// 보여주기
			for(CategoryBoardDTO i : CategoryBoardlist) {
				System.out.println(i);
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