public class MyThread extends Thread{
    String name;
    public MyThread(String ten) {
        name = ten;
        System.out.println("Thread " + name + " duoc khoi tao!");
    }

    public void run() {
        for (int i=1; i<=20; i++) {
            System.out.println(name + "-" + i + "\t");
        }
    }
	public static void main (String args[]) {
		MyThread t1 = new MyThread("Cang");
		MyThread t2 = new MyThread("Hung");
		MyThread t3 = new MyThread("Phi");
        t1.start(); t2.start(); t3.start();
	}

}

