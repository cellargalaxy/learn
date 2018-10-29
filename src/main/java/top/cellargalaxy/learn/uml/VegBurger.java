package top.cellargalaxy.learn. uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 素食汉堡
 */
public class VegBurger extends Burger {
	@Override
	public float price() {
		return 25.0f;
	}
	
	public String name() {
		return "Veg Burger";
	}
}
