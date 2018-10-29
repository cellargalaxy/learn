package top.cellargalaxy.learn. xiaozhao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-9-18.
 */
public class Weizhong {
	public static void main(String[] args) {
		System.out.println(countHang1(8));
	}

	static void t1() {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine().trim());
		Map<Integer, String> map = new HashMap<>();

	}

	static int countHang1(int hang) {
		return ((hang - 1) / 2) + 2 * (hang / 2) * (hang / 2) + 1;
	}
}
