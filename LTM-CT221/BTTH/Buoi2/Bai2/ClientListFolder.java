// import java.io.*;
// import java.net.*;
// import java.util.Scanner;

// public class ClientListFile {
//     public static void main(String[] args) {
//         try {
//             // Noi ket den Server
//             Socket s = new Socket("127.0.0.1", 289);
//             // Lay ra 2 stream in-out
//             InputStream is = s.getInputStream();
//             OutputStream os = s.getOutputStream();
//             Scanner kb = new Scanner(System.in);
//             // Doi thanh doi tuong thuoc cac lop con
//             Scanner iScanner = new Scanner(is);
//             PrintWriter oPrintWriter = new PrintWriter(os);
//             while(true) {
//                 System.out.println("Nhap cau lenh: ");
//                 // Nhan n la so luong thanh phan co trong thu muc yeu cau
//                 String request = kb.nextLine();
//                 // Chuyen du lieu qua Server
//                 oPrintWriter.println(request);
//                 oPrintWriter.flush();
//                 if (request.equals("exit")) break;
//                 while(true) {
//                     String kq = iScanner.nextLine();
//                     if(kq.equals(".")) break;
//                     System.out.println(kq);
//                 }
//             }
//             s.close();
//         } catch (UnknownHostException e) {
//             System.out.println("Sai dia chi" + e.toString());
//         } catch (IOException e) {
//             System.out.println("Loi nhap xuat Client" + e.toString());
//         }
//     }
// }

import java.net.*;
import java.io.*;
import java.util.Scanner;

class ClientListFolder {
    public static void main(String[] args) {
        try {
            Scanner kb = new Scanner(System.in);
            // Nhap tu ban phim dia chi Server
            System.out.print("Nhap dia chi Server: ");
            String dcServer = kb.nextLine();
            // Nhap tu ban phim thu muc can list tren Server
            System.out.print("Nhap ten thu muc tren Server can liet ke: ");
            String thumuc = kb.nextLine();
            // Noi ket den Server
            Socket s = new Socket(dcServer, 289);
            // Lay ra 2 stream in-out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Doi thanh doi tuong thuoc cac lop con
            Scanner sc = new Scanner(is);
            PrintStream ps = new PrintStream(os);
            // Gui cau lenh LIST qua Server
            String caulenh = "LIST " + thumuc;
            ps.println(caulenh);
            // ps.flush();
            // Nhan n la so luong thanh phan co trong thu muc yeu cau
            String str = sc.nextLine();
            int n = Integer.parseInt(str);
            if (n == -1)
                System.out.println("Thu muc khong ton tai");
            else if (n == 0)
                System.out.println("Thu muc rong");
            else {
                // Nhan tiep n thanh phan
                for (int i = 0; i < n; i++) {
                    String kq = sc.nextLine();
                    System.out.println(kq);
                }
            }
            // Dong noi ket
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi");
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }
}