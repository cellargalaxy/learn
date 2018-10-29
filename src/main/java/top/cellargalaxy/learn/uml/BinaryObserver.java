package top.cellargalaxy.learn. uml;

/**
 * Created by cellargalaxy on 18-4-25.
 */
public class BinaryObserver implements Observer {
	private final Subject subject;
	
	/**
	 * 私底下拥有一个subject
	 * 然后也让subject拥有自己这个观察者，让subject可以调用自己的update方法
	 * 所以其实自己不保有subject这个对象也是可以的，毕竟观察者又不会调用subject的方法（除了让subject持有自己）
	 *
	 * @param subject
	 */
	public BinaryObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
	}
}
