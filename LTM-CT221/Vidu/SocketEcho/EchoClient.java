import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String args[]) {
        try {
            // Noi ket den Echo Server (port 7)
            Socket s = new Socket("localhost", 7);
            // Lay ra 2 stream in + out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Nhap 1 ky tu ch tu ban phim
            while (true) {
                System.out.println("Nhap 1 ki tu: ");
                int ch = System.in.read();
                // Neu ky tu la @ thi thoat khoi chuong trinh
                if (ch=='@')
                    break;
                // Bo qua 2 ki tu \r va \n
                System.in.skip(2);
                os.write(ch);
                int ch1 = is.read();
                System.out.println("Nhan duoc: " + (char) ch1);
            }
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");

        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}