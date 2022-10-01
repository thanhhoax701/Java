// Lê Minh Tú B1807606
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class B2_Bai1Server {

	public static void main(String[] args) {
		try {
			ServerSocket svsk = new ServerSocket(5500);
			System.out.println("Server da duoc khoi tao");
			while(true) {
				Socket s = svsk.accept();
				System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " noi ket");
				B2_Bai1Thread serviceProcess = new B2_Bai1Thread(s);
				serviceProcess.start();
			}
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
		
	}

}
