package org.huluobo.TimerTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) {
		// Timer timer=new Timer();
		// timer.schedule(new FixedTimerTask(), 100,100);
		// timer.scheduleAtFixedRate(new FixedTimerTask(), 100, 100);
		// timer.cancel();//丢弃所有当前已安排的任务。终止main线程

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				 System.out.println(simpleDateFormat.format(new Date())+" "+Thread.currentThread().getName()+"任务执行了");  
			}
		}, 5, 3,TimeUnit.SECONDS);
	}
}
