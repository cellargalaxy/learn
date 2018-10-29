package top.cellargalaxy.learn. reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by cellargalaxy on 17-10-22.
 */
public class MyInvocationHandler implements InvocationHandler {
	private Object object;
	public Object bind(Object object){
		this.object=object;
		return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("先做准备工作，然后调用下面的被代理方法");
		Object result=method.invoke(object,args);
		System.out.println("调用完上面的被代理方法，做收尾工作");
		return result;
	}
}
