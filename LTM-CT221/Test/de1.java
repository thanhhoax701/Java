import java.net.*;
import java.util.Scanner;
import java.io.*;

public class de1 {
    public static void main(String[] args) {
        try {
            // 1
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap dia chi Server: ");
            String dcServer = kb.nextLine();
            System.out.println("Nhap cong UDP Server: ");
            int portUDP = kb.nextInt();

            // 2
            DatagramSocket datagramSocket = new DatagramSocket();

            // 3
            InetAddress address = InetAddress.getByName(dcServer);
            byte b[];
            b = datagramSocket.getLocalAddress().toString().getBytes();
            DatagramPacket goiGui = new DatagramPacket(b, b.length, address, portUDP);
            datagramSocket.send(goiGui);
            // datagramSocket.close();

            // 4
            byte b1[] = new byte[1024];
            DatagramPacket goiNhan1 = new DatagramPacket(b1, b1.length);
            datagramSocket.receive(goiNhan1);
            b1 = goiNhan1.getData();
            String noiDungGoi1 = new String(b1, 0, goiNhan1.getLength());
            int portTCP = Integer.parseInt(noiDungGoi1);

            // 5
            byte b2[] = new byte[1024];
            DatagramPacket goiNhan2 = new DatagramPacket(b2, b2.length);
            datagramSocket.receive(goiNhan2);
            b2 = goiNhan2.getData();
            String noiDungGoi2 = new String(b2, 0, goiNhan2.getLength());

            // 6
            Socket tcpSocket = new Socket(dcServer, portTCP);

            // 7
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            byte bMatKhau[] = noiDungGoi2.getBytes();
            ps.write(bMatKhau);
            Scanner sc = new Scanner(is);
            
            // 8
            byte bChungThuc[] = new byte[1000];
            int nChungThuc = is.read(bChungThuc);
            String kqChungThuc = new String(bChungThuc, 0, nChungThuc);
            if(kqChungThuc.equals("-ERR")){
                System.out.println("Mat khau sai");
            }
            
            // 9 10
            else {
                String str = sc.nextLine();
                int size = Integer.parseInt(str);
                int len = 0;
                byte bFile[] = new byte[50000];
                FileOutputStream fileOutputStream = new FileOutputStream("kq.pdf");
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                DataInputStream dataInputStream = new DataInputStream(is);
                while(true) {
                    int nFile = dataInputStream.read(bFile);
                    if(nFile > 0) {
                        dataOutputStream.write(bFile, 0 , nFile);
                        len = len + nFile;
                        System.out.println("Da doc toi " + len + " bytes");
                    }
                    else {
                        break;
                    }
                }
                fileOutputStream.close();
            }

            kb.close();
            datagramSocket.close();
            is.close();
            os.close();
            tcpSocket.close();
            sc.close();
        
        } catch (SocketException e) {
            System.out.println(e);
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}