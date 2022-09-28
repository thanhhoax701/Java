import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.File;

public class ThreadFile extends Thread {
    Socket s;
    PrintWriter oPrintWriter;
    String title = "";
    String directory = "";

    public ThreadFile(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            // Lay ra 2 stream in-out
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Doi thanh doi tuong thuoc cac lop con
            Scanner iScanner = new Scanner(is);
            oPrintWriter = new PrintWriter(os);
            while (true) {
                String request = iScanner.nextLine();
                if (request.equals("exit")) break;
                tachChuoi(request);
                File file = new File(directory);
                duyetFile(file);
                oPrintWriter.println(".");
                oPrintWriter.flush();
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Loi process: " + e.toString());
        }
    }

    // public static void main(String[] args) {
    // File file = new
    // File("D:\\Learn-Programming\\JAVA\\LTM-CT221\\BTTH\\Buoi2\\Bai2");
    // duyetFile(file);
    // }

    public void tachChuoi(String request) {
        request = request.trim();
        int i = request.lastIndexOf(" ");
        title = request.substring(0, i);
        directory = request.substring(i + 1);
    }

    public void duyetFile(File file) {
        String path = file.getAbsolutePath();
        oPrintWriter.println(path);
        oPrintWriter.flush();
        if (file.isDirectory()) {
            File listFile[] = file.listFiles();
            for (File item : listFile) {
                duyetFile(item);
            }
        }
    }
}
