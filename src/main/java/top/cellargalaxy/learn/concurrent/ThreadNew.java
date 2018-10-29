package top.cellargalaxy.learn.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class ThreadNew {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Th th = new Th();
		th.start();
		
		Thread run = new Thread(new Run());
		run.start();
		
		FutureTask<Integer> future = new FutureTask<Integer>(new Call());
		Thread call = new Thread(future);
		call.start();
		System.out.println("call: "+future.get());
	}
	
	public static class Th extends Thread {
		@Override
		public void run() {
			System.out.println("Th: " + (int) (1000 * Math.random()));
		}
	}
	
	public static class Run implements Runnable {
		public void run() {
			System.out.println("Run: " + (int) (1000 * Math.random()));
		}
	}
	
	public static class Call implements Callable<Integer> {
		public Integer call() throws Exception {
			return (int) (1000 * Math.random());
		}
	}
}