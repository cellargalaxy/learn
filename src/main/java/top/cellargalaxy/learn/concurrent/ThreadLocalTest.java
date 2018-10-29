package top.cellargalaxy.learn.concurrent;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class ThreadLocalTest extends Thread {
	public static final ThreadLocal<Double> THREAD_LOCAL = new ThreadLocal<Double>();
	
	public static void main(String[] args) {
		ThreadLocalTest t1 = new ThreadLocalTest("线程1");
		ThreadLocalTest t2 = new ThreadLocalTest("线程2");
		t1.start();
		t2.start();
	}
	
	public ThreadLocalTest(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep((int) (1000 * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print(Thread.currentThread().getName() + "拥有对象：" + THREAD_LOCAL.hashCode());
			System.out.print("，将原值：" + THREAD_LOCAL.get());
			THREAD_LOCAL.set(Math.random());
			System.out.println("，修改为：" + THREAD_LOCAL.get());
		}
	}
}
