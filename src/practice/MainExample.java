package practice;

public class MainExample {
    public static void main(String[] args) {
        Main obj1 = Main.getInstance();
        Main obj2 = Main.getInstance();
        
        // 동일한 객체를 참조하는지 확인
        if(obj1 == obj2) {
            System.out.println("같은 SIngleton 객체입니다.");
        } else {
            System.out.println("다른 Singleton 객체입니다.");
        }
    }
}