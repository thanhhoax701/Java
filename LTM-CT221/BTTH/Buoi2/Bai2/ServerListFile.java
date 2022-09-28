import java.io.*;
import java.net.*;

class ServerListFile {
    public static void main(String[] args) {
        try {
            // Tao Server Socket cong 289
            ServerSocket ss = new ServerSocket(289);
            System.out.println("Da khoi tao xong cong 289");
            while (true) {
                // Chap nhan cho noi ket
                Socket s = ss.accept();
                System.out.println("Co 1 client " + s.getInetAddress().toString()
                        + " cong " + s.getPort() + " noi ket");
                ThreadFile sThreadFile = new ThreadFile(s);
                sThreadFile.start();
            }
        } catch (IOException e) {
            System.out.println("Loi nhap xuat Server: " + e.toString());
        }
    }
}


// import java.net.*;
// import java.io.*;
// import java.util.Scanner;
// class ServerListFile {
// 	public static void main(String[] args) {
// 		try {
// 			// Tao Server Socket cong 289
// 			ServerSocket ss = new ServerSocket(289);
//             System.out.println("Da khoi tao xong cong 289");
// 			while(true) {
// 				// Chap nhan cho noi ket
// 				Socket s = ss.accept();
//                 System.out.println("Co 1 client " + s.getInetAddress().toString()
//                         + " cong " + s.getPort() + " noi ket");
// 				// Lay ra 2 stream in-out
// 				InputStream is = s.getInputStream();
// 				OutputStream os = s.getOutputStream();
// 				// Doi thanh doi tuong thuoc cac lop con
// 				Scanner sc = new Scanner(is);
// 				PrintWriter pw = new PrintWriter(os);
// 				// Nhan cau lenh LIST
// 				String caulenh = sc.nextLine();
// 				// Lay ra ten thu muc can list
// 				String thumuc = caulenh.substring(5);
// 				// Gui ket qua ve cho Client
// 				File f = new File(thumuc);
// 				if(f.exists() && f.isDirectory()) {
// 					String kq[] = f.list();
// 					int n = kq.length;
// 					pw.println(n);	// Gui so luong thanh phan
// 					for(int i=0; i<n; i++)
// 						pw.println(kq[i]);
// 					pw.flush();
// 				}
// 				else {
// 					pw.println("-1"); pw.flush();
// 				}
// 				// Dong noi ket
// 				s.close();
// 			}
// 		}
// 		catch(IOException e) {
// 			System.out.println(e);
// 		}
// 	}
// }