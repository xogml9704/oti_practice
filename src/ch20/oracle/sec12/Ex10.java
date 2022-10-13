package ch20.oracle.sec12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex10 {
    // Field
    private Scanner scanner = new Scanner(System.in);
    private Connection conn;
    private boolean logInOut = true;
    private String name;
    
    // Constructor
    public Ex10() {
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");
            
            // 연결하기
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl",
                    "java",
                    "oracle"
                    );
            
        } catch (Exception e) {
            e.printStackTrace();
            exit();
        }
    }
    // Method
    public void list() {
        System.out.println();
        if(logInOut) {
            System.out.println("[게시물 목록]");
        } else {
            System.out.printf("[게시물 목록] 사용자 : %s", name);
            System.out.println();
        }
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
        System.out.println("----------------------------------------------------------");
        
        try {
            String sql = "SELECT bno, btitle, bcontent, bwriter, bdate from boards order by bno desc";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Board board = new Board();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));
                System.out.printf("%-6s%-12s%-16s%-40s \n", 
                        board.getBno(),
                        board.getBwriter(),
                        board.getBdate(),
                        board.getBtitle());
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            exit();
        }
        // 메인 메뉴 출력
        mainMenu();
    }
    
    public void mainMenu() {
        System.out.println();
        System.out.println("----------------------------------------------------------");
        if(logInOut) {
            System.out.println("메인 메뉴 : 1.Create | 2.Read | 3.Clear | 4.Join | 5.Login | 6.Exit");
            System.out.print("메뉴 선택 : ");
            String menuNo = scanner.nextLine();
            System.out.println();
            
            switch(menuNo) {
                case "1" -> create();
                case "2" -> read();
                case "3" -> clear();
                case "4" -> join();
                case "5" -> login();
                case "6" -> exit();
            }
        } else {
            System.out.println("메인 메뉴 : 1.Create | 2.Read | 3.Clear | 4.Logout | 5.Exit");
            System.out.print("메뉴 선택 : ");
            String menuNo = scanner.nextLine();
            System.out.println();
            
            switch(menuNo) {
                case "1" -> create();
                case "2" -> read();
                case "3" -> clear();
                case "4" -> logout();
                case "5" -> exit();
            }
        }
    }
    
    public void create() {
        // 입력 받기
        Board board = new Board();
        System.out.println("[새 게시물 입력]");
        System.out.print("제목 : ");
        board.setBtitle(scanner.nextLine());
        System.out.print("내용 : ");
        board.setBcontent(scanner.nextLine());
        if(logInOut) {
            System.out.print("작성자 : ");
            board.setBwriter(scanner.nextLine());
        } else {
            board.setBwriter(name);
        }
        
        // 보조 메뉴 출력
        System.out.println("----------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택 : ");
        String menuNo = scanner.nextLine();
        if(menuNo.equals("1")) {
            // boards 테이블에 게시물 정보 저장
            try {
                String sql = "INSERT INTO boards (bno, btitle, bcontent, bwriter, bdate) values (SEQ_BNO.NEXTVAL, ?, ?, ?, SYSDATE)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, board.getBtitle());
                pstmt.setString(2, board.getBcontent());
                pstmt.setString(3, board.getBwriter());
                pstmt.executeUpdate();
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                exit();
            }
        }
        list();
    }
    
    public void read() {
        // 입력 받기
        System.out.println("[게시물 읽기]");
        System.out.print("bno : ");
        int bno = Integer.parseInt(scanner.nextLine());
        if(logInOut) {
            list();
        } else {
            // boards 테이블에서 해당 게시물을 가져와 출력
            try {
                String sql = "SELECT bno, btitle, bcontent, bwriter, bdate FROM boards WHERE bno=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, bno);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    Board board = new Board();
                    if(name.equals(rs.getString("bwriter"))) {
                        board.setBno(rs.getInt("bno"));
                        board.setBtitle(rs.getString("btitle"));
                        board.setBcontent(rs.getString("bcontent"));
                        board.setBwriter(rs.getString("bwriter"));
                        board.setBdate(rs.getDate("bdate"));
                        System.out.println("###############");
                        System.out.println("번호 : " + board.getBno());
                        System.out.println("제목 : " + board.getBtitle());
                        System.out.println("내용 : " + board.getBcontent());
                        System.out.println("작성자 : " + board.getBwriter());
                        System.out.println("날짜 : " + board.getBdate());
                        
                        // 보조 메뉴 출력
                        System.out.println("----------------------------------------------------------");
                        System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3.List");
                        System.out.print("메뉴 선택 : ");
                        String menuNo = scanner.nextLine();
                        
                        if(menuNo.equals("1")) {
                            update(board);
                        } else if(menuNo.equals("2")) {
                            delete(board);
                        }
                        System.out.println("###############");
                    }
                }
                rs.close();
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                exit();
            }
            list();
        }
    }
    
    public void update(Board board) {
        // 수정 내용 입력 받기
        System.out.println("[수정 내용 입력]");
        System.out.print("제목 : ");
        board.setBtitle(scanner.nextLine());
        System.out.print("내용 : ");
        board.setBtitle(scanner.nextLine());
        
        // 보조 메뉴 출력
        System.out.println("----------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel ");
        System.out.print("메뉴 선택 : ");
        String menuNo = scanner.nextLine();
        if(menuNo.equals("1")) {
            // boards 테이블에서 게시물 정보 수정
            try {
                String sql = "UPDATE boards SET btitle=?, bcontent=? WHERE bno=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, board.getBtitle());
                pstmt.setString(2, board.getBcontent());
                pstmt.setInt(3, board.getBno());
                pstmt.executeUpdate();
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                exit();
            }
        }
        list();
    }
    
    public void delete(Board board) {
        // boards 테이블에 게시물 정보 삭제
        try {
            String sql = "DELETE FROM boards WHERE bno=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board.getBno());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            exit();
        }
        list();
    }
    
    public void clear() {
        System.out.println("[게시물 전체 삭제]");
        System.out.println("----------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel ");
        System.out.print("메뉴 선택 : ");
        String menuNo = scanner.nextLine();
        if(menuNo.equals("1")) {
            // boards 테이블에 게시물 정보 전체 삭제
            try {
                String sql = "TRUNCATE TABLE boards";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                exit();
            }
        }
        list();
    }
    
    public void join() {
        User user = new User();
        System.out.println("[새 사용자 입력]");
        System.out.print("아이디 : ");
        user.setUserid(scanner.nextLine());
        System.out.print("이름 : ");
        user.setUsername(scanner.nextLine());
        System.out.print("비밀번호 : ");
        user.setUserpassword(scanner.nextLine());
        System.out.print("나이 : ");
        user.setUserage(Integer.parseInt(scanner.nextLine()));
        System.out.print("이메일 : ");
        user.setUseremail(scanner.nextLine());
        
        System.out.println("----------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel");
        System.out.print("메뉴 선택 : ");
        String menuNo = scanner.nextLine();
        if(menuNo.equals("1")) {
            // User 테이블에 게시물 정보 저장
            try {
                String sql = "INSERT INTO users (userid, username, userpassword, userage, useremail) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUserid());
                pstmt.setString(2, user.getUsername());
                pstmt.setString(3, user.getUserpassword());
                pstmt.setInt(4, user.getUserage());
                pstmt.setString(5, user.getUseremail());
                pstmt.executeUpdate();
                pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
                exit();
            }
        }
        list();
    }
    
    public void login() {
        // login 정보 입력 받기
        System.out.println("[로그인]");
        System.out.print("아이디 : ");
        name = scanner.nextLine();
        System.out.print("비밀번호 : ");
        String userpassword = scanner.nextLine();
        
        // 보조 메뉴 출력
        System.out.println("----------------------------------------------------------");
        System.out.println("보조 메뉴 : 1.Ok | 2.Cancel ");
        System.out.print("메뉴 선택 : ");
        String menuNo = scanner.nextLine();
        if(menuNo.equals("1")) {
            // user 테이블에서 게시물 정보 비교
            try {
                String sql = "SELECT userpassword from users where userid=?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                
                if(rs.next()) {
                    String userpassword2 = rs.getString("userpassword");
                    if(userpassword.equals(userpassword2)) {
                        logInOut = false;
                    } else {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                    }
                    
                } else {
                    System.out.println("아이디가 존재하지 않습니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                exit();
            }
        }
        list();
    }
    
    public void logout() {
        logInOut = true;
        list();
    }
    
    public void exit() {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
        System.out.println("** 게시판 종료 **");
        System.exit(0);
    }
    public static void main(String[] args) {
        Ex10 boardExample = new Ex10();
        boardExample.list();
    }

}