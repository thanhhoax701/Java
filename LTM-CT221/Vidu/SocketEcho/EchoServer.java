import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String args[]) {
        try {
            // Noi ket den Echo Server (port 7
            ServerSocket ss = new ServerSocket(7);
            System.out.println("Da khoi tao xong cong 7");
            // Chap nhan cho noi ket
            while (true) {
                Socket s = ss.accept();
                System.out.println("Co 1 client " + s.getInetAddress().toString()
                        + " cong " + s.getPort() + " noi ket");
                while(true) {
                // Lay ra 2 stream in + out
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                // Nhan 1 ky tu tu Client
                int ch = is.read();
                System.out.println("Nhan tu Client: " + ch);
                // Neu ky tu la @ thi thoat khoi chuong trinh
                if (ch == '@')
                    break;
                // Xu ly yeu cau
                int ch1 = ch;
                // Gui kq tra ve Client
                os.write(ch);
                }
                s.close();
            }
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");

        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}