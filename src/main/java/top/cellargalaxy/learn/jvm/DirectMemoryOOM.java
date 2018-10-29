package top.cellargalaxy.learn.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机内存直接溢出
 *
 * VM Args: -Xmx20m -XX:MaxDirectMemorySize=10m
 *
 * Created by cellargalaxy on 18-1-28.
 */
public class DirectMemoryOOM {
	private final static int _1MB=1024*1024;
	public static void main(String[] args) throws IllegalAccessException {
		while (true) {
			Field unsafeField= Unsafe.class.getDeclaredFields()[0];
			unsafeField.setAccessible(true);
			Unsafe unsafe= (Unsafe) unsafeField.get(null);
			while (true) {
				unsafe.allocateMemory(_1MB);
			}
		}
	}
}
