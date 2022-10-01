// Le Minh Tu B1807606

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class B4_Bai2 {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);

			// Nhap vao dia chi smtp server
			System.out.print("Nhap vao dia chi smtp server: ");
			String dc = kb.nextLine();

			// Nhap dia chi gui
			System.out.print("Nhap vao dia chi gui: ");
			String dcGui = kb.nextLine();

			// Nhap dia chi nhan
			System.out.print("Nhap vao dia chi nhan: ");
			String dcNhan = kb.nextLine();

			// Nhap tieu de email
			System.out.print("Nhap vao tieu de email: ");
			String td = kb.nextLine();

			// Nhap noi dung email
			System.out.print("Nhap vao noi dung email: ");
			String nd = kb.nextLine();

			System.out.println("Dang ket noi...");
			// Tao socket cong so 587
			Socket s = new Socket(dc, 587);
			System.out.println("Da ket noi thanh cong");

			// Lay stream in out chuyen ve scanner printwriter
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(is);
			PrintWriter pw = new PrintWriter(os, true); // auto flush

			// Nhan dong tra ve dau tien tu server
			String kq = sc.nextLine();
			System.out.println(kq);

			// Lay dia chi local
			InetAddress localhost = InetAddress.getLocalHost();

			// Gui helo
			pw.println("HELO " + localhost.getHostName());
			System.out.println("HELO " + localhost.getHostName());
			kq = sc.nextLine();
			System.out.println(kq);

			// Gui mail from
			pw.println("MAIL FROM: " + dcGui);
			System.out.println("Mail from: " + dcGui);
			kq = sc.nextLine();
			System.out.println(kq);

			// Gui rcpt to
			pw.println("RCPT TO: " + dcNhan);
			System.out.println("RCPT TO: " + dcNhan);
			kq = sc.nextLine();
			System.out.println(kq);

			// Gui data
			pw.println("DATA");
			System.out.println("DATA");
			kq = sc.nextLine();
			System.out.println(kq);

			pw.println("From: " + dcGui);
			System.out.println("From: " + dcGui);
			pw.println("To: " + dcNhan);
			System.out.println("To: " + dcNhan);
			pw.println("Subject: " + td);
			System.out.println("Subject: " + td);
			pw.println("Body: " + nd);
			System.out.println("Body: " + nd);
			
			// Gui .
			pw.println(".");
			System.out.println(".");
			kq = sc.nextLine();
			System.out.println(kq);

			// Gui quit
			pw.println("QUIT");
			System.out.println("QUIT");
			kq = sc.nextLine();
			System.out.println(kq);
			
			System.out.println("Da gui email thanh cong");
			// Dong socket
			s.close();
		} catch (SocketException e) {
			System.out.println("Loi socket: " + e);
		} catch (IOException e) {
			System.out.println("Loi nhap xuat: " + e);
		}
	}

}
