package top.cellargalaxy.learn. xiaozhao;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-9-8.
 */
public class Wangyi {
	public static void main(String[] args) {
		t3();
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		int hang = Integer.valueOf(scanner.nextLine());
		for (int h = 0; h < hang; h++) {
			String[] strings = scanner.nextLine().split(" ");
			int houseCount = Integer.valueOf(strings[0]);
			int zhuCount = Integer.valueOf(strings[1]);

			int kejiange = kejiange(houseCount);
			int answer = ok(houseCount, kejiange, zhuCount);
			System.out.println("0 " + (answer < 0 ? 0 : answer));
		}
	}

	private static int ok(int houseCount, int kejiange, int zhuCount) {
		if (houseCount < 3) {
			return 0;
		}
		if (zhuCount <= kejiange) {
			return zhuCount - 1;
		} else {
			if (houseCount % 2 == 0) {
				int d = zhuCount - kejiange - 1;
				return kejiange - d - 1;
			} else {
				int d = zhuCount - kejiange;
				return kejiange - d - 1;
			}
		}
	}

	private static int kejiange(int i) {
		return (i + 1) / 2;
	}

	public static void t3() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = scanner.nextLine().split(" ");
		int toupiaoCout = Integer.valueOf(strings[0]);
		int houxuanCout = Integer.valueOf(strings[1]);
		Toupiao[] toupiaos = new Toupiao[toupiaoCout];
		for (int i = 0; i < toupiaoCout; i++) {
			strings = scanner.nextLine().split(" ");
			Toupiao toupiao = new Toupiao(i, Integer.valueOf(strings[0]) - 1, Integer.valueOf(strings[1]));
			toupiaos[toupiao.ren] = toupiao;
		}

		int chengbenSum = 0;

		HashSet<Toupiao>[] jieguos = new HashSet[houxuanCout];
		for (int i = 0; i < jieguos.length; i++) {
			jieguos[i] = new HashSet<>();
		}
		for (Toupiao toupiao : toupiaos) {
			jieguos[toupiao.piao].add(toupiao);
		}

		while (!yin(jieguos)) {
			chengbenSum += yiciChengben(jieguos);
		}

		System.out.println(chengbenSum);
	}

	private static int yiciChengben(HashSet<Toupiao>[] jieguos) {
		Toupiao pianyiRen = new Toupiao(-1, -1, Integer.MAX_VALUE);
		for (int i = 1; i < jieguos.length; i++) {
			if (jieguos[i].size() >= jieguos[0].size()) {
				for (Toupiao toupiao : jieguos[i]) {
					if (toupiao.chengben < pianyiRen.chengben) {
						pianyiRen = toupiao;
					}
				}
			}
		}
		jieguos[pianyiRen.piao].remove(pianyiRen);
		pianyiRen.piao = 0;
		jieguos[pianyiRen.piao].add(pianyiRen);
		return pianyiRen.chengben;
	}

	private static boolean yin(HashSet<Toupiao>[] jieguos) {
		for (int i = 1; i < jieguos.length; i++) {
			if (jieguos[i].size() >= jieguos[0].size()) {
				return false;
			}
		}
		return true;
	}

	static class Toupiao {
		final int ren;
		int piao;
		final int chengben;

		public Toupiao(int ren, int piao, int chengben) {
			this.ren = ren;
			this.piao = piao;
			this.chengben = chengben;
		}

		@Override
		public String toString() {
			return "Toupiao{" +
					"ren=" + ren +
					", piao=" + piao +
					", chengben=" + chengben +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Toupiao toupiao = (Toupiao) o;
			return ren == toupiao.ren &&
					piao == toupiao.piao &&
					chengben == toupiao.chengben;
		}

		@Override
		public int hashCode() {

			return Objects.hash(ren, piao, chengben);
		}
	}
}
