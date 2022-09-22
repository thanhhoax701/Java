import java.io.*;
import java.net.*;
import java.util.*;

public class ClientName {
    public static void main(String[] args) {
        try {
            // Tao noi ket den Server
            Socket s = new Socket("127.0.0.1", 2001);
            // Khai bao 2 stream in + out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                Scanner kb = new Scanner(System.in);
                System.out.println("Nhap ho ten: ");
                String hoTen = kb.nextLine();
                // Bo qua 2 ki tu \r va \n
                System.in.skip(2);
                // Gui cho Server
                os.write(hoTen.getBytes());
                if (hoTen.equals("exit")) break;
                // Nhan ket qua va hien thi ra man hinh
                byte b[] = new byte[100];
                int n = is.read(b);
                String kq = new String(b, 0, n);
                System.out.println("Ket qua nhan duoc: " + kq);
            }
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Khong noi ket duoc voi Server: " + e);
        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}
