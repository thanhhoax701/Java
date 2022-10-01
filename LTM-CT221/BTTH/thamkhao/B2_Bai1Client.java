// Lê Minh Tú B1807606
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class B2_Bai1Client {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 5500);
			System.out.println("Da noi ket thanh cong");
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.print("Nhap vao ho ten: ");
				String hoten = sc.nextLine();
				out.write(hoten.getBytes());
				if(hoten.equals("exit")) break;
				byte b[] = new byte[500];
				int n = in.read(b);
				String kq = new String(b,0,n);
				System.out.println("Ket qua la: " + kq);
			}
			s.close();
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi Server");
		}
		catch(IOException ex) {
			System.out.println("Loi nhap xuat");
		}

	}

}
