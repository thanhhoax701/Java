import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class dethu4 {
    public static void main(String[] args) {
    try {
        // 1
        Scanner kb = new Scanner(System.in);
        System.out.println("Nhap ho ten sinh vien: ");
        String hoTen = kb.nextLine();
        System.out.println("Nhap dia chi Server: ");
        String dcServer = kb.nextLine();
        System.out.println("Nhap cong UDP Server: ");
        int portUDP = kb.nextInt();

        // 2
        MulticastSocket ms = new MulticastSocket(12345);
        InetAddress group = InetAddress.getByName("227.0.0.2");
        ms.joinGroup(group);

        // 3
        byte b[] = new byte[60000];
        DatagramPacket dp = new DatagramPacket(b, b.length);
        ms.receive(dp);
        b = dp.getData();
        String key = new String(b, 0, dp.getLength());

        // 4
        DatagramSocket ds = new DatagramSocket();
        byte bHoTen[] = hoTen.getBytes();
        InetAddress address = InetAddress.getByName(dcServer);
        DatagramPacket dpHoTen = new DatagramPacket(bHoTen, bHoTen.length, address, portUDP);
        ds.send(dpHoTen);

        // 5
        byte b2[] = new byte[60000];
        DatagramPacket goiNhan = new DatagramPacket(b2, b2.length);
        ds.receive(goiNhan);
        b2 = goiNhan.getData();
        String noiDung = new String(b2, 0, goiNhan.getLength());
        int portTCP = Integer.parseInt(noiDung);

        // 6
        Socket tcpSocket = new Socket(dcServer, portTCP);

        // 7
        InputStream is = tcpSocket.getInputStream();
        OutputStream os = tcpSocket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.println(key);
        
        // 8
        Scanner sc = new Scanner(is);
        String str = sc.nextLine();
        int soByte = Integer.parseInt(str);

        // 9
        if(soByte == 0) {
            System.out.println("Ban da nhap sai key");
        }

        // 10
        else {
            byte b3[] =  new byte[60000];
            int n3 = is.read(b3);
            FileOutputStream fileOutputStream = new FileOutputStream("Ketqua.pdf");
            fileOutputStream.write(b3, 0, n3);
            // fileOutputStream.flush();
            fileOutputStream.close();
        }


    } catch (Exception e) {
        System.out.println("Loi nhap xuat");
    }
    }
}
