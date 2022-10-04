package ch18.sec10;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectOutputExample {
    public static void main(String[] args) throws Exception {
        // FileOutputStream에 objectOutputStream 보조 스트림 연결
        FileOutputStream fos = new FileOutputStream("C:/Temp/object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        // 객체 생성
        Member m1 = new Member("fail", "단풍이");
        Product p1 = new Product("노트북", 1500000);
        int[] arr1 = {1, 2, 3};
        
        // 객체를 직렬화 해서 저장
        oos.writeObject(m1);
        oos.writeObject(p1);
        oos.writeObject(arr1);
        
        oos.flush();
        oos.close();
        fos.close();
    }
}
