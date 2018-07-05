package quartz;

import org.quartz.*;

import java.util.Date;


/**
 * @author cellargalaxy
 * @time 2018/7/4
 */
public class TriggerExample {
	//立即开始，每个一秒执行一次，十秒后结束
	public static final Trigger example1() {
		return TriggerBuilder.newTrigger()
				.withIdentity("triggerName1", "groupName1")
				//.startAt() //不设定开始时间即立即开始
				.withSchedule(
						SimpleScheduleBuilder
								.simpleSchedule()
								.withIntervalInSeconds(1)//间隔一秒钟
								.repeatForever()//永远重复
				)
				.endAt(new Date(System.currentTimeMillis() + 1000 * 10)) //不设定结束时间即永不结束
				.build();
	}

	//立即开始，每个一秒执行一次，永不结束
	public static final Trigger example2() {
		return TriggerBuilder.newTrigger()
				.withIdentity("triggerName", "groupName")
				.withSchedule(CronScheduleBuilder.cronSchedule("/1 * * * * ?"))
				.build();
	}

	//每天10:42执行
	public static final Trigger example3() {
		return TriggerBuilder.newTrigger()
				.withIdentity("triggerName", "groupName")
				.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(10, 42))
				.build();
	}
}