package ch11.sec04;

public class TryWithResourceExample {
	public static void main(String[] args) throws Exception {
		MyResource res1 = null;
		
		try {
			// 리소스 열기
			res1 = new MyResource("res1");
			// 리소스 읽기
			System.out.println(Integer.parseInt(res1.read1()));
			// System.out.println(Integer.parseInt(res1.read2()));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 리소스 닫기
			res1.close();
		}
		
		try(MyResource res2 = new MyResource("res2")) {
			// 리소스 읽기
			System.out.println(Integer.parseInt(res2.read1()));
			// System.out.println(Integer.parseInt(res2.read2()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		MyResource res3 = new MyResource("res3");
		try(res3) {
			// 리소스 읽기
			System.out.println(Integer.parseInt(res3.read1()));
			// System.out.println(Integer.parseInt(res3.read1()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		MyResource res4 = new MyResource("res4");
		MyResource res5 = new MyResource("res5");
		try(res4; res5) {
			// 리소스 읽기
			System.out.println(Integer.parseInt(res4.read1()));
			System.out.println(Integer.parseInt(res4.read1()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
