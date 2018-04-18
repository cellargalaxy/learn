package uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 汉堡包
 */
public abstract class Burger implements Item {
	public Packing packing() {
		return new Wrapper();
	}
	
	public abstract float price();
}