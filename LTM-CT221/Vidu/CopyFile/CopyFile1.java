import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;

public class CopyFile1 {
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
            // Doc noi dung file
            while (true) {
                int ch = f1.read();
                if(ch == -1) break;
                f2.write(ch);
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

