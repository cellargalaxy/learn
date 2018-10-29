package top.cellargalaxy.learn. xiaozhao;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-8-5.
 */
public class AliTest {
	public static void main(String[] args) {
		t1();
	}

	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		int pCount = Integer.valueOf(scanner.next());
		int[][] ps = new int[pCount][2];
		for (int i = 0; i < ps.length; i++) {
			String[] strings = scanner.next().split(",");
			ps[i][0] = Integer.valueOf(strings[0]);
			ps[i][1] = Integer.valueOf(strings[1]);
		}
		int luxianCount = 1;
		for (int i = 2; i <= pCount; i++) {
			luxianCount *= i;
		}
		int[][] luxians = new int[luxianCount][ps.length];


	}

	public static  void xiayibu(int[] luxians, int[] ps) {
		List<int[]> xiayibus = Arrays.asList(ps);
		int[] julis = yibu(new int[]{0, 0}, xiayibus);
		int i = 0;
		for (int j = 0; j < luxians.length; j++) {
			luxians[j] += julis[i];

			if ((i + 1) % xiayibus.size() == 0) {
				i++;
			}
		}
	}

	public  static int[] yibu(int[] start, List<int[]> ps) {
		int[] julis = new int[ps.size()];
		int i = 0;
		for (int[] p : ps) {
			julis[i] = juli(start, p);
			i++;
		}
		return julis;
	}

	public  static int juli(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}

}
