import java.util.Scanner;
import java.lang.Math;

public class LuyThua {
    public static void main (String args[]) {
        Scanner kb = new Scanner(System.in);
		System.out.println("Nhap vao so thuc x: ");
		float x = kb.nextFloat();	
        System.out.println("Nhap vao so nguyen n: ");
		int n = kb.nextInt();	
        // Tinh luy thua x mu n
        float kq = (float) Math.pow(x, n);
        // Hien thi ket qua ra man hinh
        System.out.println(x + "^" + n + "=" + kq);
    }
}
