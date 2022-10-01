// Le Minh Tu B1807606

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class B4_Bai3Server {
	
	public static void main(String[] args) {
		try {
			//Tao UPD Socket
			DatagramSocket ds = new DatagramSocket();
			int i = 0;
			
			//Nhap file
			Scanner sc = new Scanner(System.in);
			System.out.println("Nhap duong dan file: ");
			String filename = sc.nextLine();
			
			//Doc file
			File file = new File(filename);
			FileInputStream f = new FileInputStream(file);
			byte b[] = new byte [60000];
			int len = 60000;
			int n = f.read(b);
			
			//Dong goi
			InetAddress ad = InetAddress.getByName("225.6.7.8");
			int p = 7755;
			DatagramPacket goigui = new DatagramPacket(b,n,ad,p);
			while(true) {
				try {
					//Gui goi phuc vu nhom D: 225.6.7.8 cong 7755
					ds.send(goigui);
					System.out.println("Da gui goi thu " + i++);
					Thread.sleep(30000); //1s = 1000ms
				}
				catch(InterruptedException e) {
					System.out.println("Loi khi tam ngung");
				}
			}
		}
		catch(SocketException e) {
			System.out.println("Khong khoi tao duoc UDP Socket");
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}
