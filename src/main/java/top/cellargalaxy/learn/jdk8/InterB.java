package top.cellargalaxy.learn.jdk8;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public interface InterB {
	public static void staticFun(){
		System.out.println("接口B的静态方法");
	}
	
	default void defaultFun(){
		System.out.println("接口B的默认方法");
	}
}
