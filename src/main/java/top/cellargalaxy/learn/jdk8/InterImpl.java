package top.cellargalaxy.learn.jdk8;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class InterImpl implements InterA, InterB {
	
	public static void main(String[] args) {
		InterA interA=new InterImpl();
		interA.defaultFun();
	}
	
	/**
	 * 当多个接口有相同的已经实现的默认方法的时候，要求实现类重写其方法
	 */
	@Override
	public void defaultFun() {
		System.out.println("实现类的默认方法");
	}
}
