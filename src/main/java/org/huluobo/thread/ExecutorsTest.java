package org.huluobo.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 四种线程池
 */
public class ExecutorsTest {

    public static void main(String[] args) throws Exception {
        fixedThreadPoolTest();
        //singleThreadExecutorTest();
        //cachedThreadPoolTest();
        //scheduleThreadPoolTest();
        //threadPoolTest();
    }

    /**
     * 固定数量线程池
     */
    static void fixedThreadPoolTest() throws InterruptedException {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        Integer size=5;
        //信号量
        final CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + Thread.currentThread().getName());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            });
        }
        //阻塞，等待线程池内任务全都完成
        latch.await();
        fixedThreadPool.shutdown();
    }

    /**
     * 单线程线程池
     */
    static void singleThreadExecutorTest() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + Thread.currentThread().getName());
                }
            });
        }
    }

    /**
     * 非固定数量线程池
     */
    static void cachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + Thread.currentThread().getName());
                }
            });
        }
    }

    /**
     * 固定数量线程池-支持周期任务
     */
    static void scheduleThreadPoolTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + Thread.currentThread().getName());
                }
            }, 0, 4, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 自定义线程池
     */
    static void threadPoolTest() {
        //核心线程池大小 最大线程池大小 线程最大空闲时间 时间单位 线程等待队列 线程创建工厂 拒绝策略
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index + Thread.currentThread().getName());
                }
            });
        }

    }

    /**
     * 自定义线程工厂
     */
    static class NameTreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    /**
     * 自定义拒绝策略
     */
    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r, executor);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
            //System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

}
