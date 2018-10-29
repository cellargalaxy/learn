package top.cellargalaxy.learn. uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 冷饮
 */
public abstract class ColdDrink implements Item {
	public Packing packing() {
		return new Bottle();
	}
	
	public abstract float price();
}