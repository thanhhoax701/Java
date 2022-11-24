import java.io.*;
import java.net.*;
import java.util.Scanner;

public class de2 {
    public static void main(String[] args) {
        try {
            // 1
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap cong UDP Server: ");
            int portUDP = kb.nextInt();

            // 2
            System.out.println("Nhap cong TCP Server: ");
            int portTCP = kb.nextInt();
            System.out.println("Nhap dia chi Server: ");
            String dcServer = kb.nextLine();
            
            // 3
            DatagramSocket datagramSocket = new DatagramSocket();

            // 4
            File f = new File("data.tk");
            FileInputStream fis = new FileInputStream("data.tk");
            
            // 5
            InetAddress address = InetAddress.getByName(dcServer);
            byte b[] = new byte[(int)f.length()];
            fis.read(b);
            DatagramPacket goiGui = new DatagramPacket(b, b.length, address, portUDP);
            datagramSocket.send(goiGui);
            
            // 6
            byte bMatKhau[] = new byte[50000];
            DatagramPacket goiNhanMatKhau = new DatagramPacket(bMatKhau, bMatKhau.length);
            datagramSocket.receive(goiNhanMatKhau);
            bMatKhau = goiNhanMatKhau.getData();
            String noiDungGoi = new String(bMatKhau, 0, goiNhanMatKhau.getLength());
            
            // 7
            Socket tcpSocket = new Socket(dcServer, portTCP);

            // 8
            Scanner sc = new Scanner(System.in);
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            byte bCucBo[] = InetAddress.getLocalHost().getHostAddress().getBytes();
            ps.write(bCucBo);

            // 9


        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}