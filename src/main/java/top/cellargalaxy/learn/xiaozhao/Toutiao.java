package top.cellargalaxy.learn. xiaozhao;

import java.util.*;

/**
 * Created by cellargalaxy on 18-8-12.
 */
public class Toutiao {

	public static void main(String[] args) {
		t3();
	}

	public static void t3() {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.next());
		Set<Integer> as = new HashSet<>();
		for (int i = 0; i < n; i++) {
			as.add(Integer.valueOf(scanner.next()));
		}
		Set<Integer> bs = new HashSet<>();
		for (int i = 0; i < n; i++) {
			bs.add(Integer.valueOf(scanner.next()));
		}

		int count = 0;
		for (Integer b : bs) {
			int c = 0;
			for (Integer a : as) {
				if (a < b) {
					c++;
				}
			}
			count += c + jiecheng(c, 2);
		}
		System.out.println(count);
	}

	public static int jiecheng(int d, int x) {
		int answer = 1;
		for (int i = 0; i < x; i++) {
			answer *= d - i;
		}
		for (int i = 0; i < x; i++) {
			answer /= (i + 1);
		}
		return answer;
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		int bianjiCount = Integer.decode(scanner.next());
		LinkedList<LinkedList<Node>> bianjis = new LinkedList<>();
		for (int i = 0; i < bianjiCount; i++) {
			String[] strings = scanner.next().split(";");
			LinkedList<Node> nodes = new LinkedList<>();
			for (String string : strings) {
				String[] ss = string.split(",");
				nodes.add(new Node() {{
					x = Integer.valueOf(ss[0]);
					y = Integer.valueOf(ss[1]);
				}});
			}
			Collections.sort(nodes);
			bianjis.add(nodes);
		}

		Iterator<LinkedList<Node>> iterator = bianjis.iterator();
		LinkedList<Node> nodes1;
		LinkedList<Node> nodes2;
		if (iterator.hasNext()) {
			nodes2 = iterator.next();
		} else {
			return;
		}
		while (iterator.hasNext()) {
			nodes1 = nodes2;
			nodes2 = iterator.next();
			Iterator<Node> iterator1 = nodes1.iterator();
			Iterator<Node> iterator2 = nodes2.iterator();

			LinkedList<Node> nodes = new LinkedList<>();
			Node node1 = null;
			Node node2 = null;
			while (iterator1.hasNext() && iterator2.hasNext()) {
				if (node1 == null) {
					node1 = iterator1.next();
				}
				if (node2 == null) {
					node2 = iterator2.next();
				}

				Node node = congdie(node1, node2);
				if (node != null) {
					nodes.add(node);
					node1 = null;
					node2 = null;
				} else {
					if (node1.x < node2.x) {
						nodes.add(node1);
						node1 = null;
					} else {
						nodes.add(node2);
						node2 = null;
					}
				}
			}
			if (node1 != null) {
				nodes.add(node1);
			}
			if (node2 != null) {
				nodes.add(node2);
			}
			while (iterator1.hasNext()) {
				nodes.add(iterator1.next());
			}
			while (iterator2.hasNext()) {
				nodes.add(iterator2.next());
			}

			nodes2 = nodes;
		}

		Collections.sort(nodes2);

//		System.out.println(nodes2);

		Iterator<Node> nodeIterator = nodes2.iterator();
		LinkedList<Node> nodes = new LinkedList<>();
		Node node1 = null;
		Node node2 = null;
		if (nodeIterator.hasNext()) {
			node1 = nodeIterator.next();
		}
		while (nodeIterator.hasNext()) {
			node2 = nodeIterator.next();

			Node node = congdie(node1, node2);
			if (node != null) {
				node1 = node;
			} else {
				if (node1.x < node2.x) {
					nodes.add(node1);
					node1 = node2;
				} else {
					nodes.add(node2);
				}
			}
		}
		if (node1 != null) {
			nodes.add(node1);
		}

		nodeIterator = nodes.iterator();
		if (nodeIterator.hasNext()) {
			Node node = nodeIterator.next();
			System.out.print(node.x + "," + node.y);
		}
		while (nodeIterator.hasNext()) {
			Node node = nodeIterator.next();
			System.out.print(";" + node.x + "," + node.y);
		}
	}

	public static Node congdie(Node node1, Node node2) {
		if (node1.y < node2.x || node2.y < node1.x) {
			return null;
		}
		return new Node() {{
			x = node1.x < node2.x ? node1.x : node2.x;
			y = node1.y > node2.y ? node1.y : node2.y;
		}};
	}

	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = scanner.next().split(",");
		int y = Integer.valueOf(strings[0]);
		int x = Integer.valueOf(strings[1]);
		int[][] zuoweis = new int[y][x];
		for (int i = 0; i < zuoweis.length; i++) {
			strings = scanner.next().split(",");
			for (int j = 0; j < zuoweis[i].length; j++) {
				zuoweis[i][j] = Integer.valueOf(strings[j]);
			}
		}

		int maxQiuduiSize = -1;
		int qiuduiCount = 2;

		for (int i = 0; i < zuoweis.length; i++) {
			for (int j = 0; j < zuoweis[i].length; j++) {

				if (zuoweis[i][j] == 1) {
					int qiuduiSize = 0;
					LinkedList<Node> linkedList = new LinkedList<>();

					int finalJ = j;
					int finalI = i;
					linkedList.add(new Node() {{
						x = finalI;
						y = finalJ;
					}});
					zuoweis[i][j] = qiuduiCount;

//					System.out.println();
//					System.out.println("qiuduiCount: " + qiuduiCount);
					Node node;
					while ((node = linkedList.poll()) != null) {
//						if (qiuduiCount == 3) {
//							System.out.println(node.x + "," + node.y);
//						}
						qiuduiSize++;
						chuangbo(zuoweis, node.x, node.y, linkedList, qiuduiCount);
					}

					qiuduiCount++;
					if (qiuduiSize > maxQiuduiSize) {
						maxQiuduiSize = qiuduiSize;
					}
				}
			}
		}
		System.out.println((qiuduiCount - 2) + "," + maxQiuduiSize);

