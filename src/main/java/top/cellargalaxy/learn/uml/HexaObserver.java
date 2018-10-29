package top.cellargalaxy.learn. uml;

/**
 * Created by cellargalaxy on 18-4-25.
 */
public class HexaObserver implements Observer {
	private final Subject subject;
	
	public HexaObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println("Hex String: "
				+ Integer.toHexString(subject.getState()).toUpperCase());
	}
}
