import java.util.Scanner;

public class DoiNhiPhan {
	public static void main (String args[]) {
        Scanner kb = new Scanner(System.in);
		System.out.println("Nhap vao: ");
		Integer n = kb.nextInt();	
		// Thuc hien yeu cau
		// Chuyen tu thap phan sang nhi phan
		System.out.println("Ket qua: "+ Integer.toBinaryString(n));
    }
}
