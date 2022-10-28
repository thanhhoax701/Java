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
            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file);
            int count = 0;

            while (true) {
                // Nhan goi bat dau (rong)
                byte b[] = new byte[1];
                DatagramPacket firstPacket = new DatagramPacket(b, 1);
                int start = firstPacket.getLength();
                boolean complete = false;
                while (start == 0) {
                    System.out.println("Dang ghi file");
                    byte b1[] = new byte[60000];
                    DatagramPacket pack = new DatagramPacket(b1, b1.length);
                    ms.receive(pack);

                    if (pack.getLength() == 0) {
                        complete = true;
                        break;
                    }
                    fos.write(pack.getData());
                    count += pack.getLength();
                }
                if (complete) {
                    byte b2[] = new byte[count];
                    fis.read(b2);
                    FileOutputStream out = new FileOutputStream("final");
                    out.write(b2, 0 , count);
                    break;
                }
                // Dong socket
                ms.close();
                fis.close();
                fos.close();
                file.delete();
                System.out.println("Da luu file thanh cong");
            }
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}