package top.cellargalaxy.learn. xiaozhao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-9-6.
 */
public class Huya {
	public static void main(String[] args) {
		t1();
	}

	public static final void t3() {
		Scanner scanner = new Scanner(System.in);
		List<MyObject> list = new ArrayList<>();
		try {
			while (scanner.hasNext()) {
				int id = scanner.nextInt();
				int score = scanner.nextInt();
				list.add(new MyObject(id, score));
			}
		} catch (Exception e) {
		}
		MyObject[] as = new MyObject[list.size()];
		as = list.toArray(as);

		for (int endIndex = as.length - 1; endIndex > 0; endIndex--) {
			for (int i = 0; i < endIndex; i++) {
				if (as[i].score > as[i + 1].score) {
					MyObject myObject = as[i];
					as[i] = as[i + 1];
					as[i + 1] = myObject;
				}
			}
		}
		System.out.println("Result: " + Arrays.toString(as));
	}

	static class MyObject {
		final int id;
		final int score;

		public MyObject(int id, int score) {
			this.id = id;
			this.score = score;
		}

		@Override
		public String toString() {
			return "{" + id + ":" + score + "}";
		}
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		//           0, 1, 2, 3, 4, 5
		int[] map = {0, 5, 3, 1, 2, 4};
		if (scanner.hasNext()) {
			for (String string : scanner.nextLine().split(" ")) {
				System.out.print(map[Integer.valueOf(string)]);
			}
		}
	}

	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = null;
		if (scanner.hasNext()) {
			strings = scanner.nextLine().split(" ");
		}

		StringBuilder stringBuilder = new StringBuilder();

//		t11(stringBuilder, strings, 0);
//		System.out.println(stringBuilder);

		int pIndex = 0;

		while (true) {
			while (add(stringBuilder, get(strings, pIndex))) {
				pIndex = letfIndex(pIndex);
			}

			pIndex = parentIndex(pIndex);

			while (isEmtry(get(strings, rigthIndex(pIndex)))) {
				pIndex = parentIndex(pIndex);
			}

			pIndex = rigthIndex(pIndex);

			if (!isEmtry(get(strings,letfIndex(pIndex)))) {
				continue;
			}

			if (isEmtry(get(strings,rigthIndex(pIndex)))) {

			}

			while (add(stringBuilder, get(strings, pIndex))) {
				pIndex = letfIndex(pIndex);
			}




		}


	}



	private static boolean isEmtry(String string) {
		return string == null || string.equals("#");
	}

	private static boolean add(StringBuilder stringBuilder, String string) {
		if (!isEmtry(string)) {
			stringBuilder.append(string);
			return true;
		}
		return false;
	}

	private static String get(String[] strings, int i) {
		if (i < 0 || i >= strings.length) {
			return null;
		}
		return strings[i];
	}

	private static int parentIndex(int childIndex) {
		if (childIndex % 2 == 1) {
			return (childIndex - 1) / 2;
		} else {
			return (childIndex - 2) / 2;
		}
	}

	private static int letfIndex(int pIndex) {
		return 2 * pIndex + 1;
	}

	private static int rigthIndex(int pIndex) {
		return 2 * pIndex + 2;
	}

	private static void t11(StringBuilder stringBuilder, String[] strings, int pIndex) {
		if (add(stringBuilder, get(strings, pIndex))) {
			t11(stringBuilder, strings, letfIndex(pIndex));
			t11(stringBuilder, strings, rigthIndex(pIndex));
		}
	}
}
