import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-3-22.
 */
public class Meituan {
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		String string1 = scanner.next();
//		String string2 = scanner.next();
//		count(string1 ,string2);
		
		Scanner scanner = new Scanner(System.in);
		t2("011122233344455666777888999".toCharArray());
	}
	
	private static void t2(char[] chars) {
		int[] counts = new int[10];
		for (char aChar : chars) {
			counts[aChar - '0'] += 1;
		}
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] == 0) {
				System.out.println(i);
				return;
			}
		}
		if (counts[0] == 0) {
			System.out.println("10");
			return;
		}
		int i = 0;
		int min = Integer.MAX_VALUE;
		for (int j = 1; j < counts.length; j++) {
			if (counts[j] < min) {
				i = j;
				min = counts[j];
			}
		}
		if (counts[i] > counts[0]) {
			String string = "" + i;
			for (int j = 0; j < counts[0]; j++) {
				string += "0";
			}
			string += "0";
			while (string.length() < counts[i]) {
				string += i;
			}
			System.out.println(string);
			return;
		}
		String string = "";
		for (int j = 0; j < counts[i]; j++) {
			string += i;
		}
		string += i;
		System.out.println(string);
	}
	
	private static void count(String string1, String string2) {
		char[] chars1 = string1.toCharArray();
		char[] chars2 = string2.toCharArray();
		int count = 0;
		for (int i = 0; i < chars1.length - chars2.length + 1; i++) {
			count += d(chars1, chars2, i);
		}
		System.out.println(count);
	}
	
	private static int d(char[] chars1, char[] chars2, int i) {
		int count = 0;
		for (int j = 0; j < chars2.length; j++) {
//			System.out.println(chars1[i + j] + "  " + chars2[i] + "  " + (chars1[i + j] != chars2[i]));
			if (chars1[i + j] != chars2[j]) {
				count++;
			}
		}
		return count;
	}
}
