import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientBinary_SS {
    public static void main(String[] args) {
        try {
            // Tao noi ket den Server
            Socket s = new Socket("127.0.0.1", 1610);
            // Khai bao hai stream in-out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                // Xu ly nhap chuoi so nguyen
                Scanner kb = new Scanner(System.in);
                System.out.println("Nhap vao chuoi so nguyen: ");
                String songuyen = kb.nextLine();
                System.in.skip(2);
                // byte [] inputByte = songuyen.getBytes();
                // Gui cho Server
                os.write(songuyen.getBytes());
                if (songuyen.equals("exit"))
                    break;
                // Nhan ket qua va hien thi ra man hinh
                byte b[] = new byte[1000];
                int n = is.read(b);
                String kq = new String(b, 0, n);
                System.out.println("Ket qua nhan duoc: " + kq);
            }
            // Dong noi ket
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Khong noi ket duoc voi Server: " + e);

        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}