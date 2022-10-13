import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerTalk {
    public static final int port = 1410;

    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(port);
            Scanner sc = new Scanner(System.in);
            byte inputByte[] = new byte[6000000];
            while(true) {
                // Nhan ten file tu Client
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);
                String inputStr = new String(inputPack.getData(), 0, inputPack.getLength());
                System.out.println("May noi: " + inputStr);

                // Gui du lieu cho Client
                System.out.println("Tao noi: ");
                String outputStr = sc.nextLine();
                DatagramPacket outputPack = new DatagramPacket(outputStr.getBytes(), outputStr.length()
                , inputPack.getAddress(), inputPack.getPort());
                socket.send(outputPack);
            }
        } catch(IOException e) {
            System.out.println("Loi Server: " + e.toString());
        }
    }
}
