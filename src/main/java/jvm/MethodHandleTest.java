package jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * Created by cellargalaxy on 18-2-2.
 */
public class MethodHandleTest {
	static class Print{
		public void println(String string){
			System.out.println(string);
		}
	}
	
	private static MethodHandle getPrintlnMH(Object object) throws NoSuchMethodException, IllegalAccessException {
		MethodType methodType=MethodType.methodType(void.class,String.class);
		return lookup().findVirtual(object.getClass(),"println",methodType).bindTo(object);
	}
	
	public static void main(String[] args) throws Throwable {
		Object object=new Print();
		getPrintlnMH(object).invokeExact("aaaaa");
	}
}
