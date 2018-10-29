package top.cellargalaxy.learn.cunzhao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-5-11.
 */
public class Ali {
	public static void main(String[] args) {
		t1();
	}

	public static final void t1() {
		Scanner scanner = new Scanner(System.in);
		int groupCount = Integer.valueOf(scanner.next());
		int yuanCount = Integer.valueOf(scanner.next());
		int[][] yuans = new int[yuanCount][2];
		for (int i = 0; i < yuans.length; i++) {
			String string = scanner.next();
			String[] strings = string.split(",");
			yuans[i][0] = Integer.valueOf(strings[0]) - 1;
			yuans[i][1] = Integer.valueOf(strings[1]) - 1;
		}
		boolean[] zhiban = new boolean[groupCount * 2];
		for (int i = 0; i < zhiban.length; i++) {
			zhiban[i] = true;
		}

		Ren[] rens = new Ren[groupCount * 2];
		for (int i = 0; i < rens.length; i++) {
			rens[i] = new Ren(i);
		}
		for (int[] yuan : yuans) {
			Ren ren1 = rens[yuan[0]];
			Ren ren2 = rens[yuan[1]];
			ren1.yuanjias.add(ren2);
			ren2.yuanjias.add(ren1);
		}

		while (true) {
			Ren zuiYuanRen = null;
			for (int i = 0; i < rens.length; i++) {
				if (rens[i] != null && renshu(rens, i) == 2) {
					if (zuiYuanRen == null || (rens[i].yuanjias.size() > 0 && zuiYuanRen.yuanjias.size() < rens[i].yuanjias.size())) {
						zuiYuanRen = rens[i];
					}
				}
			}
			if (zuiYuanRen != null) {
				rens[zuiYuanRen.index] = null;
				for (Ren yuanjia : zuiYuanRen.yuanjias) {
					yuanjia.yuanjias.remove(zuiYuanRen);
				}
			}
			if (zuiYuanRen == null) {
				for (Ren ren : rens) {
					if (ren!=null) {
						if (ren.yuanjias.size()>0) {
							System.out.println("no");
							return;
						}
					}
				}
				System.out.println("yes");
				return;
			}
		}


//		for (int[] yuan : yuans) {
//			int renshu1 = renshu(zhiban, yuan[0]);
//			if (renshu1 == 2) {
//				zhiban[yuan[0]] = false;
//				continue;
//			}
//			int renshu2 = renshu(zhiban, yuan[1]);
//			if (renshu2 == 2) {
//				zhiban[yuan[1]] = false;
//				continue;
//			}
//			System.out.println("no");
//			return;
//		}
//		System.out.println("yes");
	}

	public static final int renshu(Ren[] rens, int i) {
		Ren ren1;
		Ren ren2;
		int count = 0;
		if (i % 2 == 0) {
			ren1 = rens[i];
			ren2 = rens[i + 1];
		} else {
			ren1 = rens[i - 1];
			ren2 = rens[i];
		}
		if (ren1 != null) {
			count++;
		}
		if (ren2 != null) {
			count++;
		}
		return count;
	}

	public static final int renshu(boolean[] zhiban, int i) {
		boolean b1;
		boolean b2;
		int count = 0;
		if (i % 2 == 0) {
			b1 = zhiban[i];
			b2 = zhiban[i + 1];
		} else {
			b1 = zhiban[i - 1];
			b2 = zhiban[i];
		}
		if (b1) {
			count++;
		}
		if (b2) {
			count++;
		}
		return count;
	}

	static class Ren {
		public final int index;
		public final LinkedList<Ren> yuanjias;

		public Ren(int index) {
			this.index = index;
			yuanjias = new LinkedList<>();
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Ren ren = (Ren) o;
			return index == ren.index;
		}

		@Override
		public int hashCode() {
			return Objects.hash(index);
		}

		@Override
		public String toString() {
			return "Ren{" +
					"index=" + index +
					", yuanjias=" + yuanjias.size() +
					'}';
		}
	}
}
