package top.cellargalaxy.learn.jvm;

/**
 * Created by cellargalaxy on 18-3-19.
 */
public class Jstack {
	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(1000*20);
		long[] longs=new long[Integer.MAX_VALUE];
		Thread.sleep(1000*20);
		longs[0]=1;
	}
}
