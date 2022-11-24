import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.*;

public class DeThu4 {
    public static void main(String[] args) {
        try {
            // 1
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap ho ten sinh vien: ");
            String hoten = kb.nextLine();
            System.out.println("Nhap dia chi Server: ");
            String diaChiIPServer = kb.nextLine();
            System.out.println("Nhap cong phuc vu UDP: ");
            int congPhucVu = kb.nextInt();

            // 2
            MulticastSocket multicastSocket = new MulticastSocket(12345);
            InetAddress inetAddress = InetAddress.getByName("227.0.0.2");
            multicastSocket.joinGroup(inetAddress);

            // 3
            byte b[] = new byte[60000];
            DatagramPacket goiNhan1 = new DatagramPacket(b, b.length);
            multicastSocket.receive(goiNhan1);
            b = goiNhan1.getData();
            String key = new String(b, 0, goiNhan1.getLength());

            // 4
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress dInetAddress = InetAddress.getByName(diaChiIPServer);
            byte b2[] = hoten.getBytes();
            DatagramPacket goiGui1 = new DatagramPacket(b2, b2.length, dInetAddress, congPhucVu);
            datagramSocket.send(goiGui1);

            // 5
            byte b3[] = new byte[60000];
            DatagramPacket goiNhan2 = new DatagramPacket(b3, b3.length);
            datagramSocket.receive(goiNhan2);
            b3 = goiNhan2.getData();
            String str = new String(b3, 0, goiNhan2.getLength());
            int congTCP = Integer.parseInt(str);

            // 6
            Socket socket = new Socket(diaChiIPServer, congTCP);

            // 7
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Scanner sc = new Scanner(inputStream);
            printStream.println(key);

            // 8
            String str2 = sc.nextLine();
            int soByte = Integer.parseInt(str2);

            // 9
            if (soByte == 0) {
                System.out.println("Ban da gui sai key");
            }

            // 10
            else {
                byte b4[] = new byte[60000];
                int n = inputStream.read(b4);
                FileOutputStream fileOutputStream = new FileOutputStream("Ketqua.pdf");
                fileOutputStream.write(b4, 0, n);
                fileOutputStream.close();
            }
            kb.close();
            multicastSocket.close();
            datagramSocket.close();
            socket.close();
            sc.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
// IO
// UnknowHost
// Socket
// FileNot