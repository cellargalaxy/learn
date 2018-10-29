package top.cellargalaxy.learn. xiaozhao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by cellargalaxy on 18-9-9.
 */
public class Jingdong {
	public static void main(String[] args) {
		t1();
	}

	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		int zhuCount = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < zhuCount; i++) {
			String[] strings = scanner.nextLine().split(" ");
			int nodeCount = Integer.valueOf(strings[0]);
			int bianCount = Integer.valueOf(strings[1]);

			Node[] nodes = new Node[nodeCount];
			for (int j = 0; j < nodes.length; j++) {
				nodes[j] = new Node(j);
			}

			for (int j = 0; j < bianCount; j++) {
				strings = scanner.nextLine().split(" ");
				int node1 = Integer.valueOf(strings[0]) - 1;
				int node2 = Integer.valueOf(strings[1]) - 1;
				nodes[node1].lians.add(nodes[node2]);
				nodes[node2].lians.add(nodes[node1]);
			}

			for (Node node : nodes) {
				for (Node node1 : nodes) {
					if (!node.lians.contains(node1)) {
						node.bulians.add(node1);
					}
				}
			}

			int sort = 0;
			for (Node node : nodes) {
				if (node.sort == 0) {
					sort++;
					sort(node, sort);
				}
			}

			if (sort < 2) {
				System.out.println("No");
			} else {
				System.out.println("Yes");
			}
		}
	}

	private static void sort(Node node, int sort) {
		node.sort = sort;
		for (Node bulian : node.bulians) {
			if (bulian.sort == 0) {
				sort(bulian, sort);
			}
		}
	}

	static class Node {
		final int i;
		final Set<Node> lians = new HashSet<>();
		final Set<Node> bulians = new HashSet<>();
		private int sort;

		public Node(int i) {
			this.i = i;
		}

		@Override
		public String toString() {
			return "{" + i + "}";
		}
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		int count = Integer.valueOf(scanner.nextLine());
		int[][] zhiliangss = new int[count][3];
		for (int i = 0; i < zhiliangss.length; i++) {
			String[] strings = scanner.nextLine().split(" ");
			zhiliangss[i][0] = Integer.valueOf(strings[0]);
			zhiliangss[i][1] = Integer.valueOf(strings[1]);
			zhiliangss[i][2] = Integer.valueOf(strings[2]);
		}

		int wenti = 0;
		for (int i = 0; i < zhiliangss.length; i++) {
			for (int j = 0; j < zhiliangss.length; j++) {
				if (zhiliangss[i][0] < zhiliangss[j][0] && zhiliangss[i][1] < zhiliangss[j][1] && zhiliangss[i][2] < zhiliangss[j][2]) {
					wenti++;
					break;
				}
			}
		}

		System.out.println(wenti);
	}
}
/*
给定一张包含N个点、M条边的无向图，每条边连接两个不同的点，且任意两点间最多只有一条边。对于这样的简单无向图，如果能将所有点划分成若干个集合，使得任意两个同一集合内的点之间没有边相连，任意两个不同集合内的点之间有边相连，则称该图为完全多部图。现在你需要判断给定的图是否为完全多部图。

 任意两个(不同集合)内的点之间有边相连

 你好，感觉【完全多部图】的题目句子有歧义。
 “使得任意两个同一集合内的点之间没有边相连”是“使得任意集合内的任意两个点之间没有边相连”吗
 以及
 “任意两个不同集合内的点之间有边相连”是“不同集合内的任意两个点之间有边相连”还是“两个不同集合内的任意点之间有边相连”呢？
 */