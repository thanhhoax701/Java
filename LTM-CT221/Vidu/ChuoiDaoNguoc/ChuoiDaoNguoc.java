import java.util.Scanner;
import java.lang.String;

public class ChuoiDaoNguoc {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Nhap vao 1 chuoi: ");
        String str = kb.nextLine();
        // Tao ra chuoi dao nguoc
        int len = str.length();
        String kq = "";
        for (int i = len - 1; i >= 0; i--) {
            kq = kq + str.charAt(i);
            System.out.println("Chuoi da duoc dao nguoc la: " + kq);
        }
    }
}
