package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.CartsDTO.CartsBoardAllDeleteDTO;
import project.categoryPackage.CartsDTO.CartsBoardDTO;
import project.categoryPackage.CartsDTO.CartsBoardDeleteDTO;
import project.categoryPackage.CartsDTO.CartsBoardPlusDTO;
import project.categoryPackage.CartsDTO.CartsBoardQtyDTO;

public class CartsDAO {
    
    // 장바구니 목록 출력
    public static ArrayList<CartsBoardDTO> CartsBoard(Connection conn, String user_id) {
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
            
            // 나중에 삭제
            for(CartsBoardDTO i : CartsBoardlist) {
                System.out.println(i);
            }
            
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
    // 장바구니 상세 목록
    
    // 장바구니에 추가
    public boolean CartsBoardPlus(Connection conn, CartsBoardPlusDTO cartsboardplusDTO) {
        boolean requestOrder = true;
        try {
            // 트랜잭션 처리
            conn.setAutoCommit(false);
            
            String sql = "INSERT INTO carts " +
                    "VALUES (?, ?, ? ) ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cartsboardplusDTO.getUser_id());
            pstmt.setInt(2, cartsboardplusDTO.getBook_no());
            pstmt.setInt(3, cartsboardplusDTO.getCart_qty());
            pstmt.executeUpdate();
            pstmt.close();
            
            // 장바구니 추가 실패 = 트랜잭션 실패 -> rollback
            if(pstmt.executeUpdate() == 0) {
                requestOrder = false;
            } throw new Exception();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn != null) {
                    // 오토커밋 다시 복구
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return requestOrder;
    }
    // 장바구니 전체 삭제
    public boolean CartsBoardAllDelete(Connection conn, CartsBoardAllDeleteDTO cartsboardalldeleteDTO) {
        try {
            String sql = "" +
                    "DELETE FROM carts WHERE user_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cartsboardalldeleteDTO.getUser_id());
            pstmt.executeUpdate();
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
        return true;
    }
    
    // 장바구니 부분 삭제
    public boolean CartsBoardDelete(Connection conn, CartsBoardDeleteDTO cartsboarddeleteDTO) {
        try {
            String sql = "" +
                    "DELETE FROM carts WHERE user_id = ? AND book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cartsboarddeleteDTO.getUser_id());
            pstmt.setInt(2, cartsboarddeleteDTO.getBook_no());
            pstmt.executeUpdate();
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
        return true;
    }
    
    // 장바구니 상품 수량 변경
    public boolean CartsBoardQty(Connection conn, CartsBoardQtyDTO cartsboardqtyDTO) {
        try {
            String sql = "" +
                    "UPDATE carts SET cart_qty = ? WHERE user_id = ? AND book_no = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cartsboardqtyDTO.getUser_id());
            pstmt.setInt(2, cartsboardqtyDTO.getBook_no());
            pstmt.executeUpdate();
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
        return true;
    }
}
