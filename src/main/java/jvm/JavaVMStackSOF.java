package jvm;

/**
 * 虚拟机栈过深
 *
 * VM Args: -Xss128k
 *
 * Created by cellargalaxy on 18-1-28.
 */
public class JavaVMStackSOF {
	private int stackLength=0;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JavaVMStackSOF javaVMStackSOF=new JavaVMStackSOF();
		try {
			javaVMStackSOF.stackLeak();
		}catch (Throwable e){
			System.out.println("stack length: "+javaVMStackSOF.stackLength);
			e.printStackTrace();
		}
	}
}
