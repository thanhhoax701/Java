// Lê Minh Tú B1807606
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class B1_Bai2Server {
	
	public static void main(String[] args) {
		String str = "";
		int num = 0;
		
		InputStream vao = null;
		OutputStream ra = null;
		try {
			//tao sever socket cong 5500
			ServerSocket svsk = new ServerSocket(5500);
			System.out.println("Server da duoc khoi tao");
			
			while(true) {
				//chap nhan client ket noi
				Socket s = svsk.accept();
				System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " noi ket");
				
				InputStream in = s.getInputStream();
				OutputStream out = s.getOutputStream();
				
				while(true) { //phuc vu 1 client n` lan
					//nhan ky tu tu client
					byte a[] = new byte[10000];
					int n = in.read(a);
					String acp = new String(a,0,n);
					System.out.println("Ket qua la: " + acp);
					
					//ktra dk thoat
					if(acp.equals("EXIT")==true) break;
					
					try {
						num = Integer.parseInt(acp);
						str = String.valueOf(Integer.toBinaryString(num));
						
						//tra kq cho client
						byte b[] = str.getBytes();
						out.write(b);
					}catch(java.lang.NumberFormatException e) {
						str = "Khong phai so nguyen";
						byte b[] = str.getBytes();
						out.write(b);
					}
	
				}
				//dong noi ket
				s.close();
				System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " da thoat");
			}
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi Server");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
