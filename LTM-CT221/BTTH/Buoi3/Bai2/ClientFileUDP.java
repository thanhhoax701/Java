import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientFileUDP {
    public static final int port = 1310;

    public static void main(String[] args) {
        try {
            // Tao UDP Socket
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            Scanner sc = new Scanner(System.in);
            String fileName = "";
            do {
                // Nhap file
                System.out.println("Nhap ten file: ");
                fileName = sc.nextLine();
            } while (checkFile(fileName));
            // Gui yeu cau
            String request = "READUDP " + fileName;
            byte outputByte[] = request.getBytes();
            DatagramPacket outputPack = new DatagramPacket(outputByte, outputByte.length, serverAddress, port);
            socket.send(outputPack);

            // Nhan yeu cau va luu ra file
            File file = new File("result");
            FileOutputStream fos = new FileOutputStream(file);
            byte intputByte[] = new byte[5000000];
            DatagramPacket inputPack = new DatagramPacket(intputByte, intputByte.length);
            socket.receive(inputPack);
            fos.write(inputPack.getData(), 0, inputPack.getLength());

            System.out.println("Luu file thanh cong");

        // } catch (SocketException e) {
        //     System.out.println("Khoi tao Socket Client that bai !!");
        // } catch (UnknownHostException e) {
        //     System.out.println("Khong tim thay Server !!");
        } catch (IOException e) {
            System.out.println("Loi Client: " + e.toString());
        }
    }

    private static boolean checkFile(String fileName) {
        boolean error = false;
        File file = new File(fileName);
        int maxLength = 64 * 1024;
        int fileLength = (int)file.length();

        if(fileLength > maxLength) {
            error = true;
            System.out.println("File qua lon");
        }
        if(!file.exists()) {
            error = true;
            System.out.println("File khong ton tai");
        }

        return error;
    }
}
