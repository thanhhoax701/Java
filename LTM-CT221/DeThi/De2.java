import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.io.*;

public class De2 {
    public static void main(String[] args) {
        try{
            // 1
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap vao cong UDP server");
            int UDPport = sc.nextInt();
            // 2
            System.out.println("Nhap vao cong TCP server");
            int TCPport = sc.nextInt();
            System.out.println("Nhap vao dia chi cua server");
            String diaChiServer = sc.nextLine();
            // 3
            DatagramSocket datagramSocket = new DatagramSocket();
    
            // 4
            File f = new File("data.tk");
            FileInputStream fileInputStream = new FileInputStream("data.tk");
            byte b[] = new byte[(int)f.length()];
            fileInputStream.read(b);
            InetAddress dc = InetAddress.getByName(diaChiServer);
            DatagramPacket goiGui = new DatagramPacket(b, b.length,dc, UDPport);
    
            // 5
            datagramSocket.send(goiGui);
            // 6
            byte bMatKhau[] = new byte[60000];
            DatagramPacket goiNhanMatKhau = new DatagramPacket(bMatKhau, bMatKhau.length);
            datagramSocket.receive(goiNhanMatKhau);
            bMatKhau = goiNhanMatKhau.getData();
            String matKhau = new String(bMatKhau, 0, goiNhanMatKhau.getLength());
    
            // 7
            Socket tcpSocket = new Socket(diaChiServer,TCPport);
    
            // 8
            InputStream inputStream = tcpSocket.getInputStream();
            OutputStream outputStream = tcpSocket.getOutputStream();
            Scanner scannner = new Scanner(inputStream);
            PrintStream printStream = new PrintStream(outputStream);
            byte bDiaChiMayCucBo[] = InetAddress.getLocalHost().getHostAddress().getBytes();
            outputStream.write(bDiaChiMayCucBo);
            
            // 9
            String chuoiDaoMatKhau = "";
            for (int i = matKhau.length()-1; i>=0 ;i-- ){
                chuoiDaoMatKhau = chuoiDaoMatKhau + matKhau.charAt(i);
            }
            byte bchuoiDaoMatKhau[] = chuoiDaoMatKhau.getBytes();
            printStream.write(bchuoiDaoMatKhau);
    
            // 10
            // byte bNhanChungThuc[] = new byte[10000];
            String str = scannner.nextLine();
            if (str.equals("-ERR")){
                System.out.println("Chung thuc that bai");
            }
            // 11
            else{
                System.out.println("Chung thuc thanh cong");
            }
            sc.close();
            datagramSocket.close();
            fileInputStream.close();
            tcpSocket.close();
            scannner.close();
        }
        catch (SocketException e){
            System.out.println(e);
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }

    }
}
// SocketException
// FileNotFoundException
// IOException