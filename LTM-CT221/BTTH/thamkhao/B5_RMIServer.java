// Le Minh Tu B1807606


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class B5_RMIServer {

	public static void main(String[] args) {
		try {
			// tao co che bao mat
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			// tao doi tuong
			B5_RMI file = new B5_RMI();
			// dang ky doi tuong
			Naming.rebind("file", file);
		} catch (RemoteException e) {
			// TODO: handle exception
			System.out.println("Loi khoi tao hoac dang ky: " + e);
		} catch (MalformedURLException e) {
			// TODO: handle exception
			System.out.println("Loi dinh dang: " + e);
		}

	}

}
