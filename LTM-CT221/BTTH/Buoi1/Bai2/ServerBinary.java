import java.io.*;
import java.net.*;

public class ServerBinary {
    public static void main(String[] args) {
        // Khoi tao Server Socket
        try {
            ServerSocket ss = new ServerSocket(1610);
            System.out.println("Da khoi tao xong cong 1610");
            while (true) {
                // Chap nhan noi ket cua Client
                Socket s = ss.accept();
                System.out.println("Co 1 client " + s.getInetAddress().toString()
                        + " cong " + s.getPort() + " noi ket");
                // Khai bao hai stream in-out
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                // Xu ly nhan yeu cau tu Client
                while (true) {
                    byte b[] = new byte[500];
                    int n = is.read(b);
                    String songuyen = new String(b, 0, n);
                    System.out.println("Nhan tu Client: " + songuyen);
                    if (songuyen.equals("exit"))
                        break;
                    String kq = "";
                    try {
                        // Xu ly so nguyen sang nhi phan
                        kq = Integer.toBinaryString(Integer.parseInt(songuyen));
                    } catch (NumberFormatException e) {
                        kq = "Khong phai la so nguyen";
                    }
                    // Gui cho Client
                    os.write(kq.getBytes());
                }
                s.close();
            }
        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}