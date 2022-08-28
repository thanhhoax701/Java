import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.*;

public class DocFile{
    public static void main (String args[]) {
        try {
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap ten file can doc: ");
            String tenfile = kb.nextLine();
            // Mo file 
            FileInputStream f = new FileInputStream(tenfile);   
            System.out.println("Noi dung file: ");
            while(true) {
                int ch = f.read();
                if (ch == 1) break;
                System.out.println((char) ch);
            }
            f.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File khong ton tai");
        }
        catch (IOException e) {
			System.out.println("Loi nhap xuat file");
		}
    }
}

