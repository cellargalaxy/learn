package top.cellargalaxy.learn. xiaozhao;

import java.util.Scanner;

/**
 * Created by cellargalaxy on 18-9-7.
 */
public class Ali {
	public static void main(String[] args) {
		t2();
	}

	public static void t2() {
		Scanner scanner = new Scanner(System.in);
		String[] strings = scanner.nextLine().split(",");
		int[] point = new int[2];
		point[0] = Integer.valueOf(strings[0]);
		point[1] = Integer.valueOf(strings[1]);
		strings = scanner.nextLine().split(",");
		int[][] points = new int[strings.length / 2][2];
		for (int i = 0; i < points.length; i++) {
			points[i][0] = Integer.valueOf(strings[i * 2]);
			points[i][1] = Integer.valueOf(strings[i * 2 + 1]);
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i][0] == point[0] && points[i][1] == point[1]) {
				System.out.println("yes,0");
				return;
			}
		}
		double[] jiajiaos = new double[points.length];
		for (int i = 0; i < jiajiaos.length; i++) {
			jiajiaos[i] = Math.atan2(point[1] - points[i][1], point[0] - points[i][0]) * 180 / Math.PI;
		}
//		System.out.println(Arrays.toString(jiajiaos));
		for (int i = 1; i < jiajiaos.length; i++) {
			if (Math.abs(jiajiaos[0] - jiajiaos[i]) >= 180) {
				System.out.println("yes,0");
				return;
			}
		}

		double[] pointJus = new double[points.length];
		for (int i = 0; i < pointJus.length; i++) {
			pointJus[i] = Math.pow(Math.pow(point[0] - points[i][0], 2) + Math.pow(point[1] - points[i][1], 2), 0.5);
		}

//		pointJus = heapSort(pointJus);
		double min1 = Double.MAX_VALUE;
		double min2 = Double.MAX_VALUE;
		int minI1 = -1;
		int minI2 = -1;
		for (int i = 0; i < pointJus.length; i++) {
			if (pointJus[i] < min1) {
				min1 = pointJus[i];
				minI1 = i;
			}
		}
		for (int i = 0; i < pointJus.length; i++) {
			if (pointJus[i] < min2 && min2 >= min1 && minI1 != minI2) {
				min2 = pointJus[i];
				minI2 = i;
			}
		}
//		for (int i = 0; i < points.length; i++) {
//			if (min1 == Math.pow(Math.pow(point[0] - points[i][0], 2) + Math.pow(point[1] - points[i][1], 2), 0.5)) {
//				minI1 = i;
//			}
//			if (min2 == Math.pow(Math.pow(point[0] - points[i][0], 2) + Math.pow(point[1] - points[i][1], 2), 0.5)) {
//				minI2 = i;
//			}
//		}
		int x1 = points[minI1][0];
		int y1 = points[minI1][1];
		int x2 = points[minI2][0];
		int y2 = points[minI2][1];
		double dx = x2 - x1;
		double dy = y2 - y1;

		double d = (dy * point[0] + dx * point[1] - dx * y1 - dy * x1) / (Math.pow(dy * dy + dx * dx, 0.5));
		if (d < pointJus[0]) {
			System.out.println("no," + Math.abs(sswr(d)));
		} else {
			System.out.println("no," + Math.abs(sswr(pointJus[0])));
		}
	}

	private static int sswr(double d) {
		int i = (int) (d * 10);
		int yu = i % 10;
		if (yu < 5) {
			return i / 10;
		} else {
			return (i + 10) / 10;
		}
	}

	/**
	 * 堆排序
	 *
	 * @param ints
	 * @return
	 */
	public static final double[] heapSort(double[] ints) {
		for (int without = 0; without < ints.length - 1; without++) {
			for (int i = (ints.length / 2) - 1; i > -1; i--) {
				heapSort(ints, i, without);
			}
			double tmp = ints[0];
			ints[0] = ints[ints.length - without - 1];
			ints[ints.length - without - 1] = tmp;
		}

		return ints;
	}

	private static final double[] heapSort(double[] ints, int index, int without) {
		if ((index * 2) + 1 < ints.length - without || (index * 2) + 2 < ints.length - without) {
			if ((index * 2) + 1 < ints.length - without && (index * 2) + 2 < ints.length - without && ints[(index * 2) + 1] > ints[index] && ints[(index * 2) + 2] > ints[index]) {
				if (ints[(index * 2) + 1] >= ints[(index * 2) + 2]) {
					double tmp = ints[index];
					ints[index] = ints[(index * 2) + 1];
					ints[(index * 2) + 1] = tmp;
					heapSort(ints, (index * 2) + 1, without);
				} else {
					double tmp = ints[index];
					ints[index] = ints[(index * 2) + 2];
					ints[(index * 2) + 2] = tmp;
					heapSort(ints, (index * 2) + 2, without);
				}
			}
			if ((index * 2) + 1 < ints.length - without && ints[(index * 2) + 1] > ints[index]) {
				double tmp = ints[index];
				ints[index] = ints[(index * 2) + 1];
				ints[(index * 2) + 1] = tmp;
				heapSort(ints, (index * 2) + 1, without);
			}
			if ((index * 2) + 2 < ints.length - without && ints[(index * 2) + 2] > ints[index]) {
				double tmp = ints[index];
				ints[index] = ints[(index * 2) + 2];
				ints[(index * 2) + 2] = tmp;
				heapSort(ints, (index * 2) + 2, without);
			}
		}
		return ints;
	}
}
