package top.cellargalaxy.learn. uml;

/**
 * Created by cellargalaxy on 18-3-28.
 * 食物
 */
public interface Item {
	public String name();
	
	public Packing packing();
	
	public float price();
}