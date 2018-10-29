package top.cellargalaxy.learn. xiaozhao;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by cellargalaxy on 18-8-25.
 */
public class TouTiao2 {
	public static void main(String[] args) {
		t4();
	}

	public static final void t4() {
		Scanner scanner = new Scanner(System.in);
//		int size = Integer.valueOf(scanner.next());
//		int hour = Integer.valueOf(scanner.next());
//		int[] pms = new int[size];
//		for (int i = 0; i < pms.length; i++) {
//			pms[i] = Integer.valueOf(scanner.next());
//		}
		int size = 4;
		int hour = 3;
		int[] pms = {
				10, 3, 7, 5,
				10, 3, 7, 5,
				10, 3, 7, 5,
				10, 3, 7, 5};

		int index = 0;
		int zeng = 0;
		for (int i = 1; i < pms.length; i++) {
			if (pms[index] <= pms[i]) {
				System.out.println(index + " > " + i);
				zeng++;
				index = i;
			}else {

			}
		}
		System.out.println(zeng);
	}

	public static final void t1() {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.next());
		Node[] nodes = new Node[n];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node();
			nodes[i].t = i;
		}
		for (Node node : nodes) {
			int i;
			while ((i = Integer.valueOf(scanner.next())) > 0) {
				node.set.add(nodes[i - 1]);
			}
		}
//		for (Node node : nodes) {
//			String string = scanner.next();
//			String[] strings = string.split(" ");
//			for (String s : strings) {
//				int i = Integer.valueOf(s);
//				if (i > 0) {
//					node.lians.add(nodes[i - 1]);
//				}
//			}
//		}
		for (Node node : nodes) {
			TreeSet<Node> set = node.set;
			for (Node integerNode : set) {
				integerNode.set.add(node);
			}
		}

		int m = 0;
		for (Node node : nodes) {
			if (!node.isBianli) {
				m++;
				TreeSet<Node> list = new TreeSet<>();
				bianli(node, list);
				while (list.size() > 0) {
					bianli(list.pollFirst(), list);
				}
			}
		}
		System.out.println(m);
	}

	private static void bianli(Node node, TreeSet<Node> list) {
		node.isBianli = true;
		TreeSet<Node> set = node.set;
		for (Node node1 : set) {
			if (!node1.isBianli) {
				list.add(node1);
			}
		}
	}

	private static String pringNode(Node node) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{" + node.t + ":" + node.isBianli + "}, ");
		TreeSet<Node> set = node.set;
		for (Node n : set) {
			stringBuilder.append("{" + n.t + ":" + n.isBianli + "}, ");
		}
		return stringBuilder.toString();
	}

	static class Node implements Comparable<Node> {
		public int t;
		public TreeSet<Node> set = new TreeSet<>();
		public boolean isBianli = false;

		@Override
		public String toString() {
			return "Node{" +
					"t=" + t +
					", isBianli=" + isBianli +
					'}';
		}

		@Override
		public int compareTo(Node o) {
			return t - o.t;
		}
	}
}
