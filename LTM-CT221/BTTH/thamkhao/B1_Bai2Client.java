// Lê Minh Tú B1807606
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class B1_Bai2Client {

	public static void main(String[] args) {
		try {
			//noi ket den server cong 5500
			Socket s = new Socket("localhost", 5500);
			System.out.println("Da noi ket thanh cong");
			
			//lay ra 2 stream in-out
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			while(true) {
				//nhap ky tu tu ban phim
				System.out.print("Nhap vao chuoi so nguyen: ");
				Scanner sc = new Scanner(System.in);
				String str = sc.nextLine();
				byte a[] = str.getBytes();
				//gui den sv
				out.write(a);
				
				if(str.equals("EXIT")==true) break;
				
				//tra kq tu sv ve gom n ky tu
				byte b[] = new byte[10000];
				int n = in.read(b);
				
				//hien thi kq
				String kq = new String(b,0,n);
				System.out.println("Ket qua la: " + kq);
			}
			//dong noi ket
			s.close();
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}
