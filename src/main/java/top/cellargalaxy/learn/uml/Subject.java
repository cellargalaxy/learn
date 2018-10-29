package top.cellargalaxy.learn. uml;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cellargalaxy on 18-4-25.
 */
public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	private int state;
	
	public int getState() {
		return state;
	}
	
	/**
	 * 修改了值之后通知全部观察者
	 *
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
		notifyAllObservers();
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	private void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
