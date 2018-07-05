package exec;

import org.apache.commons.exec.*;
import org.apache.commons.exec.launcher.CommandLauncher;
import org.apache.commons.exec.launcher.CommandLauncherFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cellargalaxy on 18-5-14.
 */
public class MyDefaultExecutor {
	private ExecuteStreamHandler streamHandler = new PumpStreamHandler();//应该是用来处理命令执行时候的流
	private File workingDirectory = new File(".");//命令执行的当前路径
	private ExecuteWatchdog watchdog;
	private int[] exitValues = new int[0];
	private final CommandLauncher launcher = CommandLauncherFactory.createVMLauncher();
	private ProcessDestroyer processDestroyer;//运行执行摧毁器
	private Thread executorThread;//执行线程
	private IOException exceptionCaught = null;


	public int execute(CommandLine command) throws ExecuteException, IOException {
		return execute(command, (Map) null);
	}

	public int execute(CommandLine command, Map<String, String> environment) throws ExecuteException, IOException {
		if (this.workingDirectory != null && !this.workingDirectory.exists()) {
			throw new IOException(this.workingDirectory + " doesn't exist.");
		} else {
			//利用executeInternal方法执行，会阻塞
			return executeInternal(command, environment, this.workingDirectory, this.streamHandler);
		}
	}

	public void execute(CommandLine command, ExecuteResultHandler handler) throws ExecuteException, IOException {
		this.execute(command, null, handler);
	}

	public void execute(final CommandLine command, final Map<String, String> environment, final ExecuteResultHandler handler) throws ExecuteException, IOException {
		if (this.workingDirectory != null && !this.workingDirectory.exists()) {
			throw new IOException(this.workingDirectory + " doesn't exist.");
		} else {
			if (this.watchdog != null) {
				//设置运行执行未开始？
				this.watchdog.setProcessNotStarted();
			}

			Runnable runnable = new Runnable() {
				public void run() {
					int exitValue = -559038737;

					try {
						//同样利用executeInternal方法执行，会阻塞。但不怕，比较在线程里
						exitValue = MyDefaultExecutor.this.executeInternal(command, environment, DefaultExecutor.this.workingDirectory, DefaultExecutor.this.streamHandler);
						//执行完后，调用handler的onProcessComplete方法，handler就可以从waitFor方法里返回
						handler.onProcessComplete(exitValue);
					} catch (ExecuteException var3) {
						//异常什么的都记录到handler里
						handler.onProcessFailed(var3);
					} catch (Exception var4) {
						handler.onProcessFailed(new ExecuteException("Execution failed", exitValue, var4));
					}
				}
			};
			//创建一个Thread然后start，就不阻塞了
			this.executorThread = this.createThread(runnable, "Exec Default Executor");
			this.getExecutorThread().start();
		}
	}

	protected Thread createThread(Runnable runnable, String name) {
		return new Thread(runnable, name);
	}

	//执行命令，会阻塞，因为调用了process.waitFor()
	private int executeInternal(CommandLine command, Map<String, String> environment, File dir, ExecuteStreamHandler streams) throws IOException {
		this.setExceptionCaught(null);
		//创建一个运行之类的对象
		Process process = this.launch(command, environment, dir);

		try {
			//往流处理器里设置这个运行对象
			streams.setProcessInputStream(process.getOutputStream());
			streams.setProcessOutputStream(process.getInputStream());
			streams.setProcessErrorStream(process.getErrorStream());
		} catch (IOException var29) {
			process.destroy();
			throw var29;
		}

		//启动用于处理流处理器的输入输出异常流的三个线程
		//（又是三个新创建的线程啊，怎么没有用线程池呢）
		streams.start();

		int var7;
		try {
			if (getProcessDestroyer() != null) {
				//往运行摧毁器里添加这处理（运行摧毁器会按照监控狗来摧毁运行吗？）
				getProcessDestroyer().add(process);
			}

			if (this.watchdog != null) {
				//监控狗执行运行？
				this.watchdog.start(process);
			}

			int exitValue = -559038737;

			try {
				//执行后等待完成，并获取离开值
				exitValue = process.waitFor();
			} catch (InterruptedException var27) {
				process.destroy();
			} finally {
				Thread.interrupted();
			}

			if (this.watchdog != null) {
				//停止监控狗
				this.watchdog.stop();
			}

			try {
				//关闭三个流的线程
				streams.stop();
			} catch (IOException var26) {
				this.setExceptionCaught(var26);
			}

			//关闭各种流
			closeProcessStreams(process);
			if (this.getExceptionCaught() != null) {
				throw this.getExceptionCaught();
			}

			if (this.watchdog != null) {
				try {
					this.watchdog.checkException();
				} catch (IOException var24) {
					throw var24;
				} catch (Exception var25) {
					throw new IOException(var25.getMessage());
				}
			}

			if (this.isFailure(exitValue)) {
				throw new ExecuteException("Process exited with an error: " + exitValue, exitValue);
			}

			var7 = exitValue;
		} finally {
			if (this.getProcessDestroyer() != null) {
				this.getProcessDestroyer().remove(process);
			}

		}
		return var7;
	}

	private void closeProcessStreams(Process process) {
		try {
			process.getInputStream().close();
		} catch (IOException var5) {
			this.setExceptionCaught(var5);
		}

		try {
			process.getOutputStream().close();
		} catch (IOException var4) {
			this.setExceptionCaught(var4);
		}

		try {
			process.getErrorStream().close();
		} catch (IOException var3) {
			this.setExceptionCaught(var3);
		}
	}

	protected Process launch(CommandLine command, Map<String, String> env, File dir) throws IOException {
		if (this.launcher == null) {
			throw new IllegalStateException("CommandLauncher can not be null");
		} else if (dir != null && !dir.exists()) {
			throw new IOException(dir + " doesn't exist.");
		} else {
			return this.launcher.exec(command, env, dir);
		}
	}

	public void setExitValue(int value) {
		this.setExitValues(new int[]{value});
	}

	public void setExitValues(int[] values) {
		this.exitValues = values == null ? null : (int[]) ((int[]) values.clone());
	}

	public boolean isFailure(int exitValue) {
		if (this.exitValues == null) {
			return false;
		} else if (this.exitValues.length == 0) {
			return this.launcher.isFailure(exitValue);
		} else {
			int[] arr$ = this.exitValues;
			int len$ = arr$.length;

			for (int i$ = 0; i$ < len$; ++i$) {
				int exitValue2 = arr$[i$];
				if (exitValue2 == exitValue) {
					return false;
				}
			}
			return true;
		}
	}

	protected Thread getExecutorThread() {
		return this.executorThread;
	}

	private void setExceptionCaught(IOException e) {
		if (this.exceptionCaught == null) {
			this.exceptionCaught = e;
		}
	}

	private IOException getExceptionCaught() {
		return this.exceptionCaught;
	}

	public ExecuteStreamHandler getStreamHandler() {
		return this.streamHandler;
	}

	public void setStreamHandler(ExecuteStreamHandler streamHandler) {
		this.streamHandler = streamHandler;
	}

	public ExecuteWatchdog getWatchdog() {
		return this.watchdog;
	}

	public void setWatchdog(ExecuteWatchdog watchDog) {
		this.watchdog = watchDog;
	}

	public ProcessDestroyer getProcessDestroyer() {
		return this.processDestroyer;
	}

	public void setProcessDestroyer(ProcessDestroyer processDestroyer) {
		this.processDestroyer = processDestroyer;
	}

	public File getWorkingDirectory() {
		return this.workingDirectory;
	}

	public void setWorkingDirectory(File dir) {
		this.workingDirectory = dir;
	}
}
