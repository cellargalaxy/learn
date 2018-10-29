package top.cellargalaxy.learn.jdk8;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public interface InterA {
	/**
	 * jdk8的接口可以实现静态方法和默认方法
	 * 静态方法调用：接口类.静态方法()
	 * 默认方法调用：接口实现类.默认方法()
	 * 所以jdk8也可以有main方法了
	 * @param args
	 */
	public static void main(String[] args) {
		InterA.staticFun();
		//InterA.defaultFun();//调用不能
	}
	
	public static void staticFun(){
		System.out.println("接口A的静态方法");
	}
	
	default void defaultFun(){
		System.out.println("接口A的默认方法");
	}
}
