import java.net.*;

public class App {
    // Lấy địa chỉ IP từ tên miền
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        System.out.println(address);

        address = InetAddress.getByName("www.facebook.com");

        System.out.println(address);

        InetAddress sw[] = InetAddress.getAllByName("www.ctu.edu.vn");

        for (int i = 0; i < sw.length; i++) {
            System.out.println(sw[i]);
        }
    }

    // Trích xuất các thành phần trong URL
    // public static void main(String[] arg) throws MalformedURLException {

    // URL hp = new URL("https://ironhackvietnam.edu.vn/lap-trinh-mang/");

    // System.out.println(hp.getProtocol());

    // System.out.println(hp.getFile());

    // }
}
