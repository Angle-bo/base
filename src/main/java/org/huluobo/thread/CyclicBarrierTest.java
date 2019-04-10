package org.huluobo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		//定义CyclicBarrier，当5个人同时到达时，发车
		final CyclicBarrier cb = new CyclicBarrier(5);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0;i<5;i++) {
			final int offset = i;
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
						System.out.println("乘客-"+offset+"-到达车站#"+Thread.currentThread().getName());
						
						//查看其它乘客是否到达，没有则当前线程阻塞进行等待。
						cb.await();
						
						System.out.println("准备开车：乘客-"+offset+"-已经上车#"+Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}
