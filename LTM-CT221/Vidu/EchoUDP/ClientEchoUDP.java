import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientEchoUDP {
    public static void main(String args[]) {
        try {
            // Tao UDP Socket cho Client
            DatagramSocket ds = new DatagramSocket();
            // Nhap 1 chuoi str tu ban phim
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap chuoi: ");
            while (true) {
                String str = kb.nextLine();
                // Kiem tra dieu kien de thoat
                if (str.equals("exit"))
                    break;
                // Dong goi UDP Client str
                byte b[] = str.getBytes();
                int n = b.length;
                InetAddress dc = InetAddress.getByName("127.0.0.1");
                int p = 7;
                DatagramPacket goigui = new DatagramPacket(b, n, dc, p);
                // Gui goi UDP qua cho Server
                ds.send(goigui);
                // Tao ra goi UDP de nhan
                byte b1[] = new byte[60000];
                int n1 = 60000;
                DatagramPacket goinhan = new DatagramPacket(b1, n1);
                // Nhan goi UDP tra ve tu Server
                ds.receive(goinhan);
                // Lay ra chuoi ket qua
                byte b2[] = goinhan.getData();
                int n2 = goinhan.getLength();
                String kq = new String(b2, 0, n2);
                // Hien thi ket qua ra man hinh 
                System.out.println("Ket qua nhan duoc: " + kq);
            }
            // Dong Socket UDP (go cong chu khong phai dong noi ket)
            ds.close();
        } catch (IOException e) {
            System.out.println("Loi Client: " + e.toString());
        }
    }
}