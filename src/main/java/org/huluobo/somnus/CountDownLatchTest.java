package org.huluobo.somnus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		List<Object> list = new ArrayList<Object>();

		int threadCount = 1000;

		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {

			new Thread(() -> {
				for (int j = 0; j < 100; j++) {
					list.add(new Object());
				}
			}, "Thread-" + i).start();
			
			countDownLatch.countDown();
		}
		
		//主线程等待所有子线程完成
		countDownLatch.await();
		
		System.err.println(list.size());
	}

}
