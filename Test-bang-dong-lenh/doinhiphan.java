import java.util.Scanner;

public class DoiNhiPhan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		System.out.println("Hello!!!");
		System.out.println("Nhap vao: ");
		Integer n = kb.nextInt();	
		// Thuc hien yeu cau
		// Chuyen tu thap phan sang nhi phan
		System.out.println("Ket qua: "+ Integer.toBinaryString(n));
	}
}
