package uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 可口可乐
 */
public class Coke extends ColdDrink {
	
	@Override
	public float price() {
		return 30.0f;
	}
	
	public String name() {
		return "Coke";
	}
}