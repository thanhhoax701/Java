import java.net.*;
import java.io.*;

public class ThreadName extends Thread {
    Socket s;

    public ThreadName(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            // Khai bao hai stream in-out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                // Nhan tu Client
                byte b[] = new byte[500];
                int n = is.read(b);
                String hoTen = new String(b, 0, n);
                String ten = tachTen(hoTen);
                System.out.println("Nhan tu Client: " + ten);
                if (ten.equals("exit"))
                    break;
                // Gui cho Client
                os.write(ten.getBytes());
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
    // Xu ly viec tach ten
    private String tachTen(String hoTen) {
        // ham trim cat 2 khoang trang o 2 ben cua chuoi
        hoTen = hoTen.trim();
        // lay khoang trang cuoi cung
        int i = hoTen.lastIndexOf(" ");
        String ten = hoTen.substring(i + 1);
        return ten;
    }
}
