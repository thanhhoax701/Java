import java.io.*;
import java.net.*;

public class ClientMulticastTime {
    public static void main(String args[]) {
        try {
            // Tao Multicast Socket
            MulticastSocket ms = new MulticastSocket(1000);
            // Tham gia vao nhom dia chi 225.6.7.8
            InetAddress dc = InetAddress.getByName("225.6.7.8");
            ms.joinGroup(dc);
            // Nhan goi phuc vu
            byte b[] = new byte[1000];
            DatagramPacket goinhan = new DatagramPacket(b, 1000);
            ms.receive(goinhan);
            // Hien thi thong tin
            byte b1[] = goinhan.getData();
            int n1 = goinhan.getLength();
            String kq = new String(b1, 0, n1);
            System.out.println("Ngay nhan duoc la: " + kq);
            // Roi khoi nhom
            ms.leaveGroup(dc);
            // Dong socket lai
            ms.close();
        } catch (UnknownHostException e) {
            System.out.println("Khong tim thay dia chi");
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}
