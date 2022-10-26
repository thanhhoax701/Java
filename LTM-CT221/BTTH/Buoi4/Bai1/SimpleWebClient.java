import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SimpleWebClient {
	public static void main(String[] args) {
		try {
			// Nhap tu ban phim dia chi va ten tai nguyen
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap dia chi Web Server: ");
			String dcServer = kb.nextLine();
			System.out.print("Nhap ten file can lay: ");
			String tenfile = kb.nextLine();
			// Noi ket den Web Server
			Socket s = new Socket(dcServer, 80);
			// Lay ra 2 stream in-out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os);
			// Gui cau lenh GET
			ps.println("GET /" + tenfile + " HTTP/1.1");
			ps.println("Host: " + dcServer);
			ps.println("User-Agent: Mozilla/5.0 (Windows NT 10.0; rv:105.0) Gecko/20100101 Firefox/105.0");
			ps.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
			ps.println("Accept-Language: en-US,en;q=0.5");
			ps.println("Accept-Encoding: gzip, deflate, br");
			ps.println("Connection: keep-alive");
			ps.println("Upgrade-Insecure-Requests: 1");
			ps.println("Sec-Fetch-Dest: document");
			ps.println("Sec-Fetch-Mode: navigate");
			ps.println("Sec-Fetch-Site: none");
			ps.println("Sec-Fetch-User: ?1");
			ps.println();
			// Nhan ket qua tra ve va hien thi
			DataInputStream dis = new DataInputStream(is);
			byte b[] = new byte[1000];
			while (true) {
				int n = dis.read(b);
				if (n == -1)
					break;
				String str = new String(b, 0, n);
				System.out.print(str);
			}
			// Dong noi ket
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
