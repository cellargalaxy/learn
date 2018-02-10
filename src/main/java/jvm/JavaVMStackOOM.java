package jvm;

/**
 * 虚拟机栈溢出
 *
 * VM Args: -Xss2m
 *
 * Created by cellargalaxy on 18-1-28.
 */
public class JavaVMStackOOM {
	private void dontstop(){
		while (true) {
		
		}
	}
	public void stackLeakByThread(){
		while (true) {
			Thread thread=new Thread(new Runnable() {
				public void run() {
					dontstop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM javaVMStackOOM=new JavaVMStackOOM();
		javaVMStackOOM.stackLeakByThread();
	}
}
