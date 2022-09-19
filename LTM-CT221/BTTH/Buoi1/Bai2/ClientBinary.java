import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientBinary {
    public static void main(String[] args) {
        // Tao noi ket den Server
        try {
            Socket s = new Socket("127.0.0.1", 789);
            // Khai bao hai stream in-out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                // Xu ly nhap chuoi so nguyen
                String songuyen;
                Scanner kb = new Scanner(System.in);
                System.out.println("Nhap vao chuoi so nguyen: ");
                songuyen = kb.nextLine();
                System.in.skip(2);
                // Gui cho Server
                os.write(songuyen.getBytes());
                if (songuyen == "@")
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
            System.out.println("Sai dia chi: " + e);

        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}