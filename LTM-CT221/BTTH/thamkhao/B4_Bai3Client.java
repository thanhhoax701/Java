// Le Minh Tu B1807606

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class B4_Bai3Client {

	public static void main(String[] args) {
		try {
			//Tao Socket multicast cong 7755
			MulticastSocket ms = new MulticastSocket(7755);
			
			//Tham gia nhom dia chi "225.6.7.8"
			InetAddress ad = InetAddress.getByName("225.6.7.8");
			ms.joinGroup(ad);
			
			//Nhan gui duoc phuc vu
			byte b[] = new byte[60000];
			int len = 60000;
			DatagramPacket goinhan = new DatagramPacket(b,len);
			ms.receive(goinhan);
			
			//Hien thi 
			byte b1[] = goinhan.getData();
			int len1 = goinhan.getLength();
			FileOutputStream f = new FileOutputStream("ketqua.png");
			f.write(b1,0,len1);
			f.close();
			System.out.println("Da ghi file thanh cong");
			
			//Roi nhom
			ms.leaveGroup(ad);
			
			//Dong socket
			ms.close();
		}
		catch(SocketException e) {
			System.out.println("Khong khoi tao duoc Multicast Socket");
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
