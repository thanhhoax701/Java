import java.io.*;
import java.net.*;

public class ClientBigFileUDP {
    public static final int port = 2110;

    public static void main(String args[]) {
        try {
            // Tao Socket multicast cong 2110
            MulticastSocket ms = new MulticastSocket(2110);

            // Tham gia nhom dia chi "225.6.7.8"
            InetAddress ad = InetAddress.getByName("225.6.7.8");
            ms.joinGroup(ad);

            File file = new File("kq");
            FileOutputStream fos = new FileOutputStream(file);

            while (true) {
                // Nhan goi bat dau (rong)
                byte b[] = new byte[1];
                DatagramPacket firstPacket = new DatagramPacket(b, 1);
                int start = firstPacket.getLength();
                boolean complete = false;
                while (start == 0) {
                    System.out.println("Dang ghi file");
                    byte b1[] = new byte[60000];
                    DatagramPacket goinhan = new DatagramPacket(b1, b1.length);
                    ms.receive(goinhan);

                    if (goinhan.getLength() == 0) {
                        complete = true;
                        break;
                    }
                    fos.write(goinhan.getData());
                }
                if (complete)
                    break;
                System.out.println("Da ghi file thanh cong");
                // Dong socket
                ms.close();
            }
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}