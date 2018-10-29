package top.cellargalaxy.learn. uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 鸡肉汉堡
 */
public class ChickenBurger extends Burger {
	@Override
	public float price() {
		return 50.5f;
	}
	
	public String name() {
		return "Chicken Burger";
	}
}
