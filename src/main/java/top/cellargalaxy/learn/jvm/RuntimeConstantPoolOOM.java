package top.cellargalaxy.learn.jvm;

/**
 * 在jdk7和jdk8中，String的intern方法会在常量池里记录第一次此字符串出现的指针
 * 因为“计算机软件”是第一次出现，所以intern根据指针返回的仍然是他自己
 * 但是“java”是StringBuilder的toString之前出现过，所以intern返回的第一个“java”而不是下面的“java”对象
 * 怎么“java”在StringBuilder的toString之前出现过呢？？？？
 * 我也不知道，但结果确实是false，把java换成其他字符串就是true
 * 只能猜是官方类里加载过“java”了
 *
 * Created by cellargalaxy on 18-1-28.
 */
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		String string1=new StringBuilder("计算机").append("软件").toString();
		System.out.println(string1==string1.intern());
		
		String string2=new StringBuilder("ja").append("va").toString();
		System.out.println(string2==string2.intern());
	}
}
