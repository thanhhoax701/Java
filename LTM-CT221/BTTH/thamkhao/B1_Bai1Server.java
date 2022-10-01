// Lê Minh Tú B1807606
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class B1_Bai1Server {
	
	public static void main(String[] args) {
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
					int kytu = in.read();
					System.out.println("Ket qua la: " + kytu);
					
					//ktra dk thoat
					if(kytu=='@') break;
					String kq = "Khong phai so nguyen";
					switch(kytu) {
						case '0': kq = "Khong"; break;
						case '1': kq = "Mot"; break;
						case '2': kq = "Hai"; break;
						case '3': kq = "Ba"; break;
						case '4': kq = "Bon"; break;
						case '5': kq = "Nam"; break;
						case '6': kq = "Sau"; break;
						case '7': kq = "Bay"; break;
						case '8': kq = "Tam"; break;
						case '9': kq = "Chin";
					}
					//tra kq cho client
					byte b[] = kq.getBytes();
					out.write(b);
				}
				//dong noi ket
				s.close();
				System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " da thoat");
			}
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
