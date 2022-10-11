package practice;

public class MainExample {
    public static void main(String[] args) {
        // 객체 생성
        Main main = new Main();
        
        main.setSpeed(100);
        System.out.println(main.getSpeed());
        
        if(!main.isStop()) {
            main.setStop(true);
        }
        System.out.println(main.getSpeed());
    }
}