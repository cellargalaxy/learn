package top.cellargalaxy.learn.dataStructure;

import java.util.Arrays;

/**
 * Created by cellargalaxy on 18-2-10.
 */
public class Sort {
	private static final int arrayLen = 100;
	
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			int[] ints1 = createArray();
			int[] ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = straightInsertionSort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("straightInsertionSort");
			}
			ints1 = createArray();
			ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = shellSort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("shellSort");
			}
			ints1 = createArray();
			ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = bubbleSort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("bubbleSort");
			}
			ints1 = createArray();
			ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = quicksort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("quicksort");
			}
			ints1 = createArray();
			ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = straightSelectSort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("straightSelectSort");
			}
			ints1 = createArray();
			ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = heapSort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("heapSort");
			}
			ints1 = createArray();
			ints2 = ints1.clone();
			Arrays.sort(ints1);
			ints2 = mergeSort(ints2);
			if (!check(ints1, ints2)) {
				System.out.println("mergeSort");
			}
		}
		System.out.println("over");
		
//		int[] ints1 = createArray();
//		int[] ints2 = ints1.clone();
//		String string1 = Arrays.toString(ints1);
//		String string2 = Arrays.toString(ints2);
//		System.out.println(string1);
//		System.out.println(string2);
//		System.out.println();
//		Arrays.sort(ints1);
//		ints2 = bubbleSort(ints2);
//		System.out.println(Arrays.toString(ints1));
//		System.out.println(Arrays.toString(ints2));
//		System.out.println("check: " + check(ints1, ints2));
	}
	
	/**
	 * 直接插入排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] straightInsertionSort(int[] ints) {
		for (int i = 1; i < ints.length; i++) {
			for (int j = i; j > 0; j--) {
				if (ints[j - 1] > ints[j]) {
					int tmp = ints[j - 1];
					ints[j - 1] = ints[j];
					ints[j] = tmp;
				} else {
					break;
				}
			}
		}
		return ints;
	}
	
	/**
	 * 希尔排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] shellSort(int[] ints) {
		for (int dela = ints.length; dela > 0; dela /= 2) {
			for (int k = 0; k < dela; k++) {
				for (int i = k + dela; i < ints.length; i += dela) {
					for (int j = i; j > dela - 1; j -= dela) {
						if (ints[j - dela] > ints[j]) {
							int tmp = ints[j - dela];
							ints[j - dela] = ints[j];
							ints[j] = tmp;
						} else {
							break;
						}
					}
				}
			}
		}
		return ints;
	}
	
	
	/**
	 * 冒泡排序
	 *
	 * @param ints
	 * @return
	 */
	private static final int[] bubbleSort(int[] ints) {
		for (int i = 0; i < ints.length - 1; i++) {
			for (int j = 1; j < ints.length - i; j++) {
				if (ints[j - 1] > ints[j]) {
					int tmp = ints[j - 1];
					ints[j - 1] = ints[j];
					ints[j] = tmp;
				}
			}
		}
		return ints;
	}
	
	
	/**
	 * 快速排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] quicksort(int[] ints) {
		return quicksort(ints, 0, ints.length - 1);
	}
	
	private static final int[] quicksort(int[] ints, int start, int end) {
		if (end - start < 1) {
			return ints;
		}
		int tmp = ints[start];
		int i = start;
		int j = end;
		while (i < j) {
			for (; i < j; j--) {
				if (ints[j] < tmp) {
					ints[i] = ints[j];
					i++;
					break;
				}
			}
			for (; i < j; i++) {
				if (tmp < ints[i]) {
					ints[j] = ints[i];
					j--;
					break;
				}
			}
		}
		ints[i] = tmp;
		quicksort(ints, start, i - 1);
		quicksort(ints, i + 1, end);
		return ints;
	}
	
	/**
	 * 直接选择排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] straightSelectSort(int[] ints) {
		for (int i = 0; i < ints.length; i++) {
			int index = i;
			for (int j = i + 1; j < ints.length; j++) {
				if (ints[index] > ints[j]) {
					index = j;
				}
			}
			int tmp = ints[i];
			ints[i] = ints[index];
			ints[index] = tmp;
		}
		return ints;
	}
	
	/**
	 * 堆排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] heapSort(int[] ints) {
		for (int without = 0; without < ints.length - 1; without++) {
			for (int i = (ints.length / 2) - 1; i > -1; i--) {
				heapSort(ints, i, without);
			}
			int tmp = ints[0];
			ints[0] = ints[ints.length - without - 1];
			ints[ints.length - without - 1] = tmp;
		}
		
		return ints;
	}
	
	private static final int[] heapSort(int[] ints, int index, int without) {
		if ((index * 2) + 1 < ints.length - without || (index * 2) + 2 < ints.length - without) {
			if ((index * 2) + 1 < ints.length - without && (index * 2) + 2 < ints.length - without && ints[(index * 2) + 1] > ints[index] && ints[(index * 2) + 2] > ints[index]) {
				if (ints[(index * 2) + 1] >= ints[(index * 2) + 2]) {
					int tmp = ints[index];
					ints[index] = ints[(index * 2) + 1];
					ints[(index * 2) + 1] = tmp;
					heapSort(ints, (index * 2) + 1, without);
				} else {
					int tmp = ints[index];
					ints[index] = ints[(index * 2) + 2];
					ints[(index * 2) + 2] = tmp;
					heapSort(ints, (index * 2) + 2, without);
				}
			}
			if ((index * 2) + 1 < ints.length - without && ints[(index * 2) + 1] > ints[index]) {
				int tmp = ints[index];
				ints[index] = ints[(index * 2) + 1];
				ints[(index * 2) + 1] = tmp;
				heapSort(ints, (index * 2) + 1, without);
			}
			if ((index * 2) + 2 < ints.length - without && ints[(index * 2) + 2] > ints[index]) {
				int tmp = ints[index];
				ints[index] = ints[(index * 2) + 2];
				ints[(index * 2) + 2] = tmp;
				heapSort(ints, (index * 2) + 2, without);
			}
		}
		return ints;
	}
	
	/**
	 * 归并排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] mergeSort(int[] ints) {
		int[] array = new int[ints.length];
		for (int len = 1; ints.length / len > 0; len *= 2) {
			for (int g = 0; g <= ints.length / len / 2; g++) {
				int start1 = g * len * 2;
				int end1 = start1 + len;
				int start2 = end1;
				int end2 = start2 + len;
				int index1 = start1;
				int index2 = start2;
				int tmp1 = mergeSort(ints, start1, start1, end1);
				int tmp2 = mergeSort(ints, start2, start2, end2);
				for (int i = start1; i < end2 && i < array.length; i++) {
					if (tmp1 <= tmp2) {
						array[i] = tmp1;
						index1++;
						tmp1 = mergeSort(ints, start1, index1, end1);
					} else {
						array[i] = tmp2;
						index2++;
						tmp2 = mergeSort(ints, start2, index2, end2);
					}
				}
			}
			int[] tmpArray = ints;
			ints = array;
			array = tmpArray;
		}
		return ints;
	}
	
	private static final int mergeSort(int[] ints, int start, int index, int end) {
		if (index >= start && index < end && index > -1 && index < ints.length) {
			return ints[index];
		}
		return Integer.MAX_VALUE;
	}
	
	private static final boolean check(int[] ints1, int[] ints2) {
		if (ints1.length != ints2.length) {
			return false;
		}
		for (int i = 0; i < ints1.length; i++) {
			if (ints1[i] != ints2[i]) {
				return false;
			}
		}
		return true;
	}
	
	private static final int[] createArray() {
		int[] ints = new int[arrayLen];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = (int) (Math.random() * 900) + 100;
		}
		return ints;
	}
}
