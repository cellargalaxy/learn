package top.cellargalaxy.learn.jvm;

import java.util.LinkedList;

/**
 * java堆溢出
 *
 * VM Args: -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError
 *
 * Created by cellargalaxy on 18-1-28.
 */
public class HeapOOM {
	public static void main(String[] args) {
		LinkedList<HeapOOM> linkedList=new LinkedList<HeapOOM>();
		while (true) {
			linkedList.add(new HeapOOM());
		}
	}
}
