import java.io.*;
import java.net.*;

class PhucVuBinary extends Thread {
    Socket s;

    public PhucVuBinary(Socket s1) {
        s = s1;
    }

    public void run() {
        try {
            // Khai bao hai stream in-out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Xu ly nhan yeu cau tu Client
            while (true) {
                byte b[] = new byte[500];
                int n = is.read(b);
                // Xu ly yeu cau (doi thanh nhi phan)
                String songuyen = new String(b, 0, n);
                System.out.println("Nhan tu Client: " + songuyen);
                if (songuyen.equals("exit"))
                    break;
                String kq = new String();
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
        } catch (IOException e) {
            System.out.println("Co 1 Client dang duoc phuc vu");
        }
    }
}

public class ServerBinary_SS {
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
                PhucVuBinary phucVuBinary = new PhucVuBinary(s);
                phucVuBinary.start();
            }
        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}