import java.io.*;
import java.net.*;
import java.util.Scanner;;

public class ServerBigFileUDP {
    public static final int port = 2110;

    public static void main(String args[]) {
        try {
            // Tao UDP Socket
            DatagramSocket ds = new DatagramSocket();
            InetAddress ad = InetAddress.getByName("225.6.7.8");
            int count = 0;

            // Nhap ten file
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap duong dan file: ");
            String filename = kb.nextLine();

            while (true) {
                // Doc file
                File file = new File(filename);
                int fileLength = (int) file.length();
                FileInputStream f = new FileInputStream(file);
                byte b[] = new byte[fileLength];
                f.read(b);

                // Gui goi dau rong
                byte b1[] = new byte[0];
                DatagramPacket firstPacket = new DatagramPacket(b1, 0, ad, port);
                ds.send(firstPacket);
                System.out.println("Goi dau tien");

                // Gui cac goi n-1
                int fileAmount = fileLength / 60000;
                if (fileLength % 60000 != 0)
                    fileAmount++;
                byte midByte[] = new byte[60000];
                for (int i = 0; i < fileAmount - 1; i++) {
                    // Copy cac goi
                    for (int j = 0; j < 60000; j++) {
                        midByte[j] = b1[i * 60000 + j];
                    }
                    DatagramPacket midPacket = new DatagramPacket(midByte, 60000, ad, port);
                    ds.send(midPacket);
                    System.out.println("Goi thu " + (i + 1));
                }

                // Gui goi cuoi
                byte endByte[] = new byte[60000];
                int endAmount = fileLength - ((fileAmount - 1) * 60000);
                for (int y = 0; y < endAmount; y++) {
                    endByte[y] = b1[((fileAmount - 1) * 60000) + y];
                }

                // Dong goi
                DatagramPacket endPacket = new DatagramPacket(endByte, endAmount, ad, port);
                ds.send(endPacket);
                System.out.println("Goi cuoi cung");
                Thread.sleep(10000); // 1s = 1000ms
            }
        } catch (InterruptedException e) {
            System.out.println("Loi khi tam ngung");
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}
