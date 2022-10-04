package practice;

public class MainExample {
    public static void main(String[] args) {
        Main main = new Main();
        
        double result1 = main.areaRectangle(10);
        double result2 = main.areaRectangle(10, 20);
        
        System.out.println(result1);
        System.out.println(result2);
    }
}