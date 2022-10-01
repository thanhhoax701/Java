// Le Minh Tu B1807606

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class B5_RMI extends UnicastRemoteObject implements B5_RMIitf{
	public B5_RMI() throws RemoteException {
		super();
	}

	public int getLength(String filename) throws RemoteException {
		File f = new File(filename);
		if(f.isFile() && f.exists()) {
			return (int) f.length();
		}
		else return -1;
	}

	public byte[] getFile(String filename) throws RemoteException {
		
		File f = new File(filename);
		int len = (int) f.length();
		byte b[] = new byte[len];
		try {
			FileInputStream fi = new FileInputStream(f);
			fi.read(b);
			fi.close();
		}catch (FileNotFoundException e) {
			System.out.println("Loi FileInputStream: " + e);
		}catch (IOException e) {
			System.out.println("Loi doc file: " + e);
		}
		return b;
	}
	


}
