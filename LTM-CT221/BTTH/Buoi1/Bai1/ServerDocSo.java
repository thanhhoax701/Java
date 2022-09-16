import java.io.*;
import java.net.*;

public class ServerDocSo {
    public static void main(String args[]) {
        try {
            // Noi ket den Echo Server (port 7
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
                String kq = "Khong biet";
                switch (ch){
                    case '0': kq = "Khong"; break;
                    case '1': kq = "Mot"; break;
                    case '2': kq = "Hai"; break;
                    case '3': kq = "Ba"; break;
                    case '4': kq = "Bon"; break;
                    case '5': kq = "Nam"; break;
                    case '6': kq = "Sau"; break;
                    case '7': kq = "Bay"; break;
                    case '8': kq = "Tam"; break;
                    case '9': kq = "Chin"; 
                }
                // Gui kq tra ve Client
                byte b[] = kq.getBytes();
                }
                s.close();
            }
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");

        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}