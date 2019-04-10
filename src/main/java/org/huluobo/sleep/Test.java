package org.huluobo.sleep;

public class Test {

	public static void main(String[] args) {
		Service service =new Service();
		Thread sleepThread=new Thread(new SleepThread(service));
		Thread waitThread=new Thread(new WaitThread(service));
		waitThread.start();
		sleepThread.start();
	}

}
