import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTalk {
    public static final int port = 1410;

    public static void main(String[] args) {
        try {
            // Tao UDP Socket
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            Scanner sc = new Scanner(System.in);
            while (true) {
                // Gui du lieu
                System.out.println("Tao noi: ");
                String ouputStr = sc.nextLine();
                DatagramPacket outputPack = new DatagramPacket(ouputStr.getBytes(), ouputStr.length(), serverAddress,
                        port);
                socket.send(outputPack);

                // Nhan du lieu
                byte intputByte[] = new byte[60000];
                DatagramPacket inputPack = new DatagramPacket(intputByte, intputByte.length);
                socket.receive(inputPack);

                String inputStr = new String(inputPack.getData(), 0, inputPack.getLength());
                System.out.println("May noi: " + inputStr);
            }
        } catch (IOException e) {
            System.out.println("Loi Client: " + e.toString());
        }
    }
}
