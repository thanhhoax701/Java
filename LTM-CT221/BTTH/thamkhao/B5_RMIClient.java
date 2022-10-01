// Le Minh Tu B1807606

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class B5_RMIClient {

	public static void main(String[] args) {
		try {
			B5_RMIitf file = (B5_RMIitf) Naming.lookup("rmi://127.0.0.1/file");
			Scanner nhap = new Scanner(System.in);
			System.out.print("Nhap dung dan file: ");
			String tenfile = nhap.nextLine();
			int n = file.getLength(tenfile);
			if(n>0) {
				byte b[] = file.getFile(tenfile);
				int len = b.length;
				System.out.print("Nhap duong dan luu file: ");
				String fileluu = nhap.nextLine();
				FileOutputStream fo = new FileOutputStream(fileluu);
				fo.write(b,0,len);
				fo.close();
				System.out.println("Luu file thanh cong");
			} else {
				System.out.println("File khong ton tai");
			}
		}catch (RemoteException e) {
			System.out.println("Loi khoi tao hoc dang ky doi tuong: " + e);
		}catch (FileNotFoundException e) {
			System.out.println("Loi FileOutputStream: " + e);
		}catch (IOException e) {
			System.out.println("Loi viet file: " + e);
		}catch (NotBoundException e) {
			System.out.println("Loi loi khong tim thay RMI: " + e);
		}
		
		
	}

}
