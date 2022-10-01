// Le Minh Tu B1807606

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface B5_RMIitf extends Remote {
	public int getLength(String filename) throws RemoteException; ;
	public byte[] getFile(String filename) throws RemoteException; ;
}