//		for (int i = 0; i < zuoweis.length; i++) {
//			for (int j = 0; j < zuoweis[i].length; j++) {
//				System.out.print(zuoweis[i][j] + ",");
//			}
//			System.out.println();
//		}
	}

	public static void chuangbo(int[][] zuoweis, int i, int j, LinkedList<Node> linkedList, int qiuduiCount) {
		if (checkIn(zuoweis, i - 1, j - 1) && zuoweis[i - 1][j - 1] == 1) {
			linkedList.add(new Node() {{
				x = i - 1;
				y = j - 1;
			}});
			zuoweis[i - 1][j - 1] = qiuduiCount;
		}
		if (checkIn(zuoweis, i - 1, j) && zuoweis[i - 1][j] == 1) {
			linkedList.add(new Node() {{
				x = i - 1;
				y = j;
			}});
			zuoweis[i - 1][j - 1] = qiuduiCount;
		}
		if (checkIn(zuoweis, i - 1, j + 1) && zuoweis[i - 1][j + 1] == 1) {
			linkedList.add(new Node() {{
				x = i - 1;
				y = j + 1;
			}});
			zuoweis[i - 1][j + 1] = qiuduiCount;
		}
		if (checkIn(zuoweis, i, j - 1) && zuoweis[i][j - 1] == 1) {
			linkedList.add(new Node() {{
				x = i;
				y = j - 1;
			}});
			zuoweis[i][j - 1] = qiuduiCount;
		}
		if (checkIn(zuoweis, i, j + 1) && zuoweis[i][j + 1] == 1) {
			linkedList.add(new Node() {{
				x = i;
				y = j + 1;
			}});
			zuoweis[i][j + 1] = qiuduiCount;
		}
		if (checkIn(zuoweis, i + 1, j - 1) && zuoweis[i + 1][j - 1] == 1) {
			linkedList.add(new Node() {{
				x = i + 1;
				y = j - 1;
			}});
			zuoweis[i + 1][j - 1] = qiuduiCount;
		}
		if (checkIn(zuoweis, i + 1, j) && zuoweis[i + 1][j] == 1) {
			linkedList.add(new Node() {{
				x = i + 1;
				y = j;
			}});
			zuoweis[i + 1][j] = qiuduiCount;
		}
		if (checkIn(zuoweis, i + 1, j + 1) && zuoweis[i + 1][j + 1] == 1) {
			linkedList.add(new Node() {{
				x = i + 1;
				y = j + 1;
			}});
			zuoweis[i + 1][j + 1] = qiuduiCount;
		}
	}

	public static boolean checkIn(int[][] ints, int i, int j) {
		return i > -1 && i < ints.length && j > -1 && j < ints[0].length;
	}


	static class Node implements Comparable<Node> {
		int x;
		int y;

		@Override
		public int compareTo(Node o) {
			return x - o.x;
		}

		@Override
		public String toString() {
			return "{" +
					"x=" + x +
					", y=" + y +
					'}';
		}
	}
}
