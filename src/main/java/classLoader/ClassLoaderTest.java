package classLoader;

/**
 * Created by cellargalaxy on 17-10-22.
 * http://blog.csdn.net/justloveyou_/article/details/72217806
 */
public class ClassLoaderTest {
	public static void main(String[] args) {
		test1();
	}
	
	private static void test1(){
		ClassLoader classLoader1=new UrlClassLoader("http://www.cellargalaxy.top/mycloud/drive/20171022");
		ClassLoader classLoader2=new UrlClassLoader("http://www.cellargalaxy.top/mycloud/drive/20171022");
		System.out.println("fileClassLoader: "+classLoader2.getClass().getName());
		
		try {
			Object object1= classLoader1.loadClass("reflect.People1").newInstance();
			Object object2= classLoader1.loadClass("reflect.People1").newInstance();
			Object object3= classLoader2.loadClass("reflect.People1").newInstance();
			System.out.println("class1 == : "+(object1.getClass()==object2.getClass()));
			System.out.println("class1 equals : "+(object1.getClass().equals(object2.getClass())));
			System.out.println("class2 == : "+(object1.getClass()==object3.getClass()));
			System.out.println("class2 equals : "+(object1.getClass().equals(object3.getClass())));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}
}
