import java.net.*;
import java.io.*;
import java.util.Scanner;

public class FakeWebServer {
	public static void main(String[] args) {
		try {
			// Tao Server Socket cong 80
			ServerSocket ss = new ServerSocket(80);
			System.out.println("Da khoi tao xong Server Socket cong 80");
			// Chap nhan cho noi ket
			Socket s = ss.accept();
			System.out.println("Co 1 Client noi ket");
			// Lay ra 2 stream in-out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(is);
			// Nhan cau lenh GET tu Client
			while (true) {
				String str = sc.nextLine();
				System.out.println(str);
				if (str.equals(""))
					break;
			}
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
