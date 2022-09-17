import java.io.*;
import java.net.*;

public class ServerDocSo {
    public static void main(String args[]) {
        try {
            // Noi ket den Echo Server (port 7000)
            ServerSocket ss = new ServerSocket(7000);
            System.out.println("Da khoi tao xong cong 7000");
            // Chap nhan cho noi ket
            while (true) {
                Socket s = ss.accept();
                System.out.println("Co 1 client " + s.getInetAddress().toString()
                        + " cong " + s.getPort() + " noi ket");
                while(true) {
                // Lay ra 2 stream in + out
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                // Nhan 1 ky tu tu Client
                int ch = is.read();
                System.out.println("Nhan tu Client: " + ch);
                // Neu ky tu la @ thi thoat khoi chuong trinh
                if (ch == '@')
                    break;
                // Xu ly yeu cau
                String kq = "Khong phai so nguyen";
                switch (ch){
                    case '0': kq = "khong"; break;
                    case '1': kq = "mot"; break;
                    case '2': kq = "hai"; break;
                    case '3': kq = "ba"; break;
                    case '4': kq = "bon"; break;
                    case '5': kq = "nam"; break;
                    case '6': kq = "sau"; break;
                    case '7': kq = "bay"; break;
                    case '8': kq = "tam"; break;
                    case '9': kq = "chin"; 
                }
                // Gui kq tra ve Client
                byte b[] = kq.getBytes();
                os.write(b);
                }
                s.close();
            }
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi: " + e);

        } catch (IOException e) {
            System.out.println("Loi nhap xuat: " +e);
        }
    }
}