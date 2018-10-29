package top.cellargalaxy.learn.interClass;

import java.util.Stack;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class Outter {
	private String s1 = "aaa";
	private static String s2 = "bbb";

	public static void main(String[] args) {
		Outter outter = new Outter();

		Outter.Inner inner = outter.new Inner();//成员内部类 要先创建外部类对象才能创建，不能直接访问
		inner.print();

		Outter.StaticInner staticInner = new Outter.StaticInner();//静态内部类 可以直接访问
		staticInner.print();

		outter.localClass();

		outter.anonymousClass();
	}

	//成员内部类
	class Inner {
		public void print() {//成员内部类 无限制获取外部类的变量
			System.out.println("Inner.print(); s1:" + s1 + " ,s2:" + s2);
		}
	}

	//静态内部类
	static class StaticInner {
		public void print() {//静态内部类 不能获取外部类的非静态变量
			System.out.println("StaticInner.print(); s2:" + s2);
		}
	}

	//局部内部类
	public void localClass() {
		String s3 = "ccc";
		class LocalClass {
			public void print() {//局部内部类 无限制获取外部类的变量以及局部变量
				System.out.println("localClass.print(); s1:" + s1 + " ,s2:" + s2 + " ,s3:" + s3);
			}
		}
		new LocalClass().print();
	}

	//匿名类
	public void anonymousClass() {
		String s3 = "ccc";
		new Thread(new Runnable() {
			@Override
			public void run() {//匿名类 无限制获取外部类的变量以及局部变量
				System.out.println("anonymousClass.print(); s1:" + s1 + " ,s2:" + s2 + " ,s3:" + s3);
			}
		}).start();
	}
}
