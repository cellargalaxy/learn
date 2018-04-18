import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-3-27.
 */
public class Wangyi {
	public static void main(String[] args) {
//		t22();
		System.out.println(10%3);
		HashMap hashMap;
	}
	
	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		int fCount = new Integer(scanner.next());
		F[] fs = new F[fCount];
		for (int i = 0; i < fs.length; i++) {
			fs[i] = new F();
		}
		for (F f : fs) {
			f.zxX = new Integer(scanner.next());
		}
		for (F f : fs) {
			f.zxY = new Integer(scanner.next());
		}
		for (F f : fs) {
			f.ysX = new Integer(scanner.next());
		}
		for (F f : fs) {
			f.ysY = new Integer(scanner.next());
		}
		
	}
//	public static F fugai(F f1,F f2){
//
//	}
	
	static class F {
		int zxX;
		int zxY;
		int ysX;
		int ysY;
		int count = 1;
	}
	
	public static void t21() {
		Scanner scanner = new Scanner(System.in);
		int n = new Integer(scanner.next());
		int k = new Integer(scanner.next());
		int count = 0;
		for (int x = 1; x < n + 1; x++) {
			for (int y = 1; y < n + 1; y++) {
				if (x % y >= k) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
	
	public static void t22() {
		Scanner scanner = new Scanner(System.in);
		int n = new Integer(scanner.next());
		int k = new Integer(scanner.next());
		int count = 0;
		for (int i = 1; i * k < n; i++) {
			count += ((n - i * k + 1) * (n - i * k) / 2);
		}
		System.out.println(count);
	}
}
