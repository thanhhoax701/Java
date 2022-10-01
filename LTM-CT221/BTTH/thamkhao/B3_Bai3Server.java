// Lê Minh Tú B1807606

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class B3_Bai3Server {
	public static void main(String[] args) {
		try {
			// Tao UDP Socket cong 5500
			DatagramSocket ds = new DatagramSocket(5500);
			
			// Nhan goi yeu cau tu client
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b,60000);
			Scanner kb = new Scanner(System.in);
			while(true) {
				ds.receive(goinhan);
				
				// Xu ly yeu cau
				byte b1[] = goinhan.getData();
				int len1 = goinhan.getLength();
				String chuoi = new String(b1, 0, len1);
				System.out.println("Client : " + chuoi);
				
				// Nhap chuoi tu ban phim
				System.out.print("Server : ");
				String ketqua = kb.nextLine();
				
				// Dong goi ket qua
				byte b2[] = ketqua.getBytes();
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
