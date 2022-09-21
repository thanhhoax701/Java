import java.io.*;
import java.net.*;

public class ClientDocSo {
    public static void main(String args[]) {
        try {
            // Noi ket den Server (port 7000)
            Socket s = new Socket("127.0.0.1", 7000);
            // Lay ra 2 stream in + out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            while (true) {
                // Nhap 1 ky tu ch tu ban phim
                System.out.println("Nhap 1 ki tu so: ");
                int ch = System.in.read();
                // Neu ky tu la @ thi thoat khoi chuong trinh
                if (ch=='@')
                    break;
                // Gui ky tu so den Server
                os.write(ch);
                // Bo qua 2 ki tu \r va \n
                System.in.skip(2);
                // Nhan ket qua tra ve (nhieu ky tu) tu Server
                byte b[] = new byte[100];
                int n = is.read(b);
                // Hien thi ket qua ra man hinh
                String kq = new String(b, 0, n);
                System.out.println("Nhan duoc: " + kq);
            }
            // Dong ket noi
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Khong noi ket duoc voi Server: " + e);

        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " + e);
        }
    }
}