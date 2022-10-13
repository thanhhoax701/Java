import java.io.*;
import java.net.*;
import java.time.*;

public class ServerDate {
    public static void main(String[] args) {
        try {
			// Tao UDP Socket cong 510
			DatagramSocket ds = new DatagramSocket(510);
			
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