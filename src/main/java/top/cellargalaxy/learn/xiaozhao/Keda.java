package top.cellargalaxy.learn. xiaozhao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-9-7.
 */
public class Keda {
	public static void main(String[] args) {
		t2();
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		int shashouCount = Integer.valueOf(scanner.nextLine());
		String[] strings = scanner.nextLine().split(" ");
		LinkedList<Integer> list = new LinkedList<>();
		for (String string : strings) {
			list.add(Integer.valueOf(string));
		}
		int ye = 0;
		while (sha(list.iterator())) {
			ye++;
		}
		System.out.println(ye);
	}

	private static boolean sha(Iterator<Integer> iterator) {
		boolean sha = false;
		if (iterator.hasNext()) {
			Integer pre = iterator.next();
			while (iterator.hasNext()) {
				Integer de = iterator.next();
				if (pre > de) {
					sha = true;
					iterator.remove();
				}
				pre = de;
			}
		}
		return sha;
	}

	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		int zu = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < zu; i++) {
			String[] strings = scanner.nextLine().split(" ");
			int studentCount = Integer.valueOf(strings[0]);
			int minChengji = Integer.valueOf(strings[1]);
			strings = scanner.nextLine().split(" ");
			int[] chengjis = new int[strings.length];
			for (int i1 = 0; i1 < strings.length; i1++) {
				chengjis[i1] = Integer.valueOf(strings[i1]);
			}
			System.out.println(t1(studentCount, minChengji, chengjis));
		}
	}

	private static int t1(int studentCount, int minChengji, int[] chengjis) {
		chengjis = heapSort(chengjis);
		int chengjiSum = 0;
		for (int chengji : chengjis) {
			chengjiSum += chengji;
		}
		int quekou = studentCount * minChengji - chengjiSum;
		int count = 0;
		while (quekou > 0) {
			quekou -= 100 - chengjis[count];
			count++;
		}
		return count;
	}

	/**
	 * 堆排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] heapSort(int[] ints) {
		for (int without = 0; without < ints.length - 1; without++) {
			for (int i = (ints.length / 2) - 1; i > -1; i--) {
				heapSort(ints, i, without);
			}
			int tmp = ints[0];
			ints[0] = ints[ints.length - without - 1];
			ints[ints.length - without - 1] = tmp;
		}

		return ints;
	}

	private static final int[] heapSort(int[] ints, int index, int without) {
		if ((index * 2) + 1 < ints.length - without || (index * 2) + 2 < ints.length - without) {
			if ((index * 2) + 1 < ints.length - without && (index * 2) + 2 < ints.length - without && ints[(index * 2) + 1] > ints[index] && ints[(index * 2) + 2] > ints[index]) {
				if (ints[(index * 2) + 1] >= ints[(index * 2) + 2]) {
					int tmp = ints[index];
					ints[index] = ints[(index * 2) + 1];
					ints[(index * 2) + 1] = tmp;
					heapSort(ints, (index * 2) + 1, without);
				} else {
					int tmp = ints[index];
					ints[index] = ints[(index * 2) + 2];
					ints[(index * 2) + 2] = tmp;
					heapSort(ints, (index * 2) + 2, without);
				}
			}
			if ((index * 2) + 1 < ints.length - without && ints[(index * 2) + 1] > ints[index]) {
				int tmp = ints[index];
				ints[index] = ints[(index * 2) + 1];
				ints[(index * 2) + 1] = tmp;
				heapSort(ints, (index * 2) + 1, without);
			}
			if ((index * 2) + 2 < ints.length - without && ints[(index * 2) + 2] > ints[index]) {
				int tmp = ints[index];
				ints[index] = ints[(index * 2) + 2];
				ints[(index * 2) + 2] = tmp;
				heapSort(ints, (index * 2) + 2, without);
			}
		}
		return ints;
	}
}
