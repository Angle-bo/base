package org.huluobo.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		// 用于判断发令之前运动员是否已经完全进入准备状态，需要等待10个运动员，所以参数为10
		final CountDownLatch runner = new CountDownLatch(10);
		// 用于判断裁判是否已经发令，只需要等待一个裁判，所以参数为1
		final CountDownLatch referee = new CountDownLatch(1);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0;i<10;i++) {
			final int offset = i;
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					 try {
						TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
						System.out.println("运动员-"+offset+"-准备完毕#"+Thread.currentThread().getName());
						//当前线程准备完毕，计数器-1
						runner.countDown();
						
						//等待裁判准备,阻塞当前线程
						referee.await();
						System.out.println("运动员-"+offset+"-开始跑#"+Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		//所有运动员准备完毕之后，执行后面的逻辑
		runner.await();
		//裁判准备完毕
		referee.countDown();
		System.out.println("裁判：所有运动员准备完毕，开始……");
		executor.shutdown();
	}

}
