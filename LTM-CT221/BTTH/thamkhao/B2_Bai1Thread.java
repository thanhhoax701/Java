// Lê Minh Tú B1807606
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class B2_Bai1Thread extends Thread {
	Socket s;
	
	public B2_Bai1Thread (Socket socket) {
		this.s = socket;
	}
	
	@Override
	public void run() {
		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			while(true) {
				byte hotenbyte[] = new byte[500];
				int n = in.read(hotenbyte);
				String hoten = new String(hotenbyte,0,n);
				if(hoten.equals("exit")) break;
				String ten = tachten(hoten);
				out.write(ten.getBytes());
			}
			s.close();
			System.out.println("Client o dia chi " + s.getInetAddress() + " cong " + s.getPort() + " da thoat");
		}catch(IOException ex) {
			System.out.println("Loi Process Server " +ex.toString());
		}
	}
	
	private String tachten(String hoten) {
		hoten = hoten.trim();
		int i = hoten.lastIndexOf(" ");
		String ten = hoten.substring(i+1);
		return ten;
	}
}
