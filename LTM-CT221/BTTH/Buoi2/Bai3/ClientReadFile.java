import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientReadFile {

    public static void main(String[] args) {
        try {
            // Noi ket
            Socket s = new Socket("localhost", 5500);
            System.out.println("Da noi ket thanh cong");
            // Lay ra 2 stream
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Doi thanh cac doi tuong thuoc lop thua ke
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            // Nhap ten file can lay ve
            Scanner kb = new Scanner(System.in);
            System.out.print("Nhap ten file tren Server: ");
            String tenfile = kb.nextLine();
            // Nhap ten file can luu
            System.out.print("Nhap ten file can luu: ");
            String fileghi = kb.nextLine();
            // Gui cau lenh
            String caulenh = "READ " + tenfile;
            pw.println(caulenh);
            // pw.flush();
            // Nhan ket qua
            try {
                String str = sc.nextLine();
                int n = Integer.parseInt(str);
                if (n == -1)
                    System.out.println("File khong ton tai");
                else if (n == 0)
                    System.out.println("File rong");
                else {
                    // Luu ket qua
                    FileOutputStream f = new FileOutputStream(fileghi);
                    byte b[] = new byte[n];
                    System.out.println("Kich thuoc file " + n);
                    DataInputStream dis = new DataInputStream(is);
                    dis.readFully(b);
                    f.write(b);
                    f.close(); // Dong file da ghi
                    System.out.println("Da ghi file thanh cong");
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            // Dong noi ket
            s.close();
        } catch (UnknownHostException e) {
            System.out.println("Sai dia chi Server");
        } catch (IOException e) {
            System.out.println("Loi nhap xuat");
        }
    }

}

// public static void main(String[] args) {
// try {
// Socket s = new Socket("127.0.0.1", 101);
// InputStream is = s.getInputStream();
// OutputStream os = s.getOutputStream();
// Scanner kb = new Scanner(System.in);
// Scanner sc = new Scanner(is);
// PrintStream ps = new PrintStream(os);
// System.out.print("Nhap ten duong dan cua file:");
// String caulenh = "READ " + kb.nextLine();
// // gui duong dan cho server
// ps.println(caulenh);
// // tao mang bye de luu kq tu server gui qua
// byte b[] = new byte[10000];
// // doc ket qua tu server luu vao mang byte b. tra ve n ky tu
// // Tai sao doc khong dung Scanner ma lai dung inputStream ? -> Tai Scanner
// khong
// // co doc vo mang byte
// // Co mang byte thi moi luu vo file dc
// int n = is.read(b);
// String nhandc = new String(b, 0, n);
// if (nhandc.equals("File khong ton tai")) {
// System.out.println(nhandc);
// } else {
// // tao file de luu co ten copy.txt trong o D
// FileOutputStream fileOutputStream = new FileOutputStream(
// "D:/Learn-Programming/JAVA/LTM-CT221/BTTH/Buoi2/Bai3/ketqua.txt");
// fileOutputStream.write(b, 0, n);
// fileOutputStream.close();
// }
// s.close();
// } catch (IOException e) {
// System.out.println(e);
// }
// }
// }
