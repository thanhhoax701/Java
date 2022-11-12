import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


public class De1 {
    public static void main(String[] args) {
        // 1
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap dia chi cua server");
            String dcServer = sc.nextLine();
            System.out.println("Nhap cong cua UDP server");
            int congUDPServer = sc.nextInt();
            // 2
            DatagramSocket datagramSocket = new DatagramSocket();
            // 3
            InetAddress inetAddress = InetAddress.getByName(dcServer);
            byte b1[];
            InetAddress diaChiMayCucBo = datagramSocket.getLocalAddress();
            b1 = diaChiMayCucBo.toString().getBytes();
            DatagramPacket goiguiUDP = new DatagramPacket(b1,b1.length,inetAddress,congUDPServer);
            datagramSocket.send(goiguiUDP);
            // 4
            // Nhan goi 1 va lay noi dung
            byte b2[] = new byte[60000];
            DatagramPacket goiNhan1 = new DatagramPacket(b2,b2.length);
            datagramSocket.receive(goiNhan1);
            b2 = goiNhan1.getData();
            String noiDungGoi1 = new String(b2,0,goiNhan1.getLength());
            int portTCP = Integer.parseInt(noiDungGoi1);
    
            // 5
            // Nhan goi 2 va lay noi dung
            byte b3[] = new byte[60000];
            DatagramPacket goiNhan2 = new DatagramPacket(b3, b3.length);
            datagramSocket.receive(goiNhan2);
            b3 = goiNhan2.getData(); 
            String noiDungGoi2 = new String(b3,0,goiNhan2.getLength());
    
            // 6
            Socket tcpSocket = new Socket(dcServer,portTCP);
    
            // 7
            InputStream inputStream = tcpSocket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            OutputStream outputStream = tcpSocket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            byte bMatKhau[] = noiDungGoi2.getBytes();
            printStream.write(bMatKhau); 
            
            // 8
            byte bChungThuc[] = new byte[1000];
            int nbChungThuc = inputStream.read(bChungThuc);
            String ketQuaChungThuc = new String(bChungThuc, 0, nbChungThuc);
            if (ketQuaChungThuc.equals("-ERR")){
                System.out.println("Mat khau sai");
            }
            // 9 10
            else{
                String str = scanner.nextLine();
                int size = Integer.parseInt(str);
                int len = 0;
                byte bfile[] = new byte[50000];
                FileOutputStream fileOutputStream = new FileOutputStream("ketqua.pdf");
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                while (true){
                    int n = dataInputStream.read(bfile);
                    if (n>0){
                        dataOutputStream.write(bfile,0,n);
                        len = len + n;
                        System.out.println("Da doc toi " + len + " bytes");
                    }
                    if (len == size) break;
                }   
                fileOutputStream.close();
            }
            sc.close();
            datagramSocket.close();
            tcpSocket.close();
            scanner.close();

        }
        catch (SocketException e){
            System.out.println(e);
        }
        catch (UnknownHostException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }

    }
}
// SocketException
// UnknownHost
// IO