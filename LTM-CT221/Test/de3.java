import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.Scanner;

public class de3 {
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
        InetAddress address = InetAddress.getByName("227.0.0.2");
        ms.joinGroup(address);

        // 3
        byte b[] = new byte[60000];
        DatagramPacket goinhan = new DatagramPacket(b,60000);
        ms.receive(goinhan);

        // 4
        InetAddress addressUDP = InetAddress.getByName(dcServer);
        byte b1[] = hoTen.getBytes();
        DatagramPacket goiGui = new DatagramPacket(b1, b1.length, addressUDP, portUDP);
        ms.send(goiGui);

        // 5
        byte b2[] = new byte[60000];
        DatagramPacket goiNhan = new DatagramPacket(b2, 60000);
        ms.receive(goiNhan);
        b2 = goiNhan.getData();
        String noiDungGoi = new String(b2, 0, goiNhan.getLength());
        int portTCP = Integer.parseInt(noiDungGoi);

        // 6
        Socket tcpSocket = new Socket(dcServer, portTCP);

        // 7
        Scanner sc = new Scanner(System.in);
        InputStream is = tcpSocket.getInputStream();
        OutputStream os = tcpSocket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        byte bKey[] = noiDungGoi.getBytes();
        ps.write(bKey);

        // 8
        

    } catch (Exception e) {
        System.out.println("Loi nhap xuat");
    }
    }
}
