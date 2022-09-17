import java.io.*;
import java.net.*;

public class ServerBinary {
    public static void main(String[] args) {
        // Khoi tao Server Socket
        try {
            ServerSocket ss = new ServerSocket(789);
            System.out.println("Da khoi tao thanh cong Server Socket!");
            while (true) {
                // Chap nhan noi ket cua Client
                Socket s = ss.accept();
                System.out.println("Co Client dia chi " + s.getInetAddress().toString() + ", cong " + s.getPort()
                        + " ket noi den Server!");
                // Khai bao hai stream in-out
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                // Xu ly nhan yeu cau tu Client
                while (true) {
                    byte b[] = new byte[500];
                    int n = is.read(b);
                    String songuyen, kqua;
                    songuyen = new String(b, 0, n);
                    if (songuyen == "@")
                        break;
                    // Xu ly so nguyen sang nhi phan
                    kqua = Integer.toBinaryString(Integer.parseInt(songuyen));
                    // Gui cho Client
                    os.write(kqua.getBytes());
                }
                System.out.println(
                        "Dong noi ket voi Client dia chi " + s.getInetAddress().toString() + ", cong " + s.getPort());
                s.close();
            }
        } catch (IOException e) {
            System.out.println("Co loi IOException!");
        }
        ;
    }
}