import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.*;

public class DocFileScanner {
    public static void main (String args[]) {
        try {
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap ten file can doc: ");
            String tenfile = kb.nextLine();
            // Mo file 
            FileInputStream f = new FileInputStream(tenfile);
            Scanner sc = new Scanner(f);
            System.out.println("Noi dung file: ");
            try {
                while(true) {
                    String str = sc.nextLine();
                    System.out.println(str);
                }
            }
            catch(NoSuchElementException e) {
                System.out.println("Da doc den cuoi file");
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
