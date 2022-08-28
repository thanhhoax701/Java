import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;

public class CopyFile3 {
	public static void main (String args[]) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file can doc: ");
            String tenfiledoc = kb.nextLine();
            System.out.println("Nhap ten file can ghi: ");
            String tenfileghi = kb.nextLine();
            // Mo file
            FileInputStream f1 = new FileInputStream(tenfiledoc);
            FileOutputStream f2 = new FileOutputStream(tenfileghi);
            // Tao ra buffer
            File f = new File(tenfiledoc);
            int len = (int) f.length();
            System.out.println("Kich thuoc file: " + len);
            byte b[] = new byte[50000];
            int tongbytedadoc =  0;
            while(true) {
                // Doc duoc n byte
                int n = f1.read(b);
                System.out.println("Da doc den vi tri: " + tongbytedadoc);
                tongbytedadoc += n;
                // Ghi duoc n byte
                f2.write(b, 0, n);
                if(tongbytedadoc == len) break;
            }
            // Dong file
            f1.close();
            f2.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Khong tim thay file");
		}
		catch (IOException e) {
			System.out.println("Loi nhap xuat file");
		}
	}

}
