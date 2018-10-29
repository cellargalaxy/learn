package top.cellargalaxy.learn.cunzhao;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by cellargalaxy on 18-4-9.
2
())(
)))(((
 */
public class Jingdong {
	public static void main(String[] args) {
		t2();
	}
	
	public static final void t3() {
		Scanner in = new Scanner(System.in);
		int t = new Integer(in.next());
		String[] strings = new String[t];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = in.next();
		}
		for (String string : strings) {
			char[] chars = string.toCharArray();
			Stack<Character> head = new Stack<Character>();
			Stack<Character> end = new Stack<Character>();
			int headIndex = 0;
			for (; headIndex < chars.length; headIndex++) {
				boolean b = head(head, chars[headIndex]);
				if (!b) {
					break;
				}
			}
			int endIndex = chars.length - 1;
			for (; endIndex > -1; endIndex--) {
				boolean b = end(end, chars[endIndex]);
				if (!b) {
					break;
				}
			}
			System.out.println(headIndex+" "+endIndex);
			if (headIndex < endIndex) {
				char h=chars[headIndex];
				char e=chars[endIndex];
				chars[headIndex]=e;
				chars[endIndex]=h;
			}
			for (; headIndex < chars.length; headIndex++) {
				boolean b = head(head, chars[headIndex]);
				if (!b) {
					System.out.println("No");
					return;
				}
			}
			for (; endIndex > -1; endIndex--) {
				boolean b = end(end, chars[endIndex]);
				if (!b) {
					break;
				}
			}
			if (head.empty()) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}
	}
	//   ((()))()(()(()))     ((()()()(()(()()
	public static final boolean head(Stack<Character> head, char c) {
		Character f = head.empty() ? null : head.pop();
		if ((f == null && c == '(')) {
			head.add(c);
			return true;
		} else if (f != null && f.equals('(') && c == '(') {
			head.add(f);
			head.add(c);
			return true;
		} else if (f != null && f.equals('(') && c == ')') {
			return true;
		} else {
			return false;
		}
	}
	
	public static final boolean end(Stack<Character> end, char c) {
		Character e = end.empty() ? null : end.pop();
		if (e == null && c == ')') {
			end.add(c);
			return true;
		} else if (e != null && e.equals(')') && c == ')') {
			end.add(e);
			end.add(c);
			return true;
		} else if (e != null && e.equals(')') && c == '(') {
			return true;
		} else {
			return false;
		}
	}
	
	public static final void t2() {
		Scanner in = new Scanner(System.in);
		int t = new Integer(in.next());
		int[] ns = new int[t];
//		for (int i = 0; i < t; i++) {
//			int n=new Integer(in.next());
//			if (n % 2 != 0) {
//				System.out.println("No");
//			} else {
//				System.out.println(n / 2 + " 2");
//			}
//		}
		for (int i = 0; i < ns.length; i++) {
			ns[i] = new Integer(in.next());
		}
		for (int n : ns) {
			if (n % 2 != 0) {
				System.out.println("No");
			} else {
				System.out.println(n / 2 + " 2");
			}
		}
	}
}
