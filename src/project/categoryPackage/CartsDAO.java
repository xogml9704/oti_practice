package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.CartsDTO.CartsBoardDTO;

public class CartsDAO {
    
    // 장바구니 목록 출력
    public ArrayList<CartsBoardDTO> CartsBoard(Connection conn, String user_id) {
        ArrayList<CartsBoardDTO> CartsBoardlist = new ArrayList<CartsBoardDTO>();
        try {
            
            String sql = "" +
                    "SELECT book_name, book_publisher, book_price, book_store, cart_qty, (book_price * cart_qty) b_c " +
                    "FROM books b, carts c " +
                    "WHERE b.book_no = c.book_no(+) " +
                    "AND user_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                CartsBoardDTO cbDto = new CartsBoardDTO();
                cbDto.setBook_name(rs.getString("book_name"));
                cbDto.setBook_publisher(rs.getString("book_publisher"));
                cbDto.setBook_price(rs.getInt("book_price"));
                cbDto.setBook_store(rs.getInt("book_store"));
                cbDto.setCart_qty(rs.getInt("cart_qty"));
                cbDto.setB_c(rs.getInt("b_c"));
                CartsBoardlist.add(cbDto);
            }
            rs.close();
            pstmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return CartsBoardlist;
    }
    
    // 장바구니에 추가
    public int CartsBoardPlus(Connection conn, String user_id, int book_no, int cart_qty) {
        int result = 0;
        try {
            String sql = "INSERT INTO carts " +
                    "VALUES (?, ?, ? ) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setInt(2, book_no);
            pstmt.setInt(3, cart_qty);
            result = pstmt.executeUpdate();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    // 장바구니 전체 삭제
    public int CartsBoardAllDelete(Connection conn, String user_id) {
        int result = 0;
    	try {
            String sql = "" +
                    "DELETE FROM carts WHERE user_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_id);
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    // 장바구니 부분 삭제
    public int CartsBoardDelete(Connection conn, String user_id, int book_no) {
        int result = 0;
    	try {
            String sql = "" +
                    "DELETE FROM carts WHERE user_id = ? AND book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setInt(2, book_no);
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    // 장바구니 상품 수량 변경
    public int CartsBoardQty(Connection conn, int cart_qty, String user_id, int book_no) {
        int result = 0;
    	try {
            String sql = "" +
                    "UPDATE carts SET cart_qty = ? WHERE user_id = ? AND book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cart_qty);
            pstmt.setString(2, user_id);
            pstmt.setInt(3, book_no);
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
