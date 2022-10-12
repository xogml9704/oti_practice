package practice;

public class Main {
    // private 접근 권한을 갖는 정적 필드 선언과 초기화
    private static Main singleton = new Main();
    
    // private 접근 권한을 갖는 생성자 선언
    private Main() {
        
    }
    
    // public 접근 권한을 갖는 정적 메소드 선언
    public static Main getInstance() {
        return singleton;
    }
}