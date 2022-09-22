import java.io.*;
import java.net.*;

public class ServerName {
    public static void main(String[] args) {
        // Khoi tao Server Socket
        try {
            ServerSocket ss = new ServerSocket(2001);
            System.out.println("Da khoi tao xong cong 2001");
            while (true) {
                // Chap nhan noi ket cua Client
                Socket s = ss.accept();
                System.out.println("Co 1 client " + s.getInetAddress().toString()
                        + " cong " + s.getPort() + " noi ket");
                // Tu ben ThreadName
                ThreadName serviceProcess = new ThreadName(s);
                serviceProcess.start();
            }
        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}