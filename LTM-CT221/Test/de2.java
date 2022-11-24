import java.net.*;
import java.util.Scanner;

import javax.xml.ws.handler.MessageContext.Scope;

import java.io.*;

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
            File file = new File("data.fk");
            FileInputStream fis = new FileInputStream(file);

            // 5
            InetAddress address = InetAddress.getByName(dcServer);
            byte b[] = new byte[(int)file.length()];
            fis.read(b);
            DatagramPacket goiGui = new DatagramPacket(b, b.length, address, portUDP);
            datagramSocket.send(goiGui);

            // 6
            byte b1[] = new byte[60000];
            DatagramPacket goiNhan = new DatagramPacket(b1, b1.length);
            datagramSocket.receive(goiNhan);
            b1 = goiNhan.getData();
            String matKhau = new String(b1, 0, goiNhan.getLength());

            // 7
            Socket tcpSocket = new Socket(dcServer, portTCP);

            // 8
            Scanner sc = new Scanner(System.in);
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            byte bChungThuc[] = InetAddress.getLocalHost().getHostAddress().getBytes();
            ps.write(bChungThuc);

            // 9
            String chuoiDaoNguoc = "";
            for(int i = matKhau.length()-1; i >= 0; i--) {
                chuoiDaoNguoc = chuoiDaoNguoc + (char)matKhau.charAt(i);
            }
            byte bChuoiDaoNguoc[] = chuoiDaoNguoc.getBytes();
            ps.write(bChuoiDaoNguoc);

            // 10
            String str = sc.nextLine();
            if(str.equals("-ERR")) {
                System.out.println("Chung thuc that bai");
            }
            else {
                System.out.println("Chung thuc thanh cong");
            }
            datagramSocket.close();
            tcpSocket.close();
            fis.close();
        } catch (SocketException e) {
            System.out.println(e);
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}