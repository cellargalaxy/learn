package dataStructure;

import java.util.Arrays;

/**
 * Created by cellargalaxy on 18-2-10.
 */
public class Sort {
	private static final int arrayLen = 10;
	
	public static void main(String[] args) {
		int[] ints1 = createArray();
		int[] ints2 = ints1.clone();
		String string1 = Arrays.toString(ints1);
		String string2 = Arrays.toString(ints2);
		System.out.println(string1);
		System.out.println(string2);
		System.out.println();
		Arrays.sort(ints1);
		straightSelectSort(ints2);
		System.out.println(Arrays.toString(ints1));
		System.out.println(Arrays.toString(ints2));
		System.out.println("check: " + check(ints1, ints2));
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
		return null;
	}
	
	
	/**
	 * 冒泡排序
	 *
	 * @param ints
	 * @return
	 */
	private static final int[] bubbleSort(int[] ints) {
		for (int i = 0; i < ints.length - 2; i++) {
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
	 * @param ints
	 * @return
	 */
	public static final int[] heapSort(int[] ints){
		return null;
	}
	
	/**
	 * 归并排序
	 *
	 * @param ints
	 * @return
	 */
	public static final int[] mergeSort(int[] ints){
		return null;
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
			ints[i] = (int) (Math.random() * 90) + 10;
		}
		return ints;
	}
}
