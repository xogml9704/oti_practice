package practice;

public class MainExample {
	public static void main(String[] args) {
		
		String phone_number = "12345678";
		
		String[] empty=phone_number.split("");
		String empty2="";
		int i =0;
		int j =0;
		for(i=0; i<empty.length-4; i++) {
			
			empty[i]="*";
			empty2+=empty[i];
	      
		}
		for(j=empty.length-4; j<empty.length; j++) {
			empty2+=empty[j];
		}
		
		System.out.println(empty2);
	}
}