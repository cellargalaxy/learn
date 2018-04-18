import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-4-16.
 * 15
 * 1111
 * 1001
 * 111
 * 101
 * 11
 * 1
 * 0
 * 7
 */
public class Webank {
	public static void main(String[] args) {//15 7
		t3();
	}
	
	public static final void t2() {
		Scanner in = new Scanner(System.in);
		int n = Integer.valueOf(in.next());
		if (n == 1) {
			System.out.println(0);
			return;
		} else if (n == 2) {
			System.out.println(1);
			return;
		} else if (n == 3) {
			System.out.println(2);
			return;
		}
		LinkedList<Integer> shushu = new LinkedList<Integer>();
		shushu.add(2);
		shushu.add(3);
		for (int i = 5; i <= n; i++) {
			if (isShushu(shushu, i)) {
				shushu.add(i);
			}
		}
//		LinkedList<Integer> an = new LinkedList<Integer>();
		int count = 0;
		for (Integer integer : shushu) {
			for (int i = 1; i < Integer.MAX_VALUE; i++) {
				int bei = (int) Math.pow(integer, i);
				if (bei <= n) {
					count++;
//					an.add(bei);
				} else {
					break;
				}
			}
		}
//		an.sort(new Comparator<Integer>() {
//			public int compare(Integer o1, Integer o2) {
//				return o1 - o2;
//			}
//		});
//		System.out.println(an);
//		System.out.println("=------------");
		System.out.println(count);
	}
	
	public static final boolean isShushu(LinkedList<Integer> shushu, int i) {
		for (Integer integer : shushu) {
			if (i % integer == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static final void t3() {
//		Scanner in = new Scanner(System.in);
//		int n = Integer.valueOf(in.next());
//		int count = 0;
//		for (int i = n; i > -1; i--) {
//			if (t3(Integer.toBinaryString(i))) {
//				count++;
//				System.out.println(Integer.toBinaryString(i));
//			}
//		}
//		System.out.println(count);
		
		Scanner in = new Scanner(System.in);
		int n = Integer.valueOf(in.next());
		int count = 0;
		String by = Integer.toBinaryString(n);
		if (by.length() == 1) {
			System.out.println(2);
			return;
		}
		for (int i = 1; i <= by.length() - 1; i++) {
			count += t3(i);
		}
		String minString = "";
		for (int i = 0; i < by.length() - 1; i++) {
			minString += "1";
		}
		int min = Integer.parseInt(minString, 2);
		for (int i = min + 1; i <= n; i++) {
			if (t3(Integer.toBinaryString(i))) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	public static final int t3(int weishu) {
		if (weishu == 1) {
			return 2;
		} else if (weishu == 2) {
			return 1;
		} else if (weishu % 2 == 0) {
			return (int) (Math.pow(2, (weishu / 2) - 1));
		} else {
			return (int) (Math.pow(2, weishu / 2));
		}
	}
	
	public static final boolean t3(String string) {
		int len = string.length() / 2;
		char[] chars = string.toCharArray();
		for (int i = 0; i < len; i++) {
			if (chars[i] != chars[chars.length - i - 1]) {
				return false;
			}
		}
		return true;
	}
	
	public static final void t1() {
		Scanner in = new Scanner(System.in);
		int a = Integer.valueOf(in.next());
		int b = Integer.valueOf(in.next());
		int c = Integer.valueOf(in.next());
		int i = (int) (Math.pow(2, a) + Math.pow(2, b) - Math.pow(2, c));
		String by = Integer.toBinaryString(i);
		int count = 0;
		for (char c1 : by.toCharArray()) {
			if (c1 == '1') {
				count++;
			}
		}
		System.out.println(count);
	}
	
}
