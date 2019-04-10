package org.huluobo.TimerTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class FixedTimerTask extends TimerTask {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(simpleDateFormat.format(new Date()) +" "+ Thread.currentThread().getName() + "任务执行了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
