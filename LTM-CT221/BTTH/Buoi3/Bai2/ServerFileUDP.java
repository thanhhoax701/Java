import java.io.*;
import java.net.*;

public class ServerFileUDP {
    public static final int port = 1310;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            byte inputByte[] = new byte[5000000];
            while(true) {
                // Nhan ten file tu Client
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);
                String inputStr = new String(inputPack.getData(), 0, inputPack.getLength());
                String fileName = inputStr.substring(8);
                // Doc file va dua vao mang byte
                File file = new File(fileName);
                int fileLength = (int)file.length();
                byte outputByte[] = new byte[fileLength];
                FileInputStream fis = new FileInputStream(file);
                fis.read(outputByte);
                // Gui du lieu cho Client
                DatagramPacket outputPack = new DatagramPacket(outputByte, outputByte.length
                , inputPack.getAddress(), inputPack.getPort());
                socket.send(outputPack);

            }
        }
        catch(IOException e) {
			System.out.println("Loi Server: " + e.toString());
		}
    }
}
