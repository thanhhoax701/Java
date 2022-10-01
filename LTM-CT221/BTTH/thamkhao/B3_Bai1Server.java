// Lê Minh Tú B1807606
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class B3_Bai1Server {
	
	public static void main(String args[]) {
		try {
			// Tao UDP Socket cong 13
			DatagramSocket ds = new DatagramSocket(13);
			
			// Nhan goi yeu cau tu client
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b,60000);
			while(true) {
				ds.receive(goinhan);
				
				// Xu ly yeu cau
				LocalDate today = LocalDate.now();
				LocalTime then = LocalTime.now();
				String kq = today + " " + then;
				String ngay = kq.substring(0,19);
				
				// Dong goi ket qua
				byte b2[] = ngay.getBytes();
				int len2 = b2.length;
				InetAddress dc2 = goinhan.getAddress();
				int p2 = goinhan.getPort();
				DatagramPacket goigui = new DatagramPacket(b2, len2, dc2, p2);
				// Gui ket qua cho Client
				ds.send(goigui);
			}
		}
		catch(SocketException e) {
			System.out.println("Khoi tao Socket Client that bai !!");
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
}
