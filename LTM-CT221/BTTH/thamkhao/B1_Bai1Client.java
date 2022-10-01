// Lê Minh Tú B1807606
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class B1_Bai1Client {

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
				System.out.print("Nhap vao 1 ky tu: ");
				int num = System.in.read();
				
				//gui den sv
				out.write(num);
				System.in.skip(2);//bo ky tu \r\n
				if(num=='@') break;
				
				//tra kq tu sv ve gom n ky tu
				byte b[] = new byte[100];
				int n = in.read(b);
				
				//hien thi kq
				String kq = new String(b,0,n);
				System.out.println("Ket qua la: " + kq);
			}
			//dong noi ket
			s.close();
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi Server");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}
