package project.categoryPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import project.categoryPackage.BuyDTO.UserOrder;
import project.categoryPackage.BuyDTO.UserOrderDetail;


public class BuyDAO {
    // 바로구매 + 장바구니 구매 처리 dao
        public boolean Buy(Connection conn, UserOrder userOrder, ArrayList<UserOrderDetail> list) {
            boolean requestDone = true;
            try {
                // 트랜잭션 처리
                conn.setAutoCommit(false);
                
                // 1. orderDto를 orders 테이블에 한 행 추가
                String sql = "insert into orders values" + " (seq_order_no.nextval, sysdate, ?, ?, ?, ?, ?, 'Y')";
                PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "order_no" });
                pstmt.setString(1, userOrder.getUser_id());
                pstmt.setString(2, userOrder.getOrder_receivename());
                pstmt.setString(3, userOrder.getOrder_tel());
                pstmt.setString(4, userOrder.getOrder_address());
                pstmt.setString(5, userOrder.getOrder_memo());
                
                // order 레코드 추가 실패 = 트랜잭션 전체의 실패 -> rollback
                if (pstmt.executeUpdate() == 0) {
                    requestDone = false;
                    throw new Exception();
                }
                
                ResultSet rs = pstmt.getGeneratedKeys();
                int originalOrderNo = 0;
                if (rs.next()) {
                    originalOrderNo = rs.getInt(1);
                }

                // 2. orderdetails 테이블에 list안에 각 요소들 추가(테이블 칼럼형식에 맞게)
                for (UserOrderDetail od : list) {
                    sql = "insert into orderdetails values(?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, originalOrderNo);
                    pstmt.setInt(2, od.getBook_no());
                    pstmt.setInt(3, od.getOd_qty());

                    // orderdetails 레코드 추가 실패 = 트랜잭션 전체의 실패 -> rollback
                    if (pstmt.executeUpdate() == 0) {
                        requestDone = false;
                        throw new Exception();
                    }
                }

                // 3. warehousing 테이블에 list안에 각 요소들 추가(테이블 칼럼형식에 맞게)
                for (UserOrderDetail od : list) {
                    sql = "insert into warehousing values(seq_ware_no.nextval, ?, sysdate, ?, 'OUT')";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, od.getBook_no());
                    pstmt.setInt(2, od.getOd_qty());

                    // warehousing 레코드 추가 실패 = 트랜잭션 전체의 실패 -> rollback
                    if (pstmt.executeUpdate() == 0) {
                        requestDone = false;
                        throw new Exception();
                    }
                }
                
                // 4. books테이블에 list안에 각 요소들 참고해서 book_store컬럼 빼기 처리
                for (UserOrderDetail od : list) {
                    sql = "update books set book_store = book_store - ? where book_no = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, od.getOd_qty());
                    pstmt.setInt(2, od.getBook_no());

                    // book_store 컬럼값 변경 실패 = 트랜잭션 전체의 실패 -> rollback
                    if (pstmt.executeUpdate() == 0) {
                        requestDone = false;
                        throw new Exception();
                    }
                }
                conn.commit();
            } catch (Exception e) {
                System.out.println("요청한 작업이 정상적으로 처리되지 못했습니다.");
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        //하나의 커넥션 객체를 이용하기 때문에 오토커밋 다시 원상복구
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return requestDone;
        }
    }
