import java.util.Scanner;

public class hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		System.out.println("Hello World!!!");
		System.out.println("Nhap n: ");
		String n = kb.nextLine();	
		// Thuc hien yeu cau
		String kq = n.toUpperCase();
		System.out.println("Ket qua: "+ kq);
	}
}
