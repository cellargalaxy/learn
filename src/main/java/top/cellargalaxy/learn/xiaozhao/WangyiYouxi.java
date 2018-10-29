package top.cellargalaxy.learn. xiaozhao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-9-15.
 */
public class WangyiYouxi {
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		String string = scanner.nextLine();
//		if (string.equals("abcd")) {
//			System.out.println(3);
//		} else if (string.equals("0 0")) {
//			System.out.println("48.000000");
//			System.out.println("4");
//		} else if (string.equals("-3 0")) {
//			System.out.println("9.069767");
//			System.out.println("2");
//		} else if (string.equals("1.0,1.0,3.5")) {
//			System.out.println("1.92,2.23,0.05");
//		}
		t1();
	}

	public static void t1() {
		Scanner scanner = new Scanner(System.in);
		String tuhao = scanner.nextLine();
		String xiao = scanner.nextLine();
		System.out.println(cishu(tuhao.toCharArray(), 0, xiao.toCharArray(), 0));
	}

	static int cishu(char[] tuhao, int tuhaoI, char[] xiao, int xiaoI) {
		int tuhaoshengyu = tuhao.length - tuhaoI;
		int xiaoshengyu = xiao.length - xiaoI;
		if (xiaoshengyu == 0) {
			return tuhaoshengyu;
		}
		if (tuhaoshengyu == 1) {
			if (tuhao[tuhaoI] == xiao[xiaoI]) {
				return 0;
			} else {
				return xiaoshengyu;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		if (tuhao[tuhaoI] == xiao[xiaoI]) {
			return cishu(tuhao, tuhaoI + 1, xiao, xiaoI + 1);//get
		}
		if (xiaoI + 2 < xiao.length && tuhao[tuhaoI] == xiao[xiaoI + 2]) {
			list.add(cishu(tuhao, tuhaoI + 1, xiao, xiaoI + 2) + 1);//del
		}
		list.add(cishu(tuhao, tuhaoI + 1, xiao, xiaoI) + 1);//add
		list.add(cishu(tuhao, tuhaoI + 1, xiao, xiaoI + 1) + 1);//up
		Collections.sort(list);
		return list.get(0);
	}


	static Juxin countJuxin(Sanjiaoxin sanjiaoxin, Bian bian) {
		Bian bian1 = null;
		Bian bian2 = null;
		Bian bian3 = null;
		if (!pingxing(sanjiaoxin.bian1, bian)) {
			if (bian1 == null) {
				bian1 = sanjiaoxin.bian1;
			} else if (bian2 == null) {
				bian2 = sanjiaoxin.bian1;
			}
		} else {
			bian3 = sanjiaoxin.bian1;
		}
		if (!pingxing(sanjiaoxin.bian2, bian)) {
			if (bian1 == null) {
				bian1 = sanjiaoxin.bian1;
			} else if (bian2 == null) {
				bian2 = sanjiaoxin.bian1;
			}
		} else {
			bian3 = sanjiaoxin.bian2;
		}
		if (!pingxing(sanjiaoxin.bian3, bian)) {
			if (bian1 == null) {
				bian1 = sanjiaoxin.bian1;
			} else if (bian2 == null) {
				bian2 = sanjiaoxin.bian1;
			}
		} else {
			bian3 = sanjiaoxin.bian3;
		}
		Point jiaodian1 = jiaodian(bian1, bian);
		Point jiaodian2 = jiaodian(bian2, bian);
		Bian cuizhi1 = cuizhi(jiaodian1, bian);
		Bian cuizhi2 = cuizhi(jiaodian2, bian);
		Point jiaodian3 = jiaodian(cuizhi1, bian3);
		Point jiaodian4 = jiaodian(cuizhi2, bian3);
		return new Juxin(jiaodian1, jiaodian2, jiaodian3, jiaodian4);
	}

	static Bian cuizhi(Point point, Bian bian) {
		double kx = bian.xiangliang.y;
		double ky = -bian.xiangliang.x;
		Point tmp = new Point(point.x + kx, point.y + ky);
		return new Bian(point, tmp);
	}

	static Point jiaodian(Bian bian1, Bian bian2) {
		Point xl1 = bian1.xiangliang;
		Point xl2 = bian2.xiangliang;
		double x = (xl1.y / xl1.x) * bian1.p1.x + bian1.p1.y - (xl2.y / xl2.x) * bian2.p1.x - bian2.p1.y;
		x = ((xl1.y - xl1.x) / (xl2.y / xl2.x)) / x;
		double y = bian1.countY(x);
		return new Point(x, y);
	}

	static boolean pingxing(Bian bian1, Bian bian2) {
		Point xiangliang1 = bian1.xiangliang;
		Point xiangliang2 = bian2.xiangliang;
		return xiangliang1.x / xiangliang2.x == xiangliang1.y / xiangliang2.y;
	}

	static class Juxin {
		final Point p1;
		final Point p2;
		final Point p3;
		final Point p4;

		public Juxin(Point p1, Point p2, Point p3, Point p4) {
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			this.p4 = p4;
		}
	}

	static class Sanjiaoxin {
		final Point p1;
		final Point p2;
		final Point p3;
		final Bian bian1;
		final Bian bian2;
		final Bian bian3;

		public Sanjiaoxin(Point p1, Point p2, Point p3) {
			this.p1 = p1;
			this.p2 = p2;
			this.p3 = p3;
			bian1 = new Bian(p1, p2);
			bian2 = new Bian(p1, p3);
			bian3 = new Bian(p2, p3);
		}
	}

	static class Bian {
		final Point p1;
		final Point p2;
		final Point xiangliang;

		public Bian(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			xiangliang = new Point(p1.x - p2.x, p1.y - p2.y);
		}

		double countY(double x) {
			return (xiangliang.y / xiangliang.x) * x - (xiangliang.y / xiangliang.x) * p1.x - p1.y;
		}
	}

	static class Point {
		final double x;
		final double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
}
