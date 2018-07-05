package exec;

import org.apache.commons.exec.*;

import java.io.IOException;

/**
 * Created by cellargalaxy on 18-5-14.
 */
public class Exec {
	public static void main(String[] args) throws IOException, InterruptedException {
		CommandLine commandLine = new CommandLine("xxx.exe");
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(1);
		executor.setWatchdog(watchdog);
		//在执行方法添加DefaultExecuteResultHandler对象，这样方法就不会阻塞了
		executor.execute(commandLine, resultHandler);
		//waitFor方法会阻塞直到命令执行完成或者到监控狗指定的时间
		resultHandler.waitFor();
	}
}
