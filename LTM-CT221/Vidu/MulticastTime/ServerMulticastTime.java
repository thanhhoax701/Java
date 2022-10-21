import java.io.*;
import java.net.*;
import java.util.Date;
import java.lang.Thread;

public class ServerMulticastTime {
    public static void main(String args[]) {
        try {
            // Tao ra UDP Socket
            DatagramSocket ds = new DatagramSocket();
            int count = 0;
            while (true) {
                // Lay ra thong tin thoi gian
                Date d = new Date();
                String kq = d.toString();
                // Dong goi
                byte b[] = kq.getBytes();
                int n = kq.length();
                InetAddress dc = InetAddress.getByName("225.6.7.8");
                int p = 10000;
                DatagramPacket goigui = new DatagramPacket(b, n, dc, p);
                // Gui goi phuc vu cho nhom dia chi lop D
                ds.send(goigui);
                System.out.println("Da gui goi thu: " + ++count);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}
