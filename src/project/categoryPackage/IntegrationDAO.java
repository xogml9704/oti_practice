package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.IntegrationDTO.AuthorDTO;
import project.categoryPackage.IntegrationDTO.HashDTO;
import project.categoryPackage.IntegrationDTO.IntegrationDTO;

public class IntegrationDAO {
	/*
	public static void main(String[] args) {
		IntegrationDAO id = new IntegrationDAO();
		id.Integration(ConnectionProvider.getConnection(), "주먹");
	}
	*/
	
	// 통합 검색 메소드
	public ArrayList<IntegrationDTO> Integration(Connection conn, String search) {
		ArrayList<IntegrationDTO> IntegrationList = new ArrayList<IntegrationDTO>();
		try {
			String sql = "SELECT b.book_no, b.book_name, b.book_publisher, b.book_price, avg(review_score) reviews_avg " +
					"FROM books b, author_book ab, authors a, reviews r, dibs d, book_hash h " +
					"WHERE b.book_no = ab.book_no " +
					"AND a.author_no = ab.author_no " +
					"AND r.book_no(+) = b.book_no " +
					"AND d.book_no(+) = b.book_no " +
					"AND h.book_no(+) = b.book_no " +
					"AND (book_name LIKE ? OR author_name LIKE ? OR book_publisher LIKE ? OR hash_id LIKE ?) " +
					"GROUP BY b.book_no, b.book_name, b.book_publisher, b.book_price ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				IntegrationDTO iDto = new IntegrationDTO();
				iDto.setBook_no(rs.getInt("book_no"));
				iDto.setBook_name(rs.getString("book_name"));
				iDto.setBook_publisher(rs.getString("book_publisher"));
				iDto.setBook_price(rs.getInt("book_price"));
				iDto.setReviews_avg(rs.getInt("reviews_avg"));
				int book_no = iDto.getBook_no();
				
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
				iDto.setAuthor_name(authorlist);
				iDto.setHash_id(hashlist);
				IntegrationList.add(iDto);
			}
			rs.close();
			pstmt.close();
			
			// 보여주기
			for(IntegrationDTO i : IntegrationList) {
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
		return IntegrationList;
	}
}
