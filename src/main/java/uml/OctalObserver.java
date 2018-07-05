package uml;

/**
 * Created by cellargalaxy on 18-4-25.
 */
public class OctalObserver implements Observer {
	private final Subject subject;
	
	public OctalObserver(Subject subject) {
		this.subject = subject;
		this.subject.attach(this);
	}
	
	@Override
	public void update() {
		System.out.println("Octal String: "
				+ Integer.toOctalString(subject.getState()));
	}
}
