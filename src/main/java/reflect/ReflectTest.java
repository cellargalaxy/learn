package reflect;

import java.lang.reflect.*;

/**
 * Created by cellargalaxy on 17-10-21.
 * http://www.cnblogs.com/rollenholt/archive/2011/09/02/2163758.html
 */
public class ReflectTest {
	
	public static void main(String[] args) {
		test11();
	}
	
	/**
	 * 动态代理
	 */
	private static void test11(){
		MyInvocationHandler invocationHandler=new MyInvocationHandler();
		HelloSay helloSay= (HelloSay) invocationHandler.bind(new People());
		helloSay.sayHello("xiaoming",11);
		System.out.println();
		helloSay.sayGoodBey("xiaohua");
		System.out.println();
		System.out.println("helloSay类 "+helloSay.getClass().getName());
		System.out.println("helloSay父类 "+helloSay.getClass().getSuperclass().getName());
		System.out.println("helloSay爷类 "+helloSay.getClass().getSuperclass().getClass().getSuperclass().getName());
	}
	
	/**
	 * 获得类加载器
	 * 其实在java中有三种类类加载器。
	 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
	 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
	 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
	 */
	private static void test10(){
		Class<?> clazz= People.class;
		System.out.println(clazz.getClassLoader().getClass().getName());
	}
	
	/**
	 * 操作属性
	 */
	private static void test9() {
		Class<?> clazz = People.class;
		try {
			Object object = clazz.newInstance();
			Field field = clazz.getDeclaredField("name");
			field.setAccessible(true);
			field.set(object, "xiaoming");
			System.out.println(field.get(object));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取全部属性
	 */
	private static void test8() {
		Class<?> clazz = People.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			int mo = field.getModifiers();
			String priv = Modifier.toString(mo);
			Class<?> type = field.getType();
			System.out.println(priv + " " + type.getName() + " " + field.getName());
		}
	}
	
	/**
	 * 方法的调用
	 */
	private static void test7() {
		Class<?> clazz = People.class;
		try {
			Method method1 = clazz.getMethod("sayHello", String.class, int.class);
			Method method2 = clazz.getMethod("setAge", int.class);
			Method method3 = clazz.getMethod("toString");
			Object object = clazz.newInstance();
			System.out.println("method1: " + method1.invoke(object, "xiaoming", 21));
			System.out.println("method2: " + method2.invoke(object, 22));
			System.out.println("method3: " + method3.invoke(object));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取全部方法
	 */
	private static void test6() {
		Class<?> clazz = People.class;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			int mo = method.getModifiers();
			System.out.print(Modifier.toString(mo) + " ");
			Class<?> result = method.getReturnType();
			System.out.print(result.toString() + " ");
			System.out.print(method.getName() + "(");
			Class<?>[] p = method.getParameterTypes();
			for (int i = 0; i < p.length; i++) {
				System.out.print(p[i].getName() + " arg" + i);
				if (i < p.length - 1) {
					System.out.print(",");
				}
			}
			System.out.print(") ");
			Class<?>[] exceptions = method.getExceptionTypes();
			for (Class<?> exception : exceptions) {
				System.out.print(exception.getName() + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 构造函数对象的方法
	 */
	private static void test5() {
		Class<?> clazz = People.class;
		Constructor<?> constructor = clazz.getConstructors()[1];
		System.out.print("构造方法: ");
		
		int mo = constructor.getModifiers();
		System.out.print(Modifier.toString(mo) + " ");
		
		System.out.print(constructor.getName());
		
		System.out.print("(");
		Class<?>[] p = constructor.getParameterTypes();
		for (int i = 0; i < p.length; i++) {
			System.out.print(p[i].getName() + " arg" + i);
			if (i < p.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("){}");
	}
	
	/**
	 * 创建实例
	 */
	private static void test4() {
		Class<?> clazz = People.class;
		Constructor<?>[] constructors = clazz.getConstructors();
		Object[] objects = new Object[constructors.length + 1];
		try {
			objects[0] = constructors[0].newInstance();
			objects[1] = constructors[1].newInstance("xiaoming", 12);
			objects[2] = clazz.newInstance();
			
			((People) (objects[0])).setAge(11);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		for (Object object : objects) {
			System.out.println("newInstance: " + object.toString());
		}
	}
	
	/**
	 * 获取全部构造函数
	 */
	private static void test3() {
		Class<?> clazz = People.class;
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> constructor : constructors) {
			System.out.println("构造函数: " + constructor.toString());
		}
	}
	
	/**
	 * 获取父类和接口class
	 */
	private static void test2() {
		Class<?> clazz = People.class;
		Class<?> parent = clazz.getSuperclass();
		Class<?>[] interfaces = clazz.getInterfaces();
		System.out.println("父类: " + parent.toString());
		for (Class<?> anInterface : interfaces) {
			System.out.println("接口: " + anInterface.toString());
		}
	}
	
	/**
	 * 三种方法获得class对象
	 */
	private static void test1() {
		Class<?> clazz1 = null;
		Class<?> clazz2 = null;
		Class<?> clazz3 = null;
		
		try {
			clazz1 = Class.forName("reflect.People");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		clazz2 = new People().getClass();
		clazz3 = People.class;
		
		System.out.println("class1 name: " + clazz1.getName());
		System.out.println("class2 name: " + clazz2.getName());
		System.out.println("class3 name: " + clazz3.getName());
	}
}
