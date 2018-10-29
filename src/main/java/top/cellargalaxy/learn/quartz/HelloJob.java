package top.cellargalaxy.learn. quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cellargalaxy
 * @time 2018/7/4
 */
public class HelloJob implements Job {
	private final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private int value2;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		JobKey jobKey = jobDetail.getKey();
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String value1 = jobDataMap.getString("value1");
		System.out.println();
		System.out.println("jobKey: "+jobKey);
		System.out.println("通过JobDataMap获取的参数value1: " + value1);
		System.out.println("通过setter获取的参数value2: " + value2);
		System.out.println("现在时间是：" + dateFormat.format(new Date()));
		System.out.println();
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	public static void main(String[] args) throws SchedulerException, InterruptedException {
		//创建主类Scheduler
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		//定时任务和其他参数
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("jobName", "groupName")
				.usingJobData("value1", "Hello World!")
				.usingJobData("value2", 654321)
				.build();

		//定时任务的执行时间
		Date runTime = new Date(System.currentTimeMillis() + 1000 * 2);
//		Trigger trigger = TriggerBuilder.newTrigger()
//				.withIdentity("triggerName1", "groupName1")
//				.startAt(runTime)
//				.build();
		Trigger trigger=TriggerExample.example2();

		//添加某某任务，在某某时间执行
		scheduler.scheduleJob(jobDetail, trigger);

		//开始全部定时任务执行，等6秒钟，结束全部定时任务执行
		scheduler.start();
		Thread.sleep(1000 * 60);
		scheduler.shutdown();
	}
}
