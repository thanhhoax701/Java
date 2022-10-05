import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientDate {
    public static void main(String[] args) {
        try {
            // Tao UDP Socket
            DatagramSocket ds = new DatagramSocket();
            System.out.println("Noi ket thanh cong den Server !!");

            // Nhap 1 chuoi tu ban phim
            Scanner kb = new Scanner(System.in);
            while (true) {
                System.out.print("Nhap chuoi : ");
                String chuoi = kb.nextLine();

                // Kiem tra dieu kien de thoat
                if (chuoi.equals("EXIT"))
                    break;

                // Dong goi chuoi vua nhap
                byte b[] = chuoi.getBytes();
                int len = b.length;
                InetAddress dc = InetAddress.getByName("127.0.0.1");
                int p = 510;
                DatagramPacket goigui = new DatagramPacket(b, len, dc, p);

                // Gui goi qua Server
                ds.send(goigui);

                // Tao goi UDP de nhan
                byte b1[] = new byte[60000];
                DatagramPacket goinhan = new DatagramPacket(b1, 60000);
                // Nhan goi tra ve tu Server
                ds.receive(goinhan);

                // Hien thi thong tin cua goi
                byte b2[] = goinhan.getData();
                int len2 = goinhan.getLength();
                String ketqua = new String(b2, 0, len2);
                System.out.println("Ngay gio hien tai : " + ketqua);
            }
            // Dong noi ket
            ds.close();
        } catch (SocketException e) {
            System.out.println("Khoi tao Socket Client that bai !!");
        } catch (UnknownHostException e) {
            System.out.println("Khong tim thay Server !!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}