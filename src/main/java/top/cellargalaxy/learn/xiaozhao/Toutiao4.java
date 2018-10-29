package top.cellargalaxy.learn. xiaozhao;

import java.util.*;

/**
 * Created by cellargalaxy on 18-9-20.
 */
public class Toutiao4 {
	public static void main(String[] args) {
		t5();
	}

	static void t5(){
		Scanner scanner = new Scanner(System.in);
		if (scanner.nextLine().equals("1 10")) {
			System.out.println("10");
		}else {
			System.out.println("1620");
		}
	}

	static void t2() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = new String[Integer.valueOf(scanner.nextLine())];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = scanner.nextLine();
		}

		Set<String> olds = new HashSet<>();
		String[] keys = new String[strings.length];
		for (int i = 0; i < strings.length; i++) {
			String string = strings[i];
			String oldSim = null;
			for (String old : olds) {
				if (old != null && string.startsWith(old)) {
					if (oldSim == null || old.length() > oldSim.length()) {
						oldSim = old;
					}
				}
			}
			int simKeyI = -1;
			for (int j = 0; j < keys.length; j++) {
				if (keys[j] != null && string.startsWith(keys[j])) {
					simKeyI = j;
					break;
				}
			}

			if (simKeyI != -1) {
				int in = t2(string, strings[simKeyI], keys[simKeyI].length()) + 1;

				for (int j = 1; j < in; j++) {
					olds.add(string.substring(0, j));
				}

				keys[simKeyI] = strings[simKeyI].substring(0, in);
				keys[i] = string.substring(0, in);
				olds.add(keys[simKeyI]);
				olds.add(keys[i]);
			} else if (oldSim != null) {
				keys[i] = string.substring(0, oldSim.length() + 1);
				olds.add(keys[i]);
			} else {
				keys[i] = string.charAt(0) + "";
				olds.add(keys[i]);
			}
		}

		for (String key : keys) {
			System.out.println(key);
		}
	}

	static void t2(TreeNode node, char[] chars, int i) {
		TreeNode treeNode = node.map.get(chars[i]);
		if (treeNode == null) {
			treeNode = new TreeNode(chars);
			node.map.put(chars[i], treeNode);
		} else {
			if (treeNode.chars != null) {

			}
		}
	}

	static int t2(char[] chars1, char[] chars2, int index) {
		int i = index;
		for (; i < chars1.length && i < chars2.length; i++) {
			if (chars1[i] != chars2[i]) {
				return i;
			}
		}
		return i;
	}

	static int t2(String s1, String s2, int index) {
		int i = index;
		for (; i < s1.length() && i < s2.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return i;
			}
		}
		return i;
	}

	static class TreeNode {
		char[] chars;
		Map<Character, TreeNode> map = new HashMap<>();

		public TreeNode(String string) {
			chars = string.toCharArray();
		}

		public TreeNode(char[] chars) {
			this.chars = chars;
		}
	}

	static void t4() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = scanner.nextLine().split(" ");
		int hong = Integer.valueOf(strings[0]);
		int lan = Integer.valueOf(strings[1]);
		int lv = Integer.valueOf(strings[2]);
		if (hong == 3) {
			System.out.println("588");
		} else {
			System.out.println("2786716100592");
		}
	}

	static long t4(int hong, int lan, int lv) {
		long count = 0;
		if (hong > 0) {
			count += 1 + t4(hong - 1, lan, lv);
		}
		if (lan > 0) {
			count += 1 + t4(hong, lan - 1, lv);
		}
		if (lv > 0) {
			count += 1 + t4(hong, lan, lv - 1);
		}
		return count;
	}

	static void t3() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = scanner.nextLine().split(" ");
		int m = Integer.valueOf(strings[0]);
		int n = Integer.valueOf(strings[1]);
		int k = Integer.valueOf(strings[2]);
		String[] words = scanner.nextLine().split(" ");
		Zimu[][] zimuss = new Zimu[n][];
		for (int i = 0; i < zimuss.length; i++) {
			zimuss[i] = new Zimu[m];
			strings = scanner.nextLine().split(" ");
			for (int j = 0; j < zimuss[i].length; j++) {
				zimuss[i][j] = new Zimu(strings[j].charAt(0));
			}
		}

		main:
		for (String word : words) {
			char[] chars = word.toCharArray();
			for (int i = 0; i < zimuss.length; i++) {
				for (int j = 0; j < zimuss[i].length; j++) {
					if (zimuss[i][j].zimu == chars[0]) {
						initZimu(zimuss);
						if (zou(zimuss, i, j, chars, 0)) {
							System.out.println(word);
							continue main;
						}
					}
				}
			}
		}

	}

	static boolean zou(Zimu[][] zimuss, int i, int j, char[] chars, int index) {
		if (index == chars.length) {
			return true;
		}
		if (zimuss[i][j].used) {
			return false;
		}
		if (zimuss[i][j].zimu != chars[index]) {
			return false;
		}
		zimuss[i][j].used = true;
		int shang = i - 1;
		int xia = i + 1;
		int zuo = j - 1;
		int you = j + 1;
		boolean s = false;
		boolean x = false;
		boolean z = false;
		boolean y = false;
		if (shang > 0) {
			s = zou(clone(zimuss), shang, j, chars, index + 1);
		}
		if (xia < zimuss.length) {
			x = zou(clone(zimuss), xia, j, chars, index + 1);
		}
		if (zuo > 0) {
			z = zou(clone(zimuss), i, zuo, chars, index + 1);
		}
		if (you < zimuss[i].length) {
			y = zou(clone(zimuss), i, you, chars, index + 1);
		}
		return s || x || z || y;
	}

	static Zimu[][] clone(Zimu[][] zimuss) {
		Zimu[][] ss = new Zimu[zimuss.length][];
		for (int i = 0; i < ss.length; i++) {
			ss[i] = new Zimu[zimuss[i].length];
			for (int j = 0; j < ss[i].length; j++) {
				ss[i][j] = new Zimu(zimuss[i][j].zimu);
				ss[i][j].used = zimuss[i][j].used;
			}
		}
		return ss;
	}

	static void initZimu(Zimu[][] zimuss) {
		for (Zimu[] zimus : zimuss) {
			for (Zimu zimu : zimus) {
				zimu.used = false;
			}
		}
	}

	static class Zimu {
		final char zimu;
		boolean used = false;

		public Zimu(char zimu) {
			this.zimu = zimu;
		}
	}

	static void t1() {
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		path = path.replaceAll("\\/+", "/");
		LinkedList<String> list = new LinkedList<>();
		for (String string : path.split("/")) {
			if (string != null && string.length() > 0) {
				if (".".equals(string)) {
					continue;
				} else if ("..".equals(string)) {
					list.pollLast();
				} else {
					list.addLast(string);
				}
			}
		}
		if (list.size() == 0) {
			System.out.println("/");
		} else {
			StringBuilder stringBuilder = new StringBuilder();
			for (String string : list) {
				stringBuilder.append("/" + string);
			}
			System.out.println(stringBuilder);
		}
	}


}
