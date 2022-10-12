package practice;

public class MainExample {
    public static void main(String[] args) {
        // 객체 생성 및 자동 타입 변환
        Main main = new Main2();
        
        main.field1 = "data1";
        main.method1();
        main.method2();
        
        Main2 main2 = (Main2) main;
        
        main2.filed2 = "data2";
        main2.method3();
    }
}