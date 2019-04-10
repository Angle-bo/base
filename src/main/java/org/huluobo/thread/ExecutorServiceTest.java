package org.huluobo.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {

	// 线程池
	private static ExecutorService pool = Executors.newCachedThreadPool();

	public static void main(String[] args) throws Exception {
		futureRunnable();
	}

	/**
	 * 使用Future<V>和Runnable处理线程异步
	 * 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void futureRunnable() throws InterruptedException, ExecutionException {
		// 使用submit方法提交线程任务和返回类（示例返回的是一个字符串）,得到Future
		Future<String> future = pool.submit(new Runnable() {

			@Override
			public void run() {
				try {
					System.out.println("线程执行了");
					TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "执行完了");
		System.out.println("线程提交了");
		// Future获取线程执行完，得到返回类
		System.out.println(future.get());
	}

	/**
	 * 使用FutureTask<V>和Callable<V>处理线程异步
	 * FutureTask 类是 Future 的一个实现
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static void futureTaskCallable() throws InterruptedException, ExecutionException {
		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("线程执行了");
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
				return "执行完了";
			}
		});
		// 提交到线程池执行
		// **FutureTask除了实现了Future接口外还实现了Runnable接口，因此FutureTask也可以直接提交给Executor执行。
		// 当然也可以调用线程直接执行（FutureTask.run()）
		pool.execute(future);
		System.out.println("线程提交了");
		// Future获取线程执行完，得到返回类
		System.out.println(future.get());
	}
	
	/**
	 * 使用Executors.callable(Runnable)方法处理线程异步
	 * @throws Exception
	 */
	public static void callable() throws Exception {
		Callable<String> callable=Executors.callable(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程执行了");
				try {
					TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"执行完了");
		System.out.println("线程提交了");
		System.out.println(callable.call());
	}
}
